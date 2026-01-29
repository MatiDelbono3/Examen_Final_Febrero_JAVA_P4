package org.example.Repositories;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import jakarta.persistence.criteria.Selection;
import org.example.Connections.HibernateUtils;
import org.example.DTO.DtoReporteInscripciones;
import org.example.Entities.Alumno;
import org.example.Entities.InscripcionFinal;
import org.hibernate.Session;




public class InscripcionFinalDAO {
    public void CrearInscripcion(InscripcionFinal inscripcionFinal) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.save(inscripcionFinal);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<InscripcionFinal> ObtenerInscripciones(int idAlumno) {
        try (Session session = HibernateUtils.getSession()) {
            CriteriaBuilder builder =  session.getCriteriaBuilder();
            CriteriaQuery<InscripcionFinal> criteria = builder.createQuery(InscripcionFinal.class);
            Root<InscripcionFinal> root = criteria.from(InscripcionFinal.class);
            criteria.orderBy(builder.desc(root.get("fecha")));
            criteria.select(root);
            return session.createQuery(criteria).getResultList();
        }
    }
    public void ActualizarNotaInscripcion(Integer idInscripcion, int nota) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            InscripcionFinal inscripcion = session.get(InscripcionFinal.class, idInscripcion);
            if (inscripcion != null) {
                inscripcion.setNota(nota);
                session.update(inscripcion);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Object>ObtenerPromedioNotaPorMateria(List<InscripcionFinal>Inscripciones){
        try (Session session = HibernateUtils.getSession()) {
            CriteriaBuilder builder =  session.getCriteriaBuilder();
            CriteriaQuery<Object[]>cq= builder.createQuery(Object[].class);
            List<Object>Resultados= Collections.singletonList(builder.createQuery().getResultType());
            return Resultados;
    }

}
    public DtoReporteInscripciones generarReporteInscripciones() {
        CompletableFuture<List<DtoReporteInscripciones.EstadisticaAlumno>> inscripcionesPorAlumnoFuture = CompletableFuture.supplyAsync(() -> {
            try (Session session = HibernateUtils.getSession()) {
                return session.createQuery(
                                "SELECT new org.example.DTO.DtoReporteInscripciones.EstadisticaAlumno(i.IdAlumno, COUNT(i)) " +
                                        "FROM Inscripciones i GROUP BY i.IdAlumno", DtoReporteInscripciones.EstadisticaAlumno.class)
                        .list();
            }
        });

        CompletableFuture<List<DtoReporteInscripciones.EstadisticaEstado>> inscripcionesPorEstadoFuture = CompletableFuture.supplyAsync(() -> {
            try (Session session = HibernateUtils.getSession()) {
                return session.createQuery(
                                "SELECT new org.example.DTO.DtoReporteInscripciones.EstadisticaEstado(i.estado, COUNT(i)) " +
                                        "FROM Inscripciones i GROUP BY i.estado", DtoReporteInscripciones.EstadisticaEstado.class)
                        .list();
            }
        });

        CompletableFuture.allOf(inscripcionesPorAlumnoFuture, inscripcionesPorEstadoFuture).join();

        try {
            List<DtoReporteInscripciones.EstadisticaAlumno> inscripcionesPorAlumno = inscripcionesPorAlumnoFuture.get();
            List<DtoReporteInscripciones.EstadisticaEstado> inscripcionesPorEstado = inscripcionesPorEstadoFuture.get();
            return new DtoReporteInscripciones(inscripcionesPorAlumno, inscripcionesPorEstado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}