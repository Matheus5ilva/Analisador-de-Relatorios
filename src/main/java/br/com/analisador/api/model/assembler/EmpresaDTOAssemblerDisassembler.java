package br.com.analisador.api.model.assembler;

import br.com.analisador.api.model.dto.EmpresaDTO;
import br.com.analisador.api.model.dto.inputs.EmpresaInputDTO;
import br.com.analisador.domain.model.Empresa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpresaDTOAssemblerDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    public EmpresaDTO toDTO(Empresa empresa){
        return modelMapper.map(empresa, EmpresaDTO.class);
    }

    public List<EmpresaDTO> toCollectionDTO(List<Empresa> empresas) {
        return empresas.stream()
                .map(empresa -> toDTO(empresa))
                .collect(Collectors.toList());
    }

    public Empresa toDomainObject(EmpresaInputDTO empresaInput) {
        return modelMapper.map(empresaInput, Empresa.class);
    }
}
