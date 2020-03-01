package com.example.shrinematerialdesign.shoppingcart;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.data.ShoppingCart;
import com.example.shrinematerialdesign.product.NavigationIconClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ShoppingFragment extends Fragment {

    private static final String NAME_ARGS = "name";
    private static final String TAG = "Shopping Fragment";
    private String mName;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    FloatingActionButton fab;

    List<ShoppingCartProduct> productList;

    public static ShoppingFragment newInstance(final String name){
        final ShoppingFragment shoppingFragment = new ShoppingFragment();
        final Bundle args = new Bundle(1);
        args.putString(NAME_ARGS, name);
        shoppingFragment.setArguments(args);

        return shoppingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        final Bundle args = getArguments();
        if (args == null || !args.containsKey(NAME_ARGS)){
            Log.d(TAG, "args is null or empty");
        }else{
            mName = args.getString(NAME_ARGS);
            Log.d(TAG, mName);
        }

        Toast.makeText(getActivity(), ShoppingCart.getInstance().getTest().toString(), Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shr_shopping_cart_fragment, container, false);
        setUpToolBar(view);

        fab = view.findViewById(R.id.floating_action_button);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ShoppingProductCardRecyclerViewAdapter());

        return view;
    }


    private void setUpToolBar(View view) {

        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null){
            activity.setSupportActionBar(toolbar);
            new NavigationIconClickListener(
                    getActivity(),
                    view.findViewById(R.id.product_grid),
                    new AccelerateDecelerateInterpolator());
        }

        //toolbar.setNavigationOnClickListener(new NavigationIconClickListener(getActivity(), view.findViewById(R.id.product_grid)));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.shr_toolbar_purcahse, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
