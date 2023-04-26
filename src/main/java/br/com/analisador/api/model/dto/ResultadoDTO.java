package br.com.analisador.api.model.dto;

public class ResultadoDTO {

    private String nomeRelatorio;
    private String analise;

    /** Getter e Setter **/
    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }
}
