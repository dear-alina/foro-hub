package com.alurachallenge.forohub.domain.topico.dto;

import com.alurachallenge.forohub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estado,
        String usuarioNombre
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado().toString(),
                topico.getUsuario().getNombre()
        );
    }
}