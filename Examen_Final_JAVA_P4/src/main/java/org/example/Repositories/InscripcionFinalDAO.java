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
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (idAlumno !=0 ){
                predicates.add(builder.equal(root.get("idAlumno"), idAlumno));
                criteria.orderBy(builder.desc(root.get("fecha")));
                criteria.select(root);

            }
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
    public List<Object[]>ObtenerPromedioNotaPorMateria(){
        try (Session session = HibernateUtils.getSession()) {
            CriteriaBuilder builder =  session.getCriteriaBuilder();
            CriteriaQuery<Object[]>cq= builder.createQuery(Object[].class);
            Root<InscripcionFinal> root = cq.from(InscripcionFinal.class);
            cq.multiselect(
                            root.get("materia"),
                            builder.avg(root.get("nota"))
                    )
                    .groupBy(root.get("materia"));

            return session.createQuery(cq).getResultList();
    }

}
    public List<DtoReporteInscripciones.EstadisticaAlumno>
    obtenerEstadisticaPorAlumno() {

        try (Session session = HibernateUtils.getSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DtoReporteInscripciones.EstadisticaAlumno> cq =
                    builder.createQuery(DtoReporteInscripciones.EstadisticaAlumno.class);

            Root<InscripcionFinal> root = cq.from(InscripcionFinal.class);

            cq.select(
                    builder.construct(
                            DtoReporteInscripciones.EstadisticaAlumno.class,
                            root.get("alumno").get("idAlumno"),
                            builder.count(root)
                    )
            );

            cq.groupBy(root.get("alumno").get("idAlumno"));
            return session.createQuery(cq).getResultList();
        }

}
    public List<DtoReporteInscripciones.EstadisticaEstado>
    obtenerEstadisticaPorEstado() {

        try (Session session = HibernateUtils.getSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DtoReporteInscripciones.EstadisticaEstado> cq =
                    builder.createQuery(DtoReporteInscripciones.EstadisticaEstado.class);

            Root<InscripcionFinal> root = cq.from(InscripcionFinal.class);

            cq.select(
                    builder.construct(
                            DtoReporteInscripciones.EstadisticaEstado.class,
                            root.get("estado"),
                            builder.count(root)
                    )
            );

            cq.groupBy(root.get("estado"));
            return session.createQuery(cq).getResultList();
}
    }
}