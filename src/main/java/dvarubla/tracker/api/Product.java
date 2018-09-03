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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(
            name = "product_sequence", sequenceName = "product_pk_seq",
            allocationSize = 1
    )
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String name;
}
