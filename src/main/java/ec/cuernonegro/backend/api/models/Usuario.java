package ec.cuernonegro.backend.api.models;
import java.time.LocalDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    private LocalDateTime userfchcre;

    public Usuario() {}

    public Usuario(String username, String usermail, String userpass, String useravatar, String userrol, LocalDateTime userfchcre) {
        this.username = username;
        this.usermail = usermail;
        this.userpass = userpass;
        this.useravatar = useravatar;
        this.userrol = userrol;
        this.userfchcre = userfchcre;
    }

    public Usuario(int userid, String username, String usermail, String userpasss, String usertypeident, String usernumberident, String userphone,  String userrol, String useravatar, LocalDateTime userfchcre) {
        this.userid = userid;
        this.username = username;
        this.usermail = usermail;
        this.userpass = userpasss;
        this.usertypeident = usertypeident;
        this.usernumberident = usernumberident;
        this.userphone = userphone;
        this.userrol = userrol;
        this.useravatar = useravatar;
        this.userfchcre = userfchcre;
    }

    public int getUserid() { return userid; }

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

    public LocalDateTime getUserfchcre() { return userfchcre; }
    public void setUserfchcre(LocalDateTime userfchcre) {  this.userfchcre = userfchcre; }


    // Method One: This method helps you to encrypt passwords before saving them in database for security
    public String encryptPassword(String userpass) {
        if (userpass == null || userpass.trim().isEmpty()) { return null; }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(userpass);
    }

    // Method Two: This method helps you to verify if the password whether it comes from the database is correct
    public boolean verifyPassword(String userpass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(userpass, this.userpass);
    }

    // Method Three: This method helps you to utilize an image as avatar for the user, for default.
    public String getDefaultAvatar () {
        return "https://i.pinimg.com/236x/31/ec/2c/31ec2ce212492e600b8de27f38846ed7.jpg";
    }

}
