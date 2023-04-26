package br.com.analisador.api.model.assembler;

import br.com.analisador.api.model.dto.UsuarioDTO;
import br.com.analisador.api.model.dto.UsuarioEmpresaDTO;
import br.com.analisador.api.model.dto.inputs.EmpresaInputDTO;
import br.com.analisador.api.model.dto.inputs.UsuarioInputDTO;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioDTOAssemblerDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    public UsuarioDTO toDTO(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> toCollectionDto(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> toDTO(usuario))
                .collect(Collectors.toList());
    }

    public Usuario toDomainObject(UsuarioInputDTO usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }
}
