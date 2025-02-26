/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

/**
 * Representa un cliente del hotel con un nombre, DNI y teléfono.
 * Cada cliente recibe un código único al ser creado.
 * 
 * @author Ricardo de Toro Fresno
 */
public class Cliente {
    private static int contadorClientes = 0;
    private int codigo;
    private String nombre;
    private String dni;
    private String telefono;

    /**
     * Constructor de la clase Cliente.
     * 
     * @param nombre Nombre del cliente.
     * @param dni DNI del cliente (debe ser válido).
     * @param telefono Número de teléfono del cliente.
     * @throws Exception Si el DNI no es válido.
     */
    public Cliente(String nombre, String dni, String telefono) throws Exception {
        // Validación del DNI, si no es correcto no creará el objeto
        Utilidades.validarDNI(dni); 
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.codigo = obtenerNumeroCliente();
    }

    /**
     * Genera un número de cliente único.
     * 
     * @return Número único de cliente.
     */
    private static int obtenerNumeroCliente() {
        contadorClientes++;
        return contadorClientes;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el DNI del cliente.
     * 
     * @return DNI del cliente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del cliente.
     * 
     * @param dni Nuevo DNI del cliente (debe ser válido).
     * @throws Exception Si el DNI no es válido.
     */
    public void setDni(String dni) throws Exception {
        Utilidades.validarDNI(dni);
        this.dni = dni;
    }

    /**
     * Obtiene el teléfono del cliente.
     * 
     * @return Teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     * 
     * @param telefono Nuevo número de teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el código único del cliente.
     * 
     * @return Código del cliente.
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Muestra la información del cliente en formato de texto.
     * 
     * @return Cadena con los datos del cliente.
     */
    public String mostrarInformacion() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", DNI: " + dni + ", Teléfono: " + telefono;
    }
}
