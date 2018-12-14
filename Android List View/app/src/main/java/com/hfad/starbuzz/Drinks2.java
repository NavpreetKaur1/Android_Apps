package com.hfad.starbuzz;

/**
 * Created by xcode on 2018-04-09.
 */

public class Drinks2 {

    private String name;
    private String description;
    private int imageResourceId;
    //drinks is an array of Drinks
    public static final Drinks2[] drinks = {
            new Drinks2("Pepsi", "Black water with lots of sugar", R.drawable.pepsi),
            new Drinks2("Coke", "More black water with lots of sugar", R.drawable.coke),
            new Drinks2("Fanta", "Orange Water with lots of sugar", R.drawable.fanta)

    };

    //Each Drink has a name, description, and an image resource
    private Drinks2(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }
}
