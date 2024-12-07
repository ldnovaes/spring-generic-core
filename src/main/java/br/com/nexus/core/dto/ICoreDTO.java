package br.com.nexus.core.dto;

/**
 * Interface que define operações básicas para o DTO.
 * Esta interface define os métodos essenciais para obter e definir o identificador único de um DTO.
 */
public interface ICoreDTO {

    /**
     * Retorna o identificador único do DTO.
     *
     * @return O identificador único do DTO, do tipo {@code Long}.
     */
    public Long getId();

    /**
     * Define o identificador único do DTO.
     *
     * @param id O identificador único a ser atribuído ao DTO.
     */
    public void setId(Long id);
}
