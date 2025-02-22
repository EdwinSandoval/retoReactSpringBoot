package com.example.demo.repository;

import com.example.demo.repository.entity.CategoriaEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CategoriaRepository extends R2dbcRepository<CategoriaEntity, Long> {
}
