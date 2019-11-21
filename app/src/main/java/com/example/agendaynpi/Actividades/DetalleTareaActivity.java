package com.example.agendaynpi.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.agendaynpi.Clases.Tarea;
import com.example.agendaynpi.R;

public class DetalleTareaActivity extends AppCompatActivity {

    private Tarea tarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareadetallada);

        Intent intent = getIntent();
        Object objetosIntent = intent.getSerializableExtra(ListaTareasActivity.tareaIntent);
        this.tarea = (Tarea) objetosIntent;
        cargarTarea(tarea);
    }


    public void cargarTarea(Tarea tarea) {
        TextView lblNombre = findViewById(R.id.lblNombreTareaDetallada);
        TextView lblDesc = findViewById(R.id.lblDescripcionTareaDetallada);
        TextView lblCoste = findViewById(R.id.lblCosteTareaDetallada);
        TextView lblFecha = findViewById(R.id.lblFechaTareaDetallada);
        TextView lblPrioridad = findViewById(R.id.lblPrioridadTareaDetallada);
        lblNombre.setText(tarea.getNombre());
        lblDesc.setText(tarea.getDescri());
        lblFecha.setText("Para el "+tarea.getFecha());
        lblPrioridad.setText(tarea.getPrioridad());
        lblCoste.setText("Tiene un coste de "+tarea.getCoste()+"€");
    }

    public void marcarCompletada(View v){
        this.tarea.setEstaHecha(true);
    }

    public void borrar(View v){

    }
}
