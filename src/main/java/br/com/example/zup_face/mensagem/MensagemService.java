package br.com.example.zup_face.mensagem;

import br.com.example.zup_face.enums.Visualizado;
import br.com.example.zup_face.usuario.Usuario;
import br.com.example.zup_face.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemService {

    @Autowired
    private MensageRepository mensageRepository;
    @Autowired
    private UsuarioService usuarioService;

    //Metódo para enviar mensagens entre os usuarios.
    public Mensagem sendMensagem(String emailorigem, String emailDestino, Mensagem mensagem) throws Exception {

        Mensagem newMensagem = new Mensagem();

        if (usuarioService.existUsuario(emailorigem)) {
            Usuario usuarioOrigem = usuarioService.findForIdEmail(emailorigem);
            newMensagem.setEmailOrigem(usuarioOrigem);
            //data
        }
        if (usuarioService.existUsuario(emailDestino)) {
            Usuario usuarioDestino = usuarioService.findForIdEmail(emailDestino);
            newMensagem.setEmailDestino(usuarioDestino);
            newMensagem.setVisualizado(Visualizado.NAO_VISUALIZADO);
            newMensagem.setMensagem(mensagem.getMensagem());
            return mensageRepository.save(newMensagem);
        }

        throw new Exception("Usuario não encontrado");

    }

    //Metódo para mostrar todas as mensagens.
    public List<Mensagem> mensagens() {
        return (List<Mensagem>) mensageRepository.findAll();

    }
}
