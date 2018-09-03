package dvarubla.tracker.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productPk")
    @SequenceGenerator(
            name = "productPk", sequenceName = "productPk",
            allocationSize = 1
    )
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;
}
