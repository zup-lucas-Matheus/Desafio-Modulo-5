package br.com.example.zup_face.dto.componente;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Conversor {

    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
