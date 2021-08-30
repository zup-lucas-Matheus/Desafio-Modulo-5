package br.com.example.zup_face.exeptions.handler;

import br.com.example.zup_face.exeptions.ManipuladorDeExcecoes;
import br.com.example.zup_face.exeptions.UsuarioExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandler {
    @ExceptionHandler(UsuarioExeption.class)
    public ResponseEntity<ManipuladorDeExcecoes> handlerBadRequest(UsuarioExeption userExp){

        return new ResponseEntity<>(
                ManipuladorDeExcecoes.builder()
                .status(400)
                .mensagem("Usuario não encontrado")
                .titulo("BAD REQUEST")
                .build(), HttpStatus.BAD_REQUEST);

    }

}
