package model;

import java.util.Date;
import java.util.List;

public class Alumno
{   
    private String noControl;
    private String nombre;
    private String correo;
    private String carrera;
    private String semestre;
    private double promedio;
    private Date fechaNacimiento;
    private List<String> materiasSeleccionadas;

    public Alumno(String no_control, String nombre, String correo, String carrera, String semestre, double promedio, Date fechaNacimiento, List<String> materiasSeleccionadas)
    {
        this.noControl = no_control;
        this.nombre = nombre;
        this.correo = correo;
        this.carrera = carrera;
        this.semestre = semestre;
        this.promedio = promedio;
        this.fechaNacimiento = fechaNacimiento;
        this.materiasSeleccionadas = materiasSeleccionadas;
    }
//------------------------------------------------------------------------------
    public String getNoControl()
    {
        return noControl;
    }

    public void setNoControl(String noControl)
    {
        this.noControl = noControl;
    }
//------------------------------------------------------------------------------
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
//------------------------------------------------------------------------------
    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }
//------------------------------------------------------------------------------
    public String getCarrera()
    {
        return carrera;
    }

    public void setCarrera(String carrera)
    {
        this.carrera = carrera;
    }
//------------------------------------------------------------------------------
    public String getSemestre()
    {
        return semestre;
    }

    public void setSemestre(String semestre)
    {
        this.semestre = semestre;
    }
//------------------------------------------------------------------------------
    public double getPromedio()
    {
        return promedio;
    }

    public void setPromedio(double promedio)
    {
        this.promedio = promedio;
    }
//------------------------------------------------------------------------------
    public List<String> getMateriasSeleccionadas()
    {
        return materiasSeleccionadas;
    }
    public void setMateriasSeleccionadas(List<String> materiasSeleccionadas)
    {
        this.materiasSeleccionadas = materiasSeleccionadas;
    }
//------------------------------------------------------------------------------
    public Date getFechaNacimiento()
    {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fecha)
    {
        this.fechaNacimiento = fecha;
    }
//------------------------------------------------------------------------------

}
