package ofertas.ms.oferppp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "cupos")
public class CupoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer entradas;
    private Integer salidas;
    private Integer balance;

    @OneToOne
    @JoinColumn(name = "id_oferta", referencedColumnName = "id")
    @JsonIgnore
    private OfertaEntity oferta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEntradas() {
        return entradas;
    }

    public void setEntradas(Integer entradas) {
        this.entradas = entradas;
    }

    public Integer getSalidas() {
        return salidas;
    }

    public void setSalidas(Integer salidas) {
        this.salidas = salidas;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public OfertaEntity getOferta() {
        return oferta;
    }

    public void setOferta(OfertaEntity oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        return "CupoEntity{" +
                "id=" + id +
                ", entradas=" + entradas +
                ", salidas=" + salidas +
                ", balance=" + balance +
                ", oferta=" + oferta +
                '}';
    }
}
