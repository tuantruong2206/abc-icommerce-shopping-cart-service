package au.com.nab.icommerce.cart.proxy.rest.fallback;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.cart.dto.ProductDTO;
import au.com.nab.icommerce.cart.proxy.rest.client.InventoryProductClient;
import org.springframework.stereotype.Component;

@Component
public class InventoryProductFallback implements InventoryProductClient {

    @Override
    public JsonResponse<ProductDTO> getProduct(Long id) {
        JsonResponse<ProductDTO> response = new JsonResponse<>(new ProductDTO(-1L, -1));
        response.setSuccess(false);
        return response;
    }
}
