package com.example.shrinematerialdesign.product;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shrinematerialdesign.data.ShoppingCart;
import com.example.shrinematerialdesign.utils.ImageRequester;
import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.data.ProductEntry;

import java.util.List;

public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    private List<ProductEntry> productEntryList;
    private ImageRequester imageRequest;

    public ProductCardRecyclerViewAdapter(List<ProductEntry> productEntryList) {
        this.productEntryList = productEntryList;
        imageRequest = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shr_product_card, parent,false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductCardViewHolder holder, final int position) {
        if (productEntryList != null && position < productEntryList.size()) {

            ProductEntry product = productEntryList.get(position);
            holder.productTitle.setText(product.title);
            holder.productPrice.setText(product.price);
            imageRequest.setImageFromUrl(holder.productImage, product.url);
        }

        holder.subtract_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int i = Integer.parseInt(holder.numberOfOrder.getText().toString());

                        if (i>0) {
                            i-=1;
                        }

                        if (i<=0){
                            holder.productCardView.setBackgroundResource(R.color.colorWhite);
                            holder.productCardView.setCardElevation(0);

                            ShoppingCart
                                    .getInstance()
                                    .deleteOrder(productEntryList.get(position));
                        }

                        holder.numberOfOrder.setText(String.valueOf(i));

                        ShoppingCart
                                .getInstance()
                                .addOrder(productEntryList.get(position), i);
                    }
                }, 100);
            }
        });

        holder.addition_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int i = Integer.parseInt(holder.numberOfOrder.getText().toString());
                        i+=1;

                        holder.productCardView.setBackgroundResource(R.color.productCardHighlight);
                        holder.productCardView.setCardElevation(8);

                        holder.numberOfOrder.setText(String.valueOf(i));

                        ShoppingCart
                                .getInstance()
                                .addOrder(productEntryList.get(position), i);
                    }
                }, 100);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productEntryList.size();
    }
}
