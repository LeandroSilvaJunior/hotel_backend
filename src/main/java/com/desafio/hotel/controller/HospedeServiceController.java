package com.desafio.hotel.controller;

import com.desafio.hotel.domain.entity.Hospede;
import com.desafio.hotel.service.HospedeService;
import com.desafio.hotel.vo.HospedeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("hospede")
public class HospedeServiceController implements ServiceController{

    @Autowired
    private HospedeService hospedeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> listOne(@PathVariable Long id) {
        try {
            Hospede hospede = hospedeService.findById(id);

            if (hospede == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(hospede);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping()
    public ResponseEntity<?> listAll() {
        try {
            List<Hospede> hospedes = hospedeService.findAll();

            if (hospedes == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(hospedes);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/inHotel")
    public ResponseEntity<?> listAllInHotel() {
        try {
            List<HospedeVO> hospedes = hospedeService.findAllinHotel();

            if (hospedes == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(hospedes);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/notInHotel")
    public ResponseEntity<?> listAllNotInHotel() {
        try {
            List<HospedeVO> hospedes = hospedeService.findAllNotinHotel();

            if (hospedes == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(hospedes);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/filtro")
    public ResponseEntity<?> listAllByNomeOrNumeroDocumentoOrNumeroTelefone(
            @PathParam("nome") String nome,
            @PathParam("numeroDocumento") String numeroDocumento,
            @PathParam("numeroTelefone") String numeroTelefone) {

        try {
            List<Hospede> hospedes = hospedeService.findAllByNomeOrNumeroDocumentoOrNumeroTelefone(nome, numeroDocumento, numeroTelefone);

            if (hospedes == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(hospedes);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Hospede hospede) {
        try {
            Hospede hospedePersistido = hospedeService.create(hospede);

            URI uri = this.getDomainURI(hospedePersistido.getId());

            return ResponseEntity.created(uri).body(hospede);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Hospede hospedeAtualizado, @PathVariable Long id) {
        try {
            Hospede hospedePersistido = hospedeService.update(id, hospedeAtualizado);
            return ResponseEntity.ok(hospedePersistido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            hospedeService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
