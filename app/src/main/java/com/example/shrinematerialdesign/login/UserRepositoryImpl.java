package com.example.shrinematerialdesign.login;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;

import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.utils.ShrineApplication;
import com.example.shrinematerialdesign.data.User;
import com.example.shrinematerialdesign.data.UserRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String TAG = UserRepositoryImpl.class.getSimpleName();

    public List<User> usersList = null;
    public Context context;

    public UserRepositoryImpl() {
        this.context = ShrineApplication.getAppContext();
        usersList = initUsersList(ShrineApplication.getAppContext().getResources());
    }


    @Override
    public void login(final String username, final String password, final LoginContract.UserService listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean isValid = false;
                for(User user:usersList){
                   if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
                       isValid = true;
                   }
                }

                listener.onFinished(isValid);
            }
        }, 1200);
    }

    public static List<User> initUsersList(Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.users);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, pointer);
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error writing/reading from the JSON file.", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "Error closing the input stream.", exception);
            }
        }

        String jsonUsersString = writer.toString();
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        return gson.fromJson(jsonUsersString, userListType);
    }
}
