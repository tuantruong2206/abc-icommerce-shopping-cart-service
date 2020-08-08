package au.com.nab.icommerce.cart.domain.model;

import au.com.nab.icommerce.cart.dto.BasketDetailDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "basket_detail")
public class BasketDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @Column(nullable = false)
    private Long prodId;

    @ManyToOne
    @NotNull
    private Basket belongToBasket;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @CreationTimestamp
    @Column(name = "updated_at", insertable = false)
    private Instant updatedAt;

    @PrePersist
    public void onPrepPersist() {
        setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(Instant.now());
    }

    public static void updateBasketDetail(BasketDetail basketDetail, BasketDetailDTO basketDetailDTO) {
        if (!Objects.isNull(basketDetailDTO.getQuantity())) {
            basketDetail.setQuantity(basketDetailDTO.getQuantity());
        }
    }
}
