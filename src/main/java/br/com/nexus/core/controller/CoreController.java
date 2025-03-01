package br.com.nexus.core.controller;


import br.com.nexus.core.domain.ICoreDomain;
import br.com.nexus.core.dto.ICoreDTO;
import br.com.nexus.core.service.ICoreService;
import br.com.nexus.core.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

public class CoreController<D extends ICoreDomain, N extends Number> implements ICoreController<D, N> {

    @Autowired
    private ICoreService<D, N> service;

    @Override
    @GetMapping
    public ResponseEntity<Set< ? extends ICoreDTO>> listAll(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "10") int totalResults) {
        Set<? extends ICoreDTO> paginatedResultsTransformedIntoDTO = service.listAll(currentPage, totalResults);
        return ResponseEntity.ok(paginatedResultsTransformedIntoDTO);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<? extends ICoreDTO> findById(@PathVariable N id) {
        ICoreDTO found = this.service.findById(id);
        return ResponseEntity.ok(found);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<Set< ? extends ICoreDTO>> listBy(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "10") int totalResults,
            @RequestParam Map<String, String> queryParams) {
        Set<? extends ICoreDTO> paginatedResultsTransformedIntoDTO = service.listBy(currentPage, totalResults, queryParams);
        return ResponseEntity.ok(paginatedResultsTransformedIntoDTO);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable N id) {
        this.service.delete(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessfullMessageDeleted(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<D> save(@RequestBody D entity) {
        D entitySaved = this.service.save(entity);
        return ResponseEntity.ok(entitySaved);
    }

}
