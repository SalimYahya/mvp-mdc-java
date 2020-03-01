package com.example.shrinematerialdesign.data;

import android.net.Uri;

import com.example.shrinematerialdesign.shoppingcart.ShoppingCartProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private static ShoppingCart instance;
    private static Map<ProductEntry, Integer> orders = null;

    private ShoppingCart(){
        orders = new HashMap<>();
    }

    public static ShoppingCart getInstance(){
        if (instance == null)
            instance = new ShoppingCart();

        return instance;
    }

    public void addOrder(ProductEntry productEntry, int numberOfOrder){
        if(numberOfOrder <= 0){
            deleteOrder(productEntry);
            return;
        }

        orders.put(productEntry, numberOfOrder);
    }

    public void deleteOrder(ProductEntry productEntry){
        orders.remove(productEntry);
    }

    public Map<ProductEntry, Integer> getListOfOrderedProducts(){
        return orders;
    }

    public String getString(){
        String strg = "";

        for(Map.Entry<ProductEntry, Integer> entry : orders.entrySet()){
            strg = strg + entry.getKey() + " " + entry.getValue() + "\n";
        }

        return strg;
    }

    public List<ShoppingCartProduct> getTest(){
        List <ShoppingCartProduct> orderList = new ArrayList<ShoppingCartProduct>();

        for(Map.Entry<ProductEntry, Integer> entry : orders.entrySet()){

            String name = entry.getKey().title;
            String price = entry.getKey().price;
            int numberOfOrders = entry.getValue();

            orderList.add(new ShoppingCartProduct(name, price, String.valueOf(numberOfOrders)) );

        }

        return orderList;
    }

}
