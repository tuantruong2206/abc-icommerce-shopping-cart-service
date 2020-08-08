package au.com.nab.icommerce.cart.domain.repository;

import au.com.nab.icommerce.cart.domain.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    public List<Basket> findAllByUseridAndStatus(String userid, Boolean status);
}
