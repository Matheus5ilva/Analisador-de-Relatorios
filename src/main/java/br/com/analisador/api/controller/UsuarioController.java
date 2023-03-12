package br.com.analisador.api.controller;

import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.repository.UsuarioRepository;
import br.com.analisador.domain.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    private List<Usuario> listaUsuario(){
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/{usuarioId}")
    public Usuario detalharUsuario(@PathVariable Long usuarioId){
        return usuarioService.buscarOuFalhar(usuarioId);
    }

    @PostMapping()
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioService.salvar(usuario);
    }

    @PutMapping(value = "/editar/{usuarioId}")
    public Usuario editarUsuario(@PathVariable Long usuarioId, @RequestBody Usuario usuario){
        Usuario usuarioAtual = usuarioService.buscarOuFalhar(usuarioId);
        BeanUtils.copyProperties(usuario, usuarioAtual, "id");
        return usuarioService.atualizar(usuarioAtual);
    }

    @DeleteMapping(value = "/deletar/{usuarioId}")
    public void deletarUsuario(@PathVariable Long usuarioId){
        usuarioService.excluir(usuarioId);
    }
}
