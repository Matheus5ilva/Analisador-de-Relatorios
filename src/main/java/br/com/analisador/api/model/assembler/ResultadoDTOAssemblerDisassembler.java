package br.com.analisador.api.model.assembler;

import br.com.analisador.api.model.dto.EmpresaDTO;
import br.com.analisador.api.model.dto.ResultadoDTO;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Resultado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultadoDTOAssemblerDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public ResultadoDTO toDTO(Resultado resultado){
        return modelMapper.map(resultado, ResultadoDTO.class);
    }
}
