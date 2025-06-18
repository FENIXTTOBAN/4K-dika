package www.sistemaspracticas.ofertapostulacion_ms.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPersona;

    private String titulo;
    private String descripcion;
    private Boolean estado;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModalidadOferta modalidad;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    public ModalidadOferta getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadOferta modalidad) {
        this.modalidad = modalidad;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "id=" + id +
                ", idPersona=" + idPersona +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", modalidad=" + modalidad +
                ", empresa=" + empresa +
                '}';
    }
}
