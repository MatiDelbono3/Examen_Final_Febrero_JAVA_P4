package org.example.Services;

import java.util.List;

import org.example.Entities.InscripcionFinal;
import org.example.Repositories.InscripcionFinalDAO;

public class InscripcionFinalService {

    private InscripcionFinalDAO inscripcionFinalDAO;

    public InscripcionFinalService( InscripcionFinalDAO inscripcionFinalDAO)
    {
        this.inscripcionFinalDAO = inscripcionFinalDAO;
    }

    public void CrearInscripcion(InscripcionFinal inscripcionFinal) {

        if (inscripcionFinal.getMateria() == null || inscripcionFinal.getMateria().isEmpty()) {
            throw new IllegalArgumentException("La materia es obligatoria");
        }
        if (inscripcionFinal.getEstado() == null || inscripcionFinal.getEstado().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio");
        }
        inscripcionFinalDAO.CrearInscripcion(inscripcionFinal);
    }

    public List<InscripcionFinal> ObtenerInscripciones (int idAlumno) {
        return inscripcionFinalDAO.ObtenerInscripciones(idAlumno);
    }

    public void ActualizarNota(Integer idInscripcion, int Nota) {

        if (idInscripcion == null || idInscripcion <= 0) {
            throw new IllegalArgumentException("ID de paciente no válido");
        }


        if (Nota < 1 || Nota > 10 ) {
            throw new IllegalArgumentException("La nota debe estar entre 1 y 10");
        }
        inscripcionFinalDAO.ActualizarNotaInscripcion(idInscripcion, Nota);
    }
}