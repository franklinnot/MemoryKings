

package logica;

import java.sql.Date;

public class Empleado extends Persona{
    
    int idEmpleado, idConsulta;
    char nivelPrivilegio;
    String cargo;
    float salario;
    byte[] fotoEmpleado, curriculum;
    Date fechaContrato, fechaVencimientoContrato;
    
    public Empleado(){
    
    }
    
    public Empleado(int idEmpleado, int idConsulta,char nivelPrivilegio, String cargo, float salario, byte[] fotoEmpleado, byte[] curriculum, Date fechaContrato, Date fechaVencimientoContrato) {
        this.idEmpleado = idEmpleado;
        this.idConsulta = idConsulta;
        this.nivelPrivilegio = nivelPrivilegio;
        this.cargo = cargo;
        this.salario = salario;
        this.fotoEmpleado = fotoEmpleado;
        this.curriculum = curriculum;
        this.fechaContrato = fechaContrato;
        this.fechaVencimientoContrato = fechaVencimientoContrato;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    
    public char getNivelPrivilegio() {
        return nivelPrivilegio;
    }

    public void setNivelPrivilegio(char nivelPrivilegio) {
        this.nivelPrivilegio = nivelPrivilegio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public byte[] getFotoEmpleado() {
        return fotoEmpleado;
    }

    public void setFotoEmpleado(byte[] fotoEmpleado) {
        this.fotoEmpleado = fotoEmpleado;
    }

    public byte[] getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(byte[] curriculum) {
        this.curriculum = curriculum;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Date getFechaVencimientoContrato() {
        return fechaVencimientoContrato;
    }

    public void setFechaVencimientoContrato(Date fechaVencimientoContrato) {
        this.fechaVencimientoContrato = fechaVencimientoContrato;
    }
    
    
}
