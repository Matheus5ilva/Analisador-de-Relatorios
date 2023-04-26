package br.com.analisador.domain.model;

import br.com.analisador.domain.model.enums.TipoPessoa;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;


@Entity
public class Empresa extends Pessoa {

    @NotBlank
    private String chaveApiKey;

    /** Getter e Setter **/
    public String getChaveApiKey() {
        return chaveApiKey;
    }

    public void setChaveApiKey(String chaveApiKey) {
        this.chaveApiKey = chaveApiKey;
    }
}
