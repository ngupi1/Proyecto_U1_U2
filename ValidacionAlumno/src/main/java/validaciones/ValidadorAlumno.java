package validaciones;
 
import java.util.Collection;
import java.util.Date;

public class ValidadorAlumno
{

    private static final java.util.regex.Pattern NOMBRE
            = java.util.regex.Pattern.compile("^\\p{L}+(\\s\\p{L}+)*$");

    private static final java.util.regex.Pattern CORREO
            = java.util.regex.Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    //--------------------------------------------------------------------------    
    public static boolean esVacio(String texto)
    {
        return texto == null || texto.trim().isEmpty();
    }
    //------------------------------------------------------------------------------    
    public static boolean nombreValido(String nombre)
    {
        return nombre != null && NOMBRE.matcher(nombre.trim()).matches();
    }
    //--------------------------------------------------------------------------    
    public static boolean correoValido(String correo)
    {
        return correo != null && CORREO.matcher(correo.trim()).matches();
    }
    //--------------------------------------------------------------------------
    public static boolean comboBoxValido(Object seleccion)
    {
        if (seleccion == null)
        {
            return false;
        }

        String texto = seleccion.toString().trim().toLowerCase();
        // Evita opciones por defecto tipo "Seleccione", "Seleccionar", etc.
        return !texto.isEmpty() && !texto.contains("seleccionar") && !texto.contains("seleccione");
    }
    //--------------------------------------------------------------------------
    public static double promedioValido(String promedioTexto)
    {
        double promedio;
        try
        {
            promedio = Double.parseDouble(promedioTexto);
        } catch (NumberFormatException e)
        {
            return -1;
        }
        if (!Double.isFinite(promedio) || promedio < 0 || promedio > 10)
        {
            return -1;
        }
        return promedio;
    }
    //--------------------------------------------------------------------------
     public static boolean fechaValida(Date fecha)
    {
        if (fecha == null)
        {
            return false;
        }
        Date hoy = new Date();
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        try
        {
            Date fechaSinHora = formato.parse(formato.format(fecha));
            Date hoySinHora = formato.parse(formato.format(hoy));
            return !fechaSinHora.after(hoySinHora);
        } catch (java.text.ParseException e)
        {
            return false;
        }
    }
    //--------------------------------------------------------------------------
    public static boolean listaValida(Collection<?> lista)
    {
        return lista != null && !lista.isEmpty();
    }
}
