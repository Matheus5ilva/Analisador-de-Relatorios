package br.com.analisador.domain.model;

import br.com.analisador.domain.model.enums.TipoPessoa;
import br.com.analisador.domain.model.enums.TipoUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario extends Pessoa{
    @ManyToOne
    private Empresa empresa;
    private String email;
    private TipoUsuario tipoUsuario;

    public Usuario() {
        super();
    }

    public Usuario(Long id, String nome, TipoPessoa tipoPessoa, Boolean ativo, Empresa empresa, String email, TipoUsuario tipoUsuario) {
        super(id, nome, tipoPessoa, ativo);
        this.empresa = empresa;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
