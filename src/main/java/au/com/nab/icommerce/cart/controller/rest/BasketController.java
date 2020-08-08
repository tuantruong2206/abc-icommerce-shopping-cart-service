package au.com.nab.icommerce.cart.controller.rest;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.base.util.HeaderUtil;
import au.com.nab.icommerce.cart.dto.BasketDTO;
import au.com.nab.icommerce.cart.dto.BasketDetailDTO;
import au.com.nab.icommerce.cart.service.BasketDetailService;
import au.com.nab.icommerce.cart.service.BasketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * All REST APIs of Basket.
 */
@RestController
@RequestMapping(path = "cart", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasketController {
    private final Logger log = LoggerFactory.getLogger(BasketController.class);

    private final BasketService basketService;

    private final BasketDetailService basketDetailService;

    private final String ENTITY_NAME = "basket";

    private final String ENTITY_DETAIL_NAME = "basket_detail";

    public BasketController(BasketService basketService, BasketDetailService basketDetailService) {
        this.basketService = basketService;
        this.basketDetailService = basketDetailService;
    }

    /**
     * Get basket item by Id
     * @param id
     * @return
     */
    @GetMapping("/basket/{id}")
    public ResponseEntity<JsonResponse<BasketDTO>> getBasketById(@PathVariable Long id) throws Exception {
        log.info("REST request to get Basket: {}", id);
        return new ResponseEntity<>(new JsonResponse<>(this.basketService.getBasketById(id)), HttpStatus.OK);
    }

    /**
     * Get basket detail item by Id
     * @param id
     * @return
     */
    @GetMapping("/basket-detail/{id}")
    public ResponseEntity<JsonResponse<BasketDetailDTO>> getBasketDetailById(@PathVariable Long id) throws Exception {
        log.info("REST request to get Basket detail: {}", id);
        return new ResponseEntity<>(new JsonResponse<>(this.basketDetailService.getBasketDetailById(id)), HttpStatus.OK);
    }

    /**
     * create Basket Item
     * @param basketDTO
     * @param bindingResult
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/basket")
    public ResponseEntity<JsonResponse<BasketDTO>> createBasketItem(@Valid @RequestBody BasketDTO basketDTO, BindingResult bindingResult) throws URISyntaxException {
        //TODO need Basket Validator here
        log.info("REST request to update basket: {}", basketDTO);
        basketDTO = this.basketService.createBasket(basketDTO.getUserid());
        return ResponseEntity.created(new URI("/inventory/product/" + basketDTO.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, basketDTO.getId().toString()))
                .body(new JsonResponse<>(basketDTO));
    }

    /**
     * create BasketDetail Item
     * @param basketDetailDTO
     * @param bindingResult
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/basket-detail")
    public ResponseEntity<JsonResponse<BasketDetailDTO>> createBasketDetailItem(@Valid @RequestBody BasketDetailDTO basketDetailDTO, BindingResult bindingResult) throws URISyntaxException {
        //TODO need Basket Validator here
        log.info("REST request to add basket Detail: {}", basketDetailDTO);
        BasketDetailDTO result = this.basketDetailService.addProductCart(basketDetailDTO);
        return ResponseEntity.created(new URI("/cart/basket-detail/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_DETAIL_NAME, result.getId().toString()))
                .body(new JsonResponse<>(result));
    }

    /**
     * update Basket Item
     * @param basketDTO
     * @param bindingResult
     * @return
     * @throws URISyntaxException
     */
    @PutMapping("/basket")
    public ResponseEntity<JsonResponse<BasketDTO>> updateBasketItem(@Valid @RequestBody BasketDTO basketDTO, BindingResult bindingResult) throws URISyntaxException {
        //TODO need Basket Validator here
        log.info("REST request to update basket Detail: {}", basketDTO);
        BasketDTO result = this.basketService.updateBasketStatus(basketDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_DETAIL_NAME, result.getId().toString()))
                .body(new JsonResponse<>(result));
    }

    /**
     * update BasketDetail Item
     * @param basketDetailDTO
     * @param bindingResult
     * @return
     * @throws URISyntaxException
     */
    @PutMapping("/basket-detail")
    public ResponseEntity<JsonResponse<BasketDetailDTO>> updateBasketDetailItem(@Valid @RequestBody BasketDetailDTO basketDetailDTO, BindingResult bindingResult) throws URISyntaxException {
        //TODO need Basket Validator here
        log.info("REST request to update basket Detail: {}", basketDetailDTO);
        BasketDetailDTO result = this.basketDetailService.updateProductCart(basketDetailDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_DETAIL_NAME, result.getId().toString()))
                .body(new JsonResponse<>(result));
    }

    /**
     * delete basket Item by id
     * @param id
     * @return
     */
    @DeleteMapping("/basket/{id}")
    public ResponseEntity<JsonResponse<Object>> deleteBasketById(@PathVariable Long id) {
        log.info("REST request to update Product: {}", id);
        this.basketService.deleteBasket(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
                .body(new JsonResponse<>());
    }

    /**
     * delete basket detail Item by id
     * @param id
     * @return
     */
    @DeleteMapping("/basket-detail/{id}")
    public ResponseEntity<JsonResponse<Object>> deleteBasketDetailById(@PathVariable Long id) {
        log.info("REST request to update Product: {}", id);
        this.basketDetailService.deleteBasketDetail(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_DETAIL_NAME, id.toString()))
                .body(new JsonResponse<>());
    }
}
