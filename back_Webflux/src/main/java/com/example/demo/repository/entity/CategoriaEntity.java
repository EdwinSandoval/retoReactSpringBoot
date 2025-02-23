package com.example.demo.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Setter
@Builder
@Table("categoria")
public class CategoriaEntity {

    @Id
    Long id;
    String descripcion;

    public CategoriaEntity() {
    }

    public CategoriaEntity(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    //    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductoEntity> productos;

}
