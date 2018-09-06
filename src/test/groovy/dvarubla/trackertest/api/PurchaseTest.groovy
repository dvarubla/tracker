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
        def date = "11.09.2007 16:00"
        def purchases = [
                [product: "Product1", price: 100.7, count: 10],
                [product: "Product2", price: 100.3, count: 5]
        ]
        def ids = client.post(purchase, [shop: shop, purchaseDate: date, purchases: purchases])
        expect:
        ids.size == 2
        parsePurchaseResp(client.get(purchase, [:])) == parsePurchaseQuery(shop, date, purchases)
    }

    def "addOnlyPurchase"(){
        given:
        def shopName = "Shop1"
        def date = "11.09.2007 16:00"
        def purchases = [
                [product: "Product1", price: 100.7, count: 10.5],
                [product: "Product2", price: 100.3, count: 5]
        ]
        def ids = client.post(purchase, [shop: shopName, purchaseDate: date, purchases: purchases])
        expect:
        ids.size == 2
        client.get(shop, [:])*.name == ["Shop1"]
        client.get(product, [:])*.name == ["Product1", "Product2"]
        parsePurchaseResp(client.get(purchase, [:])) == parsePurchaseQuery(shopName, date, purchases)
    }

    def parsePurchaseQuery(shop, date, purchases){
        purchases.collect{it + [shop: shop, purchaseDate: date]}
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
