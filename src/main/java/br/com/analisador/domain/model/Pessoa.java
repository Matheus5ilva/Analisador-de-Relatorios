package br.com.analisador.domain.model;

import br.com.analisador.core.validation.Groups;
import br.com.analisador.domain.model.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable{

    @Serial
    private static final long serialVersionUID = -3452958440683487318L;
    @NotNull(groups = Groups.EmpresaId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private TipoPessoa tipoPessoa;

    private Boolean ativo;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

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

    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public OffsetDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;

        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
