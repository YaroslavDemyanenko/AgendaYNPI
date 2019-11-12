package com.example.agendaynpi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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

    public class Registro extends DialogFragment {
        @Override
        public Dialog onCreateDialog(final Bundle savedInstanceState) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final LayoutInflater inflater = this.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.registro, null))
                    .setPositiveButton(R.string.registrarse, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            EditText campo = (EditText) getDialog().findViewById(R.id.txtUsuarioLogin);
                            editor.putString("usuario", String.valueOf(campo.getText()));
                        }
                    })
                    .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Registro.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }
    }
}
