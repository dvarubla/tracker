package dvarubla.tracker.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
class AddPurchasesRequest {
    @SuppressWarnings("WeakerAccess")
    @NoArgsConstructor
    static class Purchase{
        @Getter
        @Setter
        private String purchaseDate;
        @Getter
        @Setter
        private BigDecimal price;
        @Getter
        @Setter
        private String product;
        @Getter
        @Setter
        private int count;
    }

    @Getter
    @Setter
    private String shop;

    @Getter
    @Setter
    private List<Purchase> purchases;
}
