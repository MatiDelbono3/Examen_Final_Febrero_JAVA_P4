package org.example.DTO;
import java.util.List;

public class DtoReporteInscripciones {
    private List<EstadisticaAlumno> inscripcionesPorAlumno;
    private List<EstadisticaEstado> inscripcionesPorEstado;

    public DtoReporteInscripciones(List<EstadisticaAlumno> inscripcionesPorAlumno, List<EstadisticaEstado> inscripcionesPorEstado) {
        this.inscripcionesPorAlumno = inscripcionesPorAlumno;
        this.inscripcionesPorEstado = inscripcionesPorEstado;
    }

    public List<EstadisticaAlumno> getInscripcionesPorAlumno() {
        return inscripcionesPorAlumno;
    }

    public void setInscripcionesPorAlumno(List<EstadisticaAlumno> inscripcionesPorAlumno) {
        this.inscripcionesPorAlumno= inscripcionesPorAlumno;
    }

    public List<EstadisticaEstado> getInscripcionesPorEstado() {
        return inscripcionesPorEstado;
    }

    public void setTurnosPorEstado(List<EstadisticaEstado> inscripcionesPorEstado) {
        this.inscripcionesPorEstado = inscripcionesPorEstado;
    }

    public static class EstadisticaAlumno {
        private int idAlumno;
        private long cantidad;

        public EstadisticaAlumno(int idAlumno, long cantidad) {
            this.idAlumno = idAlumno;
            this.cantidad = cantidad;
        }

        public int getidAlumno() {
            return idAlumno;
        }

        public void setIdAlumno(int idAlumno) {
            this.idAlumno = idAlumno;
        }

        public long getCantidad() {
            return cantidad;
        }

        public void setCantidad(long cantidad) {
            this.cantidad = cantidad;
        }
    }

    public static class EstadisticaEstado {
        private String estado;
        private long cantidad;

        public EstadisticaEstado(String estado, long cantidad) {
            this.estado = estado;
            this.cantidad = cantidad;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public long getCantidad() {
            return cantidad;
        }

        public void setCantidad(long cantidad) {
            this.cantidad = cantidad;
        }
    }
}
