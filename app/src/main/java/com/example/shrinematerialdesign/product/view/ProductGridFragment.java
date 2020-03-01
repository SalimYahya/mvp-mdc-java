package com.example.shrinematerialdesign.product.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shrinematerialdesign.MainActivity;
import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.data.ProductEntry;
import com.example.shrinematerialdesign.data.ShoppingCart;
import com.example.shrinematerialdesign.login.LoginFragment;
import com.example.shrinematerialdesign.product.NavigationIconClickListener;
import com.example.shrinematerialdesign.product.ProductCardRecyclerViewAdapter;
import com.example.shrinematerialdesign.product.ProductContract;
import com.example.shrinematerialdesign.product.ProductGridItemDecoration;
import com.example.shrinematerialdesign.product.presenter.ProductPresenterImpl;
import com.example.shrinematerialdesign.shoppingcart.ShoppingFragment;
import com.example.shrinematerialdesign.utils.SharedPreferenceManager;
import com.example.shrinematerialdesign.utils.ShrineApplication;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductGridFragment extends Fragment implements ProductContract.ProductView {

    private static final String TAG = "Product Grid";
    private static final String NAME_ARGS = "name";
    private String mName;

    ProductContract.ProductPresenter productPresenter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MaterialButton btn_featured, btn_appartment, btn_accessories, btn_shoes, btn_tops, btn_bottoms, btn_dresses, btn_logout;
    FloatingActionButton fab;

    public static ProductGridFragment newInstance(final String name){
        final ProductGridFragment productGridFragment = new ProductGridFragment();
        final Bundle args = new Bundle(1);
        args.putString(NAME_ARGS, name);
        productGridFragment.setArguments(args);

        return productGridFragment;
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false);
        setUpToolBar(view);

        fab = view.findViewById(R.id.floating_action_button);
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));

        productPresenter = new ProductPresenterImpl(this);
        productPresenter.performGetAllProduct();

        fab.setOnClickListener(fabOnClick);
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

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(getContext(), view.findViewById(R.id.product_grid)));

        // Setting toolbar buttons
        initToolbarButtons(view);
        initToolbarButtonsOnClick(toolbarButtonsOnClick);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.shr_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setProductList(List<ProductEntry> products) {
        recyclerView.setAdapter(new ProductCardRecyclerViewAdapter(products));

        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));
    }


    public void initToolbarButtons(View view){
        btn_featured = view.findViewById(R.id.btn_featured);
        btn_appartment = view.findViewById(R.id.btn_apartment);
        btn_accessories = view.findViewById(R.id.btn_accessories);
        btn_shoes = view.findViewById(R.id.btn_shoes);
        btn_tops = view.findViewById(R.id.btn_tops);
        btn_bottoms = view.findViewById(R.id.btn_bottoms);
        btn_dresses = view.findViewById(R.id.btn_dresses);
        btn_logout = view.findViewById(R.id.btn_logout);
    }

    private void initToolbarButtonsOnClick(View.OnClickListener toolbarButtonsOnClick) {
        btn_featured.setOnClickListener(toolbarButtonsOnClick);
        btn_appartment.setOnClickListener(toolbarButtonsOnClick);
        btn_accessories.setOnClickListener(toolbarButtonsOnClick);
        btn_shoes.setOnClickListener(toolbarButtonsOnClick);
        btn_tops.setOnClickListener(toolbarButtonsOnClick);
        btn_bottoms.setOnClickListener(toolbarButtonsOnClick);
        btn_dresses.setOnClickListener(toolbarButtonsOnClick);
        btn_logout.setOnClickListener(toolbarButtonsOnClick);
    }

    /**
     * Initialize FAB Click
     * */
    View.OnClickListener fabOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (ShoppingCart
                    .getInstance()
                    .getListOfOrderedProducts()
                    .size() > 0)
            {
                ((MainActivity) getActivity()).replaceFragment(ShoppingFragment.newInstance("shopping fragment"), "shopping fragment");
                Toast.makeText(getActivity(),
                        ShoppingCart.getInstance().getString(), Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(getActivity(),
                        "Your Shopping Cart is Empty", Toast.LENGTH_SHORT).show();
            }
        }
    };


    /**
     * Initialize Toolbar Clicks
     * */
    View.OnClickListener toolbarButtonsOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_featured:
                    Toast.makeText(getActivity(),"FEATURED CLICKED",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_apartment:
                    Toast.makeText(getActivity(),"APARTMENT CLICKED",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_accessories:
                    Toast.makeText(getActivity(),"ACCESSORIES CLICKED",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_shoes:
                    Toast.makeText(getActivity(),"SHOES CLICKED",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_tops:
                    Toast.makeText(getActivity(),"TOPS CLICKED",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_bottoms:
                    Toast.makeText(getActivity(),"BOTTOMS CLICKED",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_dresses:
                    Toast.makeText(getActivity(),"DRESSES CLICKED",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_logout:
                    final FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    Toast.makeText(getActivity(),"SIGNING OUT",Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferenceManager.getInstance(ShrineApplication.getAppContext()).logout();
                            transaction.replace(R.id.container, new LoginFragment());
                            transaction.commit();
                        }
                    }, 2000);
                    //SharedPreferenceManager.getInstance(ShrineApplication.getAppContext()).logout(); // Logout
                    break;
            }
        }
    };
}
