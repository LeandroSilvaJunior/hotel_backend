package com.desafio.hotel.service;

import com.desafio.hotel.domain.entity.Estadia;
import com.desafio.hotel.domain.entity.Hospede;
import com.desafio.hotel.domain.repository.EstadiaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EstadiaServiceImpl implements EstadiaService{

    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private HospedeService hospedeService;

    @Override
    public Estadia findById(Long id) {
        return estadiaRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Estadia> findAll(Pageable pageable) { return estadiaRepository.findAll(pageable); }

    @Override
    public Estadia create(Estadia estadia) throws Exception {
        Hospede hospede = this.findHospede(estadia.getHospede());
        estadia.setHospede(hospede);

        BigDecimal valorEstadia = this.calcularValorEstadia(estadia);
        estadia.setValor(valorEstadia);

        return estadiaRepository.save(estadia);
    }

    @Override
    public Estadia update(Long id, Estadia estadiaAtualizada) throws Exception {
        Optional<Estadia> estadiaPesistida = estadiaRepository.findById(id);

        if (estadiaPesistida.isPresent()) {
            return estadiaRepository.save(atualizarDadosEstadia(estadiaAtualizada, estadiaPesistida.get()));
        } else {
            throw new Exception("Não foi identificado nenhuma estadia com o código: " + id);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Estadia> hospedePesistido = estadiaRepository.findById(id);

        if (hospedePesistido.isPresent()) {
            estadiaRepository.delete(hospedePesistido.get());
        } else {
            throw new Exception("Não foi identificado nenhuma estadia com o código: " + id);
        }
    }

    private Estadia atualizarDadosEstadia(Estadia estadiaFonte, Estadia estadiaAlvo) throws Exception {
        if (!estadiaFonte.getDataEntrada().equals(estadiaAlvo.getDataEntrada())
                || !estadiaFonte.getDataEntrada().equals(estadiaAlvo.getDataEntrada())) {
            BigDecimal valorEstadia = this.calcularValorEstadia(estadiaFonte);
            estadiaFonte.setValor(valorEstadia);
        }

        Hospede hospede = this.findHospede(estadiaFonte.getHospede());
        estadiaFonte.setHospede(hospede);

        BeanUtils.copyProperties(estadiaFonte, estadiaAlvo, "id");
        return estadiaAlvo;
    }

    private Hospede findHospede(Hospede hospede) throws Exception {
        Hospede hospedePersistido = hospedeService.findFirstByIdOrNomeOrNumeroDocumentoOrNumeroTelefone(
                hospede.getId(),
                hospede.getNome(),
                hospede.getNumeroDocumento(),
                hospede.getNumeroTelefone());

        if (hospedePersistido == null) {
            throw new Exception("Não foi encontrado um hospede com os dados informados");
        } else {
            return hospedePersistido;
        }
    }

    private BigDecimal calcularValorEstadia(Estadia estadia) {
        LocalDateTime dataEntrada = estadia.getDataEntrada();
        LocalDateTime dataSaida = estadia.getDataSaida();

        BigDecimal valorEstadia = BigDecimal.ZERO;
        for (LocalDateTime date = dataEntrada; date.isBefore(dataSaida); date = date.plusDays(1)) {
            valorEstadia = valorEstadia.add(this.getValorDiaria(date.getDayOfWeek()));

            if (estadia.getAdicionalVeiculo()) {
                valorEstadia = valorEstadia.add(this.getValorAdicionalVeiculo(date.getDayOfWeek()));
            }
        }

        if (dataSaida.getHour() >= 16 && dataSaida.getMinute() > 30) {
            valorEstadia = valorEstadia.add(this.getValorDiaria(dataSaida.getDayOfWeek()));
        }

        return valorEstadia;
    }

    private BigDecimal getValorAdicionalVeiculo(DayOfWeek dayOfWeek) {
        if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            return BigDecimal.valueOf(20);
        } else {
            return BigDecimal.valueOf(15);
        }
    }

    private BigDecimal getValorDiaria(DayOfWeek dayOfWeek) {
        if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            return BigDecimal.valueOf(150);
        } else {
            return BigDecimal.valueOf(120);
        }
    }
}
