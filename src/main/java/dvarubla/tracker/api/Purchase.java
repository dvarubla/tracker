package dvarubla.tracker.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

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
    private LocalDateTime purchaseDate;

    @Getter
    @Setter
    private BigDecimal count;

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

    @Formula("count * price")
    @Getter
    private BigDecimal totalCost;
}
