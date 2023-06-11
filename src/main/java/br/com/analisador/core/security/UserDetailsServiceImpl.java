package br.com.analisador.core.security;

import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.model.enums.TipoUsuario;
import br.com.analisador.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha não foi encontrado"));

        UsuarioDetails usuarioDetails = new UsuarioDetails();
        usuarioDetails.setUsuario(usuario);

        return new User(usuarioDetails.getUsername(), usuarioDetails.getPassword(), true, true, true, true, usuarioDetails.getAuthorities());
    }
}
