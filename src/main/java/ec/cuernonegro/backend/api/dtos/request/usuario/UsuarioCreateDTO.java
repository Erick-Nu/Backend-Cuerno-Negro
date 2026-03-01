package ec.cuernonegro.backend.api.dtos.request.usuario;

public class UsuarioCreateDTO {

    private String name;
    private String phone;
    private String mail;
    private String password;

    public UsuarioCreateDTO() {}

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }


    public String getPhone() { return this.phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getMail() { return this.mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    public void validate() throws Exception {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new Exception("El nombre es requerido");
        }
        if (this.phone == null || this.phone.trim().isEmpty()) {
            throw new Exception("El teléfono es requerido");
        }
        if (this.mail == null || this.mail.trim().isEmpty()) {
            throw new Exception("El correo es requerido");
        }
        if (this.password == null || this.password.trim().isEmpty()) {
            throw new Exception("La contraseña es requerida");
        }
    }

}
