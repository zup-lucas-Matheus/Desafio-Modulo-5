package br.com.example.zup_face.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario saveUsuario(@RequestBody @Valid Usuario usuario){

        return usuarioService.saveUsuario(modelMapper.map(usuario, Usuario.class));
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
