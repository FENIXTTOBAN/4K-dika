package www.sistemaspracticas.practicasevidencias_ms.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="evidencia")
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombreArchivo;
    private String descripcion;
    private String urlArchivo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaSubida;
    private Long idPractica;

    @Enumerated(EnumType.STRING)
    private EstadoEvidencia estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public Long getIdPractica() {
        return idPractica;
    }

    public void setIdPractica(Long idPractica) {
        this.idPractica = idPractica;
    }

    public EstadoEvidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoEvidencia estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Evidencia{" +
                "id=" + id +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", urlArchivo='" + urlArchivo + '\'' +
                ", fechaSubida=" + fechaSubida +
                ", idPractica=" + idPractica +
                ", estado=" + estado +
                '}';
    }
}
