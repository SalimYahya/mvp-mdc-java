package com.example.shrinematerialdesign.product.model;

import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;

import com.example.shrinematerialdesign.product.ProductContract;
import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.utils.ShrineApplication;
import com.example.shrinematerialdesign.data.ProductEntry;
import com.example.shrinematerialdesign.data.ProductRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<ProductEntry> getAllProduct() {

        Resources resources = ShrineApplication.getAppContext().getResources();
        InputStream inputStream = resources.openRawResource(R.raw.products);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(ProductEntry.TAG, "Error writing/reading from the JSON file.", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(ProductEntry.TAG, "Error closing the input stream.", exception);
            }
        }
        String jsonProductsString = writer.toString();
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<ProductEntry>>() {}.getType();

        return gson.fromJson(jsonProductsString, productListType);
    }

    @Override
    public void testGetAll(final ProductContract.ProductInteractor listener) {

        final List<ProductEntry> productList = testGetFromGson();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(productList);
            }
        }, 1200);
    }

    public List<ProductEntry> testGetFromGson(){
        Resources resources = ShrineApplication.getAppContext().getResources();
        InputStream inputStream = resources.openRawResource(R.raw.products);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(ProductEntry.TAG, "Error writing/reading from the JSON file.", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(ProductEntry.TAG, "Error closing the input stream.", exception);
            }
        }
        String jsonProductsString = writer.toString();
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<ProductEntry>>() {}.getType();

        return gson.fromJson(jsonProductsString, productListType);
    }
}
