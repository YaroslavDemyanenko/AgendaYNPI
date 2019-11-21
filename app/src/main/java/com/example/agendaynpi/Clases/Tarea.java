package com.example.agendaynpi.Clases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.agendaynpi.BaseSQLite.SQLiteHelper;

import java.util.Date;

public class Tarea {
    private String nombre, descri, coste, prioridad;
    private int codigo;
    private Date fecha;


    public Tarea(String nombre, String descri, Date fecha, String coste, String prioridad, Context contexto) {
        this.nombre = nombre;
        this.descri = descri;
        this.fecha = fecha;
        this.coste = coste;
        this.prioridad = prioridad;
        this.codigo = consultaCodigoMaximo(contexto);
    }

    public int consultaCodigoMaximo(Context context) {
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codMax="";
        Cursor fila = bd.rawQuery("select max(codigo) from tareas", null);
        if (fila.moveToFirst()) {
            codMax=fila.getString(0);
        } else {
            Toast.makeText(context, "Codigo maximo no encontrado", Toast.LENGTH_SHORT).show();
        }

        bd.close();
        return Integer.valueOf(codMax);
    }
}
