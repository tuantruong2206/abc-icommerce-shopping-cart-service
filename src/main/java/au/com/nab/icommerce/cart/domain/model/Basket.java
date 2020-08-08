package au.com.nab.icommerce.cart.domain.model;

import au.com.nab.icommerce.cart.dto.BasketDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String userid;

    @OneToMany(mappedBy = "belongToBasket", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JsonIgnore
    private Set<BasketDetail> basketDetails = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @CreationTimestamp
    @Column(name = "updated_at", insertable = false)
    private Instant updatedAt;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @PrePersist
    public void onPrepPersist() {
        setCreatedAt(Instant.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(Instant.now());
    }

    public static Basket packingNewBasket(String userid) {
        Basket basket = new Basket();
        basket.setUserid(userid);
        basket.setStatus(true);
        return basket;
    }

    public static void updateBasket(Basket basket, BasketDTO basketDTO) {
        if (!Objects.isNull(basketDTO.getStatus())) {
            basket.setStatus(basketDTO.getStatus());
        }
    }




}

