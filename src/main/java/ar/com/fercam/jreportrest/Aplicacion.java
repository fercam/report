package ar.com.fercam.jreportrest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import spark.Spark;

/**
 *
 * @author David Perez <davidosvaldoperez@gmail.com>
 */
public class Aplicacion {

    private static Properties props;

    private static void leerArchivoProperties() throws IOException {
        props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try ( InputStream stream = loader.getResourceAsStream("application.properties")) {
            props.load(stream);
        }
    }

    public static Integer getPuerto() {
        return Integer.valueOf(props.getProperty("port"));
    }

    public static String getDirectorio() {
        return props.getProperty("path");
    }

    public static Connection getConexion() {
        String user = props.getProperty("user");
        String pass = props.getProperty("pass");
        String url = props.getProperty("url");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        leerArchivoProperties();
        try(Connection c = getConexion()){
            System.out.println("La conexion a la base de datos fue exitosa");
        }
        Spark.port(getPuerto());
        Spark.before("*", new FiltroCors());
        Spark.post("/reporte/:nombre", new ProcesadorReportes());
    }

}
