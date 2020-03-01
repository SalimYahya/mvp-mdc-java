package com.example.shrinematerialdesign.registration;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.login.LoginContract;
import com.example.shrinematerialdesign.login.LoginPresenterImpl;
import com.example.shrinematerialdesign.product.view.ProductGridFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

    TextInputLayout inpt_firsname, inpt_lastname, inpt_username, inpt_password, inpt_confirm_password;
    TextInputEditText ed_firstname, ed_lastname, ed_username, ed_password, ed_confim_password;
    MaterialButton bt_submit;
    ProgressBar progressBar;
    RegistrationContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.registration_fragment, container, false);

        initView(view);

        presenter = new RegistrationPresenterImpl(this);

        bt_submit.setOnClickListener(click);

        return view;
    }

    void initView(View view){

        ed_firstname = view.findViewById(R.id.ed_firstname);
        inpt_firsname = view.findViewById(R.id.inpt_firstname);

        ed_lastname = view.findViewById(R.id.ed_lastname);
        inpt_lastname = view.findViewById(R.id.inpt_lastname);

        ed_username = view.findViewById(R.id.ed_username);
        inpt_username = view.findViewById(R.id.inpt_username);

        ed_password = view.findViewById(R.id.ed_password);
        inpt_password = view.findViewById(R.id.inpt_password);

        ed_confim_password = view.findViewById(R.id.ed_confirm_password);
        inpt_confirm_password = view.findViewById(R.id.inpt_confirm_password);

        bt_submit = view.findViewById(R.id.submit);
        progressBar = view.findViewById(R.id.progressBar);
    }


    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onClick();
        }
    };


    @Override
    public String getFirstname() {
        return ed_firstname.getText().toString();
    }

    @Override
    public String getLastname() {
        return ed_lastname.getText().toString();
    }

    @Override
    public String getUsername() {
        return ed_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return ed_password.getText().toString();
    }

    @Override
    public String getConfirmedPassword() {
        return ed_confim_password.getText().toString();
    }

    @Override
    public void showFirstnameError() {
        inpt_firsname.setError("");
    }

    @Override
    public void showLastnameError() {
        inpt_lastname.setError("");
    }

    @Override
    public void showConfirmPasswordError() {
        inpt_confirm_password.setError("Password are not Match");
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showResponse(String response) {

    }

    @Override
    public void navigateToHome(String showResponse) {
        Toast.makeText(
                getActivity(),
                showResponse,
                Toast.LENGTH_SHORT)
                .show();

        ProductGridFragment productGridFragment = new ProductGridFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.container, productGridFragment);
        transaction.commit();
    }

}
