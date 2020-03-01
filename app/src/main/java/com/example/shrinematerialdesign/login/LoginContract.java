package com.example.shrinematerialdesign.login;

public interface LoginContract {
    interface View{
        String getUsername();

        String getPassword();

        void showProgress();

        void hideProgress();

        void showUsernameError();

        void showPasswordError();

        void navigateHome();

        void showLoginError();
    }

    interface Presenter{
        void onClick();
    }

    interface UserService{
        void onFinished(boolean bool);
    }
}
