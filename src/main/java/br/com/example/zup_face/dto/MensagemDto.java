package br.com.example.zup_face.dto;

import br.com.example.zup_face.enums.Visualizado;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor

public class MensagemDto {

    @Email(message = "Email origem é obrigatorio")
    private String emailOrigem;
    private String mensagem;
    @Email(message = "Email destino é obrigatorio")
    private String emailDestino;
    private Visualizado visualizado;
    private LocalDateTime date;

}
