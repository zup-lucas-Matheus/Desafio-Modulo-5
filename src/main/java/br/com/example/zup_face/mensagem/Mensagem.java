package br.com.example.zup_face.mensagem;


import br.com.example.zup_face.enums.Visualizado;
import br.com.example.zup_face.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mensagens")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mensagem;
    @ManyToOne
    @JoinColumn(name = "user_origem")
    @Email(message = "{validacao.email.obrigatório}")
    private Usuario emailOrigem;
    @ManyToOne
    @JoinColumn(name = "user_destino")
    @Email(message = "{validacao.email.obrigatório}")
    private Usuario emailDestino;
    private Visualizado visualizado;
    private LocalDateTime date;


}
