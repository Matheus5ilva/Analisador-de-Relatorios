package br.com.analisador.api.model.dto.inputs;

import br.com.analisador.domain.model.enums.TipoPessoa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmpresaInputDTO {

    @NotBlank
    private String nome;
    @NotNull
    private TipoPessoa tipoPessoa;
    @NotNull
    private Boolean ativo;
    @NotBlank
    private String chaveApiKey;

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

    public String getChaveApiKey() {
        return chaveApiKey;
    }

    public void setChaveApiKey(String chaveApiKey) {
        this.chaveApiKey = chaveApiKey;
    }
}
