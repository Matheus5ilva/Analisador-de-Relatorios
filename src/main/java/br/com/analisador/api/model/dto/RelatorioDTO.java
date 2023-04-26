package br.com.analisador.api.model.dto;

public class RelatorioDTO {

    private Long numeroAnalise;
    private String nomeRelatorio;

    /** Getter e Setter **/
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
