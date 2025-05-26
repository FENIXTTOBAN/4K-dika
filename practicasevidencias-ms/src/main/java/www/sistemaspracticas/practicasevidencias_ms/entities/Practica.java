package www.sistemaspracticas.practicasevidencias_ms.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="practica")
public class Practica {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;
    private Long idPostulacion;
    private Long idPersona;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoPractica estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Long idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoPractica getEstado() {
        return estado;
    }

    public void setEstado(EstadoPractica estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Practica{" +
                "id=" + id +
                ", idPostulacion=" + idPostulacion +
                ", idPersona=" + idPersona +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado=" + estado +
                '}';
    }
}
