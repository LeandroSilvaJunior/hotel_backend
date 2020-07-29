package com.desafio.hotel.domain.repository.customRepository;

import com.desafio.hotel.domain.entity.Hospede;

import java.util.List;


public interface HospedeCustomReposotory {

    List<Hospede> findAllByNomeOrNumeroDocumentoOrNumeroTelefone(String nome, String numeroDocumento, String numeroTelefone);
}
