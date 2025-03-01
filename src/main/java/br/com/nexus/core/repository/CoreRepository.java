package br.com.nexus.core.repository;

import br.com.nexus.core.domain.ICoreDomain;
import br.com.nexus.core.dto.ICoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CoreRepository<D extends ICoreDomain, N extends Number> extends JpaRepository<D, N>, JpaSpecificationExecutor<D> {

}
