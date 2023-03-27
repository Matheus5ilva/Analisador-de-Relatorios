package br.com.analisador.domain.model.dto;

import br.com.analisador.core.validation.Groups;
import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.Usuario;
import br.com.analisador.domain.model.enums.TipoPessoa;
import br.com.analisador.domain.model.enums.TipoUsuario;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

public class UsuarioDTO {

    @NotBlank
    private String nome;

    @NotNull
    private TipoPessoa tipoPessoa;
    @NotNull
    private Boolean ativo;
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.EmpresaId.class)
    @NotNull
    private Empresa empresa;
    @Email
    private String email;
    @NotNull
    private TipoUsuario tipoUsuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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

    public Usuario transformaObjeto(){
        return new Usuario(null, this.getNome(), this.getTipoPessoa(), this.getAtivo(), this.getEmpresa(), this.getEmail(), this.getTipoUsuario());
    }
}
