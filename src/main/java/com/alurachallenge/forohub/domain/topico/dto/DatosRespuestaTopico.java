package com.alurachallenge.forohub.domain.topico.dto;


import com.alurachallenge.forohub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estado,
        Long autorId,
        String autorNombre,
        String autorCorreo
) {
    public DatosRespuestaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado().toString(),
                topico.getUsuario().getId(),
                topico.getUsuario().getNombre(),
                topico.getUsuario().getCorreoElectronico()
        );
    }
}