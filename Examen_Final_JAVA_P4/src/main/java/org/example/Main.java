package org.example;

import java.time.LocalDate;
import java.util.List;

import org.example.Entities.Alumno;
import org.example.Entities.InscripcionFinal;

import org.example.Repositories.InscripcionFinalDAO;
import org.example.Services.InscripcionFinalService;


public class Main {

    public static void main(String[] args) {

        Alumno alumno=new Alumno("Juan Bautista Dutto", 43841259, true);
        InscripcionFinalService inscripcionService = new InscripcionFinalService(new InscripcionFinalDAO());


        try {
            InscripcionFinal nuevaInscripcion = new InscripcionFinal( alumno, "Ingenieria de Software",  LocalDate.of(2025, 12, 3), 6, "APROBADO");
            inscripcionService.CrearInscripcion(nuevaInscripcion);
            System.out.println("Inscripcion creada con éxito");

        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear inscripcion: " + e.getMessage());
        }


        try {
            System.out.println("Listado de inscripciones:");

            // Listar inscripciones del alumno
            List<InscripcionFinal> inscripciones =
                    inscripcionService.ObtenerInscripciones(alumno.getIdAlumno());

            inscripciones.forEach(i ->
                    System.out.println(i.getMateria() + " - " + i.getNota())
            );


        } catch (Exception e) {
            System.out.println("Error al obtener inscripciones: " + e.getMessage());
        }


        try {
            inscripcionService.ActualizarNota(1, 7);
            System.out.println("Nota actualizada con éxito");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al actualizar nota: " + e.getMessage());
        }
        // Promedio por materia
        inscripcionService.obtenerPromedioNotaPorMateria().forEach(obj ->
                System.out.println("Materia: " + obj[0] + " | Promedio: " + obj[1]));

    }
}