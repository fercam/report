package ar.com.fercam.jreportrest;

import spark.Filter;
import spark.Request;
import spark.Response;

/**
 *
 * @author David Perez <davidosvaldoperez@gmail.com>
 */
public class FiltroCors implements Filter {

    @Override
    public void handle(Request request, Response response) throws Exception {
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Methods", request.headers("Access-Control-Request-Methods"));
        response.header("Access-Control-Allow-Headers", request.headers("Access-Control-Request-Headers"));
        response.header("Access-Control-Max-Age", "86400"); //24 horas
    }

}
