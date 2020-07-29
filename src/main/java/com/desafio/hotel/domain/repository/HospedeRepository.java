package com.desafio.hotel.domain.repository;

import com.desafio.hotel.domain.entity.Hospede;
import com.desafio.hotel.domain.repository.customRepository.HospedeCustomReposotory;
import com.desafio.hotel.vo.HospedeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long>, HospedeCustomReposotory {

    Hospede findFirstByIdOrNomeOrNumeroDocumentoOrNumeroTelefone(
            Long id,
            String nome,
            String numeroDocumento,
            String numeroTelefone);

    @Query(value = "select distinct new com.desafio.hotel.vo.HospedeVO(h) " +
            "from Hospede h " +
            "join h.estadias e " +
            "where e is not null " +
            "and current_date between e.dataEntrada and e.dataSaida",
            countQuery = "select distinct count(h) " +
                    "from Hospede h " +
                    "join h.estadias e " +
                    "where e is not null " +
                    "and current_date between e.dataEntrada and e.dataSaida")
    Page<HospedeVO> findAllInHotel(Pageable pageable);

    @Query(value = "select distinct new com.desafio.hotel.vo.HospedeVO(h) " +
            "from Hospede h " +
            "join h.estadias e " +
            "where e is not null " +
            "and e.dataSaida < current_date ",
            countQuery = "select distinct count(h) " +
                    "from Hospede h " +
                    "join h.estadias e " +
                    "where e is not null " +
                    "and e.dataSaida < current_date ")
    Page<HospedeVO> findAllNotInHotel(Pageable pageable);
}
