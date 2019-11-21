package com.example.agendaynpi.Actividades;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.agendaynpi.BaseSQLite.SQLiteHelper;
import com.example.agendaynpi.Clases.Tarea;
import com.example.agendaynpi.R;

import java.util.Calendar;

public class CrearTareasActivity extends AppCompatActivity {

    private SQLiteHelper admin;
    private SQLiteDatabase bd;
    private EditText txtNombre,txtDesc,txtCoste,calenFecha;
    private Spinner spinnerPrioridad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creartarea);
        txtNombre=(EditText)findViewById(R.id.txtTareaNombre);
        txtDesc=(EditText)findViewById(R.id.txtTareaDesc);
        txtCoste=(EditText)findViewById(R.id.txtCosteTarea);
        calenFecha=(EditText)findViewById(R.id.calenFecha);
        spinnerPrioridad=(Spinner)findViewById(R.id.spinnerPrioridad);
    }

    public void guardarTareaEnBd(View v) {
        //DB BROWSER
        String nombre = txtNombre.getText().toString();
        String descri = txtDesc.getText().toString();
        String fecha =calenFecha.getText().toString();
        String coste = txtCoste.getText().toString();
        String prioridad = spinnerPrioridad.getSelectedItem().toString();
        Tarea tarea = new Tarea(nombre,descri,fecha,coste,prioridad,this);
        tarea.guardarTarea(this);
        Toast.makeText(this, "Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show();
    }


    public void cogerFecha(View view) {
        switch (view.getId()) {
            case R.id.calenFecha:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {

        }
    }


    //https://programacionymas.com/blog/como-pedir-fecha-android-usando-date-picker
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
