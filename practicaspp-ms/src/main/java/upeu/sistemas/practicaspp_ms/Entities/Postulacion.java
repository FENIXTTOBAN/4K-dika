package upeu.sistemas.practicaspp_ms.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "postulacion")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;

    @Enumerated(EnumType.STRING)
    private EstadoPostulacion estado;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fecha_postulacion")
    private LocalDate fechaPostulacion;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;  // El estudiante que hace la postulación

    @ManyToOne
    @JoinColumn(name = "id_oferta")
    private Oferta oferta;  // La oferta de práctica a la que se postula

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public EstadoPostulacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPostulacion estado) {
        this.estado = estado;
    }

    public LocalDate getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDate fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        return "Postulacion{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                ", estado=" + estado +
                ", fechaPostulacion=" + fechaPostulacion +
                ", persona=" + persona +
                ", oferta=" + oferta +
                '}';
    }
}
