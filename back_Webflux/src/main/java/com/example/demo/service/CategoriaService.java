package com.example.demo.service;

import com.example.demo.controller.dto.CategoriaDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriaService {

    Mono<CategoriaDto> getCategoria(Long clienteId);
    Mono<CategoriaDto> createCategoria(CategoriaDto clienteDto);
    Mono<CategoriaDto> updateCategoria(Long clienteId, CategoriaDto clienteDto);
    Mono<Void> deleteCategoria(Long clienteId);
    Flux<CategoriaDto> getAllCategorias();

}
