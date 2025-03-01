package br.com.nexus.core.service;

import br.com.nexus.core.annotation.OrderBy;
import br.com.nexus.core.domain.ICoreDomain;
import br.com.nexus.core.dto.ICoreDTO;
import br.com.nexus.core.exception.NotFoundException;
import br.com.nexus.core.repository.CoreRepository;
import br.com.nexus.core.utils.ReflectionUtil;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public abstract class CoreService<D extends ICoreDomain, N extends Number> implements ICoreService<D, N> {

    @Autowired
    private CoreRepository<D, N> repository;

    private final Class<D> typeEntityClass;

    public CoreService(Class<D> typeEntityClass) {
        this.typeEntityClass = typeEntityClass;
    }

    private Specification<D> createSpecification(Map<String, String> queryParams) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            queryParams.forEach((key, value) -> {
                boolean fieldSearchedIsString = ReflectionUtil.isFieldTypeString(key, typeEntityClass);

                if (fieldSearchedIsString) {
                    predicates.add(criteriaBuilder.like(root.get(key), "%" + value + "%"));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get(key), value));
                }
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Set<? extends ICoreDTO> transformListEntityIntoDTO(Page<D> listWithDomain) {
        Set<ICoreDTO> dtos = new LinkedHashSet<>();

        for (D entity : listWithDomain) {
            ICoreDTO correspondingDTO = this.transformEntityIntoDTO(entity);
            dtos.add(correspondingDTO);
        }

        return dtos;
    }

    @Override
    public <T extends ICoreDTO> T findById(N id) {
        Optional<D> entityFound = repository.findById(id);
        return entityFound
                .map(entity -> (T) transformEntityIntoDTO(entity))
                .orElseThrow(() -> new NotFoundException("Não encontrado nenhuma entidade com id " + id));
    }

    @Override
    public Set<? extends ICoreDTO> listAll(int currentPage, int totalResults) {
        Pageable pageable = PageRequest.of(currentPage, totalResults, getOrdenation());
        Page<D> entitys = repository.findAll(pageable);
        return this.transformListEntityIntoDTO(entitys);
    }

    @Override
    public Sort getOrdenation() {
        OrderBy orderingAnnotation  = this.typeEntityClass.getAnnotation(OrderBy.class);
        Sort.Direction sortDirection  = orderingAnnotation.sortDirection();
        String fieldToBeUsedForSorting  = orderingAnnotation.fieldToBeUsedForSorting();
        return Sort.by(sortDirection , fieldToBeUsedForSorting );
    }

    @Override
    public Set<? extends ICoreDTO> listBy(int currentPage, int totalResults, Map<String, String> queryParams) {
        Pageable pageable = PageRequest.of(currentPage, totalResults, getOrdenation());
        Specification<D> specification = createSpecification(queryParams);
        Page<D> entitys = repository.findAll(specification, pageable);
        return this.transformListEntityIntoDTO(entitys);
    }

    @Override
    public D save(D entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(N id) {
        this.repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException("Não encontrado nenhuma entidade com id " + id));
    }


    @Override
    public abstract <T extends ICoreDTO> T transformEntityIntoDTO(D entity);

}
