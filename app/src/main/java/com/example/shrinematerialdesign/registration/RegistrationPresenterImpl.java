package com.example.shrinematerialdesign.registration;

import com.example.shrinematerialdesign.data.RegistrationRepository;

public class RegistrationPresenterImpl implements RegistrationContract.Presenter, RegistrationContract.Interactor {

    RegistrationContract.View view;
    private RegistrationRepository repository;

    public RegistrationPresenterImpl(RegistrationContract.View view) {
        this.view = view;
        repository = new RegistrationRepositoryImpl();
    }

    @Override
    public void onClick() {

        //TODO: Validate Inputs
        //---------------------
        ///////////////////////

        view.showProgress();
        repository.register(view.getFirstname(),
                view.getLastname(),
                view.getUsername(),
                view.getPassword(),
                this);
    }

    @Override
    public void onFinishedSubmit(String response) {
        view.hideProgress();
        view.navigateToHome(response);
    }
}
