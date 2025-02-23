package com.example.demo.service.impl;

import com.example.demo.repository.entity.CategoriaEntity;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

//package com.example.demo.service.impl;

import com.example.demo.controller.dto.ProductoDto;
import com.example.demo.exceptions.exception.NoExisteCategoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.entity.ProductoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private ProductoEntity productoEntity;
    private ProductoDto productoDto;

    @BeforeEach
    void setUp() {
        productoEntity = ProductoEntity.builder()
                .id(1L)
                .nombre("Laptop")
                .precio(1200.50)
                .descripcion("Laptop de gama alta")
                .marca("Dell")
                .categoriaId(2)
                .build();

        productoDto = new ProductoDto(
                1L, "Laptop", 1200.50, "Laptop de gama alta", "Dell", 2
        );
    }

    @Test
    void testGetProducto_Existente() {
        when(productoRepository.findById(1L)).thenReturn(Mono.just(productoEntity));

        Mono<ProductoDto> result = productoService.getProducto(1L);

        StepVerifier.create(result)
                .expectNext(productoDto)
                .verifyComplete();

        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProducto_NoExistente() {
        when(productoRepository.findById(1L)).thenReturn(Mono.empty());

        Mono<ProductoDto> result = productoService.getProducto(1L);

        StepVerifier.create(result)
                .verifyComplete();

        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateProducto_CategoriaExiste() {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(2L);
        categoriaEntity.setDescripcion("Electrónica");

        when(categoriaRepository.findById(2L)).thenReturn(Mono.just(categoriaEntity)); // Simula que la categoría existe
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(Mono.just(productoEntity));

        Mono<ProductoDto> result = productoService.createProducto(productoDto);

        StepVerifier.create(result)
                .expectNext(productoDto)
                .verifyComplete();

        verify(categoriaRepository, times(1)).findById(2L);
        verify(productoRepository, times(1)).save(any(ProductoEntity.class));
    }



    @Test
    void testUpdateProducto() {
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(Mono.just(productoEntity));

        Mono<ProductoDto> result = productoService.updateProducto(1L, productoDto);

        StepVerifier.create(result)
                .expectNext(productoDto)
                .verifyComplete();

        verify(productoRepository, times(1)).save(any(ProductoEntity.class));
    }

    @Test
    void testDeleteProducto() {
        when(productoRepository.deleteById(1L)).thenReturn(Mono.empty());

        Mono<Void> result = productoService.deleteProducto(1L);

        StepVerifier.create(result)
                .verifyComplete();

        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllProductos() {
        when(productoRepository.findAll()).thenReturn(Flux.just(productoEntity));

        Flux<ProductoDto> result = productoService.getAllProductos();

        StepVerifier.create(result)
                .expectNext(productoDto)
                .verifyComplete();

        verify(productoRepository, times(1)).findAll();
    }
}
