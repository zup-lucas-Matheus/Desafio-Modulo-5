package br.com.example.zup_face.usuario;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);

}
