
package Principal;

import controlador.Controlador;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.Pasajero;
import vista.RegistrarPasajero;
import vista.Vista;

/**
 *
 * @author CAMILO
 */
public class Principal {

    public static void main(String[] args) {
        Vista v = new Vista();
        Pasajero e = new Pasajero();
        RegistrarPasajero r = new RegistrarPasajero();

        new Controlador(e, v, r);
        v.setTitle("El Avion");
        r.setTitle("Registrar Pasajero");
        r.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        v.setLocationRelativeTo(v);
        v.setVisible(true);
        v.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
