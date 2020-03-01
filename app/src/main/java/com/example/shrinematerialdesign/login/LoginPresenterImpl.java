package com.example.shrinematerialdesign.login;

import android.content.Context;

import com.example.shrinematerialdesign.data.UserRepository;
import com.example.shrinematerialdesign.utils.SharedPreferenceManager;
import com.example.shrinematerialdesign.utils.ShrineApplication;

public class LoginPresenterImpl implements LoginContract.Presenter, LoginContract.UserService {

    LoginContract.View view;
    UserRepository repository;

    public LoginPresenterImpl(LoginContract.View view) {
        this.view = view;
        repository = new UserRepositoryImpl();
    }

    @Override
    public void onClick() {

        if (view.getUsername().isEmpty()) {
            view.hideProgress();
            view.showUsernameError();
            return;
        }

        if (view.getPassword().isEmpty()) {
            view.hideProgress();
            view.showPasswordError();
            return;
        }

        view.showProgress();
        repository.login(view.getUsername(), view.getPassword(), this);
    }

    @Override
    public void onFinished(boolean bool) {
        view.hideProgress();

        if (bool){

            // Save username
            SharedPreferenceManager
                    .getInstance(ShrineApplication.getAppContext())
                    .userLogin(view.getUsername());

            view.navigateHome();

        }else{
            view.showLoginError();
        }
    }
}
