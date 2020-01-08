package ar.com.fercam;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.junit.Test;

/**
 *
 * @author David Perez <davidosvaldoperez@gmail.com>
 */
public class JasperCompile {
    
    //@Test
    public void compilar() throws JRException{
        JasperCompileManager.compileReportToFile(
                "/home/jasper/report2.jrxml",
                "/home/jasper/report2.jasper"
        );
                
                
    }

}
