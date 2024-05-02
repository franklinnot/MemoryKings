

package logica;

public class Empresa {
    String ruc;
    int telefono;
    String correo,dirrecion,nombre_Comercial,razon_Social;

    public Empresa() {
    }

    public Empresa(String ruc, int telefono, String correo, String dirrecion, String nombre_Comercial, String razon_Social) {
        this.ruc = ruc;
        this.telefono = telefono;
        this.correo = correo;
        this.dirrecion = dirrecion;
        this.nombre_Comercial = nombre_Comercial;
        this.razon_Social = razon_Social;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDirrecion() {
        return dirrecion;
    }

    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }

    public String getNombre_Comercial() {
        return nombre_Comercial;
    }

    public void setNombre_Comercial(String nombre_Comercial) {
        this.nombre_Comercial = nombre_Comercial;
    }

    public String getRazon_Social() {
        return razon_Social;
    }

    public void setRazon_Social(String razon_Social) {
        this.razon_Social = razon_Social;
    }
    
    
}
