package com.example.shrinematerialdesign.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shrinematerialdesign.MainActivity;
import com.example.shrinematerialdesign.product.view.ProductGridFragment;
import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.registration.RegistrationFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment implements LoginContract.View {

    private static final String NAME_ARGS = "name";
    private static final String TAG = "Login Fragment";
    private String mName;

    TextInputLayout inpt_username, inpt_password;
    TextInputEditText ed_username, ed_password;
    MaterialButton bt_login, bt_register, bt_forgotPassword;
    ProgressBar progressBar;
    LoginContract.Presenter presenter;

    public static LoginFragment newInstance(final String name){
        final LoginFragment loginFragment = new LoginFragment();
        final Bundle args = new Bundle(1);
        args.putString(NAME_ARGS, name);
        loginFragment.setArguments(args);

        return loginFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        View view = inflater.inflate(R.layout.login_fragment, container, false);

        initView(view);
        bt_login.setOnClickListener(click);
        bt_register.setOnClickListener(click);
        bt_forgotPassword.setOnClickListener(click);

        presenter = new LoginPresenterImpl(this);


        bt_login.setOnClickListener(click);
        bt_register.setOnClickListener(click);

        return view;
    }

    void initView(View view){
        ed_username = view.findViewById(R.id.ed_username);
        ed_username.setText("salim");
        ed_username.setOnKeyListener(inputListener);
        inpt_username = view.findViewById(R.id.inpt_username);

        ed_password = view.findViewById(R.id.ed_password);
        ed_password.setText("123");
        ed_password.setOnKeyListener(inputListener);
        inpt_password = view.findViewById(R.id.inpt_password);


        bt_login = view.findViewById(R.id.login);
        bt_forgotPassword = view.findViewById(R.id.forget_password);
        bt_register = view.findViewById(R.id.registration);

        progressBar = view.findViewById(R.id.progressBar);
    }

    View.OnKeyListener inputListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            switch (v.getId())
            {
                case R.id.ed_username:
                    if (ed_username.getText() != null && ed_username.getText().toString().length() > 0)
                        inpt_username.setError(null);
                    break;
                case R.id.ed_password:
                    if (ed_password.getText() != null && ed_password.getText().toString().length() > 0)
                        inpt_username.setError(null);
                    break;
            }

            return false;
        }
    };

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login:
                    presenter.onClick();
                    break;
                case R.id.registration:
                    RegistrationFragment registrationFragment = new RegistrationFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.container, registrationFragment);
                    transaction.commit();
            }
        }
    };

    @Override
    public String getUsername() {
        return ed_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return ed_password.getText().toString();
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
    public void showUsernameError() {
        inpt_username.setError("Username mut not be Empty");
    }

    @Override
    public void showPasswordError() {
        inpt_password.setError("Password must not be Empty");
    }

    @Override
    public void navigateHome() {
        Toast.makeText(
                getActivity(),
                "Navigating",
                Toast.LENGTH_SHORT)
                .show();

        /*ProductGridFragment productGridFragment = new ProductGridFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.container, productGridFragment);
        transaction.commit();*/

        ((MainActivity) getActivity()).replaceFragment(ProductGridFragment.newInstance("product grid fragment"), "product grid fragment");

    }

    @Override
    public void showLoginError() {
        Toast.makeText(
                getActivity(),
                "User Not Found",
                Toast.LENGTH_SHORT)
                .show();
    }
}
