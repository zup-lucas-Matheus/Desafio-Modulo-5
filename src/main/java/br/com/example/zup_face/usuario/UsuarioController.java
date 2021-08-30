package br.com.example.zup_face.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }


}
