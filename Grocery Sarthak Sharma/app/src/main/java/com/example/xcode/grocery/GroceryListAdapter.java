package com.example.xcode.grocery;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.LinkedList;

    /**
     * Shows how to implement a simple Adapter for a RecyclerView.
     * Demonstrates how to add a click handler for each item in the ViewHolder.
     */
    public class GroceryListAdapter
            extends RecyclerView.Adapter<GroceryListAdapter.GroceryViewHolder> {

        private final LinkedList<Object> mGroceryList;
        private final LayoutInflater mInflater;

        class GroceryViewHolder extends RecyclerView.ViewHolder {
//                implements View.OnClickListener {

            public final TextView wordItemView;
            final GroceryListAdapter gAdapter;

            /**
             * Creates a new custom view holder to hold the view to display
             * in the RecyclerView.
             *
             * @param itemView The view in which to display the data.
             * @param adapter  The adapter that manages the the data and
             *                 views for the RecyclerView.
             */
            public GroceryViewHolder(View itemView, GroceryListAdapter adapter) {
                super(itemView);

                CardView cv;
                TextView vegetable_name;
                TextView vegetable_price;
                TextView vegetable_quantity;
                TextView vegetable_weight;
                ImageView vegetable_image;

                cv = (CardView)itemView.findViewById(R.id.cv);
                vegetable_name = (TextView)itemView.findViewById(R.id.vegetable_name);
                vegetable_price = (TextView)itemView.findViewById(R.id.vegetable_price);
                vegetable_image = (ImageView)itemView.findViewById(R.id.vegetable_photo);
                vegetable_quantity = (TextView)itemView.findViewById(R.id.vegetable_quantity);
                vegetable_weight = (TextView)itemView.findViewById(R.id.vegetable_weight);


                wordItemView =  itemView.findViewById(R.id.vegetable_name);
                this.gAdapter = adapter;
               // itemView.setOnClickListener(this);
            }

        }

        public GroceryListAdapter(Context context, LinkedList<Object> wordList) {
            mInflater = LayoutInflater.from(context);
            this.mGroceryList = wordList;
        }

        /**
         * Inflates an item view and returns a new view holder that contains it.
         * Called when the RecyclerView needs a new view holder to represent an item.
         *
         * @param parent   The view group that holds the item views.
         * @param viewType Used to distinguish views, if more than one
         *                 type of item view is used.
         * @return a view holder.
         */
        @Override
            public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Inflate an item view.
            View itemView = mInflater.inflate(R.layout.grocerylist_item, parent, false);
            return new GroceryViewHolder(itemView, this);
        }

        /**
         * Sets the contents of an item at a given position in the RecyclerView.
         *
         * @param holder   The view holder for that position in the RecyclerView.
         * @param position The position of the item in the RecycerView.
         */
        @Override
        public void onBindViewHolder(GroceryViewHolder holder, int position) {
            // Retrieve the data for that position.
            String current = mGroceryList.get(position).toString();

            // Add the data to the view holder.
            holder.wordItemView.setText(current);
        }

        /**
         * Returns the size of the container that holds the data.
         *
         * @return Size of the list of data.
         */
        @Override
        public int getItemCount() {
            return mGroceryList.size();
        }

    }
