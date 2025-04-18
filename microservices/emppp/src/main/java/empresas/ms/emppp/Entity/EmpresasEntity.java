package empresas.ms.emppp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Empresas")
public class EmpresasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ruc;
    private String direccion;
    private String pais;
    private String provincia;
    private String canton;

    private Long idSecretaria;

    public EmpresasEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public Long getIdSecretaria() {
        return idSecretaria;
    }

    public void setIdSecretaria(Long idSecretaria) {
        this.idSecretaria = idSecretaria;
    }

    @Override
    public String toString() {
        return "EmpresasEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ruc='" + ruc + '\'' +
                ", direccion='" + direccion + '\'' +
                ", pais='" + pais + '\'' +
                ", provincia='" + provincia + '\'' +
                ", canton='" + canton + '\'' +
                ", idSecretaria=" + idSecretaria +
                '}';
    }
}
