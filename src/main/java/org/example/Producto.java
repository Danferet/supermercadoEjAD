package org.example;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    private String nombre;
    private BigDecimal precio;
    private int cantidad;
    
    public String verDetalle(){
        return "Producto: " + getNombre() + " | Precio: " + getPrecio() + " | Stock: " + getCantidad();

    }
}
