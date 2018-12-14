package com.example.xcode.grocery;

import java.util.ArrayList;
import java.util.List;

public class GroceryData {


        public int productImage;
        public String productName;
        public String productPrice;
        public String productWeight;
        public String productQty;

        public GroceryData(String productName, int productImage, String productPrice, String productWeight, String productQty) {
            this.productImage = productImage;
            this.productName = productName;
            this.productPrice = productPrice;
            this.productWeight = productWeight;
            this.productQty = productQty;
        }
        @Override
        public String toString(){
        return productImage+","+productName+","+productPrice+","+productQty
                +","+productWeight;
    }
        public String getProductQty() {
            return productQty;
        }

        public void setProductQty(String productQty) {
            this.productQty = productQty;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductWeight() {
            return productWeight;
        }

        public void setProductWeight(String productWeight) {
            this.productWeight = productWeight;
        }

        public int getProductImage() {
            return productImage;
        }

        public void setProductImage(int productImage) {
            this.productImage = productImage;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    private List<GroceryData> gList;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.

    }
