package www.sistemaspracticas.auth_server.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_info")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String password;
    private String rol; // ROLES: ESTUDIANTE, PRACTICADOR

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String usuario, String password, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public static class Builder {
        private Long id;
        private String usuario;
        private String password;
        private String rol;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder usuario(String usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder rol(String rol) {
            this.rol = rol;
            return this;
        }


        public UsuarioEntity build() {
            return new UsuarioEntity(id, usuario, password, rol);
        }
    }

    // Método estático para el builder
    public static Builder builder() {return new Builder();}
}
