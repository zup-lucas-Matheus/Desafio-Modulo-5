package br.com.example.zup_face.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> allUsuario(){
        return usuarioService.allUsuario();

    }

    @GetMapping("/{email}")
    public Usuario findUsuarioForId(@PathVariable String email){
        return usuarioService.findForIdEmail(email);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{email}")
    public void deleteUsuarioForId(@PathVariable String email){
        usuarioService.deleteUsuario(email);
    }





}
