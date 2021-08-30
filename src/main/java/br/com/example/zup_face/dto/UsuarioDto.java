package br.com.example.zup_face.dto;

import br.com.example.zup_face.enums.Cargo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {

    private String email;
    private String nome;
    private String sobrenome;
    private Cargo cargo;


}
