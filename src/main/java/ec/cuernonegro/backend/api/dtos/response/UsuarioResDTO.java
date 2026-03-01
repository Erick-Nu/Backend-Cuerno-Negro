package ec.cuernonegro.backend.api.dtos.response;

public class UsuarioResDTO {

    private int id;
    private String name;
    private String last;
    private String email;
    private String img;

    public UsuarioResDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLast() { return last; }
    public void setLast(String last) { this.last = last; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

}
