package br.com.nexus.core.controller;

import br.com.nexus.core.domain.ICoreDomain;
import br.com.nexus.core.dto.ICoreDTO;
import org.springframework.http.ResponseEntity;

import java.util.Set;

/**
 * Interface que define operações básicas de controle para entidades do tipo {@code T}.
 * Esta interface fornece métodos para listar, buscar por ID e salvar entidades.
 *
 * @param <T> Tipo da entidade que implementa {@link ICoreDTO}. A entidade que será manipulada pelo controller.
 * @param <N> Tipo do identificador da entidade, que deve ser uma subclasse de {@link Number}.
 */
public interface ICoreController<T extends ICoreDomain, N extends Number> {

    /**
     * Retorna uma lista paginada de todas os DTOs que representam as entidades do tipo {@code T}.
     *
     * @param paginaAtual Número da página atual (baseado em 0).
     * @param quantidadeResultados Quantidade de resultados por página.
     * @return Uma resposta HTTP contendo uma lista de DTOs que representam uma página de entidades do tipo {@code T}.
     */
    ResponseEntity<Set<? extends ICoreDTO>> listarTodos(int paginaAtual, int quantidadeResultados);

    /**
     * Busca uma entidade pelo seu identificador único {@code id}.
     *
     * @param id O identificador único da entidade.
     * @return Uma resposta HTTP contendo a entidade encontrada ou um erro caso não encontrada.
     */
    ResponseEntity<? extends ICoreDTO> buscaPorId(N id);

    /**
     * Salva uma nova entidade ou atualiza uma entidade existente.
     *
     * @param entidade A entidade a ser salva ou atualizada.
     * @return Uma resposta HTTP contendo a entidade salva ou um erro caso a operação falhe.
     */
    ResponseEntity<T> salvar(T entidade);
}

