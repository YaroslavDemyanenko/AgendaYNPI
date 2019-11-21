package com.example.agendaynpi.Actividades;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaynpi.Clases.Tarea;
import com.example.agendaynpi.R;

import java.util.Calendar;
import java.util.Locale;

public class CrearTareasActivity extends AppCompatActivity {

    private EditText txtNombre, txtDesc, txtCoste, calenFecha;
    private Spinner spinnerPrioridad;
    private int ultimoAnio, ultimoMes, ultimoDiaDelMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creartarea);
        txtNombre = findViewById(R.id.txtTareaNombre);
        txtDesc = findViewById(R.id.lblDescripcionTarea);
        txtCoste = findViewById(R.id.lblCosteTarea);
        calenFecha = findViewById(R.id.lblFechaTarea);

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
        //DB BROWSER
        String nombre = txtNombre.getText().toString();
        String descri = txtDesc.getText().toString();
        String fecha = calenFecha.getText().toString();
        String coste = txtCoste.getText().toString();
        String prioridad = spinnerPrioridad.getSelectedItem().toString();
        Tarea tarea = new Tarea(nombre, descri, fecha, coste, prioridad,0, this);
        tarea.guardarTarea(this);
        Toast.makeText(this, "Tarea guardad", Toast.LENGTH_SHORT).show();
    }


    /**
     public void consultapordescripcion(View v) {
     admin = new SQLiteHelper(this,
     "administracion", null, 1);
     bd = admin.getWritableDatabase();
     String descri = et2.getText().toString();
     Cursor fila = bd.rawQuery("select codigo,precio from articulos where descripcion='" + descri +"'", null);
     if (fila.moveToFirst()) {
     et1.setText(fila.getString(0));
     et3.setText(fila.getString(1));
     } else
     Toast.makeText(this, "No existe un artículo con dicha descripción", Toast.LENGTH_SHORT).show();
     bd.close();
     }

     public void bajaporcodigo(View v) {
     admin = new SQLiteHelper(this, "administracion", null, 1);
     bd = admin.getWritableDatabase();
     String cod= et1.getText().toString();
     int cant = bd.delete("articulos", "codigo=" + cod, null);
     bd.close();
     et1.setText("");
     et2.setText("");
     et3.setText("");
     if (cant == 1)
     Toast.makeText(this, "Se borró el artículo con dicho código", Toast.LENGTH_SHORT).show();
     else
     Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
     }
     **/


}
