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

public class FoodCategoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);

        ArrayAdapter<Food> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, Food.food);
        ListView listFood= findViewById(R.id.list_drinks);
        listFood.setAdapter(listAdapter);


        //Create the listener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listDrinks,
                                            View itemView,
                                            int position,
                                            long id) {
                        //Pass the drink the user clicks on to DrinkActivity
                        Intent intent = new Intent(FoodCategoryActivity.this,
                                FoodActivity.class);
                        intent.putExtra(FoodActivity.Extra_Food, (int) id);
                        startActivity(intent);
                    }
                };

        //Assign the listener to the list view
        listFood.setOnItemClickListener(itemClickListener);
    }


}
