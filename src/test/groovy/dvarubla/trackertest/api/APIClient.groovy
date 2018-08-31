package dvarubla.trackertest.api

import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.JSON

class APIClient {
    private RESTClient _client

    APIClient(String url){
        _client = new RESTClient(url)
        _client.ignoreSSLIssues()
    }

    private def callMethod(methodName, name, queryData){
        Map params = [path: name]
        if(methodName == "post"){
            params += [body: queryData, contentType: JSON]
        } else {
            params += [query: queryData]
        }
        _client."$methodName"(params).data
    }

    def get(APIName name, Map queryData){
        callMethod("get", name, queryData)
    }

    def post(APIName name, Map queryData){
        callMethod("post", name, queryData)
    }

    def delete(APIName name, Map queryData){
        callMethod("delete", name, queryData)
    }
}
