package au.com.nab.icommerce.cart.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotNull(message = "Please enter id")
    private Long id;

    @NotNull
    @Min(value = 0, message = "Min is 0")
    private Integer quantity;
}
