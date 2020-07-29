package com.desafio.hotel.service;

import com.desafio.hotel.domain.entity.Hospede;
import com.desafio.hotel.vo.HospedeVO;

import java.util.List;

public interface HospedeService {

    Hospede findById(Long id);

    List<Hospede> findAll();

    Hospede create(Hospede hospede);

    Hospede update(Long id, Hospede hospedeAtualizado) throws Exception;

    void delete(Long id) throws Exception;

    Hospede findFirstByIdOrNomeOrNumeroDocumentoOrNumeroTelefone(Long id, String nome, String numeroDocumento, String numeroTelefone);

    List<Hospede> findAllByNomeOrNumeroDocumentoOrNumeroTelefone(String nome, String numeroDocumento, String numeroTelefone);

    List<HospedeVO> findAllinHotel();

    List<HospedeVO> findAllNotinHotel();
}
