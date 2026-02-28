package ec.cuernonegro.backend.api.models.repository;
import ec.cuernonegro.backend.api.configurations.ConexionDB;
import ec.cuernonegro.backend.api.models.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class UsuarioDAO implements UsuarioREP {
    // Implementation of the methods of the user interface

    private static final String SQL_INSERT_USER =
            "INSERT INTO usuarios (username, usermail, userpass, userrol, useravatar, usertoken, userfchcre) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public int saveUser (Usuario user) throws SQLException {

        Usuario userDB = null;
        int userid = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection conn = ConexionDB.getConnection()){

            ps = conn.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getUsermail());
            ps.setString(3, user.getUserpass());
            ps.setString(4, user.getUserrol());
            ps.setString(5, user.getUseravatar());
            ps.setString(6, user.getUsertoken());
            ps.setObject(7, user.getUserfchcre());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                userid = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el usuario en la base de datos", e);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return userid;
    }

    private static final String SQL_SELECT_USER_BY_EMAIL =
            "SELECT * FROM usuarios WHERE usermail = ?";

    @Override
    public Usuario findUserByEmail(String email) throws SQLException {

        Usuario userDB = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try(Connection conn = ConexionDB.getConnection()) {

            ps = conn.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                userDB = new Usuario();
                userDB.setUserid(rs.getInt("userid"));
                userDB.setUsername(rs.getString("username"));
                userDB.setUsermail(rs.getString("usermail"));
                userDB.setUserpass(rs.getString("userpass"));
                userDB.setUserrol(rs.getString("userrol"));
                userDB.setUseravatar(rs.getString("useravatar"));
                userDB.setUsertoken(rs.getString("usertoken"));
                userDB.setUserfchcre(rs.getTimestamp("userfchcre").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el usuario por email en la base de datos", e);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return userDB;
    }

    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT * FROM usuarios WHERE userid = ?";

    @Override
    public Usuario findUserById(int userid) throws SQLException {
        Usuario userDB = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try(Connection conn = ConexionDB.getConnection()){

            ps = conn.prepareStatement(SQL_SELECT_USER_BY_ID);
            ps.setInt(1, userid);
            rs = ps.executeQuery();

            if (rs.next()) {
                userDB = new Usuario();
                userDB.setUserid(rs.getInt("userid"));
                userDB.setUsername(rs.getString("username"));
                userDB.setUsermail(rs.getString("usermail"));
                userDB.setUserpass(rs.getString("userpass"));
                userDB.setUserrol(rs.getString("userrol"));
                userDB.setUseravatar(rs.getString("useravatar"));
                userDB.setUsertoken(rs.getString("usertoken"));
                userDB.setUserfchcre(rs.getTimestamp("userfchcre").toLocalDateTime());
            }
        } catch (Exception e){
            throw new RuntimeException("Error al buscar el usuario por ID en la base de datos", e);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return userDB;
    }

    @Override
    public boolean updateUser(Usuario user) throws SQLException {
        return false;
    }

    @Override
    public String updateUserToken(int userid, String usertoken) throws SQLException {
        return "";
    }

    @Override
    public boolean existUserWithEmail(String email) throws SQLException {
        return false;
    }

}
