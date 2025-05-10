package upeu.sistemas.practicaspp_ms.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "evidencia")
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Solo 'id' como clave primaria

    private String nombreArchivo;
    private String urlArchivo;
    private String comentario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_subida")
    private LocalDate fechaSubida;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoEvidencia estado;

    @ManyToOne
    @JoinColumn(name = "id_practica")
    private Practica practica;

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

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDate fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public EstadoEvidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoEvidencia estado) {
        this.estado = estado;
    }

    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    @Override
    public String toString() {
        return "Evidencia{" +
                "id=" + id +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", urlArchivo='" + urlArchivo + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fechaSubida=" + fechaSubida +
                ", estado=" + estado +
                ", practica=" + practica +
                '}';
    }
}
