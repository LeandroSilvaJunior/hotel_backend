package com.desafio.hotel.service;

import com.desafio.hotel.domain.entity.Estadia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstadiaService {

    Estadia findById(Long id);

    Page<Estadia> findAll(Pageable pageable);

    Estadia create(Estadia estadia) throws Exception;

    Estadia update(Long id, Estadia estadiaAtualizada) throws Exception;

    void delete(Long id) throws Exception;
}
