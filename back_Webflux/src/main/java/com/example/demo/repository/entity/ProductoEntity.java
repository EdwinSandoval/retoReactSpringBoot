package com.example.demo.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("producto")
public class ProductoEntity {

    @Id
    private Long id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String marca;
    private Integer categoriaId;

}
