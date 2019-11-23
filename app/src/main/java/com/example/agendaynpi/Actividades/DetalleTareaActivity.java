package com.example.agendaynpi.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.agendaynpi.Clases.Tarea;
import com.example.agendaynpi.R;

public class DetalleTareaActivity extends AppCompatActivityActionBar {


    TextView lblNombre;
    TextView lblDesc;
    TextView lblCoste;
    TextView lblFecha;
    TextView lblPrioridad;
    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareadetallada);

        lblNombre = findViewById(R.id.lblNombreTareaDetallada);
        lblDesc = findViewById(R.id.lblDescripcionTareaDetallada);
        lblCoste = findViewById(R.id.lblCosteTareaDetallada);
        lblFecha = findViewById(R.id.lblFechaTareaDetallada);
        lblPrioridad = findViewById(R.id.lblPrioridadTareaDetallada);

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
        lblFecha.setText(getString(R.string.detalle_para_fecha, tarea.getFecha()));
        lblPrioridad.setText(tarea.getPrioridadString(this));
        lblCoste.setText(getString(R.string.detalle_coste, tarea.getCoste()));
    }

    public void marcarCompletada(View v) {
        this.tarea.setEstaHecha(true);
        this.tarea.marcarCompletada(this);
    }

    public void borrarTarea(View v) {
        this.tarea.borrarTarea(this);
        pasarA(ListaTareasActivity.class);
    }

    public void volverALista(View v) {
        pasarA(ListaTareasActivity.class);
    }

}
