package controller;

import java.util.ArrayList;
import java.util.List;
import model.Alumno;

public class AlumnoController
{

    private List<Alumno> alumnos = new ArrayList<>();
//------------------------------------------------------------------------------    

    public boolean agregaAlumno(Alumno alumno)
    {
        for (Alumno objAlumno : alumnos)
        {
            if (objAlumno.getNoControl().equals(alumno.getNoControl()))
            {
                return false;
            }
        }
        alumnos.add(alumno);
        return true;
    }
//------------------------------------------------------------------------------    

    public List<Alumno> consultaAlumnos()
    {
        return alumnos;
    }
//------------------------------------------------------------------------------    

    public Alumno consultaPorNumControlONombre(String busqueda)
    {
        for (Alumno objAlumno : alumnos)
        {
            if (objAlumno.getNoControl().equals(busqueda))
            {
                return objAlumno;
            }
            if (objAlumno.getNombre().equals(busqueda))
            {
                return objAlumno;
            }
        }
        return null;
    }
//------------------------------------------------------------------------------    

    public boolean actualizaAlumno(Alumno alumnoActualizado)
    {
        for (int i = 0; i < alumnos.size(); i++)
        {
            if (alumnos.get(i).getNoControl().equals(alumnoActualizado.getNoControl()))
            {
                alumnos.set(i, alumnoActualizado);
                return true;
            }
        }
        return false;
    }
//------------------------------------------------------------------------------    

    public boolean eliminaAlumno(Alumno alumnoEliminado)
    {
        for (int i = 0; i < alumnos.size(); i++)
        {
            if (alumnos.get(i).getNoControl().equals(alumnoEliminado.getNoControl()))
            {
                alumnos.remove(i);
                return true;
            }
        }
        return false;
    }
}
