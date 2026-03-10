package com.alurachallenge.forohub.domain.topico;

import com.alurachallenge.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructor para crear un nuevo tópico (sin id, con fecha y estado por defecto)
    public Topico(String titulo, String mensaje, Usuario usuario) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.usuario = usuario;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = Estado.ACTIVO;
    }

    // Metodo para actualizar datos (podría usarse en el service)
    public void actualizarDatos(String titulo, String mensaje) {
        this.titulo = titulo;
        this.mensaje = mensaje;
    }
}