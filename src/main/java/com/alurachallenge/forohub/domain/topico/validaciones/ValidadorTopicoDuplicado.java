package com.alurachallenge.forohub.domain.topico.validaciones;

import com.alurachallenge.forohub.domain.topico.TopicoRepository;
import com.alurachallenge.forohub.domain.topico.dto.DatosRegistroTopico;
import com.alurachallenge.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoDuplicado implements ValidadorDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionDeIntegridad("Ya existe un tópico con el mismo título y mensaje");
        }
    }
}