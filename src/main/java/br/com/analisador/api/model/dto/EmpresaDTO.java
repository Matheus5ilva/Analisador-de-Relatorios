package br.com.analisador.api.model.dto;

import br.com.analisador.domain.model.enums.TipoPessoa;

public class EmpresaDTO {

    private Long id;
    private String nome;
    private TipoPessoa tipoPessoa;
    private Boolean ativo;
    private String chaveApiKey;

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

    public String getChaveApiKey() {
        return chaveApiKey;
    }

    public void setChaveApiKey(String chaveApiKey) {
        this.chaveApiKey = chaveApiKey;
    }
}
