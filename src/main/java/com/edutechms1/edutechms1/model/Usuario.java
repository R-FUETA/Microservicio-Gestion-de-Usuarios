package com.edutechms1.edutechms1.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad JPA para representar a los usuarios en la base de datos.
 */
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String contrasena; // En este caso, en texto plano

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    private boolean activo;
}
