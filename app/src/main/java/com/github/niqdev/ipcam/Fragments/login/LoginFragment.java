package com.github.niqdev.ipcam.Fragments.login;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.niqdev.ipcam.LoginActivity;
import com.github.niqdev.ipcam.MainActivity;
import com.github.niqdev.ipcam.R;
import com.google.firebase.iid.FirebaseInstanceId;


/**
 * Created by ctin on 14/09/17.
 */

public class LoginFragment extends Fragment {

    private EditText usuario;
    private EditText password;
    private Button aceptar;
    private Button registro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if(container!=null){
            View rootView = inflater.inflate(R.layout.login_content_layout,container,false);

            usuario = (EditText)rootView.findViewById(R.id.Usuario_login);
            password = (EditText)rootView.findViewById(R.id.password_login);
            aceptar = (Button)rootView.findViewById(R.id.aceptar);
            registro = (Button)rootView.findViewById(R.id.registro);

            aceptar.setOnClickListener(accept);
            registro.setOnClickListener(register);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String pass = preferences.getString(LoginActivity.PASSWORD,"");

            if(pass.equals("admin")) {
                registro.setVisibility(View.VISIBLE);
            }
            else {
                registro.setVisibility(View.INVISIBLE);
            }



            return rootView;
        }
        return null;
    }


    private View.OnClickListener accept = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            iniciarSesion();
        }
    };
    private  View.OnClickListener register = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            registroApp();
        }
    };

    private void iniciarSesion(){
        final String TAG = "MyFirebaseIIDService";

        String Usuario=usuario.getText().toString();
        String Password=password.getText().toString();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Token: " + refreshedToken);

        if(! (Usuario.equals("") && Password.equals(""))) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            String name = preferences.getString(LoginActivity.USER,"");
            String pass = preferences.getString(LoginActivity.PASSWORD,"");

            if(Usuario.equals(name) && Password.equals(pass)){
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
            else {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Alerta")
                        .setMessage("Usuario y/o contraseña incorrectos")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        }
        else{
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(getContext());
            }
            builder.setTitle("Alerta")
                    .setMessage("Campos vacios")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }


    }

    private void registroApp(){
        String Password=password.getText().toString();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("PassLog", Password);
        String pass = preferences.getString(LoginActivity.PASSWORD,"");
        editor.apply();


        Log.i("Prueba: ",pass);

        if(pass.equals("admin")) {
            this.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new RegistroFragment())
                    .commit();
        }
        else {
            this.getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new RegistroFragment_2())
                    .commit();
        }
    }



}
