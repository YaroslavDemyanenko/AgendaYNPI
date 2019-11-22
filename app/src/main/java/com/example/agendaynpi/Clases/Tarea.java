package com.example.agendaynpi.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.agendaynpi.BaseSQLite.SQLiteHelper;

import java.io.Serializable;

public class Tarea implements Serializable {
    private String nombre, descri, coste, fecha;
    private int prioridad;
    private boolean estaHecha, paraBorrar, paraModificar;

    public Tarea(String nombre, String descri, String fecha, String coste, int prioridad, int estaHecha, Context contexto) {
        this.nombre = nombre;
        this.descri = descri;
        this.fecha = fecha;
        this.coste = coste;
        if (prioridad>0 && prioridad<5){
            this.prioridad = prioridad;
        }else {
            this.prioridad = 1;
        }

        if (estaHecha == 1) {
            this.estaHecha = true;
        } else {
            this.estaHecha = false;
        }
        this.paraModificar = false;
    }

    public boolean guardarTarea(Context context) {
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("descripcion", descri);
        registro.put("coste", coste);
        registro.put("fecha", fecha);
        registro.put("prioridad", prioridad);
        registro.put("tareaHecha", 0);

        bd.insert("tareas", null, registro);
        bd.close();
        return true;
    }

    public boolean borrarTarea(Context context) {
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        int cant = bd.delete("tareas", "nombre=" + this.nombre + " and descripcion=" + this.descri, null);
        bd.close();
        return cant > 0;
    }

    public boolean actualizarTarea(Context context, String nuevoNombre,String nuevaDesc) {
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nuevoNombre);
        registro.put("descripcion", nuevaDesc);
        registro.put("coste", coste);
        registro.put("fecha", fecha);
        registro.put("prioridad", prioridad);
        if (estaHecha == true) {
            registro.put("tareaHecha", 1);
        } else {
            registro.put("tareaHecha", 0);
        }
        int cant = bd.update("tareas", registro,"nombre=" + this.nombre + " and descripcion=" + this.descri, null);
        this.nombre=nuevoNombre;
        this.descri=nuevaDesc;
        bd.close();
        return cant > 0;
    }

    public void setParaModificar(boolean paraModificar) {
        this.paraModificar = paraModificar;
    }

    public void setParaBorrar(boolean paraBorrar) {
        this.paraBorrar = paraBorrar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescri() {
        return descri;
    }

    public String getCoste() {
        return coste;
    }


    public String getFecha() {
        return fecha;
    }

    public boolean isEstaHecha() {
        return estaHecha;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public void setCoste(String coste) {
        this.coste = coste;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstaHecha(boolean estaHecha) {
        this.estaHecha = estaHecha;
    }

}
