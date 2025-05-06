package postulaciones.ms.posppp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "postulacion")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idEstudiante;
    private Long idOferta;

        @Enumerated(EnumType.STRING)
        private Estado estado = Estado.PENDIENTE;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate fechaPostulacion;

        private String descripcion;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getIdEstudiante() {
            return idEstudiante;
        }

        public void setIdEstudiante(Long idEstudiante) {
            this.idEstudiante = idEstudiante;
        }

        public Long getIdOferta() {
            return idOferta;
        }

        public void setIdOferta(Long idOferta) {
            this.idOferta = idOferta;
        }

        public Estado getEstado() {
            return estado;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }

        public LocalDate getFechaPostulacion() {
            return fechaPostulacion;
        }

        public void setFechaPostulacion(LocalDate fechaPostulacion) {
            this.fechaPostulacion = fechaPostulacion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        @Override
        public String toString() {
            return "Postulacion{" +
                    "id=" + id +
                    ", idEstudiante=" + idEstudiante +
                    ", idOferta=" + idOferta +
                    ", estado=" + estado +
                    ", fechaPostulacion=" + fechaPostulacion +
                    ", descripcion='" + descripcion + '\'' +
                    '}';
        }


}
