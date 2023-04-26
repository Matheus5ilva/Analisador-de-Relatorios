package br.com.analisador.api.model.dto;

public class UsuarioEmpresaDTO {

    private Long id;
    private String nome;
    private Boolean ativo;

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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
