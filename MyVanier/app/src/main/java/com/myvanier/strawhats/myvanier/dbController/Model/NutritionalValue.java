package com.myvanier.strawhats.myvanier.dbController.Model;

public class NutritionalValue {
    int calories;
    int totalfat;
    int cholesterol;
    int sodium;
    int carbs;
    int protein;
    int vitaminA;
    int vitaminC;

    public NutritionalValue() {

    }

    public NutritionalValue(int cal, int fat, int chol, int sod, int carb, int prot, int vitA, int vitC) {
        calories = cal;
        totalfat = fat;
        cholesterol = chol;
        sodium = sod;
        carbs = carb;
        protein = prot;
        vitaminA = vitA;
        vitaminC = vitC;
    }
}
