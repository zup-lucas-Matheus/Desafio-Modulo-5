package br.com.example.zup_face.usuario;

import br.com.example.zup_face.enums.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "zuppers")
public class Usuario {

    @Id
    @Email(message = "{validacao.email.obrigatório}")
    private String email;
    @NotBlank(message = "{validacao.nome.obrigatorio}")
    @Size(min = 2, max = 20)
    private String nome;
    @Size(min = 2, max = 20)
    private String sobrenome;
    private Cargo cargo;


}
