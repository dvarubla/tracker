package dvarubla.tracker.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shop")
@NoArgsConstructor
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
    private String name;
}
