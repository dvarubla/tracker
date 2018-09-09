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

    def "unitPlot"(){
        when:
        def purchases1 = [
                [product: "Product1", price: 0.5, count: 6],
                [product: "Product2", price: 1.2, count: 4]
        ]
        def purchases2 = [
                [product: "Product4", price: 0.5, count: 1],
                [product: "Product5", price: 1.2, count: 5]
        ]
        def purchasesUnlisted = [
                [product: "Product4", price: 0.5, count: 3],
                [product: "Product5", price: 1.8, count: 5]
        ]
        client.post(purchase, [shop: "Shop1", purchaseDate: "21.03.2007 13:50", purchases: purchases1])
        client.post(purchase, [shop: "Shop2", purchaseDate: "22.03.2007 13:50", purchases: purchases2])
        client.post(purchase, [shop: "Shop3", purchaseDate: "23.03.2007 13:50", purchases: purchasesUnlisted])
        then:
        client.get([stat, unitPlot], [startDate:"21.03.2007", endDate: "22.03.2007"]) == [
                plot: [money:[7.8, 6.5], labels:["21.03.2007", "22.03.2007"]]
        ]
    }
}
