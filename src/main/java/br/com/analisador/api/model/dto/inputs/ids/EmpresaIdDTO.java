package br.com.analisador.api.model.dto.inputs.ids;

import javax.validation.constraints.NotNull;

public class EmpresaIdDTO {

    @NotNull
    private Long id;

    /** Getter e Setter **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
