package ofertas.ms.oferppp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ofertas")
public class OfertaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idEmpresa;
    private Long idAdmin;
    private String nombrePuesto;
    private String descripcionPuesto;
    private String requisitos;
    private String modalidad;

    @Enumerated(EnumType.STRING)
    private EstadoOferta estado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaPublicacion;

    @OneToOne(mappedBy = "oferta", cascade = CascadeType.ALL)
    private CupoEntity cupo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getDescripcionPuesto() {
        return descripcionPuesto;
    }

    public void setDescripcionPuesto(String descripcionPuesto) {
        this.descripcionPuesto = descripcionPuesto;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public EstadoOferta getEstado() {
        return estado;
    }

    public void setEstado(EstadoOferta estado) {
        this.estado = estado;
    }

    public CupoEntity getCupo() {
        return cupo;
    }

    public void setCupo(CupoEntity cupo) {
        this.cupo = cupo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "OfertaEntity{" +
                "id=" + id +
                ", idEmpresa=" + idEmpresa +
                ", idAdmin=" + idAdmin +
                ", nombrePuesto='" + nombrePuesto + '\'' +
                ", descripcionPuesto='" + descripcionPuesto + '\'' +
                ", requisitos='" + requisitos + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", estado=" + estado +
                ", fechaPublicacion=" + fechaPublicacion +
                ", cupo=" + cupo +
                '}';
    }
}
