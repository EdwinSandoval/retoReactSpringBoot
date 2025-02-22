package com.example.demo.service.impl;

import com.example.demo.controller.dto.CategoriaDto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.entity.CategoriaEntity;
import com.example.demo.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public Mono<CategoriaDto> getCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .map(categoriaEntity -> new CategoriaDto(categoriaEntity.getId(), categoriaEntity.getDescripcion()));
    }

    @Override
    public Mono<CategoriaDto> createCategoria(CategoriaDto categoriaDto) {
        return categoriaRepository.save(CategoriaEntity.builder()
                .descripcion(categoriaDto.descripcion())
                .build())
                .map(categoriaEntity -> new CategoriaDto(categoriaEntity.getId(), categoriaEntity.getDescripcion()));
    }

    @Override
    public Mono<CategoriaDto> updateCategoria(Long categoriaId, CategoriaDto categoriaDto) {
        return categoriaRepository.save(CategoriaEntity.builder()
                .id(categoriaId)
                        .descripcion(categoriaDto.descripcion())
                .build())
                .map(categoriaEntity -> new CategoriaDto(categoriaEntity.getId(), categoriaEntity.getDescripcion()));
    }

    @Override
    public Mono<Void> deleteCategoria(Long categoriaId) {
        return categoriaRepository.deleteById(categoriaId);
    }

    @Override
    public Flux<CategoriaDto> getAllCategorias() {
        return categoriaRepository.findAll()
                .map(categoriaEntity -> new CategoriaDto(categoriaEntity.getId(), categoriaEntity.getDescripcion()));
    }
}
