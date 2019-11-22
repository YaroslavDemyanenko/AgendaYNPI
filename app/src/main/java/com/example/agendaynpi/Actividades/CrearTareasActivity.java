package com.example.agendaynpi.Actividades;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaynpi.Clases.Tarea;
import com.example.agendaynpi.R;

import java.util.Calendar;
import java.util.Locale;

public class CrearTareasActivity extends AppCompatActivity {

    private EditText txtNombre, txtDesc, txtCoste, calenFecha;
    private TextView lblCrearModificarTarea;
    private Spinner spinnerPrioridad;
    private int ultimoAnio, ultimoMes, ultimoDiaDelMes;
    private Tarea tarea;
    private boolean modoModificacion = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creartarea);
        txtNombre = findViewById(R.id.txtTareaNombre);
        txtDesc = findViewById(R.id.lblDescripcionTarea);
        txtCoste = findViewById(R.id.lblCosteTarea);
        calenFecha = findViewById(R.id.lblFechaTarea);
        lblCrearModificarTarea = findViewById(R.id.lblCrearModificarTarea);

        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);
        refrescarFechaEnEditText();

        calenFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialogoFecha = new DatePickerDialog(CrearTareasActivity.this, AlertDialog.THEME_HOLO_DARK, listenerDeDatePicker, ultimoAnio, ultimoMes, ultimoDiaDelMes);
                dialogoFecha.show();
            }
        });

        spinnerPrioridad = findViewById(R.id.spinnerPrioridad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.prioridades_array, R.layout.spinneryaros);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrioridad.setAdapter(adapter);

        Intent intent = getIntent();
        Object objetosIntent = intent.getSerializableExtra(ListaTareasActivity.tareaIntent);
        this.tarea = (Tarea) objetosIntent;
        this.modoModificacion = !(this.tarea == null);
        if (modoModificacion) {
            cargarDatosEnCampos();
        } else {
            lblCrearModificarTarea.setText("Crear Tarea");
        }
    }

    private void cargarDatosEnCampos() {
        lblCrearModificarTarea.setText("Modificar Tarea");
        txtNombre.setText(this.tarea.getNombre());
        txtDesc.setText(this.tarea.getDescri());
        spinnerPrioridad.setSelection(tarea.getPrioridad());
        txtCoste.setText(this.tarea.getCoste());
        calenFecha.setText(this.tarea.getFecha());
    }


    public void refrescarFechaEnEditText() {
        String fecha = String.format(Locale.getDefault(), "%02d-%02d-%02d", ultimoAnio, ultimoMes + 1, ultimoDiaDelMes);
        calenFecha.setText(fecha);
    }

    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int anio, int mes, int diaDelMes) {
            ultimoAnio = anio;
            ultimoMes = mes;
            ultimoDiaDelMes = diaDelMes;
            refrescarFechaEnEditText();
        }
    };


    public void guardarTareaEnBd(View v) {
        String nombre = txtNombre.getText().toString();
        String descri = txtDesc.getText().toString();
        String fecha = calenFecha.getText().toString();
        String coste = txtCoste.getText().toString();
        int prioridad = spinnerPrioridad.getSelectedItemPosition();
        if (!modoModificacion) {
            Tarea tareaNueva = new Tarea(nombre, descri, fecha, coste, prioridad, 0);
            tareaNueva.guardarTarea(this);
            Toast.makeText(this, "Tarea guardada", Toast.LENGTH_SHORT).show();
            pasarA(MenuPrincipalActivity.class);
        } else {
            Tarea tareaModificada = new Tarea(nombre, descri, fecha, coste, prioridad, this.tarea.isEstaHechaInt());
            this.tarea.actualizarTarea(this,tareaModificada);
            Toast.makeText(this, "Tarea modificada", Toast.LENGTH_SHORT).show();
            pasarA(ListaTareasActivity.class);
        }
    }


    private void pasarA(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }
}
