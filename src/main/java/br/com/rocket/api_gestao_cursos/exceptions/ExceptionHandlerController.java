package br.com.rocket.api_gestao_cursos.exceptions;

import br.com.rocket.api_gestao_cursos.dto.MessageExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(Exception.class)
    public ResponseEntity methodExceptionGeneric(Exception ex) {

        var error = MessageExceptionDTO.builder()
                .error(ex.getMessage())
                .component("ExceptionHandlerController")
                .build();

        return ResponseEntity.badRequest().body(error);
    }

}
