package br.com.example.zup_face.usuario;

import br.com.example.zup_face.dto.UsuarioDto;
import br.com.example.zup_face.dto.componente.Conversor;
import br.com.example.zup_face.enums.Cargo;
import br.com.example.zup_face.exeptions.UsuarioExption;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @MockBean
    private UsuarioService usuarioService;
    @Autowired
    private MockMvc mockMvc; // Simula uma chamada HTTP.
    @SpyBean
    private ModelMapper modelMapper;
    private UsuarioDto usuarioDto;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        usuarioDto = new UsuarioDto();
        usuarioDto.setEmail("ola@123");
        usuarioDto.setNome("lucas");
        usuarioDto.setSobrenome("Matheus");
        usuarioDto.setCargo(Cargo.DEV_JR);
        objectMapper = new ObjectMapper();

    }

    @Test
    public void testarRotaDeCadastroDeUsuarioCaminhoPositivo() throws Exception {
        Usuario usuarioModel = modelMapper.map(usuarioDto, Usuario.class);
        Mockito.when(usuarioService.saveUsuario(Mockito.any(Usuario.class)))
            .thenReturn(usuarioModel);
        String json = objectMapper.writeValueAsString(usuarioDto);

        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andExpect(MockMvcResultMatchers.
                                jsonPath("$.email", CoreMatchers.equalTo(usuarioDto.getEmail())))
                        .andExpect(MockMvcResultMatchers.
                                jsonPath("$.nome", CoreMatchers.equalTo(usuarioDto.getNome())))
                        .andExpect(MockMvcResultMatchers.
                                jsonPath("$.sobrenome", CoreMatchers.equalTo(usuarioDto.getSobrenome())))
                        .andExpect(MockMvcResultMatchers
                                .jsonPath("$.cargo", CoreMatchers.equalTo(usuarioDto.getCargo().toString())));

    }

    @Test
    public void testarValidacaoDeCadastroDeUsuario() throws Exception {
        Usuario usuarioModel = modelMapper.map(usuarioDto, Usuario.class);
        Mockito.when(usuarioService.saveUsuario(Mockito.any(Usuario.class)))
                .thenReturn(usuarioModel);
        usuarioDto.setEmail("lmat");
        usuarioDto.setNome("A");
        String json = objectMapper.writeValueAsString(usuarioDto);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    public void testarrBuscaDeUsuarioPorId() throws Exception {

        Mockito.when(usuarioService.findForIdEmail(Mockito.anyString()))
                .thenReturn(modelMapper.map(usuarioDto, Usuario.class));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/users/42")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        String jsonResposta = resultActions.andReturn().getResponse().getContentAsString();
        Usuario usuario = objectMapper.readValue(jsonResposta, Usuario.class);

    }
    @Test
    public void testarBuscaDeContatoPeloIdNaoExiste() throws Exception {

        Mockito.when(usuarioService.findForIdEmail(Mockito.anyString()))
                .thenThrow(Exception.class);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/users/42")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void testarDeleteDeUsuario() throws Exception {
        Usuario usuario = new Usuario();

        Mockito.when(usuarioService.findForIdEmail(Mockito.anyString()))
                .thenReturn(usuario);

        usuarioService.deleteUsuario("matheus@123");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/users/matheus@123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    public void testarDeleteDeUsuarioCaminhoNegativo() throws Exception {

        Usuario usuario = new Usuario();

        Mockito.doThrow(UsuarioExption.class).when(usuarioService).deleteUsuario(Mockito.anyString());
//        Exception exception = new Exception("Usuario não encontrado");

//        usuarioService.deleteUsuario("matheus123");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/users/matheus123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
