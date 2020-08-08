package au.com.nab.icommerce.cart.proxy.rest.client;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.cart.dto.ProductDTO;
import au.com.nab.icommerce.cart.proxy.rest.fallback.InventoryProductFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * this client helps to call rest to inventory service to gather product data
 */
@FeignClient(value = "nab-icommerce-inventory-service", fallback = InventoryProductFallback.class)
public interface InventoryProductClient {

    @RequestMapping(method = RequestMethod.GET, value = "/inventory/product/{id}", consumes = "application/json")
    JsonResponse<ProductDTO> getProduct(@PathVariable (value="id") Long id);

}
