package com.example.demo.controller;

import com.example.demo.controller.dto.CategoriaDto;
import com.example.demo.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/{categoriaId}")
    Mono<CategoriaDto> getCategoria(@PathVariable("categoriaId") Long categoriaId) {
        return categoriaService.getCategoria(categoriaId);
    }

    @PostMapping
    Mono<CategoriaDto> createCategoria(@RequestBody CategoriaDto categoriaDto) {
        return categoriaService.createCategoria(categoriaDto);
    }

    @PutMapping("/{categoriaId}")
    Mono<CategoriaDto> updateCategoria(@PathVariable("categoriaId") Long categoriaId, @RequestBody CategoriaDto categoriaDto) {
        return categoriaService.updateCategoria(categoriaId, categoriaDto);
    }

    @DeleteMapping("/{categoriaId}")
    Mono<Void> deleteCategoria(@PathVariable("categoriaId") Long categoriaId) {
        return categoriaService.deleteCategoria(categoriaId);
    }

    @GetMapping("/all")
    Flux<CategoriaDto> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }
}
