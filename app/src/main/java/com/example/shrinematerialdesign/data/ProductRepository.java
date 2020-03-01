package com.example.shrinematerialdesign.data;

import com.example.shrinematerialdesign.product.ProductContract;

import java.util.List;

public interface ProductRepository {

    List<ProductEntry> getAllProduct();
    void testGetAll(ProductContract.ProductInteractor listener);
}
