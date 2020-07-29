package com.desafio.hotel.domain.repository.customRepository;

import com.desafio.hotel.domain.entity.Hospede;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface HospedeCustomReposotory {

    Page<Hospede> findAllByNomeOrNumeroDocumentoOrNumeroTelefone(String nome, String numeroDocumento, String numeroTelefone, Pageable pageable);
}
