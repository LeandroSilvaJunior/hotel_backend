package com.desafio.hotel.vo;

import com.desafio.hotel.domain.entity.Estadia;
import com.desafio.hotel.domain.entity.Hospede;

import java.math.BigDecimal;
import java.util.Comparator;

public class HospedeVO {
    private Long id;
    private String nome;
    private String numeroDocumento;
    private String numeroTelefone;
    private BigDecimal totalGasto;
    private BigDecimal totalGastoUltimaEstadia;

    public HospedeVO(Hospede hospede) {
        this.id = hospede.getId();
        this.nome = hospede.getNome();
        this.numeroDocumento = hospede.getNumeroDocumento();
        this.numeroTelefone = hospede.getNumeroTelefone();
        this.totalGasto = hospede.getEstadias().stream().map(Estadia::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.totalGastoUltimaEstadia = hospede.getEstadias().stream().sorted(Comparator.comparing(Estadia::getDataSaida)).findFirst().get().getValor();
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

    public BigDecimal getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(BigDecimal totalGasto) {
        this.totalGasto = totalGasto;
    }

    public BigDecimal getTotalGastoUltimaEstadia() {
        return totalGastoUltimaEstadia;
    }

    public void setTotalGastoUltimaEstadia(BigDecimal totalGastoUltimaEstadia) {
        this.totalGastoUltimaEstadia = totalGastoUltimaEstadia;
    }
}
