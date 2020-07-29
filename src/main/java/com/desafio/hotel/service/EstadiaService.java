package com.desafio.hotel.service;

import com.desafio.hotel.domain.entity.Estadia;

import java.util.List;

public interface EstadiaService {

    Estadia findById(Long id);

    List<Estadia> findAll();

    Estadia create(Estadia estadia) throws Exception;

    Estadia update(Long id, Estadia estadiaAtualizada) throws Exception;

    void delete(Long id) throws Exception;
}
