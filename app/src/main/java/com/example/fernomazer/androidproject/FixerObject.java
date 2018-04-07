package com.example.fernomazer.androidproject;
import com.google.gson.JsonObject;
public class FixerObject {
    String base;
    String date;
    JsonObject rates;

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public JsonObject getRates() {
        return rates;
    }

    public double getRate(String code) {
        return rates.has(code) ? rates.get(code).getAsDouble() : 0;
    }
}
