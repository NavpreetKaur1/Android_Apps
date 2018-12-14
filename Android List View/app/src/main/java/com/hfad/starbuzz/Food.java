package com.hfad.starbuzz;

/**
 * Created by xcode on 2018-04-09.
 */

public class Food {

    private String name;
    private String description;
    private int imageResourceId;
    //drinks is an array of Drinks
    public static final Food[] food = {
            new Food("Burger", "Meat in center with two buns", R.drawable.burger),
            new Food("Pizza", "flat bread with cheese and toppings", R.drawable.pizza),
            new Food("Donut", "Bread with lotso of sugar", R.drawable.donut)

    };

    //Each Drink has a name, description, and an image resource
    private Food(String name, String description, int imageResourceId) {
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
