package com.example.demo.repository;

import com.example.demo.repository.entity.ProductoEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface ProductoRepository extends R2dbcRepository<ProductoEntity, Long> {
    Flux<ProductoEntity> findByCategoriaId(Integer categoriaId);
}
