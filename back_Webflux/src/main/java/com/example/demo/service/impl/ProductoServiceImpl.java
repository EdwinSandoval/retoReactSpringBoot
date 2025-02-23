package com.example.demo.service.impl;

import com.example.demo.controller.dto.ProductoDto;
import com.example.demo.exceptions.exception.NoExisteCategoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.entity.ProductoEntity;
import com.example.demo.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public Mono<ProductoDto> getProducto(Long productoId) {
        return productoRepository.findById(productoId)
                .map(productoEntity -> new ProductoDto(productoEntity.getId(), productoEntity.getNombre(), productoEntity.getPrecio(),
                        productoEntity.getDescripcion(),productoEntity.getMarca(),productoEntity.getCategoriaId()
                       ));
    }


@Override
public Mono<ProductoDto> createProducto(ProductoDto productoDto) {
    return categoriaRepository.findById(Long.valueOf(productoDto.categoriaId())) // Validar que la categoría exista
            .switchIfEmpty(Mono.error(new NoExisteCategoria("Categoría no encontrada"))) // Lanzar error si no existe
            .then(productoRepository.save(ProductoEntity.builder()
                    .nombre(productoDto.nombre())
                    .precio(productoDto.precio())
                    .descripcion(productoDto.descripcion())
                    .marca(productoDto.marca())
                    .categoriaId(productoDto.categoriaId()) // Usar solo el ID de la categoría
                    .build()))
            .map(productoEntity -> new ProductoDto(
                    productoEntity.getId(),
                    productoEntity.getNombre(),
                    productoEntity.getPrecio(),
                    productoEntity.getDescripcion(),
                    productoEntity.getMarca(),
                    productoEntity.getCategoriaId()
                   ));
}


    @Override
    public Mono<ProductoDto> updateProducto(Long productoId, ProductoDto productoDto) {
        return productoRepository.save(ProductoEntity.builder()
                        .id(productoId)
                        .nombre(productoDto.nombre())
                        .precio(productoDto.precio())
                        .descripcion(productoDto.descripcion())
                        .marca(productoDto.marca())
                        .categoriaId(productoDto.categoriaId())
                .build())
                .map(productoEntity -> new ProductoDto(productoEntity.getId(), productoEntity.getNombre(), productoEntity.getPrecio()
                        ,productoEntity.getDescripcion(),productoEntity.getMarca(),productoEntity.getCategoriaId()
                        ));
    }

    @Override
    public Mono<Void> deleteProducto(Long productoId) {
        return productoRepository.deleteById(productoId);
    }

    public Flux<ProductoDto> getAllProductos() {
        return productoRepository.findAll()
                .map(productoEntity -> new ProductoDto(
                        productoEntity.getId(), productoEntity.getNombre(),
                        productoEntity.getPrecio()
                        ,productoEntity.getDescripcion(),productoEntity.getMarca(),
                        productoEntity.getCategoriaId()));
    }
}
