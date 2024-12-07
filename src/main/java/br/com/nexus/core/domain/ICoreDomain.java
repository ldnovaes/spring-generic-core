package br.com.nexus.core.domain;

import br.com.nexus.core.dto.ICoreDTO;

/**
 * Interface que define operações básicas para entidades de domínio.
 * Esta interface define os métodos essenciais para obter e definir o identificador único de uma entidade.
 */
public interface ICoreDomain {

    /**
     * Retorna o identificador único da entidade.
     *
     * @return O identificador único da entidade, do tipo {@code Long}.
     */
    public Long getId();

    /**
     * Define o identificador único da entidade.
     *
     * @param id O identificador único a ser atribuído à entidade.
     */
    public void setId(Long id);
}
