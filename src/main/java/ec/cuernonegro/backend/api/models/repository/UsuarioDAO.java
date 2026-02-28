package ec.cuernonegro.backend.api.repository;
import ec.cuernonegro.backend.api.models.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public interface UsuarioDAO {
    // Here we will define the methods we can use with our users.
    boolean saveUser(Usuario user, Connection conn) throws SQLException;
    Usuario findUserByEmail(String email) throws SQLException;
    Usuario findUserById(int userid) throws SQLException;
    boolean updateUser (Usuario user) throws SQLException;
    String updateUserToken (int userid, String usertoken) throws SQLException;
    boolean existUserWithEmail(String email) throws SQLException;
}
