package br.com.analisador.domain.service;

import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    @Transactional
    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    @Transactional
    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();

        } catch (RuntimeException e) {
            throw new RuntimeException(
                    String.format("Não existe um cadastro de usuário com codigo %d", usuarioId));

        }
    }

    public Usuario buscarOuFalhar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException(
                String.format("Não existe um cadastro de usuário com codigo %d", usuarioId)));
    }
}
