package br.com.example.zup_face.handler;

import br.com.example.zup_face.exeptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExeptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemDeErroValidation manipularExcecoes(MethodArgumentNotValidException exception){

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        List<Erro> erros = fieldErrors.stream().map(objeto -> new Erro(objeto.getDefaultMessage())).collect(Collectors.toList());
        return new MensagemDeErroValidation(400, erros);

    }


    @ExceptionHandler(UsuarioExption.class)
    public ResponseEntity<MensagemErroUser> handlerBadRequest(UsuarioExption ex){
        return new ResponseEntity<>(
            MensagemErroUser.builder()
                .status(400)
                    .mensagem("Usuario não encontrado")
                    .titulo("BAD REQUEST")
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MensagemExption.class)
    public ResponseEntity<MensagemErroMsn> handlerBadRequestMensage(MensagemExption erro){

        List<Erro> erros = new ArrayList<>();

        return new ResponseEntity<>(
                MensagemErroMsn.builder()
                .status(400)
                 .mensagem("Mensagem não encontrada")
                .build(), HttpStatus.BAD_REQUEST);

    }




}
