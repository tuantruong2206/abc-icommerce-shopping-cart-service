package au.com.nab.icommerce.cart.dto.mapper;

import au.com.nab.icommerce.cart.domain.model.Basket;
import au.com.nab.icommerce.cart.dto.BasketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Basket and its full DTO
 */
@Mapper(componentModel = "spring", uses ={})
public interface BasketMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userid", target = "userid")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "basketDetails", target = "basketDetails")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    BasketDTO toDto(Basket product);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userid", target = "userid")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    Basket toEntity(BasketDTO productDTO);
}
