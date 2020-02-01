package com.JanzEvie.app;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class App
{
    public static void main( String[] args ) {
//        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
//                .header("accept", "application/json")
//                .queryString("apiKey", "123")
//                .field("parameter", "value")
//                .field("foo", "bar")
//                .asJson();

        HttpResponse<JsonNode> response = Unirest.get("https://api.weather.gov/gridpoints/TOP/31,80")
                .asJson();

        JSONObject responsejson = response.getBody().getObject();
        JSONArray results = responsejson.getJSONObject("properties").getJSONObject("temperature").getJSONArray("values");
        System.out.println(results);
    }
}
