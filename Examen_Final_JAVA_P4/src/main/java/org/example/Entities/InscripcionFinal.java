package org.example.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "InscripcionFinal")
public class InscripcionFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;

    @ManyToOne
    @JoinColumn(name = "idAlumno", nullable = false)
    private Alumno alumno;
    private String materia;
    private LocalDate fecha;
    private int nota;
    private String estado;

    public InscripcionFinal(Alumno alumno, String materia, LocalDate fecha, int nota, String estado) {
        this.alumno=alumno;
        this.materia=materia;
        this.fecha=fecha;
        this.nota=nota;
        this.estado=estado;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public InscripcionFinal() {
    }

    public InscripcionFinal(Alumno alumno, LocalDate fecha, String materia, int nota, String estado) {
        this.alumno = alumno;
        this.fecha = fecha;
        this.materia= materia;
        this.nota=nota;
        this.estado = estado;
    }
}
