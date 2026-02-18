package ec.cuernonegro.backend.api.models;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

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

    public Usuario(String username, String usermail, String userpass, String userrol) {
        this.username = username;
        this.usermail = usermail;
        this.userpass = userpass;
        this.userrol = userrol;
        this.userfchcre = LocalDateTime.now();
    }

    public Usuario(int userid, String username, String usermail, String userpass, String usertypeident, String usernumberident, String userphone, String userrol, String useravatar, String usertoken, LocalDateTime userfchcre) {
        this.userid = userid;
        this.username = username;
        this.usermail = usermail;
        this.userpass = userpass;
        this.usertypeident = usertypeident;
        this.usernumberident = usernumberident;
        this.userphone = userphone;
        this.userrol = userrol;
        this.useravatar = useravatar;
        this.usertoken = usertoken;
        this.userfchcre = userfchcre;
    }

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


    /// Method One: This method helps you to encrypt passwords before saving them in database for security
    public String encryptPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) return null;
        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    /// Method Two: This method helps you to verify if the password whether it comes from the database is correct
    public boolean verifyPassword(String plainPassword) {
        if (this.userpass == null) return false;
        return new BCryptPasswordEncoder().matches(plainPassword, this.userpass);
    }

    ///  Method Three: This method helps you to utilize an image as avatar for the user, for default.
    public String getDefaultAvatar() {
        return "https://i.pinimg.com/236x/31/ec/2c/31ec2ce212492e600b8de27f38846ed7.jpg";
    }

    /**
     * Method Four: This method helps you to create a token for the user
     * @param secretKey Obtained from application.properties
     */
    public String generateToken(String secretKey) {
        final long expirationMillis = 31536000000L;
        final long currentMillis = System.currentTimeMillis();

        String token = Jwts.builder()
                .setSubject(this.usermail)
                .setIssuedAt(new Date(currentMillis))
                .setExpiration(new Date(currentMillis + expirationMillis))
                .claim("userid", this.userid)
                .claim("userrole", this.userrol)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        this.usertoken = token;
        return token;
    }

    /// Method Five: This method helps you to encoding the user's token
    public static Usuario decodeUserToken(String token, String secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            Usuario user = new Usuario();
            user.setUserid(claims.get("userid", Integer.class));
            user.setUsermail(claims.getSubject());
            user.setUserrol(claims.get("userrole", String.class));
            user.setUsertoken(token);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}