package br.com.analisador.api.controller;

import br.com.analisador.domain.exception.NegocioException;
import br.com.analisador.domain.exception.UsuarioNaoEncontradoException;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.model.dto.UsuarioDTO;
import br.com.analisador.domain.repository.UsuarioRepository;
import br.com.analisador.domain.service.EmpresaService;
import br.com.analisador.domain.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    private List<Usuario> listaUsuario(){
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/{usuarioId}")
    public Usuario detalharUsuario(@PathVariable Long usuarioId){
        return usuarioService.buscarOuFalhar(usuarioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody UsuarioDTO usuarioDto){
        return usuarioService.salvar(usuarioDto);
    }

    @PutMapping(value = "/editar/{usuarioId}")
    public Usuario editarUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioDTO usuarioDto){
        try{
            Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
            BeanUtils.copyProperties(usuarioDto, usuarioAtual, "id");
            return usuarioService.atualizar(usuarioAtual);
        }catch (UsuarioNaoEncontradoException usuarioException){
            throw new NegocioException(usuarioException.getMessage(), usuarioException);
        }
    }

    @DeleteMapping(value = "/deletar/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }
}
