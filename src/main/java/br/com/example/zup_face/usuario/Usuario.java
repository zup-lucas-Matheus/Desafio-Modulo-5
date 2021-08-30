package br.com.example.zup_face.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "zuppers")
public class Usuario {

    @Id
    private String email;
    private String nome;
    private String sobrenome;


}
