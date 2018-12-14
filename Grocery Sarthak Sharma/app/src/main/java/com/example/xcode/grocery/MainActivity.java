package com.example.xcode.grocery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.xcode.grocery.GroceryData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private RecyclerView mRecyclerView;
//    private GroceryListAdapter gAdapter;
//    private final LinkedList<Object> mGroceryList = new LinkedList<>();
    private List<GroceryData> groceryData;
    private RecyclerView rv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData() {
        groceryData = new ArrayList<>();
        groceryData.add(new GroceryData("Lemons", R.drawable.lemon, "900", "1 ton", "99"));
        groceryData.add(new GroceryData("Melons", R.drawable.melons, "500", "3 ton", "100"));
        groceryData.add(new GroceryData("Kerala", R.drawable.karela, "1000", "4 ton", "20"));
    }

    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(groceryData);
        rv.setAdapter(adapter);
    }

}













//     GroceryData gd = new GroceryData("Lemons", R.drawable.lemon, "900", "1 ton", "99" );
//     GroceryData md = new GroceryData("Melons", R.drawable.melons, "500", "3 ton", "100" );
//     GroceryData zd = new GroceryData("Kerala", R.drawable.karela, "1000", "4 ton", "20" );

//        mGroceryList.add(new GroceryData("Lemons", R.drawable.melons, "900", "1 ton", "99"));
//        mGroceryList.add(new GroceryData("Melons", R.drawable.melons, "500", "3 ton", "100"));
//        mGroceryList.add(new GroceryData("Kerala", R.drawable.karela, "1000", "4 ton", "20"));
//        System.out.println("the lists are below FADFADSFDASFADSF");
//        mGroceryList.add(gd.toString());
//        mGroceryList.add(md.toString());
//        mGroceryList.add(zd.toString());
       // System.out.println("Grocery List " + mGroceryList.toString());
        // Put initial data into the word list.
//
//        for (int i = 0; i < mGroceryList.size(); i++) {
//            System.out.println(mGroceryList.get(i));
//        }

//        // Create recycler view.
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        // Create an adapter and supply the data to be displayed.
//        gAdapter = new GroceryListAdapter(this, mGroceryList);
//        // Connect the adapter with the recycler view.
//        mRecyclerView.setAdapter(gAdapter);
//        // Give the recycler view a default layout manager.
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//}