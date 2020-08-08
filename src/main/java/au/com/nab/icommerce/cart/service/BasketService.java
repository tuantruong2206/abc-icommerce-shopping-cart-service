package au.com.nab.icommerce.cart.service;

import au.com.nab.icommerce.cart.dto.BasketDTO;

public interface BasketService {

    public BasketDTO createBasket(String userid);
    public BasketDTO getBasketById(Long id);
    public BasketDTO updateBasketStatus(BasketDTO basketDTO);
    public void deleteBasket(Long id);
}
