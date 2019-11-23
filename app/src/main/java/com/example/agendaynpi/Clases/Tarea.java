package com.example.agendaynpi.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.agendaynpi.BaseSQLite.SQLiteHelper;
import com.example.agendaynpi.R;

import java.io.Serializable;

public class Tarea implements Serializable {
    private String nombre, descri, coste, fecha;
    private int prioridad;
    private boolean estaHecha;

    public Tarea(String nombre, String descri, String fecha, String coste, int prioridad, int estaHecha) {
        this.nombre = nombre;
        this.descri = descri;
        this.fecha = fecha;
        this.coste = coste;
        if (prioridad > 0 && prioridad < 5) {
            this.prioridad = prioridad;
        } else {
            this.prioridad = 1;
        }
        this.estaHecha = estaHecha == 1;
    }

    public boolean guardarTarea(Context context) {
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = crearContentValues(Tarea.this, 0);

        bd.insert("tareas", null, registro);
        bd.close();
        return true;
    }

    public boolean borrarTarea(Context context) {
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String[] valoresWhere = {this.nombre, this.descri};
        int cant = bd.delete("tareas", "nombre=? and descripcion=?", valoresWhere);
        bd.close();
        return cant > 0;
    }

    public boolean actualizarTarea(Context context, Tarea tarNueva) {
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //Actualizar base de datos
        ContentValues registro = crearContentValues(tarNueva, tarNueva.isEstaHechaInt());
        String[] valoresWhere = {this.nombre, this.descri};
        int cant = bd.update("tareas", registro, "nombre=? and descripcion=?", valoresWhere);
        //Actualizar el propio objeto
        this.nombre = tarNueva.nombre;
        this.descri = tarNueva.descri;
        this.fecha = tarNueva.fecha;
        this.coste = tarNueva.coste;
        this.prioridad = tarNueva.prioridad;
        this.estaHecha = tarNueva.estaHecha;
        bd.close();
        return cant > 0;
    }

    private ContentValues crearContentValues(Tarea tarNueva, int estaHechaInt) {
        ContentValues registro = new ContentValues();
        registro.put("nombre", tarNueva.nombre);
        registro.put("descripcion", tarNueva.descri);
        registro.put("coste", tarNueva.coste);
        registro.put("fecha", tarNueva.fecha);
        registro.put("prioridad", tarNueva.prioridad);
        registro.put("tareaHecha", estaHechaInt);
        return registro;
    }

    public boolean marcarCompletada(Context context) {
        this.estaHecha = true;
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = crearContentValues(this, this.isEstaHechaInt());
        String[] valoresWhere = {this.nombre, this.descri};
        int cant = bd.update("tareas", registro, "nombre=? and descripcion=?", valoresWhere);
        return cant > 0;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public boolean isEstaHecha() {
        return estaHecha;
    }

    public void setEstaHecha(boolean estaHecha) {
        this.estaHecha = estaHecha;
    }

    public int isEstaHechaInt() {
        if (estaHecha) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getCoste() {
        return coste;
    }

    public void setCoste(String coste) {
        this.coste = coste;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getPrioridadString(Context context){
        return context.getResources().getStringArray(R.array.prioridades_array)[prioridad];
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
