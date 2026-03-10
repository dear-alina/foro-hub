package com.alurachallenge.forohub.domain.respuesta;


import com.alurachallenge.forohub.domain.topico.Topico;
import com.alurachallenge.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")

    private Usuario usuario;
    private Boolean solucion = false;

    public Respuesta(String mensaje, Topico topico, Usuario usuario) {
        this.mensaje = mensaje;
        this.topico = topico;
        this.usuario = usuario;
        this.fechaCreacion = LocalDateTime.now();
    }
}