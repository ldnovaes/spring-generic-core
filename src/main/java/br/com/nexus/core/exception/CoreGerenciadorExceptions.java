package br.com.nexus.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CoreGerenciadorExceptions {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<Object> lidaComNaoEncontradoException(NaoEncontradoException ex, WebRequest request) {
        ErroDetalhes erroDetalhes = ErroDetalhes.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .requestDescription(request.getDescription(false))
                .build();
        return new ResponseEntity<>(erroDetalhes, HttpStatus.BAD_REQUEST);
    }
}
