package com.example.demo.controller.dto;

import com.example.demo.repository.entity.CategoriaEntity;

public record ProductoDto(Long id, String nombre, Double precio,
                          String descripcion, String marca,Integer categoriaId) {
}
