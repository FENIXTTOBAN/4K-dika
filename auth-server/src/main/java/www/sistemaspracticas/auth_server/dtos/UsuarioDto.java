package www.sistemaspracticas.auth_server.dtos;

public class UsuarioDto {
    private String username;
    private String password;
    private String rol;

    public UsuarioDto() {
    }

    public UsuarioDto(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        private String username;
        private String password;
        private String rol;

        public Builder username(String username) {
            this.username = username;
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

        public UsuarioDto build() {return new UsuarioDto(username, password, rol);}
    }

    //Método estático para el builder
    public static Builder builder() {return new Builder();}
}
