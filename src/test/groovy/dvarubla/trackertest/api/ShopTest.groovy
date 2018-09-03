package dvarubla.trackertest.api

import spock.lang.Unroll

import static dvarubla.trackertest.api.APIName.shop

class ShopTest extends Spec{
    def "noShopsByDefault"(){
        expect:
            client.get(shop, [:]) == []
    }

    @Unroll
    def "createOneShop #name"(){
        when:
            long id = client.post(shop, [name: name])["id"] as long
        then:
            client.get(shop, [:]) == [[name: name, id: id]]
        where:
            name << ["Shop1", "Shop2"]
    }

    def "createMultipleShops"(){
        when:
        List<Long> ids = names.collect{client.post(shop, [name: it])["id"] as long}
        then:
        client.get(shop, [:]).sort() == [names, ids].transpose().collect{shop, id -> [name: shop, id: id]}.sort()
        where:
        names = ["Shop1", "Shop2", "Shop3"]
    }

    @Unroll
    def "deleteShops [#delIds]"(){
        when:
        def names = ["Shop1", "Shop2", "Shop3", "Shop4", "Shop5"]
        def ids = names.collect{client.post(shop, [name: it])["id"] as long}
        def pairs = [names, ids].transpose().collect{shop, id -> [name: shop, id: id]}
        delIds.forEach{
            client.delete(shop, [id: pairs.get(it).id])
        }
        delIds.sort().reverse().forEach{
            pairs.removeAt(it)
        }
        then:
        client.get(shop, [:]).sort() == pairs.sort()
        where:
        delIds << [[1,2,3], [0], [4,2]]
    }
}
