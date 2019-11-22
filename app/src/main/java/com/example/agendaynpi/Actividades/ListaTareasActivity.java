package com.example.agendaynpi.Actividades;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaynpi.BaseSQLite.SQLiteHelper;
import com.example.agendaynpi.Clases.Tarea;
import com.example.agendaynpi.R;

import java.util.ArrayList;
import java.util.List;

public class ListaTareasActivity extends AppCompatActivity {
    public static final String tareaIntent = "tarea";
    public CheckBox cbhHechas, cbhPendientes;
    public List<Tarea> tareas;
    public List<Tarea> tareasCopia;
    public ListView listaTareas;
    public ArrayAdapter<Tarea> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listatareas);

        cbhHechas = findViewById(R.id.cbxHechas);
        cbhPendientes = findViewById(R.id.cbxPendientes);
        listaTareas = findViewById(R.id.listTareas);
        tareas = consultaTareas();
        tareasCopia.addAll(tareas);
        cargarAdaptador();
    }

    private void cargarAdaptador() {
        arrayAdapter = new ArrayAdapter<Tarea>(this, android.R.layout.simple_list_item_1, tareasCopia);
        listaTareas.setAdapter(arrayAdapter);
        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarea tareaSeleccionada = (Tarea) parent.getItemAtPosition(position);
                pasarATareaDetallada(tareaSeleccionada);
            }
        });
    }

    public List<Tarea> consultaTareas() {
        SQLiteHelper admin = new SQLiteHelper(this, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery("select *  from tareas ", null);

        tareas = new ArrayList<Tarea>();
        if (fila.moveToFirst()) {
            for (int i = 0; i < fila.getCount(); i++) {
                tareas.add(new Tarea(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getString(4), fila.getInt(5), this));
                fila.moveToNext();
            }
            fila.close();
        } else
            Toast.makeText(this, "No hay tareas, agrega alguna", Toast.LENGTH_SHORT).show();
        bd.close();
        return tareas;
    }

    private void pasarA(Class clase) {
        Intent intent = new Intent(this, clase);
        startActivity(intent);
    }

    public void actualizarListaTareas(View v) {
        boolean hechas, pendientes;
        hechas = cbhHechas.isChecked();
        pendientes = cbhPendientes.isChecked();
        tareasCopia = new ArrayList<Tarea>();
        if (hechas && !pendientes) {
            for (Tarea tar : tareas) {
                if (tar.isEstaHecha()) {
                    tareasCopia.add(tar);
                }
            }
        } else if (!hechas && pendientes) {
            for (Tarea tar : tareas) {
                if (!tar.isEstaHecha()) {
                    tareasCopia.add(tar);
                }
            }
        } else {
            for (Tarea tar : tareas) {
                tareasCopia.add(tar);
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }

    private void pasarATareaDetallada(Tarea tarea) {
        Intent intent = new Intent(this, DetalleTareaActivity.class);
        intent.putExtra(tareaIntent, tarea);
        startActivity(intent);
    }


}
