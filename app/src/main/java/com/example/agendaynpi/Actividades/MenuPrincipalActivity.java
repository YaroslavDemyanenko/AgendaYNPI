package com.example.agendaynpi.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.agendaynpi.R;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipal);
    }

    public void irALista(View view) {
        pasarA(ListaTareasActivity.class);
    }

    public void irACrear(View view) {
        pasarA(CrearTareasActivity.class);
    }

    private void pasarA(Class clase) {
        Intent intent = new Intent(MenuPrincipalActivity.this, clase);
        startActivity(intent);
    }
}
