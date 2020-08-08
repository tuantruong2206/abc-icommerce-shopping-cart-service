package au.com.nab.icommerce.cart.service.impl;

import au.com.nab.icommerce.base.enumeration.MessageCodeEnum;
import au.com.nab.icommerce.base.exception.NABException;
import au.com.nab.icommerce.cart.domain.model.Basket;
import au.com.nab.icommerce.cart.domain.repository.BasketRepository;
import au.com.nab.icommerce.cart.dto.BasketDTO;
import au.com.nab.icommerce.cart.dto.mapper.BasketMapper;
import au.com.nab.icommerce.cart.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    private final BasketMapper basketMapper;

    public BasketServiceImpl(BasketRepository basketRepository, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.basketMapper = basketMapper;
    }

    @Override
    public BasketDTO createBasket(String userid) {
        List<Basket> lst = this.basketRepository.findAllByUseridAndStatus(userid, true);
        if (lst.size() != 0) {
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        }
        Basket basket = this.basketRepository.save(Basket.packingNewBasket(userid));
        return this.basketMapper.toDto(basket);
    }

    @Override
    public BasketDTO getBasketById(Long id) {
        Optional<Basket> optBasket = this.basketRepository.findById(id);
        if (optBasket.isPresent())
            return this.basketMapper.toDto(optBasket.get());
        else
            return null;
    }

    @Override
    public BasketDTO updateBasketStatus(BasketDTO basketDTO) {
        Optional<Basket> optBasket = this.basketRepository.findById(basketDTO.getId());
        if (!optBasket.isPresent())
            //TODO need to enhance for detail error code
            throw new NABException(MessageCodeEnum.COMMON_ERROR_001, HttpStatus.BAD_REQUEST);
        Basket basket;
        basket = optBasket.get();
        Basket.updateBasket(basket, basketDTO);
        Basket result = this.basketRepository.save(basket);
        return this.basketMapper.toDto(result);
    }

    @Override
    public void deleteBasket(Long id) {
        this.basketRepository.deleteById(id);
    }
}
