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
        Forecast[] test = getSevenDayForecast(args[0]);
        System.out.println(test[0]);
    }
    public static Forecast[] getSevenDayForecast(String arg) {
        GeoCoords test = new GeoCoords(arg);
        HttpResponse<JsonNode> response = Unirest.get("https://api.weather.gov/points/" + test.toString()).asJson();
        if (response.getStatus() != 200) {
            throw new RuntimeException("HTTP Code " + response.getStatus() + ": " + response.getStatusText());
        }
        response = Unirest.get(response.getBody().getObject().getJSONObject("properties").getString("forecast")).asJson();
        JSONObject responseJson = response.getBody().getObject();
        JSONArray forecastsJson = responseJson.getJSONObject("properties").getJSONArray("periods");
        Forecast[] forecasts = new Forecast[forecastsJson.length()];
        for (int i = 0; i < forecastsJson.length(); i++) {
            Forecast forecast = new Forecast(forecastsJson.getJSONObject(i));
            forecasts[i] = forecast;
        }
        return forecasts;
    }
}