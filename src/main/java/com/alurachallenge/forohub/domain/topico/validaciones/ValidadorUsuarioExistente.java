package com.alurachallenge.forohub.domain.topico.validaciones;

import com.alurachallenge.forohub.domain.topico.dto.DatosRegistroTopico;
import com.alurachallenge.forohub.domain.usuario.UsuarioRepository;
import com.alurachallenge.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsuarioExistente implements ValidadorDeTopicos {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosRegistroTopico datos) {
        if (!usuarioRepository.existsById(datos.usuarioId())) {
            throw new ValidacionDeIntegridad("El usuario especificado no existe");
        }
    }
}