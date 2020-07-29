package com.desafio.hotel.domain.repository.customRepository;

import com.desafio.hotel.domain.entity.Hospede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospedeCustomReposotoryImpl implements HospedeCustomReposotory {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Hospede> findAllByNomeOrNumeroDocumentoOrNumeroTelefone(
            String nome, String numeroDocumento, String numeroTelefone, Pageable pageable) {

        StringBuilder stringQuery = new StringBuilder();
        Map<String, String> parameters = new HashMap<>();

        stringQuery.append("select h from Hospede h ");
        String condicao = "where ";

        if (!StringUtils.isEmpty(nome)) {
            stringQuery.append(condicao).append("lower(h.nome) like lower(concat('%', :nome,'%')) ");
            condicao = "and ";
            parameters.put("nome", nome);
        }

        if (!StringUtils.isEmpty(numeroDocumento)) {
            stringQuery.append(condicao).append("h.numeroDocumento = :numeroDocumento ");
            condicao = "and ";
            parameters.put("numeroDocumento", numeroDocumento);
        }

        if (!StringUtils.isEmpty(numeroTelefone)) {
            stringQuery.append(condicao).append("h.numeroTelefone = :numeroTelefone ");
            parameters.put("numeroTelefone", numeroTelefone);
        }

        TypedQuery<Hospede> query = entityManager.createQuery(stringQuery.toString(), Hospede.class);
        parameters.forEach((k,v) -> query.setParameter(k,v));

        List<Hospede> resultQuery = query.getResultList();

        return new PageImpl<>(resultQuery, pageable, resultQuery.size());
    }
}
