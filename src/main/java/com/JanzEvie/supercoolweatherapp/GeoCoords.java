package com.JanzEvie.supercoolweatherapp;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class GeoCoords {
    public Float lat;
    public Float lng;

    public GeoCoords(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }
    // Takes in an address in correct Google api format e.g.
    // 1600+Amphitheatre+Parkway,+Mountain+View,+CA
    // and uses Google's Geocoding API to turn it into a lat/long
    public GeoCoords(String address) {
        HttpResponse<JsonNode> response = Unirest.get("https://maps.googleapis.com/maps/api/geocode/json").
                queryString("key", "AIzaSyBQSIK6kkO9WVcOc9EhH7HcWAsbuqp2Zto").
                queryString("address", address).asJson();
        JSONObject responseJson = response.getBody().getObject();
        if (responseJson.getJSONArray("results").length() == 0) {
            throw new RuntimeException("Address not found or invalid.");
        }
        JSONObject coordsJson = responseJson.getJSONArray("results").getJSONObject(0).
                getJSONObject("geometry").getJSONObject("location");
        lat = coordsJson.getFloat("lat");
        lng = coordsJson.getFloat("lng");
    }

    @Override
    public String toString() {
        return lat.toString() + "," + lng.toString();
    }
}
