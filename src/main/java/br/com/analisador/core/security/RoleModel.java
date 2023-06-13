package br.com.analisador.core.security;


import org.springframework.security.core.GrantedAuthority;

public enum RoleModel implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_CUSTOM;

    @Override
    public String getAuthority() {
        return name();
    }
}
