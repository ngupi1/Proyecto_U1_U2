package controller;

import java.util.Arrays;
import java.util.List;

public class CatalogoController
{

    public List<String> obtenerCarrera()
    {
        return Arrays.asList("Seleccione una carrera",
                "Ing. Sistemas Computacionales",
                "Ing. Quimica", "Ing. Industrial",
                "Ing. Fieston",
                "Ing. Logistica");
    }

    public List<String> obtenerMaterias()
    {
        return Arrays.asList(
                "Matematicas discretas",
                "Fundamentos de programacion",
                "Fisica general",
                "Inglés I",
                "Química",
                "Cálculo diferencial",
                "Taller de ética");
    }

    public List<String> obtenerSemestres()
    {
        return Arrays.asList("Seleccione un semestre",
                "Primero",
                "Segundo",
                "Tercero",
                "Cuarto",
                "Quinto",
                "Sexto",
                "Séptimo",
                "Octavo",
                "Noveno",
                "Décimo",
                "Onceavo",
                "Doceavo");
    }
}
