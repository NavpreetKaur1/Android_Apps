package com.hfad.starbuzz;

/**
 * Created by xcode on 2018-04-09.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ColdDrinkActivity extends Activity {

    public static final String EXTRA_COLD_DRINKID = "cDrinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_COLD_DRINKID);
        Drinks2 drinks2 = Drinks2.drinks[drinkId];

        //Populate the drink name
        TextView name = findViewById(R.id.name);
        name.setText(drinks2.getName());

        //Populate the drink description
        TextView description = findViewById(R.id.description);
        description.setText(drinks2.getDescription());

        //Populate the drink image
        ImageView photo = (ImageView)findViewById(R.id.photo);
        photo.setImageResource(drinks2.getImageResourceId());
        photo.setContentDescription(drinks2.getName());
    }
}
