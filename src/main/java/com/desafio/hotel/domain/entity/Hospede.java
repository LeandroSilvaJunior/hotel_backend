package com.desafio.hotel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @NotEmpty(message = "O número do documento precisa ser informado")
    @Column(nullable = false, unique = true, length = 11)
    private String numeroDocumento;

    @NotEmpty(message = "O número do telefone precisa ser informado")
    @Column(nullable = false, length = 11)
    private String numeroTelefone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospede")
    @JsonIgnore
    private Set<Estadia> estadias = new HashSet<>();

    public Hospede(String nome, String numeroDocumento, String numeroTelefone) {
        this.nome = nome;
        this.numeroDocumento = numeroDocumento;
        this.numeroTelefone = numeroTelefone;
    }

    public Hospede() {
    }

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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Set<Estadia> getEstadias() {
        return estadias;
    }

    public void setEstadias(Set<Estadia> estadias) {
        this.estadias = estadias;
    }
}
