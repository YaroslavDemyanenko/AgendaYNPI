package com.example.agendaynpi.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.agendaynpi.BaseSQLite.SQLiteHelper;

import java.io.Serializable;
import java.util.Date;

public class Tarea implements Serializable {
    private String nombre, descri, coste, prioridad,fecha;
    private boolean estaHecha,paraBorrar;

    public Tarea(String nombre, String descri, String fecha, String coste, String prioridad, int estaHecha, Context contexto) {
        this.nombre = nombre;
        this.descri = descri;
        this.fecha = fecha;
        this.coste = coste;
        this.prioridad = prioridad;
        if (estaHecha == 1){
            this.estaHecha=true;
        }else{
            this.estaHecha=false;
        }
        this.paraBorrar=false;
    }

    public boolean guardarTarea(Context context){
        SQLiteHelper admin = new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("descripcion", descri);
        registro.put("coste", coste);
        registro.put("fecha", fecha);
        registro.put("prioridad", prioridad);
        registro.put("tareaHecha",0);

        bd.insert("tareas", null, registro);
        bd.close();
        return true;
    }

    public boolean borrarTarea(Context context){
        SQLiteHelper admin=new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();

        int cant=bd.delete("tareas", "nombre="+this.nombre+" and descripcion="+this.descri,null);
        bd.close();
        return cant>0;
    }

    public boolean TODOUPDATE(Context context){
        SQLiteHelper admin=new SQLiteHelper(context, "tareas", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();

        int cant=bd.delete("tareas", "nombre="+this.nombre+" and descripcion="+this.descri,null);
        bd.close();
        return cant>0;
    }

    public boolean isParaBorrar() {
        return paraBorrar;
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

    public String getPrioridad() {
        return prioridad;
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

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEstaHecha(boolean estaHecha) {
        this.estaHecha = estaHecha;
    }

}
