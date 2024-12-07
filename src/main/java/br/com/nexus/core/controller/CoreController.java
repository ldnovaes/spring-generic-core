package br.com.nexus.core.controller;


import br.com.nexus.core.domain.ICoreDomain;
import br.com.nexus.core.dto.ICoreDTO;
import br.com.nexus.core.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CoreController<D extends ICoreDomain, N extends Number> implements ICoreController<D, N> {

    @Autowired
    private ICoreService<D, N> service;

    @Override
    @GetMapping
    public ResponseEntity<Set< ? extends ICoreDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int paginaAtual,
            @RequestParam(defaultValue = "10") int quantidadeResultados) {

        Set<? extends ICoreDTO> resultadosPaginadosTransformadosEmDTO = service.listarTodos(paginaAtual, quantidadeResultados);
        return ResponseEntity.ok(resultadosPaginadosTransformadosEmDTO);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<? extends ICoreDTO> buscaPorId(@PathVariable N id) {
        ICoreDTO registroBuscadoPorId = this.service.buscaPorId(id);
        return ResponseEntity.ok(registroBuscadoPorId);
    }

    @Override
    @PostMapping
    public ResponseEntity<D> salvar(@RequestBody D entidade) {
        D entidadeSalva = this.service.salvar(entidade);
        return ResponseEntity.ok(entidadeSalva);
    }
}
