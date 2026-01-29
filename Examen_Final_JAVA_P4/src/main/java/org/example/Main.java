package org.example;

import java.time.LocalDate;

import org.example.Entities.Alumno;
import org.example.Entities.InscripcionFinal;

import org.example.Repositories.InscripcionFinalDAO;
import org.example.Services.InscripcionFinalService;


public class Main {

    public static void main(String[] args) {


        InscripcionFinalService inscripcionService = new InscripcionFinalService(new InscripcionFinalDAO());


        try {
            InscripcionFinal nuevaInscripcion = new InscripcionFinal( new Alumno("Juan Bautista Dutto", 43567809, true), "Ingenieria de Software",  LocalDate.of(2025, 12, 3), 6, "APROBADO");
            inscripcionService.CrearInscripcion(nuevaInscripcion);
            System.out.println("Inscripcion creada con éxito");

        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear inscripcion: " + e.getMessage());
        }


        try {
            System.out.println("Listado de inscripciones:");
            int idAlumno = 0;
            inscripcionService.ObtenerInscripciones(idAlumno).forEach(inscripcionFinal -> {
                System.out.println(inscripcionFinal.getAlumno() + " " + inscripcionFinal.getMateria());
            });
        } catch (Exception e) {
            System.out.println("Error al obtener pacientes: " + e.getMessage());
        }


        try {
            inscripcionService.ActualizarNota(1, 7);
            System.out.println("Paciente actualizado con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al actualizar paciente: " + e.getMessage());
        }
    }
}