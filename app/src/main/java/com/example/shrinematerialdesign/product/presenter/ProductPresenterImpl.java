package com.example.shrinematerialdesign.product.presenter;

import com.example.shrinematerialdesign.product.ProductContract;
import com.example.shrinematerialdesign.data.ProductEntry;
import com.example.shrinematerialdesign.data.ProductRepository;
import com.example.shrinematerialdesign.product.model.ProductRepositoryImpl;

import java.util.List;


public class ProductPresenterImpl implements ProductContract.ProductPresenter, ProductContract.ProductInteractor {

    ProductContract.ProductView view;
    ProductRepository productRepository;

    List<ProductEntry> productList;

    public ProductPresenterImpl(ProductContract.ProductView view) {
        this.view = view;
        productRepository = new ProductRepositoryImpl() ;
        productList = null;
    }

    @Override
    public void performGetAllProduct() {
        view.showProgress();
        productRepository.testGetAll(this);
    }

    @Override
    public void onFabClick() {

    }

    @Override
    public void onFinished(List<ProductEntry> list) {
        if (view != null){
            view.hideProgress();
            view.setProductList(list);
        }
    }
}
