package br.com.analisador.domain.model.dto;

import br.com.analisador.domain.model.Empresa;
import br.com.analisador.domain.model.enums.TipoPessoa;
import br.com.analisador.domain.service.EmpresaService;

public class EmpresaDTO {

    private String nome;
    private TipoPessoa tipoPessoa;

    private Boolean ativo;
    private String chaveApiKey;

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

    public Empresa transformaEmObjeto(){
        return new Empresa(null, this.getNome(), this.getTipoPessoa(), this.getAtivo(), this.getChaveApiKey());
    }
}
