package app;

import controller.AlumnoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.VentanaInicio;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String args[]) {

        log.info("Iniciando el Sistema de Control Escolar...");
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());


            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button.arc", 10); // Botones ligeramente redondeados
            UIManager.put("TextComponent.arc", 5); // Cajas de texto redondeadas
        } catch (Exception ex) {
            System.err.println("Error al inicializar");
        }
        java.awt.EventQueue.invokeLater(()
                -> {
            AlumnoController controller = new AlumnoController();
            new VentanaInicio(controller).setVisible(true);
        });
    }
}
