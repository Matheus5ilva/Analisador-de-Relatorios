package br.com.analisador.api.controller;

import br.com.analisador.domain.exception.NegocioException;
import br.com.analisador.domain.exception.UsuarioNaoEncontradoException;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.model.dto.UsuarioDTO;
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

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @GetMapping
    private List<Usuario> listaUsuario() {
        logger.info("Buscando lista de usuários");
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/{usuarioId}")
    public Usuario detalharUsuario(@PathVariable Long usuarioId) {
        logger.info("Detalhando usuário com id {}", usuarioId);
        return usuarioService.buscarOuFalhar(usuarioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDto) {
        logger.info("Criando novo usuário: {}", usuarioDto.toString());
        return usuarioService.salvar(usuarioDto);
    }

    @PutMapping(value = "/editar/{usuarioId}")
    public Usuario editarUsuario(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioDTO usuarioDto) {
        try {
            Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
            BeanUtils.copyProperties(usuarioDto, usuarioAtual, "id");
            logger.info("Atualizando usuário com id {}: {}", usuarioId, usuarioDto.toString());
            return usuarioService.atualizar(usuarioAtual);
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
