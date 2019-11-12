package com.example.agendaynpi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    SharedPreferences preferencias;
    SharedPreferences.Editor editor;
    boolean primeroLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.preferencias = getSharedPreferences("login", Context.MODE_PRIVATE);
        this.editor = preferencias.edit();
    }

    private boolean comprobarUsuario(String usuarioTxt, String contrasenyaTxt) {

        String usuario = preferencias.getString("usuario", "root");
        String contrasenya = preferencias.getString("contrasenya", "admin");

        if (usuario.equals(usuarioTxt)) {
            if (usuarioTxt.equals("root")) {
                primeroLogin = true;
            }
            return contrasenya.equals(contrasenyaTxt);
        } else {
            return false;
        }
    }

    private void cambioDeUsuario(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
    }

}
