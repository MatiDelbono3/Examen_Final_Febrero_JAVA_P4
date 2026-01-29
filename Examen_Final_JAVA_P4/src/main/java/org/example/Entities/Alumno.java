package org.example.Entities;



import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlumno;

    private String nombre;
    private int DNI;
    private boolean Activo;
    public Alumno() {
    }

    public Alumno(String nombre, int DNI, boolean activo) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.Activo = activo;

    }
}

