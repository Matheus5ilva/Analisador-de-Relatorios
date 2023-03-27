package br.com.analisador.domain.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRelatorio;
    @Column(name = "analise", length = 5000)
    private String analise;
    private Integer numeroToken;
    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Usuario usuario;

    @CreationTimestamp
    private LocalDateTime dataHoraAnalise;

    public Resultado() {
    }

    public Resultado(Long id, String nomeRelatorio, String analise, Integer numeroToken, Empresa empresa, Usuario usuario, LocalDateTime dataHoraAnalise) {
        this.id = id;
        this.nomeRelatorio = nomeRelatorio;
        this.analise = analise;
        this.numeroToken = numeroToken;
        this.empresa = empresa;
        this.usuario = usuario;
        this.dataHoraAnalise = dataHoraAnalise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getNumeroToken() {
        return numeroToken;
    }

    public void setNumeroToken(Integer numeroToken) {
        this.numeroToken = numeroToken;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataHoraAnalise() {
        return dataHoraAnalise;
    }

    public void setDataHoraAnalise(LocalDateTime dataHoraAnalise) {
        this.dataHoraAnalise = dataHoraAnalise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resultado resultado)) return false;

        return id.equals(resultado.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
