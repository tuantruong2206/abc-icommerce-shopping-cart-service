package au.com.nab.icommerce.cart.domain.repository;

import au.com.nab.icommerce.cart.domain.model.Basket;
import au.com.nab.icommerce.cart.domain.model.BasketDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketDetailRepository extends JpaRepository<BasketDetail, Long> {

    List<BasketDetail> findAllByProdIdAndBelongToBasket(Long id, Basket basket);
}
