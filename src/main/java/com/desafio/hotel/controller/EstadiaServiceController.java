package com.desafio.hotel.controller;

import com.desafio.hotel.domain.entity.Estadia;
import com.desafio.hotel.service.EstadiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("estadia")
public class EstadiaServiceController implements ServiceController {

    @Autowired
    private EstadiaService estadiaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> listOne(@PathVariable Long id) {
        try {
            Estadia estadia = estadiaService.findById(id);

            if (estadia == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(estadia);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping()
    public ResponseEntity<?> listAll() {
        try {
            List<Estadia> estadia = estadiaService.findAll();

            if (estadia == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(estadia);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/checkin")
    public ResponseEntity<?> create(@RequestBody @Valid Estadia estadia) {
        try {
            Estadia estadiaPersistida = estadiaService.create(estadia);

            URI uri = this.getDomainURI(estadiaPersistida.getId());

            return ResponseEntity.created(uri).body(estadia);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Estadia estadiaAtualizada, @PathVariable Long id) {
        try {
            Estadia estadiaPersistida = estadiaService.update(id, estadiaAtualizada);
            return ResponseEntity.ok(estadiaPersistida);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            estadiaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
