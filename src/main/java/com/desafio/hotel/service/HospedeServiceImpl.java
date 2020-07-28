package com.desafio.hotel.service;

import com.desafio.hotel.domain.entity.Hospede;
import com.desafio.hotel.domain.repository.HospedeRepository;
import com.desafio.hotel.vo.HospedeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospedeServiceImpl implements HospedeService {

    @Autowired
    private HospedeRepository hospedeRepository;

    @Override
    public Hospede findById(Long id) {
        return hospedeRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Hospede> findAll(Pageable pageable) {
        return hospedeRepository.findAll(pageable);
    }

    @Override
    public Hospede create(Hospede hospede) {
        return hospedeRepository.save(hospede);
    }

    @Override
    public Hospede update(Long id, Hospede hospedeAtualizado) throws Exception {
        Optional<Hospede> hospedePesistido = hospedeRepository.findById(id);

        if (hospedePesistido.isPresent()) {
            return hospedeRepository.save(atualizarDadosHospede(hospedeAtualizado, hospedePesistido.get()));
        } else {
            throw new Exception("Não foi identificado nenhum hospede com o código: " + id);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Hospede> hospedePesistido = hospedeRepository.findById(id);

        if (hospedePesistido.isPresent()) {
            hospedeRepository.delete(hospedePesistido.get());
        } else {
            throw new Exception("Não foi identificado nenhum hospede com o código: " + id);
        }
    }

    @Override
    public Hospede findFirstByNomeOrNumeroDocumentoOrNumeroTelefone(String nome, String numeroDocumento, String numeroTelefone) {
        return hospedeRepository.findFirstByNomeOrNumeroDocumentoOrNumeroTelefone(nome, numeroDocumento, numeroTelefone);
    }

    @Override
    public Page<HospedeVO> findAllinHotel(Pageable pageable) {
        return hospedeRepository.findAllInHotel(pageable);
    }

    @Override
    public Page<HospedeVO> findAllNotinHotel(Pageable pageable) {
        return hospedeRepository.findAllNotInHotel(pageable);
    }

    private Hospede atualizarDadosHospede(Hospede hospedeFonte, Hospede hospedeAlvo) {
        BeanUtils.copyProperties(hospedeFonte, hospedeAlvo,"id");
        return hospedeAlvo;
    }
}
