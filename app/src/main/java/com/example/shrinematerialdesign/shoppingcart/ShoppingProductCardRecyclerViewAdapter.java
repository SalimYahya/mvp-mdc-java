package com.example.shrinematerialdesign.shoppingcart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.data.ProductEntry;
import com.example.shrinematerialdesign.data.ShoppingCart;
import com.example.shrinematerialdesign.utils.ImageRequester;

import java.util.List;

public class ShoppingProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ShoppingProductCardViewHolder> {

    private List<ShoppingCartProduct> shoppingCartProductList;
    private ImageRequester imageRequest;

    public ShoppingProductCardRecyclerViewAdapter() {
        this.shoppingCartProductList = ShoppingCart.getInstance().getOrderList();
        imageRequest = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ShoppingProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_shopping_cart_product_card, parent,false);
        return new ShoppingProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingProductCardViewHolder holder, final int position) {
        if (shoppingCartProductList != null && position < shoppingCartProductList.size()) {

            ShoppingCartProduct product = shoppingCartProductList.get(position);
            holder.productName.setText(product.name);
            holder.productQty.setText(product.getNumberOfOrders().concat(" items X ").concat(product.price));
            imageRequest.setImageFromUrl(holder.productImage, product.url);

            int qtyTemp = Integer.parseInt(product.getNumberOfOrders());
            int productPriceTemp = Integer.parseInt(product.price.replaceAll("\\$", ""));
            int totalTemp = productPriceTemp * qtyTemp;
            holder.totalPricePerProduct.setText("$".concat(String.valueOf(totalTemp)));

            holder.numberOfOrder.setText(product.getNumberOfOrders());
        }
    }


    @Override
    public int getItemCount() {
        return shoppingCartProductList.size();
    }
}
