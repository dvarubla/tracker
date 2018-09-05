package dvarubla.trackertest.api

import static dvarubla.trackertest.api.APIName.*

class PurchaseTest extends Spec{
    def "noPurchasesByDefault"(){
        expect:
        client.get(purchase, [:]) == []
    }

    def "addShopProductPurchase"(){
        given:
        client.post(shop, [name: "Shop1"])
        client.post(product, [name: "Product1"])
        client.post(product, [name: "Product2"])
        def shop = "Shop1"
        def purchases = [
                [product: "Product1", price: 100.7, count: 10, purchaseDate: "11.09.2007 16:00"],
                [product: "Product2", price: 100.3, count: 5,  purchaseDate: "21.09.2018 11:00"]
        ]
        def ids = client.post(purchase, [shop: shop, purchases: purchases])
        expect:
        ids.size == 2
        parsePurchaseResp(client.get(purchase, [:])) == parsePurchaseQuery(shop, purchases)
    }

    def parsePurchaseQuery(shop, purchases){
        purchases.collect{it + [shop: shop]}
    }

    def parsePurchaseResp(arr){
        arr.collect{[
                shop: it.shop.name,
                product: it.product.name,
                price: it.price,
                count: it.count,
                purchaseDate: it.purchaseDate]}
    }
}
