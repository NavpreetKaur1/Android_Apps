package com.example.xcode.grocery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class RVAdapter extends RecyclerView.Adapter<RVAdapter.GroceryListHolder>{

    @NonNull
    @Override
    public GroceryListHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grocerylist_item, viewGroup, false);
        GroceryListHolder pvh = new GroceryListHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryListHolder groceryListHolder, int i) {
        groceryListHolder.vegetable_name.setText(groceryData.get(i).productName);
        groceryListHolder.vegetable_quantity.setText(groceryData.get(i).productQty);
        groceryListHolder.vegetable_price.setText(groceryData.get(i).productPrice);
        groceryListHolder.vegetable_weight.setText(groceryData.get(i).productWeight);
        groceryListHolder.vegetable_image.setImageResource(groceryData.get(i).productImage);
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return groceryData.size();
    }
    List<GroceryData> groceryData;

    RVAdapter(List<GroceryData> groceryData){
        this.groceryData = groceryData;
    }
    public static class GroceryListHolder extends RecyclerView.ViewHolder {
        List<GroceryData> groceryData;

        CardView cv;
        TextView vegetable_name;
        TextView vegetable_price;
        TextView vegetable_quantity;
        TextView vegetable_weight;
        ImageView vegetable_image;


        GroceryListHolder(View itemView) {
        super(itemView);

        cv = itemView.findViewById(R.id.cv);
        vegetable_name = itemView.findViewById(R.id.vegetable_name);
        vegetable_price = itemView.findViewById(R.id.vegetable_price);
        vegetable_image = itemView.findViewById(R.id.vegetable_photo);
        vegetable_quantity = itemView.findViewById(R.id.vegetable_quantity);
        vegetable_weight = itemView.findViewById(R.id.vegetable_weight);


            vegetable_image.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String veggieName = "Karela was selected";
                    Toast toast = Toast.makeText(v.getContext(), veggieName, Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        }

    }



}
