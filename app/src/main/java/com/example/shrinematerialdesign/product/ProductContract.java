package com.example.shrinematerialdesign.product;

import androidx.fragment.app.Fragment;

import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.data.ProductEntry;

import java.util.List;

public interface ProductContract {

    interface ProductView{
        void showProgress();
        void hideProgress();

        void setProductList(List<ProductEntry> products);

    }

    interface ProductPresenter{
        void performGetAllProduct();

        /**
         * Needs modifications
         * */
        void onFabClick();
    }

    interface ProductInteractor{
        void onFinished(List<ProductEntry> list);
    }
}
