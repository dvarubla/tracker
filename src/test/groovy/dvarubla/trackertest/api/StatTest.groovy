package dvarubla.trackertest.api

import static dvarubla.trackertest.api.APIName.*

class StatTest extends Spec {
    def "zeroTotalMoneyByDefault"(){
        expect:
        client.get([stat, totalMoney], [:]) == [totalMoney: 0]
    }

    def "totalMoney"(){
        when:
        def purchases = [
                [product: "Product1", price: 0.5, count: 6],
                [product: "Product2", price: 1.2, count: 4]
        ]
        client.post(purchase, [shop: "Shop1", purchaseDate: "21.03.2007 13:50", purchases: purchases])
        then:
        client.get([stat, totalMoney], [:]) == [totalMoney: 7.8]
    }
}
