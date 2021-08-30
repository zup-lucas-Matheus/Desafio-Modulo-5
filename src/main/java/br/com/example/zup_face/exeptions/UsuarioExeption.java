package br.com.example.zup_face.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioExeption extends RuntimeException {

    public UsuarioExeption(String message) {
        super(message);
    }
}
