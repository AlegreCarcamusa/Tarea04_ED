/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Representa una reserva en el hotel.
 * Contiene información sobre el cliente, fechas de entrada y salida,
 * tipo de habitación, si requiere cama supletoria y el coste total de la reserva.
 * 
 * @author Ricardo de Toro Fresno
 */
public class Reserva {
    private static int contadorReservas = 0;
    private int codigoReserva;
    private Cliente cliente;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private TipoHabitacion tipoHabitacion;
    private boolean camaSupletoria;
    private double costeTotal;
    
    // Constantes fijas para el precio
    private static final double PRECIO_DOBLE = 50.0;
    private static final double PRECIO_SUITE = 100.0;
    private static final double RECARGO_CAMA_SUPLETORIA = 20.0;

    /**
     * Crea una nueva reserva para un cliente en el hotel.
     * 
     * @param cliente Cliente que realiza la reserva.
     * @param fechaEntrada Fecha de entrada al hotel.
     * @param fechaSalida Fecha de salida del hotel.
     * @param tipoHabitacion Tipo de habitación seleccionada.
     * @param camaSupletoria Indica si se requiere cama supletoria.
     * @throws Exception Si la fecha de salida no es posterior a la de entrada.
     */
    public Reserva(Cliente cliente, LocalDate fechaEntrada, LocalDate fechaSalida,
                   TipoHabitacion tipoHabitacion, boolean camaSupletoria) throws Exception {
        if (!fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.codigoReserva = obtenerCodigoReserva();
        this.cliente = cliente;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.tipoHabitacion = tipoHabitacion;
        this.camaSupletoria = camaSupletoria;
        this.costeTotal = calcularCosteTotal();
    }

    /**
     * Genera un nuevo código de reserva incremental.
     * 
     * @return Código de la reserva.
     */
    private static int obtenerCodigoReserva() {
        contadorReservas++;
        return contadorReservas;
    }

    /**
     * Calcula el coste total de la reserva en función del número de noches,
     * el tipo de habitación y si se requiere cama supletoria.
     * Aplica un descuento del 10% si la estancia es mayor a 7 noches.
     * 
     * @return Coste total de la reserva.
     */
    public double calcularCosteTotal() {
        long noches = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        double precioNoche = (tipoHabitacion == TipoHabitacion.DOBLE) ? PRECIO_DOBLE : PRECIO_SUITE;
        
        if (camaSupletoria) {
            precioNoche += RECARGO_CAMA_SUPLETORIA;
        }
        double total = noches * precioNoche;
        if (noches > 7) {
            total *= 0.9; // Aplica un descuento del 10%
        }
        return total;
    }

    /**
     * Devuelve una representación en texto de los detalles de la reserva.
     * 
     * @return Información de la reserva.
     */
    public String mostrarDetalles() {
        return "Código Reserva: " + codigoReserva + "\n" +
               "Cliente: " + cliente.mostrarInformacion() + "\n" +
               "Fecha de Entrada: " + fechaEntrada + "\n" +
               "Fecha de Salida: " + fechaSalida + "\n" +
               "Tipo de Habitación: " + tipoHabitacion + "\n" +
               "Cama Supletoria: " + (camaSupletoria ? "Sí" : "No") + "\n" +
               "Coste Total: " + costeTotal + " Euros";
    }

    public int getCodigoReserva() {
        return codigoReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public boolean isCamaSupletoria() {
        return camaSupletoria;
    }

    public double getCosteTotal() {
        return costeTotal;
    }
    
    /**
     * Establece la fecha de entrada de la reserva y recalcula el coste total.
     * 
     * @param fechaEntrada Nueva fecha de entrada.
     * @throws Exception Si la fecha de salida es anterior a la de entrada.
     */
    public void setFechaEntrada(LocalDate fechaEntrada) throws Exception {
        if (fechaSalida != null && !fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.fechaEntrada = fechaEntrada;
        this.costeTotal = calcularCosteTotal();
    }

    /**
     * Establece la fecha de salida de la reserva y recalcula el coste total.
     * 
     * @param fechaSalida Nueva fecha de salida.
     * @throws Exception Si la fecha de salida no es posterior a la de entrada.
     */
    public void setFechaSalida(LocalDate fechaSalida) throws Exception {
        if (fechaEntrada != null && !fechaSalida.isAfter(fechaEntrada)) {
            throw new Exception("La fecha de salida debe ser posterior a la de entrada.");
        }
        this.fechaSalida = fechaSalida;
        this.costeTotal = calcularCosteTotal();
    }
}
