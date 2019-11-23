package com.example.agendaynpi.Actividades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaynpi.Funciones.Comodin;
import com.example.agendaynpi.R;

public class AppCompatActivityActionBar extends AppCompatActivity {
    protected void pasarA(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuactionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.actionBarCrearTarea) {
            pasarA(CrearTareasActivity.class);
        }
        if (id == R.id.actionBarCambiarContra) {
            mostrarDialogoCambioContrasenya();
        }
        if (id == R.id.actionBarAcercaDe) {
            mostrarDialogoAcercaDe();
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarDialogoAcercaDe() {
        LayoutInflater li = LayoutInflater.from(this);
        View vistaDialogo = li.inflate(R.layout.acercade, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(vistaDialogo);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        alertDialogBuilder.show();
    }

    public void mostrarDialogoCambioContrasenya() {
        LayoutInflater li = LayoutInflater.from(this);
        View vistaDialogo = li.inflate(R.layout.registro, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(vistaDialogo);

        SharedPreferences preferencias = getSharedPreferences("login", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferencias.edit();
        final String contrasenya = preferencias.getString("contrasenya", "admin");
        final EditText usuarioTxt = vistaDialogo.findViewById(R.id.txtUsuarioRegistro);
        final EditText contrasenyaTxtActual = vistaDialogo.findViewById(R.id.txtContrasenyaRegistroActual);
        final EditText contrasenyaTxtNueva = vistaDialogo.findViewById(R.id.txtContrasenyaRegistroNueva);
        final Activity actividad = this;
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String usuario = usuarioTxt.getText().toString();
                        String contrasenyaActual = contrasenyaTxtActual.getText().toString();
                        String contrasenyaNueva = contrasenyaTxtNueva.getText().toString();
                        if (!usuario.equals("") && !contrasenyaActual.equals("") && !contrasenyaNueva.equals("")) {
                            if (contrasenya.equals(contrasenyaActual)) {
                                editor.putString("usuario", usuario);
                                editor.putString("contrasenya", contrasenyaNueva);
                                editor.commit();
                                Comodin.ocultarTeclado(actividad);
                                Toast.makeText(actividad, getString(R.string.avisoContrasenyaCambiada), Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(actividad, getString(R.string.avisoContrasenyaNoCoincide), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(actividad, getString(R.string.avisoCamposVacios), Toast.LENGTH_LONG).show();
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

    //Esto desactiva el boton de volver
    @Override
    public void onBackPressed() {

    }
}
