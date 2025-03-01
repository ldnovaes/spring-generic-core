package br.com.nexus.core.annotation;

import org.springframework.data.domain.Sort;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação personalizada para especificar a ordem de classificação dos dados em uma classe.
 *
 * <p>Esta anotação é usada para definir a direção e o campo pelo qual os dados
 * devem ser ordenados em operações que envolvam ordenação.</p>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OrderBy {

    /**
     * Define a direção da ordenação.
     *
     * <p>Os valores possíveis são:</p>
     * <ul>
     *   <li><b>Sort.Direction.ASC</b>: Ordenação ascendente (padrão).</li>
     *   <li><b>Sort.Direction.DESC</b>: Ordenação descendente.</li>
     * </ul>
     *
     * @return A direção da ordenação (ASC ou DESC).
     */
    Sort.Direction sortDirection() default Sort.Direction.ASC;

    /**
     * Especifica o campo que será usado para ordenação.
     *
     * <p>Por padrão, o campo será "id".</p>
     *
     * @return O nome do campo para a ordenação.
     */
    String fieldToBeUsedForSorting()  default "id";
}
