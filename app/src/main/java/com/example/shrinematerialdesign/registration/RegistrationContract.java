package com.example.shrinematerialdesign.registration;

public interface RegistrationContract {

    interface View{
        String getFirstname();
        String getLastname();
        String getUsername();
        String getPassword();
        String getConfirmedPassword();

        void showFirstnameError();
        void showLastnameError();
        void showConfirmPasswordError();

        void showProgress();
        void hideProgress();

        void showResponse(String response);
        void navigateToHome(String showResponse);

    }

    interface Presenter{
        void onClick();
    }

    interface Interactor{
        void onFinishedSubmit(String response);
    }
}
