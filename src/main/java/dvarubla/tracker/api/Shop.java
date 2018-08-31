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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_sequence")
    @SequenceGenerator(
            name = "shop_sequence", sequenceName = "shop_pk_seq",
            allocationSize = 1
    )
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;
}
