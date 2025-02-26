/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Clase de utilidad que proporciona métodos para validar DNI y 
 * validar rangos de fechas en el sistema de reservas del hotel.
 * 
 * @author Ricardo de Toro Fresno
 */
public class Utilidades {
  
	/**
     * Valida si un DNI es correcto en cuanto a su formato y letra correspondiente.
     * 
     * @param dni DNI a validar (8 números seguidos de 1 letra mayúscula).
     * @throws Exception Si el DNI no tiene el formato correcto o la letra no coincide.
     */
    public static void validarDNI(String dni) throws Exception {
        if (dni == null || dni.length() != 9) {
            throw new Exception("El DNI debe tener 9 caracteres (8 números y 1 letra)");
        }
        String numeros = dni.substring(0, 8);
        char letra = dni.charAt(8);
        if (!numeros.matches("\\d{8}") || !Character.isUpperCase(letra)) {
            throw new Exception("El formato del DNI es incorrecto. Debe contener 8 números seguidos de una letra.");
        }
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(numeros);
        int resto = numero % 23;
        char letraCorrecta = letras.charAt(resto);
        if (letra != letraCorrecta) {
            throw new Exception("La letra del DNI no es válida para el número proporcionado");
        }
    }

    /**
     * Solicita una fecha al usuario y la devuelve en formato LocalDate.
     * 
     * @param mensaje Mensaje mostrado al usuario para solicitar la fecha.
     * @return LocalDate con la fecha introducida por el usuario.
     */
    public static LocalDate leerFecha(String mensaje) {
        Scanner sc = new Scanner(System.in);
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.print(mensaje + " (formato yyyy-MM-dd): ");
                String input = sc.nextLine();
                fecha = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Por favor, introduzca la fecha en formato yyyy-MM-dd.");
            }
        }
        return fecha;
    }
    
    /**
     * Valida que una fecha de entrada no sea anterior a la actual y que la fecha 
     * de salida sea posterior a la de entrada.
     * 
     * @param fechaInicio Fecha de entrada.
     * @param fechaFin Fecha de salida.
     * @throws Exception Si la fecha de entrada es anterior a la actual o la 
     * fecha de salida no es posterior a la de entrada.
     */
    public static void validarFechas(LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        LocalDate hoy = LocalDate.now();
        if (fechaInicio.isBefore(hoy)) {
            throw new Exception("La fecha de entrada no puede ser anterior a la fecha actual.");
        }
        if (!fechaFin.isAfter(fechaInicio)) {
            throw new Exception("La fecha de salida debe ser posterior a la fecha de entrada.");
        }
    }
}
