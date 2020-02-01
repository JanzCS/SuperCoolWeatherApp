package com.JanzEvie.app;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class App
{
    public static void main( String[] args ) {
//        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
//                .header("accept", "application/json")
//                .queryString("apiKey", "123")
//                .field("parameter", "value")
//                .field("foo", "bar")
//                .asJson();

        String body = Unirest.get("https://api.weather.gov/gridpoints/TOP/31,80")
                .asString()
                .getBody();

        System.out.println(body);
    }
}
