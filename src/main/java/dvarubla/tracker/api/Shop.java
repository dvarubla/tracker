package dvarubla.tracker.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "shop")
@NoArgsConstructor
@EqualsAndHashCode
class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopPk")
    @SequenceGenerator(
            name = "shopPk", sequenceName = "shopPk",
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
