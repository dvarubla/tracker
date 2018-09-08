package dvarubla.trackertest.api

import groovyx.net.http.RESTClient

import static groovyx.net.http.ContentType.JSON

class APIClient {
    private RESTClient _client

    APIClient(String url){
        _client = new RESTClient(url)
        _client.ignoreSSLIssues()
    }

    private static def convertToSnake(methodName){
        methodName.replaceAll(/\B[A-Z]/) { '-' + it }.toLowerCase()
    }

    private def callMethod(methodName, name, queryData){
        Map params = [path: convertToSnake(name)]
        if(methodName == "post"){
            params += [body: queryData, contentType: JSON]
        } else {
            params += [query: queryData]
        }
        _client."$methodName"(params).data
    }

    def get(List<APIName> names, Map queryData){
        callMethod("get", names.join("/"), queryData)
    }

    def get(APIName name, Map queryData){
        callMethod("get", name.name(), queryData)
    }

    def post(APIName name, Map queryData){
        callMethod("post", name.name(), queryData)
    }

    def delete(APIName name, Map queryData){
        callMethod("delete", name.name(), queryData)
    }
}
