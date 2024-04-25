

package persistencia;
import java.sql.Date;

public class Conexion {
    
    Date fecha_conexion;
    String tipo_conexion;

    public Conexion(Date fecha_conexion, String tipo_conexion) {
        this.fecha_conexion = fecha_conexion;
        this.tipo_conexion = tipo_conexion;
    }
    
    public Conexion(){}

    public Date getFecha_conexion() {
        return fecha_conexion;
    }

    public void setFecha_conexion(Date fecha_conexion) {
        this.fecha_conexion = fecha_conexion;
    }
    
    
    
}
