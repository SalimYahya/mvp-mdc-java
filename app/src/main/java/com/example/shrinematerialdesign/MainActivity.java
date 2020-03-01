package com.example.shrinematerialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.shrinematerialdesign.login.LoginFragment;
import com.example.shrinematerialdesign.product.view.ProductGridFragment;
import com.example.shrinematerialdesign.utils.NavigationHost;
import com.example.shrinematerialdesign.utils.SharedPreferenceManager;
import com.example.shrinematerialdesign.utils.ShrineApplication;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    SharedPreferenceManager sharedPreferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            sharedPreferenceManager = SharedPreferenceManager.getInstance(ShrineApplication.getAppContext());

            String className;

            if (sharedPreferenceManager.isLoggedIn()){
                className = "product fragment";

                /*fragmentManager.beginTransaction()
                        .addToBackStack(className)
                        .replace(R.id.container, ProductGridFragment.newInstance(className), className)
                        .commit();*/

                replaceFragment(ProductGridFragment.newInstance(className), className);


            }else{
                className = "login fragment";
                /*fragmentManager.beginTransaction()
                        .addToBackStack(className)
                        .replace(R.id.container, LoginFragment.newInstance(className), className)
                        .commit();*/

                replaceFragment(LoginFragment.newInstance(className), className);

            }

        }
    }

    @Override
    public void replaceFragment(Fragment fragment, String tag){
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if (currentFragment != null){
            if (currentFragment.getClass() == fragment.getClass()){
                return;
            }
        }

        if (getSupportFragmentManager().findFragmentByTag(tag) != null){
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            if (getSupportFragmentManager().findFragmentByTag("Login Fragment") != null){
                Log.d("TAG", "User Logout");
                sharedPreferenceManager.logout();
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.container, fragment, tag)
                .commit();
    }


    @Override
    public void onBackPressed() {
        int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();
        if (fragmentsInStack > 1){
            getSupportFragmentManager().popBackStack();
        }else if (fragmentsInStack == 1){
            finish();
        }else {
            super.onBackPressed();
        }
    }

}
