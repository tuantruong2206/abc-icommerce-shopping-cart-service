package au.com.nab.icommerce.cart.service;


import au.com.nab.icommerce.cart.dto.BasketDTO;
import au.com.nab.icommerce.cart.dto.BasketDetailDTO;

import java.util.List;

public interface BasketDetailService {

    public BasketDetailDTO addProductCart(BasketDetailDTO basketDetailDTO);
    public BasketDetailDTO updateProductCart(BasketDetailDTO basketDetailDTO);
    public void deleteBasketDetail(Long id);
    public List<BasketDetailDTO> getBasketDetails(BasketDetailDTO basketDetailDTO);
    public BasketDetailDTO getBasketDetailById(Long id);

}
