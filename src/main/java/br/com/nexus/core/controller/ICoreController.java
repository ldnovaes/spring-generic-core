package br.com.nexus.core.controller;

import br.com.nexus.core.domain.ICoreDomain;
import br.com.nexus.core.dto.ICoreDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Set;

public interface ICoreController<T extends ICoreDomain, N extends Number> {

    ResponseEntity<Set<? extends ICoreDTO>> listAll(int currentPage, int totalResults);

    ResponseEntity<? extends ICoreDTO> findById(N id);

    ResponseEntity<T> save(T entidade);

    ResponseEntity<Set< ? extends ICoreDTO>> listBy(int currentPage, int totalResults, Map<String, String> queryParams);

    ResponseEntity<Map<String, String>> delete(N id);

}

