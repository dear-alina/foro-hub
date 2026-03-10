package com.alurachallenge.forohub.domain.topico;

import com.alurachallenge.forohub.domain.topico.dto.DatosActualizarTopico;
import com.alurachallenge.forohub.domain.topico.dto.DatosListadoTopico;
import com.alurachallenge.forohub.domain.topico.dto.DatosRegistroTopico;
import com.alurachallenge.forohub.domain.topico.dto.DatosRespuestaTopico;
import com.alurachallenge.forohub.domain.topico.validaciones.ValidadorDeTopicos;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import com.alurachallenge.forohub.domain.usuario.UsuarioRepository;
import com.alurachallenge.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorDeTopicos> validadores;

    // Registrar un nuevo tópico
    @Transactional
    public DatosRespuestaTopico registrar(DatosRegistroTopico datos) {
        // Ejecutar todas las validaciones
        validadores.forEach(v -> v.validar(datos));

        // Una vez validadas, obtenemos la referencia del autor
        Usuario autor = usuarioRepository.getReferenceById(datos.usuarioId());
        Topico topico = new Topico(datos.titulo(), datos.mensaje(), autor);
        topico = topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }

    // Listar todos los tópicos con paginación
    public Page<DatosListadoTopico> listar(Pageable paginacion) {
        return topicoRepository.findAll(paginacion)
                .map(DatosListadoTopico::new);
    }

    // Obtener un tópico por ID
    public DatosRespuestaTopico obtenerPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("El tópico no existe"));
        return new DatosRespuestaTopico(topico);
    }

    // Actualizar un tópico
    @Transactional
    public DatosRespuestaTopico actualizar(Long id, DatosActualizarTopico datos) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("El tópico no existe"));

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje()) &&
                !(topico.getTitulo().equals(datos.titulo()) && topico.getMensaje().equals(datos.mensaje()))) {
            throw new ValidacionDeIntegridad("Ya existe otro tópico con el mismo título y mensaje");
        }

        topico.actualizarDatos(datos.titulo(), datos.mensaje());
        return new DatosRespuestaTopico(topico);
    }

    // Eliminar un tópico
    @Transactional
    public void eliminar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("El tópico no existe");
        }
        topicoRepository.deleteById(id);
    }
}