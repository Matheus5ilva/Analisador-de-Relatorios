package br.com.analisador.api.controller;

import br.com.analisador.api.model.assembler.UsuarioDTOAssemblerDisassembler;
import br.com.analisador.api.model.dto.inputs.UsuarioInputDTO;
import br.com.analisador.domain.exception.NegocioException;
import br.com.analisador.domain.exception.UsuarioNaoEncontradoException;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.api.model.dto.UsuarioDTO;
import br.com.analisador.domain.repository.UsuarioRepository;
import br.com.analisador.domain.service.EmpresaService;
import br.com.analisador.domain.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioDTOAssemblerDisassembler usuarioDTOAssemblerDisassembler;

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping
    private List<UsuarioDTO> listaUsuario() {
        logger.info("Buscando lista de usuários");
        return usuarioDTOAssemblerDisassembler.toCollectionDto(usuarioRepository.findAll());
    }

    @GetMapping(value = "/{usuarioId}")
    public UsuarioDTO detalharUsuario(@PathVariable Long usuarioId) {
        logger.info("Detalhando usuário com id {}", usuarioId);
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
        return usuarioDTOAssemblerDisassembler.toDTO(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO criarUsuario(@RequestBody @Valid UsuarioInputDTO usuarioDto) {
        logger.info("Criando novo usuário: {}", usuarioDto.toString());
        Usuario usuario = usuarioDTOAssemblerDisassembler.toDomainObject(usuarioDto);
        return usuarioDTOAssemblerDisassembler.toDTO(usuarioService.salvar(usuario));
    }

    @PutMapping(value = "/editar/{usuarioId}")
    public UsuarioDTO editarUsuario(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInputDTO usuarioDto) {
        try {
            logger.info("Atualizando usuário com id {}: {}", usuarioId, usuarioDto.toString());
            Usuario usuario = usuarioDTOAssemblerDisassembler.toDomainObject(usuarioDto);
            Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
            BeanUtils.copyProperties(usuario, usuarioAtual, "id", "dataCadastro");
            return usuarioDTOAssemblerDisassembler.toDTO(usuarioService.salvar(usuarioAtual));
        } catch (UsuarioNaoEncontradoException usuarioException) {
            logger.error("Usuário não encontrado ao tentar atualizar usuário com id {}: {}", usuarioId, usuarioDto.toString(), usuarioException);
            throw new NegocioException(usuarioException.getMessage(), usuarioException);
        }
    }

    @DeleteMapping(value = "/deletar/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long usuarioId) {
        logger.info("Deletando usuário com id {}", usuarioId);
        usuarioService.excluir(usuarioId);
    }
}
