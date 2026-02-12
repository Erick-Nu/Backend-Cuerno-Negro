package ec.cuernonegro.backend.api.configurations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL =  "jdbc:postgresql://localhost:5434/db_cuervo_negro";
    private static final String USER = "dev";
    private static final String PASSWORD = "abc123";

    // Método para la conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el Driver de la base de datos" + e.getMessage());
        }
    }

    // Método para cerrar la conexión a la base de datos
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error crítico al intentar cerrar la conexión: " + e.getMessage());
        } finally {
            conn = null;
        }
    }

}
