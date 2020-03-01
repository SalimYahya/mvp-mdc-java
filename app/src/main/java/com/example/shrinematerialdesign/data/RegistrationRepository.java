package com.example.shrinematerialdesign.data;

import com.example.shrinematerialdesign.registration.RegistrationContract;

public interface RegistrationRepository {
    void register(String firstName, String lastName, String username, String password, RegistrationContract.Interactor Listener);
}
