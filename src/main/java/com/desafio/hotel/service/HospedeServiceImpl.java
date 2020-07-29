package com.desafio.hotel.service;

import com.desafio.hotel.domain.entity.Hospede;
import com.desafio.hotel.domain.repository.HospedeRepository;
import com.desafio.hotel.vo.HospedeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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

    public Hospede findFirstByIdOrNomeOrNumeroDocumentoOrNumeroTelefone(
            Long id, String nome, String numeroDocumento, String numeroTelefone) {
        return hospedeRepository.findFirstByIdOrNomeOrNumeroDocumentoOrNumeroTelefone(id, nome, numeroDocumento, numeroTelefone);
    }

    @Override
    public Page<Hospede> findAllByNomeOrNumeroDocumentoOrNumeroTelefone(
            String nome, String numeroDocumento, String numeroTelefone, Pageable pageable) {

        if (StringUtils.isEmpty(nome) && StringUtils.isEmpty(numeroDocumento) && StringUtils.isEmpty(numeroTelefone)) {
            return null;
        } else {
            return hospedeRepository.findAllByNomeOrNumeroDocumentoOrNumeroTelefone(nome, numeroDocumento, numeroTelefone, pageable);
        }
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
