package br.com.analisador.domain.model.dto;

public class RelatorioDTO {

    private Long numeroAnalise;
    private String nomeRelatorio;

    public Long getNumeroAnalise() {
        return numeroAnalise;
    }

    public void setNumeroAnalise(Long numeroAnalise) {
        this.numeroAnalise = numeroAnalise;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }
}
