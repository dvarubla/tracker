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
}
