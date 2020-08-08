package au.com.nab.icommerce.cart.proxy.rest.proxy;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.cart.dto.ProductDTO;
import au.com.nab.icommerce.cart.proxy.rest.client.InventoryProductClient;
import org.springframework.stereotype.Component;

@Component
public class InventoryProductServiceProxy {


    private final InventoryProductClient inventoryProductClient;

    public InventoryProductServiceProxy(InventoryProductClient inventoryProductClient) {
        this.inventoryProductClient = inventoryProductClient;
    }

    public JsonResponse<ProductDTO> getProductById(Long id) {
        return inventoryProductClient.getProduct(id);
    }
}
