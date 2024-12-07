package br.com.nexus.core.service;

import br.com.nexus.core.annotation.OrdernarPor;
import br.com.nexus.core.domain.ICoreDomain;
import br.com.nexus.core.dto.ICoreDTO;
import br.com.nexus.core.exception.NaoEncontradoException;
import br.com.nexus.core.repository.CoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public abstract class CoreService<D extends ICoreDomain, N extends Number> implements ICoreService<D, N> {

    @Autowired
    private CoreRepository<D, N> repository;

    private final Class<D> tipoClasseEntidade;

    public CoreService(Class<D> tipoClasseEntidade) {
        this.tipoClasseEntidade = tipoClasseEntidade;
    }

    @Override
    public <T extends ICoreDTO> T buscaPorId(N id) {
        Optional<D> entidadeBuscadaPorId = repository.findById(id);
        return entidadeBuscadaPorId
                .map(entidade -> (T) transformaEntidadeEmDTO(entidade))
                .orElseThrow(() -> new NaoEncontradoException("Não encontrado nenhuma entidade com id " + id));
    }

    @Override
    public Set<? extends ICoreDTO> listarTodos(int paginaAtual, int quantidadeResultados) {
        Pageable pageable = PageRequest.of(paginaAtual, quantidadeResultados, obterOrdenacao());
        Set<ICoreDTO> listaComDtoMapeado = new LinkedHashSet<>();
        Page<D> entidades = repository.findAll(pageable);

        for (D entidade : entidades) {
            ICoreDTO dtoReferente = this.transformaEntidadeEmDTO(entidade);
            listaComDtoMapeado.add(dtoReferente);
        }

        return listaComDtoMapeado;
    }

    @Override
    public D salvar(D entidade) {
        return this.repository.save(entidade);
    }

    @Override
    public abstract <T extends ICoreDTO> T transformaEntidadeEmDTO(D entidade);

    @Override
    public Sort obterOrdenacao() {
        OrdernarPor ordenacaoAnnotation = this.tipoClasseEntidade.getAnnotation(OrdernarPor.class);
        Sort.Direction direcaoOrdenacao = ordenacaoAnnotation.direcaoOrdenadacao();
        String campoQueSeraUsadoParaOrdenacao = ordenacaoAnnotation.campoQueSeraUsadoParaOrdenacao();
        return Sort.by(direcaoOrdenacao, campoQueSeraUsadoParaOrdenacao);
    }

}
