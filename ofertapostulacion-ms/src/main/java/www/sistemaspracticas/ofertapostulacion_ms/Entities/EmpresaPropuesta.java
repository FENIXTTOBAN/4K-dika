package www.sistemaspracticas.ofertapostulacion_ms.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "empresa_propuesta")
public class EmpresaPropuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_estudiante")
    private Long EstudianteId;
    private String nombreEmpresa;
    private String documentoUrl;
    private Boolean aprobada = false;
    @Column(name = "id_practicador")
    private Long AprobadaPor;
    private LocalDate fechaPropuesta;
    private LocalDate fechaInicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return EstudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        EstudianteId = estudianteId;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDocumentoUrl() {
        return documentoUrl;
    }

    public void setDocumentoUrl(String documentoUrl) {
        this.documentoUrl = documentoUrl;
    }

    public Boolean getAprobada() {
        return aprobada;
    }

    public void setAprobada(Boolean aprobada) {
        this.aprobada = aprobada;
    }

    public Long getAprobadaPor() {
        return AprobadaPor;
    }

    public void setAprobadaPor(Long aprobadaPor) {
        AprobadaPor = aprobadaPor;
    }

    public LocalDate getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(LocalDate fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "EmpresaPropuesta{" +
                "id=" + id +
                ", EstudianteId=" + EstudianteId +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", documentoUrl='" + documentoUrl + '\'' +
                ", aprobada=" + aprobada +
                ", AprobadaPor=" + AprobadaPor +
                ", fechaPropuesta=" + fechaPropuesta +
                ", fechaInicio=" + fechaInicio +
                '}';
    }
}
