package com.example.shrinematerialdesign.product;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.shrinematerialdesign.R;
import com.google.android.material.card.MaterialCardView;

class ProductCardViewHolder extends RecyclerView.ViewHolder {

    public NetworkImageView productImage;
    public TextView productTitle, productPrice, numberOfOrder;
    ImageButton subtract_btn, addition_btn;
    MaterialCardView productCardView;

    public ProductCardViewHolder(@NonNull final View itemView) {
        super(itemView);

        productCardView = itemView.findViewById(R.id.shr_product_card);
        productImage = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        productPrice = itemView.findViewById(R.id.product_price);

        numberOfOrder = itemView.findViewById(R.id.number_of_order);
        subtract_btn = itemView.findViewById(R.id.subtract_order);
        addition_btn = itemView.findViewById(R.id.add_order);

    }

}
