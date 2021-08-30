package br.com.example.zup_face.exeptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ManipuladorDeExcecoes {

    private int status;
    private String mensagem;
    private String titulo;


}
