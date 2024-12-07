package br.com.nexus.core.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ErroDetalhes {

    private HttpStatus httpStatus;

    private String errorMessage;

    private String requestDescription;
}
