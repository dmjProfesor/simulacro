package controladores;

public class CineManager {

    // Capacidad actual de la sala 
    private int asientosDisponibles;

    public CineManager(int capacidadInicial) {
        this.asientosDisponibles = capacidadInicial;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    /**
     * Calcula el precio base de la entrada según el día y la edad.
     * REGLAS:
     * 1. Precio estándar: 10 euros.
     * 2. Día del Espectador ("miercoles"): Precio único de 5 euros para todos.
     * 3. Jubilados (65 años o más): Precio 6 euros (salvo en miércoles, que es 5).
     * 4. Niños (menos de 10 años): Gratis (0 euros).
     */
    public double calcularPrecioBase(String dia, int edad) {
        double precio = 10.0;

        if (dia == "miercoles") { 
            return 5.0;
        }

        if (edad < 10) {
            return 0.0;
        }

        if (edad > 65) { 
            precio = 6.0;
        }

        return precio;
    }

    /**
     * Aplica descuentos extra sobre el precio base.
     * REGLAS:
     * 1. Si es estudiante: 10% de descuento adicional.
     * 2. Si tiene tarjeta socio: 20% de descuento adicional.
     * 3. Los descuentos NO se acumulan. Si tiene ambos, se aplica el mejor (20%).
     */
    public double aplicarDescuentos(double precioBase, boolean esEstudiante, boolean esSocio) {
        
        if (precioBase == 0) return 0;

        if (esSocio) {
            return precioBase * 0.80; 
        } 
        
        if (esEstudiante) {
                    return precioBase * 0.10; 
        }

        return precioBase;
    }

    /**
     * Intenta reservar una cantidad de asientos.
     * REGLAS:
     * 1. Si hay hueco, resta los asientos y retorna true.
     * 2. Si NO hay hueco suficiente, no hace nada y retorna false.
     */
    public boolean reservarButacas(int cantidad) {
        
             asientosDisponibles -= cantidad;

        if (asientosDisponibles < 0) {
            return false; 
        }

        return true;
    }
}
