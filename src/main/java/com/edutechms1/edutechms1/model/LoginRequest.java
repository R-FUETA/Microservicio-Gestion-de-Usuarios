package com.edutechms1.edutechms1.model;

import lombok.Data;

/**
 * DTO para encapsular los datos de inicio de sesión del usuario.
 */
@Data
public class LoginRequest {
    private String correo;
    private String contrasena;
}
