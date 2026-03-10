package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.topico.TopicoService;
import com.alurachallenge.forohub.domain.topico.dto.DatosActualizarTopico;
import com.alurachallenge.forohub.domain.topico.dto.DatosListadoTopico;
import com.alurachallenge.forohub.domain.topico.dto.DatosRegistroTopico;
import com.alurachallenge.forohub.domain.topico.dto.DatosRespuestaTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;

    // POST /topicos - Registrar nuevo tópico
    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrar(
            @RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriComponentsBuilder) {
        var response = service.registrar(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    // GET /topicos - Listar todos los tópicos
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        var response = service.listar(paginacion);
        return ResponseEntity.ok(response);
    }

    // GET /topicos/{id} - Detalle de un tópico específico
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallar(@PathVariable Long id) {
        var response = service.obtenerPorId(id);
        return ResponseEntity.ok(response);
    }

    // PUT /topicos/{id} - Actualizar un tópico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {
        var response = service.actualizar(id, datos);
        return ResponseEntity.ok(response);
    }

    // DELETE /topicos/{id} - Eliminar un tópico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}