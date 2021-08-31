package br.com.example.zup_face.usuario;

import br.com.example.zup_face.exeptions.UsuarioExption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Metódo para cadastrar usuario.
    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //Metódo para trazer todos os usuarios.
    public List<Usuario> allUsuario(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

    //Metódo para encontrar o usuario por ID.
    public Usuario findForIdEmail(String email) throws Exception {
        return usuarioRepository.findById(email)
                .orElseThrow(() -> new UsuarioExption("Usuario não encontrado"));

    }
    //Metódo para deletar usuarios
    public void deleteUsuario(String email) throws Exception {
        usuarioRepository.delete(findForIdEmail(email));

    }

    //Metódo para verificar se já existe.
    public boolean existUsuario(String email){
        return usuarioRepository.findByEmail(email).isPresent();

    }

}
