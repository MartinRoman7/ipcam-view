package com.github.niqdev.ipcam.Fragments.login;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.niqdev.ipcam.LoginActivity;
import com.github.niqdev.ipcam.MainActivity;
import com.github.niqdev.ipcam.R;


/**
 * Created by ctin on 14/09/17.
 */

public class RegistroFragment_2 extends Fragment {
    private EditText usuario;
    private EditText password;
    private EditText passworda;
    private EditText passwordold;
    private Button cancelar;
    private Button aceptar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if(container!=null){
            View rootView = inflater.inflate(R.layout.registro_layout_2,container,false);

            usuario = (EditText)rootView.findViewById(R.id.Usuario_registro_2);
            password = (EditText)rootView.findViewById(R.id.password_registro_2);
            passworda = (EditText)rootView.findViewById(R.id.passworda_registro_2);
            passwordold = (EditText)rootView.findViewById(R.id.password_old_2);
            cancelar = (Button)rootView.findViewById(R.id.cancelar_2);
            aceptar = (Button)rootView.findViewById(R.id.aceptar_2);

            aceptar.setOnClickListener(accept);
            cancelar.setOnClickListener(cancel);

            return rootView;
        }
        return null;
    }

    private View.OnClickListener accept = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hacerRegistro();
        }
    };

    private  View.OnClickListener cancel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finalizarApp();
        }
    };

    private void hacerRegistro(){
        String Usuario=usuario.getText().toString();
        String Password=password.getText().toString();
        String PasswordA=passworda.getText().toString();
        String PasswordO=passwordold.getText().toString();

        if(! (Usuario.equals("") && Password.equals("") && PasswordA.equals("") && PasswordO.equals(""))) {
            if(Password.equals(PasswordA)){
                //JSONObject JsonRegistro = new JSONObject();
                try {
                    //JsonRegistro.put("Usuario", Usuario);
                    //JsonRegistro.put("Contraseña", Password);
                    //String Json = JsonRegistro.toString();

                    //Toast.makeText(getContext(), Json, Toast.LENGTH_LONG).show();


                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    String pass = preferences.getString(LoginActivity.PASSWORD,"");


                    if (PasswordO.equals(pass)) {

                        try {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(LoginActivity.USER, Usuario);
                            editor.putString(LoginActivity.PASSWORD, Password);
                            editor.apply();

                            this.getFragmentManager()
                                    .beginTransaction()
                                    .add(R.id.contenedor, new LoginFragment())
                                    .commit();
                        } catch (Exception e) {

                        }
                    } else {
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(getContext());
                        }
                        builder.setTitle("Alerta")
                                .setMessage("Contraseña antigua incorrecta")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }


                }catch (Exception e){

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
                        .setMessage("No coinciden contraseñas")
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



    private void finalizarApp(){
        this.getFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor, new LoginFragment())
                .commit();
    }
    
}

