package www.sistemaspracticas.personas_ms.dtos;

import www.sistemaspracticas.personas_ms.entities.TipoPersona;

public class RegistroCompleto {
    // Datos para auth-server
    private String username;
    private String password;
    private String rol; // "ESTUDIANTE" o "PRACTICADOR"

    // Datos para personas-ms
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private TipoPersona tipoPersona;
    private Boolean estado;

    // Getters y setters manuales

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public TipoPersona getTipoPersona() { return tipoPersona; }
    public void setTipoPersona(TipoPersona tipoPersona) { this.tipoPersona = tipoPersona; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}

