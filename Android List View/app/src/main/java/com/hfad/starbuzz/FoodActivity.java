package com.hfad.starbuzz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xcode on 2018-04-09.
 */

public class FoodActivity extends Activity {
    public static final String Extra_Food = "foodID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int foodId = (Integer)getIntent().getExtras().get(Extra_Food);
        Food food = Food.food[foodId];

        //Populate the drink name
        TextView name = findViewById(R.id.name);
        name.setText(food.getName());

        //Populate the drink description
        TextView description = findViewById(R.id.description);
        description.setText(food.getDescription());

        //Populate the drink image
        ImageView photo = findViewById(R.id.photo);
        photo.setImageResource(food.getImageResourceId());
        photo.setContentDescription(food.getName());
    }

}
