package br.com.example.zup_face.mensagem;

import br.com.example.zup_face.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensagemService {

    @Autowired
    private MensageRepository mensageRepository;
    @Autowired
    private UsuarioService usuarioService;




}
