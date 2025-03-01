package br.com.nexus.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CoreExceptionManager {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        DetailErrorResponse detailErrorResponse = DetailErrorResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(ex.getMessage())
                .requestDescription(request.getDescription(false))
                .build();
        return new ResponseEntity<>(detailErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
