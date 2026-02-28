package ec.cuernonegro.backend.api.models.entity;
import java.time.LocalDateTime;

public class Usuario {
    private int userid;
    private String username;
    private String usermail;
    private String userpass;
    private String usertypeident;
    private String usernumberident;
    private String userphone;
    private String userrol;
    private String useravatar;
    private String usertoken;
    private LocalDateTime userfchcre;

    public Usuario() {}

    public int getUserid() { return userid; }
    public void setUserid(int userid) { this.userid = userid; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getUsermail() { return usermail; }
    public void setUsermail(String usermail) { this.usermail = usermail; }

    public String getUserpass() { return userpass; }
    public void setUserpass(String userpass) { this.userpass = userpass; }

    public String getUsertypeident() { return usertypeident; }
    public void setUsertypeident(String usertypeident) { this.usertypeident = usertypeident; }

    public String getUsernumberident() { return usernumberident; }
    public void setUsernumberident(String usernumberident) { this.usernumberident = usernumberident; }

    public String getUserphone() { return userphone; }
    public void setUserphone(String userphone) { this.userphone = userphone; }

    public String getUserrol() { return userrol; }
    public void setUserrol(String userrol) { this.userrol = userrol; }

    public String getUseravatar() { return useravatar; }
    public void setUseravatar(String useravatar) { this.useravatar = useravatar; }

    public String getUsertoken() { return usertoken; }
    public void setUsertoken(String usertoken) { this.usertoken = usertoken; }

    public LocalDateTime getUserfchcre() { return userfchcre; }
    public void setUserfchcre(LocalDateTime userfchcre) { this.userfchcre = userfchcre; }

}