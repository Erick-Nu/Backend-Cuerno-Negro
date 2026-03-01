package ec.cuernonegro.backend.api.dtos.request.usuario;

public class UsuarioCreateDTO {

    private String name;
    private String last;
    private String mail;
    private String password;

    public UsuarioCreateDTO() {}

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getLast() { return this.last; }
    public void setLast(String last) { this.last = last; }

    public String getMail() { return this.mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

}
