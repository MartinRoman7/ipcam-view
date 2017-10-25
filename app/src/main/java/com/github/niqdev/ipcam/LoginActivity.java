package com.github.niqdev.ipcam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.niqdev.ipcam.Fragments.login.LoginFragment;


/**
 * Created by ctin on 14/09/17.
 */

public class LoginActivity extends AppCompatActivity {
    public static final String USER = "user_app";
    public static final String PASSWORD = "password_app";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_content_layout);

        this.getFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor,new LoginFragment())
                .commit();

    }
}
