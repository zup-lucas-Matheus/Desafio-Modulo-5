package br.com.example.zup_face.usuario;

import br.com.example.zup_face.exeptions.UsuarioExption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowableOfType;

@SpringBootTest
public class UsuarioServiceTest {
/*

    @BeforeEach
    void setUp() { MockitoAnnotations.initMocks(this); }
*/

//    @InjectMocks
    @Autowired
    private UsuarioService usuarioService;
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testarMetodoDeSalvaUsuario(){

        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class)))
                .thenReturn(usuario);

        Usuario usuarioTest = usuarioService.saveUsuario(usuario);
        Assertions.assertEquals(usuario, usuarioTest);

    }

    @Test
    public void testarMetodoQueRetornaTodosOsUsuarios(){
        Usuario usuario = new Usuario();

        List<Usuario> usuarios = Arrays.asList(usuario);
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> usuariosTest = Arrays.asList(usuario);
        Assertions.assertEquals(usuarios, usuarioService.allUsuario());
    }

    @Test
    public void testarMetodoEncontrarUsuarioPorIdPositivo() throws Exception {
        Usuario usuario = new Usuario();
        Optional<Usuario> usuarioOptional = Optional.of(usuario);

        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(usuarioOptional);
        Assertions.assertEquals(usuario, usuarioService.findForIdEmail("lucas@123"));
    }

    @Test
    public void testarMetodoEncontrarUsuarioPorIdNegativoParaLancaExecao() throws Exception {
        Usuario usuario = new Usuario();
        Optional<Usuario> usuarioOptional = Optional.empty();

        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(usuarioOptional);

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> {usuarioService.findForIdEmail("324e3");});

        Assertions.assertTrue(exception.getMessage().equals("Usuario não encontrado"));

    }

    @Test
    public void testarMetodoParaExcluirUsuarioPorIdPositivo() throws Exception {
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(Optional.of(usuario));
        usuarioService.deleteUsuario("lmatheyus@123");

        Mockito.verify(usuarioRepository).delete(usuario);
    }

/*    @Test
    public void testarMetodoParaExcluirUsuarioPorIdNegativoExeption() throws Exception {
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        usuarioService.deleteUsuario("lmatheyus@123");
        Mockito.verify(usuarioRepository).delete(usuario);

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> {usuarioService.findForIdEmail("324e3");});

    }*/


    @Test
    public void testarMetodoParaExcluirUsuarioPorIdNegativoExeption() throws Exception {
        Usuario usuario = new Usuario();
        UsuarioExption exption = new UsuarioExption("Usuario não encontrado");

        Mockito.when(usuarioRepository.findById(Mockito.anyString())).thenThrow(exption);
//        usuarioService.deleteUsuario("lmatheyus@123");
//        Mockito.verify(usuarioRepository).delete(usuario);

/*        UsuarioExption exception = catchThrowableOfType (
                () -> usuarioService.deleteUsuario("324e3"), UsuarioExption.class);*/

        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> {usuarioService.deleteUsuario("324e3");});

        Assertions.assertNotNull(exception);
        Assertions.assertEquals("Usuario não encontrado", exception.getMessage());
    }

}
