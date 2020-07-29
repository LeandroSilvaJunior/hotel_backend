package com.desafio.hotel.domain.repository;

import com.desafio.hotel.domain.entity.Estadia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadiaRepository extends JpaRepository<Estadia, Long> {

}
