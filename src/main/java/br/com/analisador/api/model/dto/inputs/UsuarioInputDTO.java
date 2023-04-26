package br.com.analisador.api.model.dto.inputs;

import br.com.analisador.api.model.dto.inputs.ids.EmpresaIdDTO;
import br.com.analisador.domain.model.enums.TipoPessoa;
import br.com.analisador.domain.model.enums.TipoUsuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    private TipoPessoa tipoPessoa;
    @NotNull
    private Boolean ativo;

    @NotNull
    private EmpresaIdDTO empresa;
    @Email
    private String email;
    @NotNull
    private TipoUsuario tipoUsuario;

    /** Getter e Setter **/
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

    public EmpresaIdDTO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaIdDTO empresa) {
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
