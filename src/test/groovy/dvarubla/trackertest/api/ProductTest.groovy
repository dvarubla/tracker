package dvarubla.trackertest.api

import spock.lang.Unroll

import static dvarubla.trackertest.api.APIName.product

class ProductTest extends Spec{
    def "noProductsByDefault"(){
        expect:
            client.get(product, [:]) == []
    }

    @Unroll
    def "createOneProduct #name"(){
        when:
            long id = client.post(product, [name: name])["id"] as long
        then:
            client.get(product, [:]) == [[name: name, id: id]]
        where:
            name << ["Product1", "Product2"]
    }

    def "createMultipleProducts"(){
        when:
        List<Long> ids = names.collect{client.post(product, [name: it])["id"] as long}
        then:
        client.get(product, [:]).sort() == [names, ids].transpose().collect{product, id -> [name: product, id: id]}.sort()
        where:
        names = ["Product1", "Product2", "Product3"]
    }

    @Unroll
    def "deleteProducts [#delIds]"(){
        when:
        def names = ["Product1", "Product2", "Product3", "Product4", "Product5"]
        def ids = names.collect{client.post(product, [name: it])["id"] as long}
        def pairs = [names, ids].transpose().collect{product, id -> [name: product, id: id]}
        delIds.forEach{
            client.delete(product, [id: pairs.get(it).id])
        }
        delIds.sort().reverse().forEach{
            pairs.removeAt(it)
        }
        then:
        client.get(product, [:]).sort() == pairs.sort()
        where:
        delIds << [[1,2,3], [0], [4,2]]
    }
}
