package com.example.shrinematerialdesign.data;

import com.example.shrinematerialdesign.login.LoginContract;

public interface UserRepository {
    void login(String username, String password, LoginContract.UserService listener);
}
