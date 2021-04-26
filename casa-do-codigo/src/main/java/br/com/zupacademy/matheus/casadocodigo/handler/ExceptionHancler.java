package br.com.zupacademy.matheus.casadocodigo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHancler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private List<Error> erroDeValidacao(MethodArgumentNotValidException e) {
        List<Error> erros = new ArrayList<>();
        for (FieldError x : e.getBindingResult().getFieldErrors()){
            erros.add(new Error(x.getField(), messageSource.getMessage(x, LocaleContextHolder.getLocale())));
        }
        return erros;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public Error illegalArgument(IllegalStateException e) {
        return new Error("estadoId", e.getMessage());
    }
}
