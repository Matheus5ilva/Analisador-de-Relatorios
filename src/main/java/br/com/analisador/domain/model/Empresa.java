package br.com.analisador.domain.model;

import br.com.analisador.domain.model.enums.TipoPessoa;
import jakarta.persistence.Entity;

@Entity
public class Empresa extends Pessoa {

    private String chaveApiKey;

    public Empresa() {
    }

    public Empresa(Long id, String nome, TipoPessoa tipoPessoa, Boolean ativo, String chaveApiKey) {
        super(id, nome, tipoPessoa, ativo);
        this.chaveApiKey = chaveApiKey;
    }

    public String getChaveApiKey() {
        return chaveApiKey;
    }

    public void setChaveApiKey(String chaveApiKey) {
        this.chaveApiKey = chaveApiKey;
    }
}
