package br.com.example.zup_face.mensagem;

import br.com.example.zup_face.enums.Visualizado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MensageRepository extends CrudRepository<Mensagem, Integer> {

    List<Mensagem> findAllByEmailDestinoEmailAndVisualizado(String email, Visualizado visualizado);

}
