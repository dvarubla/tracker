package dvarubla.tracker.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@EqualsAndHashCode
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
    @NaturalId
    @EqualsAndHashCode.Include
    private String name;
}
