package br.com.analisador.domain.model.enums;

public enum TipoPessoa {
    EMPRESA("Empresa"),
    USUARIO("Usuário");

    private String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

