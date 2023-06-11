package br.com.analisador.domain.service;

import br.com.analisador.domain.exception.EntidadeEmUsoException;
import br.com.analisador.domain.exception.UsuarioNaoEncontradoException;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private static final String MSG_USUARIO_EM_USO = "Usuário de código %d não pode ser removida, pois está em uso";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaService empresaService;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        logger.info("Salvando usuário: {}", usuario);
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        Empresa empresa = empresaService.buscarOuFalhar(usuario.getEmpresa().getId());
        usuario.setEmpresa(empresa);
        usuario = usuarioRepository.save(usuario);
        logger.info("Usuário salvo: {}", usuario.getId());
        return usuario;
    }

    @Transactional
    public void excluir(Long usuarioId) {
        logger.info("Excluindo usuário de id: {}", usuarioId);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
        try {
            usuarioRepository.delete(usuario);
            usuarioRepository.flush();
            logger.info("Usuário excluído com sucesso: {}", usuarioId);
        } catch (DataIntegrityViolationException e) {
            logger.error("Não foi possível excluir o usuário de id {}, pois está em uso", usuarioId);
            throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
        }
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        logger.info("Buscando usuário de id: {}", usuarioId);
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> {
                    logger.error("Usuário de id {} não encontrado", usuarioId);
                    return new UsuarioNaoEncontradoException(usuarioId);
                });
    }
}
