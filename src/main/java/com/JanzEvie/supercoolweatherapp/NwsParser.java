package com.JanzEvie.supercoolweatherapp;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;

public class NwsParser {
    public static void main(String[] args) {
        GeoCoords test = new GeoCoords("801+W+White+St,+Marion,+IL");
        HttpResponse<JsonNode> response = Unirest.get("https://api.weather.gov/points/" + test.toString()).asJson();
        if (response.getStatus() != 200) {
            throw new RuntimeException("HTTP Code " + response.getStatus() + ": " + response.getStatusText());
        }
        response = Unirest.get(response.getBody().getObject().getJSONObject("properties").getString("forecast")).asJson();
        JSONObject responseJson = response.getBody().getObject();
        JSONArray forecasts = responseJson.getJSONObject("properties").getJSONArray("periods");
        for (int i = 0; i < forecasts.length(); i++) {
            JSONObject forecast = forecasts.getJSONObject(i);
            System.out.println(forecast.get("name") + ": " + forecast.get("temperature"));
        }
    }
}