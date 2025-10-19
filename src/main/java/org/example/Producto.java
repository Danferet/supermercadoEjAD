package org.example;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    private String nombre;
    private BigDecimal precio;
    private int cantidad;

    public String verDetalle(){
        return "Producto: " + getNombre() + " | Precio: " + getPrecio() + " | Stock: " + getCantidad();

    }

    @Override
    public String toString() {
        return "Producto: " + getNombre() + " | Precio: " + getPrecio() + " | Stock: " + getCantidad();
    }
}
