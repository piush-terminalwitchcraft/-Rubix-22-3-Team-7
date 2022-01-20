package com.krishana.androidhackathontemplates;

public class NutritionModel {
    private String Nutrition;
    private  String value;

    public String getNutrition() {
        return Nutrition;
    }
    public String getValue() {
        return value;
    }

    public NutritionModel(String nutrition,  String value) {
        this.Nutrition = nutrition;
        this.value = value;
    }
}
