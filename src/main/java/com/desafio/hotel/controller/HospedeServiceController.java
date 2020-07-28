package com.desafio.hotel.controller;

import com.desafio.hotel.domain.entity.Hospede;
import com.desafio.hotel.service.HospedeService;
import com.desafio.hotel.vo.HospedeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;

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
    public ResponseEntity<?> listAll(Pageable pageable) {
        try {
            Page<Hospede> hospedes = hospedeService.findAll(pageable);

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

    @GetMapping("/inHotel")
    public ResponseEntity<?> listAllInHotel(Pageable pageable) {
        try {
            Page<HospedeVO> hospedes = hospedeService.findAllinHotel(pageable);

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
    public ResponseEntity<?> listAllNotInHotel(Pageable pageable) {
        try {
            Page<HospedeVO> hospedes = hospedeService.findAllNotinHotel(pageable);

            if (hospedes == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(hospedes);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
