package au.com.nab.icommerce.cart.service.impl;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.base.enumeration.MessageCodeEnum;
import au.com.nab.icommerce.base.exception.NABException;
import au.com.nab.icommerce.cart.domain.model.Basket;
import au.com.nab.icommerce.cart.domain.model.BasketDetail;
import au.com.nab.icommerce.cart.domain.repository.BasketDetailRepository;
import au.com.nab.icommerce.cart.domain.repository.BasketRepository;
import au.com.nab.icommerce.cart.dto.BasketDTO;
import au.com.nab.icommerce.cart.dto.BasketDetailDTO;
import au.com.nab.icommerce.cart.dto.ProductDTO;
import au.com.nab.icommerce.cart.dto.mapper.BasketDetailMapper;
import au.com.nab.icommerce.cart.dto.mapper.BasketMapper;
import au.com.nab.icommerce.cart.proxy.rest.proxy.InventoryProductServiceProxy;
import au.com.nab.icommerce.cart.service.BasketDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BasketDetailServiceImpl implements BasketDetailService {

    private final BasketDetailRepository basketDetailRepository;

    private final BasketRepository basketRepository;

    private final BasketDetailMapper basketDetailMapper;

    private final BasketMapper basketMapper;

    private final InventoryProductServiceProxy inventoryProductServiceProxy;

    private final Logger log = LoggerFactory.getLogger(BasketDetailServiceImpl.class);

    public BasketDetailServiceImpl(InventoryProductServiceProxy inventoryProductServiceProxy, BasketDetailRepository basketDetailRepository,
                                   BasketDetailMapper basketDetailMapper, BasketRepository basketRepository,
                                   BasketMapper basketMapper) {
        this.basketDetailRepository = basketDetailRepository;
        this.basketDetailMapper = basketDetailMapper;
        this.inventoryProductServiceProxy = inventoryProductServiceProxy;
        this.basketRepository = basketRepository;
        this.basketMapper = basketMapper;
    }

    @Override
    public BasketDetailDTO addProductCart(BasketDetailDTO basketDetailDTO) {
        log.info("add Product to cart");
        JsonResponse<ProductDTO> response = this.inventoryProductServiceProxy.getProductById(basketDetailDTO.getProdId());
        if (!response.getSuccess()) {
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }
        ProductDTO productDTO = response.getData();
        if (productDTO.getQuantity() < basketDetailDTO.getQuantity()) {
            throw new NABException(
                    MessageCodeEnum.COMMON_ERROR_001,
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Basket> optBasket = this.basketRepository.findById(basketDetailDTO.getBelongToBasketId());
        if (!optBasket.isPresent()) {
            //TODO need to define detail error code for enhancement
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }

        if (!optBasket.get().getStatus()) {
            //TODO need to define detail error code for enhancement
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }
        List<BasketDetail> basketDetails = this.basketDetailRepository.findAllByProdIdAndBelongToBasket(basketDetailDTO.getProdId(), optBasket.get());

        if (basketDetails.size()>= 1) {
            //TODO need to define detail error code for enhancement
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }
        BasketDetail basketDetail = this.basketDetailRepository.save(this.basketDetailMapper.toEntity(basketDetailDTO));
        return this.basketDetailMapper.toDto(basketDetail);
    }

    @Override
    public BasketDetailDTO updateProductCart(BasketDetailDTO basketDetailDTO) {
        log.info("update basket detail");
        Optional<BasketDetail> optBasketDetail = this.basketDetailRepository.findById(basketDetailDTO.getId());
        if (optBasketDetail.isPresent()) {
            BasketDetail basketDetail = optBasketDetail.get();
            BasketDetail.updateBasketDetail(basketDetail, basketDetailDTO);
            BasketDetail result = this.basketDetailRepository.save(basketDetail);
            return this.basketDetailMapper.toDto(result);
        }
        throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
    }

    /*@Override
    public BasketDTO updateBasket(BasketDTO basketDTO) {
        //skip validate basket ID
        Basket basket = this.basketRepository.getOne(basketDTO.getId());
        if (Objects.isNull(basket)) {
            //TODO need to update detail error code
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }
        Basket.updateBasket(basket, basketDTO);
        Basket result = this.basketRepository.save(this.basketMapper.toEntity(basketDTO));
        return this.basketMapper.toDto(result);
    }*/

    @Override
    public void deleteBasketDetail(Long id) {
        this.basketDetailRepository.deleteById(id);
    }

    @Override
    public List<BasketDetailDTO> getBasketDetails(BasketDetailDTO basketDetailDTO) {
        //TODO will implement and enhance later
        return null;
    }

    @Override
    public BasketDetailDTO getBasketDetailById(Long id) {
        Optional<BasketDetail> optBasketDetail = this.basketDetailRepository.findById(id);
        if (optBasketDetail.isPresent()) {
            BasketDetail basketDetail = optBasketDetail.get();
            return this.basketDetailMapper.toDto(basketDetail);
        }
        return null;
    }


}
