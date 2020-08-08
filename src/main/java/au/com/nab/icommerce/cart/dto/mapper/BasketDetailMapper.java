package au.com.nab.icommerce.cart.dto.mapper;

import au.com.nab.icommerce.cart.domain.model.BasketDetail;
import au.com.nab.icommerce.cart.dto.BasketDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Basket Detail and its full DTO
 */
@Mapper(componentModel = "spring", uses ={})
public interface BasketDetailMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "prodId", target = "prodId")
    @Mapping(source = "belongToBasket.id", target = "belongToBasketId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    BasketDetailDTO toDto(BasketDetail basketDetail);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "prodId", target = "prodId")
    @Mapping(source = "belongToBasketId", target = "belongToBasket.id")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    BasketDetail toEntity(BasketDetailDTO basketDetailDTO);
}
