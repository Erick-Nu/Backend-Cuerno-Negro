package ec.cuernonegro.backend.api.models.repository;
import ec.cuernonegro.backend.api.models.entity.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public interface UsuarioREP {
    // Here we will define the methods we can use with our users.
    int saveUser (Usuario user) throws SQLException;
    Usuario findUserByEmail(String email) throws SQLException;
    Usuario findUserById (int userid) throws SQLException;

    boolean updateUser (Usuario user) throws SQLException;
    String updateUserToken (int userid, String usertoken) throws SQLException;
    boolean existUserWithEmail (String email) throws SQLException;

}
