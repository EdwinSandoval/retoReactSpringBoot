package com.example.demo.controller.dto;

public record ProductoDto(Long id, String nombre, Double precio,
                          String descripcion,String marca, String categoria) {
}
