package br.com.analisador.domain.model;

import br.com.analisador.core.validation.Groups;
import br.com.analisador.domain.model.enums.TipoPessoa;
import br.com.analisador.domain.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;


@Entity
public class Usuario extends Pessoa{
    @Valid
    @ConvertGroup(from = Default.class, to = Groups.EmpresaId.class)
    @NotNull
    @ManyToOne
    private Empresa empresa;

    @Email
    private String email;
    @NotNull
    private TipoUsuario tipoUsuario;

    /** Getter e Setter **/
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
