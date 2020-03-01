package com.example.shrinematerialdesign.shoppingcart;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.shrinematerialdesign.R;
import com.google.android.material.card.MaterialCardView;

class ShoppingProductCardViewHolder extends RecyclerView.ViewHolder {

    public TextView productName, totalPricePerProduct, productQty, numberOfOrder;
    ImageButton subtract_btn, addition_btn;
    MaterialCardView productCardView;

    public ShoppingProductCardViewHolder(@NonNull final View itemView) {
        super(itemView);

        productCardView = itemView.findViewById(R.id.shr_shopping_cart_product_card);
        productName = itemView.findViewById(R.id.product_name);
        productQty = itemView.findViewById(R.id.product_qty);
        totalPricePerProduct = itemView.findViewById(R.id.total_price_per_product);

        subtract_btn = itemView.findViewById(R.id.subtract_order);
        numberOfOrder = itemView.findViewById(R.id.number_of_order);
        addition_btn = itemView.findViewById(R.id.add_order);
    }

}
