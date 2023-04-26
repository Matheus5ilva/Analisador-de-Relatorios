package br.com.analisador.api.model.dto;

import br.com.analisador.domain.model.enums.TipoPessoa;
import br.com.analisador.domain.model.enums.TipoUsuario;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private TipoPessoa tipoPessoa;
    private Boolean ativo;
    private String email;
    private TipoUsuario tipoUsuario;
    private UsuarioEmpresaDTO empresa;

    /** Getter e Setter **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UsuarioEmpresaDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(UsuarioEmpresaDTO empresa) {
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
