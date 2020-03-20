package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.Pasajero;
import vista.RegistrarPasajero;
import vista.Vista;
import modelo.Avion;
import modelo.Clase;
import modelo.Silla;
import modelo.Ubicacion;
import vista.datosPasajero;

/**
 *
 * @author CAMILO
 */
public class Controlador implements ActionListener {
    
    private final Pasajero pasajero;
    private final Vista vista;
    private final RegistrarPasajero registrar;
    private datosPasajero datos;
    private Avion avion;
    Silla silla = new Silla();

    public Controlador(Pasajero pasajero, Vista vista, RegistrarPasajero registrar) {
        this.pasajero = pasajero;
        this.vista = vista;
        this.registrar = registrar;

        actionListener(this);
    }

    private void actionListener(ActionListener controlador) {

        vista.btnregistrar.addActionListener(controlador);
        vista.btnporcentaje.addActionListener(controlador);
        vista.btneliminar.addActionListener(controlador);
        vista.btnopcion1.addActionListener(controlador);
        vista.btnbuscar.addActionListener(controlador);
        vista.btnopcion2.addActionListener(controlador);

        registrar.btnregistrar.addActionListener(controlador);
        registrar.btncancelar.addActionListener(controlador);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        try {
            if (evento.getActionCommand().contentEquals("Registrar Pasajero")) {
                RegistrarPasajero registrarp = new RegistrarPasajero();
                registrarp.setTitle("Registrar Pasajero");
                registrarp.setVisible(true);
                try {
                    if (evento.getActionCommand().contentEquals("Registrar")) {

                        String nombre = this.registrar.txtnombre.getText();
                        String cedula = this.registrar.txtnombre.getText();

                        pasajero.setNombre(nombre);
                        pasajero.setCedula(cedula);

                        registrarp.txtnombre.setText("");
                        registrarp.txtcedula.setText("");

                        //CLASE
                        RegistrarPasajero reg = new RegistrarPasajero();
                        String i = reg.boxclase.getSelectedItem().toString();
                        if (silla.getClase() == Clase.ECONOMICA) {
                            i = "ECONOMICA";
                        } else if (silla.getClase() == Clase.EJERCUTIVA) {
                            i = "EJECUTIVA";
                        }
                        
                           //UBICACION
                        String subicacion;
                        if (silla.getUbicacion() == Ubicacion.CENTRAL) {
                            subicacion = "Centro";
                        } else if (silla.getUbicacion() == Ubicacion.PASILLO) {
                            subicacion = "Pasillo";
                        } else {
                            subicacion = "Ventana";
                        }

                    } else if (evento.getActionCommand().contentEquals("Cancelar")) {
                        registrarp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
            if (evento.getActionCommand().contentEquals("Eliminar Pasajero")) {
                String cedula = JOptionPane.showInputDialog(null, "Ingrese el número de cédula", "Eliminar pasajero", JOptionPane.QUESTION_MESSAGE);
                if (cedula != null && !cedula.isEmpty()) {
                    Pasajero l = new Pasajero(cedula, "no importa");
                    if (!avion.desasignarSilla(l)) {
                        JOptionPane.showMessageDialog(null, "El pasajero no tenía silla asignada", "Eliminar pasajero", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "Error en el número de cédula.", "Eliminar pasajero", JOptionPane.ERROR_MESSAGE);
                }

            }
            if (evento.getActionCommand().contentEquals("Opcion 1")) {
                String respuesta = avion.metodo1();

                JOptionPane.showMessageDialog(null, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE);

            }
            if (evento.getActionCommand().contentEquals("Opcion 2")) {
                String respuesta = avion.metodo1();

                JOptionPane.showMessageDialog(null, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE);

            }
            if (evento.getActionCommand().contentEquals("Buscar Pasajero")) {
                String cedula = JOptionPane.showInputDialog(null, "Ingrese el número de cédula", "Buscar pasajero", JOptionPane.QUESTION_MESSAGE);
                if (cedula != null && !cedula.isEmpty()) {

                    Pasajero pa = new Pasajero(cedula, " no importa");
                    Silla sill = new Silla();
                    Avion av = new Avion();
                    sill = av.buscarPasajero(pa);

                    if (sill != null) {
                        RegistrarPasajero re = new RegistrarPasajero();
                        re.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "El pasajero no se encuentra registrado", "Buscar pasajero", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                 datos.setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
