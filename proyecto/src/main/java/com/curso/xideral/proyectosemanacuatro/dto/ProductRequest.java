package com.curso.xideral.proyectosemanacuatro.dto;

import com.curso.xideral.proyectosemanacuatro.models.Category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductRequest(

        @NotBlank @Size(max = 100) String nombre,
        @NotNull @Positive Double precio,
        String descripcion,
        @NotNull Category categoria,
        @NotNull @Min(0) Integer inventario
) {

}
