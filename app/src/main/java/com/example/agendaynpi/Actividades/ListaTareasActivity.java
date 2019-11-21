package com.example.agendaynpi.Actividades;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaynpi.BaseSQLite.SQLiteHelper;
import com.example.agendaynpi.Clases.Tarea;
import com.example.agendaynpi.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaTareasActivity extends AppCompatActivity {
    public static final String tareaIntent="tarea";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listatareas);


        ListView listaTareas = (ListView) findViewById(R.id.listTareas);
        List<Tarea> tareas = consultaTareas();
        ArrayAdapter<Tarea> arrayAdapter = new ArrayAdapter<Tarea>(this, android.R.layout.simple_list_item_1, tareas);
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
        List<Tarea> tareas = new ArrayList<Tarea>();
        Cursor fila = bd.rawQuery("select *  from tareas ", null);
        if (fila.moveToFirst()) {
            for (int i = 0; i < fila.getCount(); i++) {
                tareas.add(new Tarea(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4),fila.getInt(5),this));
                fila.moveToNext();
            }
            fila.close();
        } else
            Toast.makeText(this, "No existe una persona con dicho dni", Toast.LENGTH_SHORT).show();
        bd.close();
        return tareas;
    }

    private void pasarA(Class clase) {
        Intent intent = new Intent(this,clase);
        startActivity(intent);
    }

    private void pasarATareaDetallada(Tarea tarea) {
        Intent intent = new Intent(this,DetalleTareaActivity.class );
        intent.putExtra(tareaIntent, tarea);
        startActivity(intent);
    }





}
