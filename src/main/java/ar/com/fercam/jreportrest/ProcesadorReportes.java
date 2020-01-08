package ar.com.fercam.jreportrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JasperRunManager;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author David Perez <davidosvaldoperez@gmail.com>
 */
public class ProcesadorReportes implements Route {

    private final ObjectMapper mapper;

    public ProcesadorReportes() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String nombreReporte = Aplicacion.getDirectorio() + request.params("nombre") + ".jasper";
        Map<String, Object> atributos = mapper.readValue(request.body(), Map.class);
        try ( Connection conexion = Aplicacion.getConexion()) {
            byte[] bytes = JasperRunManager.runReportToPdf(nombreReporte, atributos, conexion);
            response.type("application/pdf");
            return bytes;
        }
    }

}
