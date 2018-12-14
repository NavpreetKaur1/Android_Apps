package com.hfad.starbuzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by xcode on 2018-04-09.
 */

public class DrinkCategoryActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);


        ArrayAdapter<Drinks2> listAdpapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, Drinks2.drinks);
        ListView listDrinks2 = findViewById(R.id.list_drinks2);
        listDrinks2.setAdapter(listAdpapter2);

        //Create the listener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> listDrinks,
                                            View itemView,
                                            int position,
                                            long id) {
                        //Pass the drink the user clicks on to DrinkActivity
                        Intent intent = new Intent(DrinkCategoryActivity2.this,
                                ColdDrinkActivity.class);
                        intent.putExtra(ColdDrinkActivity.EXTRA_COLD_DRINKID, (int) id);
                        //Intent intent2 = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
                        startActivity(intent);
                    }
                };

        //Assign the listener to the list view
        listDrinks2.setOnItemClickListener(itemClickListener);
    }
}