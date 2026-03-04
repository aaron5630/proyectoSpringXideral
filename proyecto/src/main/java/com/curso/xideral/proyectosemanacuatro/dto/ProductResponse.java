package com.curso.xideral.proyectosemanacuatro.dto;

import java.time.LocalDateTime;

import com.curso.xideral.proyectosemanacuatro.models.Category;



public record ProductResponse(Long id, 
    String nombre,
     Double precio, 
     String descripcion, 
     Category categoria,
    Integer inventario, 
    LocalDateTime creadoEn

    
) {
    

}
