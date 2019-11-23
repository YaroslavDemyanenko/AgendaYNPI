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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaynpi.Funciones.Comodin;
import com.example.agendaynpi.R;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferencias;
    SharedPreferences.Editor editor;
    Activity actividadActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.preferencias = getSharedPreferences("login", Context.MODE_PRIVATE);
        this.editor = preferencias.edit();
        this.actividadActual = this;
    }


    //Esto desactiva el boton de volver
    @Override
    public void onBackPressed() {

    }
    public void login(View v) {
        EditText usuarioTxt = findViewById(R.id.txtUsuarioLogin);
        EditText contrasenyaTxt = findViewById(R.id.txtContrasenyaLogin);
        String usuarioIntroducido = usuarioTxt.getText().toString();
        String contrasenyaIntroducida = contrasenyaTxt.getText().toString();
        String usuario = preferencias.getString("usuario", "root");
        String contrasenya = preferencias.getString("contrasenya", "admin");
        Comodin.ocultarTeclado(this);
        if (usuario.equals(usuarioIntroducido)) {
            if (contrasenya.equals(contrasenyaIntroducida)) {

                pasarA(MenuPrincipalActivity.class);
            } else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG);
            }
        } else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG);
        }
    }

    private void pasarA(Class clase) {
        Intent intent = new Intent(LoginActivity.this, clase);
        startActivity(intent);
    }
}