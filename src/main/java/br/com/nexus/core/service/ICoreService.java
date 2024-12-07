package br.com.nexus.core.service;

import br.com.nexus.core.domain.ICoreDomain;
import br.com.nexus.core.dto.ICoreDTO;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.Set;

/**
 * Interface que define operações básicas de serviço para entidades do tipo {@code D}.
 * Esta interface fornece métodos para buscar, listar e salvar entidades.
 *
 * @param <D> Tipo da entidade que implementa {@link ICoreDomain}. A entidade que será manipulada pelo serviço.
 * @param <N> Tipo do identificador da entidade, que deve ser uma subclasse de {@link Number}.
 */
public interface ICoreService<D extends ICoreDomain, N extends Number> {

    /**
     * Busca uma entidade pelo seu identificador único {@code id}.
     *
     * @param id O identificador único da entidade.
     * @return Um {@link Optional} contendo a entidade encontrada, ou vazio caso não exista uma entidade com o identificador fornecido.
     */
    <T extends ICoreDTO> T buscaPorId(N id);

    /**
     * Retorna uma lista paginada de todas as entidades do tipo {@code D} mas em um DTO.
     *
     * @param paginaAtual O numero da pagina atual ou a pagina que está sendo utilizada na consulta.
     * @param quantidadeResultados A quantidade de resultados em uma única consulta.
     * @return Uma página de entidades do tipo {@code D} em um objeto DTO.
     */
    Set<? extends ICoreDTO> listarTodos(int paginaAtual, int quantidadeResultados);

    /**
     * Salva uma nova entidade ou atualiza uma entidade existente.
     *
     * @param entidade A entidade a ser salva ou atualizada.
     * @return A entidade salva ou atualizada.
     */
    D salvar(D entidade);

    /**
     * Transforma uma entidade em um DTO. É abstrato e cada serviço precisa implementar sua própria lógica de transformação
     *
     * @return Um novo DTO referente à entidade existente definida ao serviço.
     */
    <T extends ICoreDTO> T transformaEntidadeEmDTO(D entidade);


    Sort obterOrdenacao();
}
