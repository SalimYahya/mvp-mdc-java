package com.example.shrinematerialdesign.registration;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import com.example.shrinematerialdesign.R;
import com.example.shrinematerialdesign.data.RegistrationRepository;
import com.example.shrinematerialdesign.data.User;
import com.example.shrinematerialdesign.utils.ShrineApplication;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class RegistrationRepositoryImpl implements RegistrationRepository {

    private Context context;

    @Override
    public void register(final String firstName, final String lastName, final String username, final String password, final RegistrationContract.Interactor listener) {

        /*String json = "{    \"firstName\": "+ firstName + ", " +
                            "\"lastName\": "+ lastName +"," +
                            "\"username\": "+ username + "," +
                            "\"password\": "+ password +
                        "}";*/


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                User user = new User(firstName, lastName, username, password);
                String json = gson.toJson(user);


                FileOutputStream outputStream;
                try {

                    outputStream = ShrineApplication.getAppContext().openFileOutput("user.json", Context.MODE_PRIVATE);
                    outputStream.write(json.getBytes());
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                listener.onFinishedSubmit("Testing Delay");
            }
        }, 1200);
    }
}
