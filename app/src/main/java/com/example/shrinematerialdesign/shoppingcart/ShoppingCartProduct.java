package com.example.shrinematerialdesign.shoppingcart;

import android.net.Uri;

import com.example.shrinematerialdesign.data.ProductEntry;

public class ShoppingCartProduct {

    String name;
    String price;
    String numberOfOrders;
    String url;

    public ShoppingCartProduct(String name, String price, String numberOfOrders, String url) {
        this.name = name;
        this.price = price;
        this.numberOfOrders = numberOfOrders;

        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(String numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
