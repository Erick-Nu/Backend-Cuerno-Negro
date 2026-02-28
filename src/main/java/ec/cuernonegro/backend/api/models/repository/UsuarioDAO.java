package ec.cuernonegro.backend.api.models.repository;
import ec.cuernonegro.backend.api.configurations.ConexionDB;
import ec.cuernonegro.backend.api.models.entity.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

    @Override
    public Usuario findUserByEmail(String email) throws SQLException {
        return null;
    }

    @Override
    public Usuario findUserById(int userid) throws SQLException {
        return null;
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
