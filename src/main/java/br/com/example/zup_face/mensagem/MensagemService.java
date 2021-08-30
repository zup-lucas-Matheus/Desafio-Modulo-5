package br.com.example.zup_face.mensagem;

import br.com.example.zup_face.enums.Visualizado;
import br.com.example.zup_face.usuario.Usuario;
import br.com.example.zup_face.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
            newMensagem.setDate(LocalDateTime.now());
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
    public List<Mensagem> allMensagens() {
        return (List<Mensagem>) mensageRepository.findAll();

    }

    //Metódo para buscar a mensagem por id.
    public Optional<Mensagem> findByIdMessager(int id){
        return mensageRepository.findById(id);
    }

    //Metódo para deletar a mensagem.
    public void deleteMensagem(Integer id) throws Exception {
        mensageRepository.delete(findMenssageForId(id));
    }

    //Pesquisar mensagem por id
    public Mensagem findMenssageForId(Integer id) throws Exception {

        Optional<Mensagem> mensagemBusca = mensageRepository.findById(id);

        if (mensagemBusca.isPresent()) {
            return mensagemBusca.get();
        }
        throw new Exception("Mensagem não encontrada");

    }

    //Visualizar mensagem por id da mensagem.
    public Mensagem visualizarMsnPorId(Integer id) throws Exception {
        Mensagem mensagem = new Mensagem();
        mensagem = findMenssageForId(id);

        if (mensagem == findMenssageForId(id)) {
            mensagem.setVisualizado(Visualizado.VISUALIZADO);
            mensagem.setDate(LocalDateTime.now());
            mensageRepository.save(mensagem);
            enviarMensagemAutomatica(mensagem.getEmailOrigem().getEmail(), mensagem.getEmailDestino().getNome());
            return mensagem;
        }
        else {
            mensagem.setVisualizado(Visualizado.NAO_VISUALIZADO);
            return mensagem;
        }

    }

    //Metódo para trazer a quantidade de mensagens não vizualizadas.
    public List<Mensagem> filtrarMensagem(String email){
        return this.mensageRepository.findAllByEmailDestinoEmailAndVisualizado(email, Visualizado.NAO_VISUALIZADO);
    }

    public void enviarMensagemAutomatica(String emailDestino, String nomeDestino) throws Exception {

        Usuario usuarioOrigem = usuarioService.findForIdEmail("sistema@email.com");
        Mensagem mensagemAutomatica = new Mensagem();

        mensagemAutomatica.setMensagem("O " +   nomeDestino + " Leu sua mensagem. Talvez ele ignore ou não");
        mensagemAutomatica.setEmailOrigem(usuarioOrigem);
        if (usuarioService.existUsuario(emailDestino)) {
            Usuario usuarioDestino = usuarioService.findForIdEmail(emailDestino);
            mensagemAutomatica.setEmailDestino(usuarioDestino);
            mensagemAutomatica.setDate(LocalDateTime.now());
            mensagemAutomatica.setVisualizado(Visualizado.NAO_VISUALIZADO);
            mensageRepository.save(mensagemAutomatica);

        }
        else {
            throw new Exception("Usuario não encontrado");
        }

    }




}
