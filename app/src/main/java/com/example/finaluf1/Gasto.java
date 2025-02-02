package com.example.finaluf1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gastos")
public class Gasto {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private float cantidad;
    private String fecha;
    private String categoria;

    public Gasto(String nombre, float cantidad, String fecha, String categoria) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCategoria() {
        return categoria;
    }
}
