package br.com.analisador.domain.service;

import br.com.analisador.domain.exception.EntidadeEmUsoException;
import br.com.analisador.domain.exception.UsuarioNaoEncontradoException;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private static final String MSG_USUARIO_EM_USO
            = "Usuário de código %d não pode ser removida, pois está em uso";
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaService empresaService;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        Empresa empresa = empresaService.buscarOuFalhar(usuario.getEmpresa().getId());
        usuario.setEmpresa(empresa);
        return usuarioRepository.save(usuario);

    }

    @Transactional
    public void excluir(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
        try {
            usuarioRepository.delete(usuario);
            usuarioRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
        }
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
