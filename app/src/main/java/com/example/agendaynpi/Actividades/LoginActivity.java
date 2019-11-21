package com.example.agendaynpi.Actividades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaynpi.Funciones.Comodin;
import com.example.agendaynpi.R;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferencias;
    SharedPreferences.Editor editor;
    Activity loginAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.preferencias = getSharedPreferences("login", Context.MODE_PRIVATE);
        this.editor = preferencias.edit();
        this.loginAct=this;
    }

    public void login(View v) {
        EditText usuarioTxt = findViewById(R.id.txtUsuarioLogin);
        EditText contrasenyaTxt = findViewById(R.id.txtContrasenyaLogin);
        String usuarioIntroducido = usuarioTxt.getText().toString();
        String contrasenyaIntroducida = contrasenyaTxt.getText().toString();

        String usuario = preferencias.getString("usuario", "root");
        String contrasenya = preferencias.getString("contrasenya", "admin");

        if (usuario.equals(usuarioIntroducido)) {
            if (usuario.equals("root")) {
                mostrarDialogo(v);
                pasarASiguente();
            }
            if (contrasenya.equals(contrasenyaIntroducida)) {
                Comodin.hideKeyboard(loginAct);
                pasarASiguente();
            }
        }
    }

    private void pasarASiguente() {
        Intent intent = new Intent(LoginActivity.this, CrearTareasActivity.class);
        startActivity(intent);
    }

    public void mostrarDialogo(View v) {
        LayoutInflater li = LayoutInflater.from(v.getContext());
        View vistaDialogo = li.inflate(R.layout.registro, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
        alertDialogBuilder.setView(vistaDialogo);
        final EditText usuarioTxt = vistaDialogo.findViewById(R.id.txtUsuarioRegistro);
        final EditText contrasenyaTxt = vistaDialogo.findViewById(R.id.txtContrasenyaRegistro);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String usuario = usuarioTxt.getText().toString();
                        String contrasenya = contrasenyaTxt.getText().toString();
                        if (!usuario.equals("") && !contrasenya.equals("")) {
                            editor.putString("usuario", usuario);
                            editor.putString("contrasenya", contrasenya);
                            editor.commit();
                            Comodin.hideKeyboard(loginAct);
                        }
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.show();
    }
}