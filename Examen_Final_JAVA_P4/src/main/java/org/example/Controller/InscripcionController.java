package org.example.Controller;
import org.example.Connections.HibernateUtils;
import org.hibernate.Session;

import org.example.DTO.DtoReporteInscripciones;
import org.example.Repositories.InscripcionFinalDAO;
import org.example.Services.InscripcionFinalService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/inscripciones")
public class InscripcionController {

    private final InscripcionFinalService inscripcionService;

    public InscripcionController() {

        InscripcionFinalDAO dao =
                new InscripcionFinalDAO();

        this.inscripcionService =
                new InscripcionFinalService(dao);
    }

    @GET
    @Path("/reporte")
    @Produces(MediaType.APPLICATION_JSON)
    public DtoReporteInscripciones generarReporteInscripciones() {
        return inscripcionService.generarReporteInscripciones();
    }
}