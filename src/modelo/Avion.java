
package modelo;

/**
 *
 * @author CAMILO
 */
public class Avion {

    public final int EJECUTIVA = 10;
    public final int ECONOMICA = 50;
    private Silla[] sEjecutiva;
    private Silla[] sEcomica;

    public Avion() {

        Ubicacion ubicacion;
        sEjecutiva = new Silla[EJECUTIVA];
        sEcomica = new Silla[ECONOMICA];

        sEjecutiva[0] = new Silla(1, Clase.EJERCUTIVA, Ubicacion.CENTRAL);
        sEjecutiva[1] = new Silla(2, Clase.EJERCUTIVA, Ubicacion.CENTRAL);
        sEjecutiva[2] = new Silla(3, Clase.EJERCUTIVA, Ubicacion.PASILLO);
        sEjecutiva[3] = new Silla(4, Clase.EJERCUTIVA, Ubicacion.VENTANA);
        sEjecutiva[4] = new Silla(5, Clase.EJERCUTIVA, Ubicacion.VENTANA);
        sEjecutiva[5] = new Silla(6, Clase.EJERCUTIVA, Ubicacion.CENTRAL);
        sEjecutiva[6] = new Silla(7, Clase.EJERCUTIVA, Ubicacion.VENTANA);
        sEjecutiva[7] = new Silla(8, Clase.EJERCUTIVA, Ubicacion.PASILLO);
        sEjecutiva[8] = new Silla(9, Clase.EJERCUTIVA, Ubicacion.PASILLO);
        sEjecutiva[9] = new Silla(10, Clase.EJERCUTIVA, Ubicacion.CENTRAL);

        for (int nsilla = 1 + EJECUTIVA, j = 1; j <= ECONOMICA; nsilla++, j++) {

            if (j % 9 == 1 || j % 9 == 0) {
                ubicacion = Ubicacion.VENTANA;

            } else if (j % 6 == 2 || j % 6 == 5) {
                ubicacion = Ubicacion.CENTRAL;
                // Sillas pasillo
            } else {
                ubicacion = Ubicacion.PASILLO;
            }

            sEcomica[j - 1] = new Silla(nsilla, Clase.ECONOMICA, ubicacion);
        }

    }

    public Silla asiganarSilla(Clase clase, Ubicacion ubicacion, Pasajero pasajero) {

        Silla silla = null;
        if (clase == Clase.EJERCUTIVA) {
            silla = buscarSillaEjecutivaLibre(ubicacion);
        } else if (clase == Clase.ECONOMICA) {
            silla = buscarSillaEconomicaLibre(ubicacion);
        }
        if (silla != null) {
            silla.AsignarPasajero(pasajero);
        }
        return silla;

    }

    public Silla buscarSillaEjecutivaLibre(Ubicacion ubicacion) {

        boolean encontrado = false;
        Silla silla = null;
        for (int i = 0; i < EJECUTIVA && !encontrado; i++) {
            silla = sEjecutiva[i];
            if (!(silla.sillasAsignada()) && silla.getUbicacion() == ubicacion) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            silla = null;
        }
        return silla;
    }

    public Silla buscarSillaEconomicaLibre(Ubicacion ubicacion) {
        boolean encontrado = false;
        Silla silla = null;

        for (int i = 0; i < ECONOMICA && !encontrado; i++) {
            silla = sEcomica[i];
            if (!(silla.sillasAsignada()) && silla.getUbicacion() == ubicacion) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            silla = null;
        }
        return silla;
    }

    public Silla buscarPasajero(Pasajero Pasajero) {
        // Busca el pasajero en ejecutiva
        Silla silla = buscarPasajeroEjecutivo(Pasajero);
        // Si no estaba en ejecutiva
        if (null == silla) {
            // Busca en económica
            silla = buscarPasajeroEconomico(Pasajero);
        }

        return silla;

    }

    public Silla buscarPasajeroEjecutivo(Pasajero Pasajero) {
        boolean encontrado = false;
        Silla silla = null;
        for (int i = 0; i < EJECUTIVA && !encontrado; i++) {
            silla = sEjecutiva[i];
            if (silla.sillasAsignada() && silla.getPasajero().igualA(Pasajero)) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            silla = null;
        }
        return silla;
    }

    public Silla buscarPasajeroEconomico(Pasajero pPasajero) {
        boolean encontrado = false;
        Silla silla = null;
        for (int i = 0; i < ECONOMICA && !encontrado; i++) {
            silla = sEcomica[i];
            if (silla.sillasAsignada() && silla.getPasajero().igualA(pPasajero)) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            silla = null;
        }
        return silla;
    }

    public boolean desasignarSilla(Pasajero pPasajero) {
        // Busca el pasajero en el avión
        Silla silla = buscarPasajero(pPasajero);
        boolean resultado = false;
        // Si lo encuentra desasigna
        if (silla != null) {
            silla.desasignarSilla();
            resultado = true;
        }
        return resultado;
    }

    public int contarSillasEjecutivasOcupadas() {
        int contador = 0;
        for (Silla sillaEjecutiva : sEjecutiva) {
            if (sillaEjecutiva.sillasAsignada()) {
                contador++;
            }
        }
        return contador;
    }

    public int contarSillasEconomicasOcupadas() {
        int contador = 0;
        for (Silla sillaEconomica : sEcomica) {
            if (sillaEconomica.sillasAsignada()) {
                contador++;
            }
        }
        return contador;
    }

    public double calcularPorcentajeOcupacion() {
        double porcentaje;
        int totalSillas = ECONOMICA + EJECUTIVA;
        int sillasOcupadas = contarSillasEconomicasOcupadas() + contarSillasEjecutivasOcupadas();
        porcentaje = (double) sillasOcupadas / totalSillas * 100;
        return porcentaje;
    }

    public Silla[] obtenerSillasEjecutivas() {
        return sEjecutiva;
    }

    public Silla[] obtenerSillasEconomicas() {
        return sEcomica;
    }

    public String metodo1() {
        return "Respuesta 1";
    }

    public String metodo2() {
        return "Respuesta 2";
    }

    public Avion(Silla[] sEjecutiva, Silla[] sEcomica) {
        this.sEjecutiva = sEjecutiva;
        this.sEcomica = sEcomica;
    }

    public Silla[] getsEjecutiva() {
        return sEjecutiva;
    }

    public void setsEjecutiva(Silla[] sEjecutiva) {
        this.sEjecutiva = sEjecutiva;
    }

    public Silla[] getsEcomica() {
        return sEcomica;
    }

    public void setsEcomica(Silla[] sEcomica) {
        this.sEcomica = sEcomica;
    }
    
    
}
