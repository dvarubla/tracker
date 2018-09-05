package dvarubla.tracker.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase")
@NoArgsConstructor
@EqualsAndHashCode
class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchasePk")
    @SequenceGenerator(
            name = "purchasePk", sequenceName = "purchasePk",
            allocationSize = 1
    )
    @Getter
    @Setter
    @EqualsAndHashCode.Include
    private long id;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    @JsonSerialize(using = DateSerializer.class)
    private LocalDateTime purchaseDate;

    @Getter
    @Setter
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId")
    @Getter
    @Setter
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    @Getter
    @Setter
    private Product product;
}
