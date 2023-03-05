package br.com.analisador.domain.model;

import jakarta.persistence.Entity;

@Entity
public class Empresa extends Pessoa{

    private String chaveApiKey;

    public String getChaveApiKey() {
        return chaveApiKey;
    }

    public void setChaveApiKey(String chaveApiKey) {
        this.chaveApiKey = chaveApiKey;
    }
}
