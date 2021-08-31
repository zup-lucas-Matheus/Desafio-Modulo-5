package br.com.example.zup_face.dto;

import br.com.example.zup_face.enums.Cargo;
import jdk.jfr.Name;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class UsuarioDto {

    @Email(message = "Email é obrigatório")
    private String email;
    @NotBlank(message = "nome é obrigatorio")
    private String nome;
    private String sobrenome;
    private Cargo cargo;


}
