package br.com.example.zup_face.mensagem;


import br.com.example.zup_face.dto.MensagemDto;
import br.com.example.zup_face.dto.QuantidadeMsnNaoLida;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("mensagens")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/chat")
    public Mensagem saveMensagerZupper(@RequestBody @Valid MensagemDto mensagemDto) throws Exception {
        Mensagem mensagem = modelMapper.map(mensagemDto, Mensagem.class);
        return mensagemService.sendMensagem(mensagemDto.getEmailOrigem(), mensagemDto.getEmailDestino(), mensagem);
    }

    @GetMapping
    public List<Mensagem> allMensagens() {
        return mensagemService.allMensagens();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMensagem(@PathVariable Integer id) throws Exception {
        mensagemService.deleteMensagem(id);
    }

    @GetMapping("/{mensagemId}")
    public MensagemDto viewMessages(@PathVariable(name = "mensagemId") Integer id) throws Exception {
        Mensagem mensagem = mensagemService.visualizarMsnPorId(id);
        return modelMapper.map(mensagem, MensagemDto.class);
    }

    @GetMapping("/usuario/perfil/{email}")
    public QuantidadeMsnNaoLida quantidadeDeMSN(@PathVariable String email){

        QuantidadeMsnNaoLida msn = new QuantidadeMsnNaoLida();
        msn.setMensagemNaoView(mensagemService.filtrarMensagem(email).size());
        return  msn;
    }


}
