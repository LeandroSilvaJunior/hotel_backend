package com.desafio.hotel.service;

import com.desafio.hotel.domain.entity.Hospede;
import com.desafio.hotel.vo.HospedeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospedeService {

    Hospede findById(Long id);

    Page<Hospede> findAll(Pageable pageable);

    Hospede create(Hospede hospede);

    Hospede update(Long id, Hospede hospedeAtualizado) throws Exception;

    void delete(Long id) throws Exception;

    Hospede findFirstByNomeOrNumeroDocumentoOrNumeroTelefone(String nome, String numeroDocumento, String numeroTelefone);

    Page<HospedeVO> findAllinHotel(Pageable pageable);

    Page<HospedeVO> findAllNotinHotel(Pageable pageable);
}
