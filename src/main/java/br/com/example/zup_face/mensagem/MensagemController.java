package br.com.example.zup_face.mensagem;


import br.com.example.zup_face.dto.MensagemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mensagens")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/chat")
    public Mensagem saveMensagerZupper(@RequestBody MensagemDto mensagemDto) throws Exception {
        Mensagem mensagem = modelMapper.map(mensagemDto, Mensagem.class);
        return mensagemService.sendMensagem(mensagemDto.getEmailOrigem(), mensagemDto.getEmailDestino(), mensagem);
    }

    @GetMapping
    public List<Mensagem> allMensagens() {
        return mensagemService.allMensagens();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteMensagem(Integer id) throws Exception {
        mensagemService.deleteMensagem(id);
    }



}
