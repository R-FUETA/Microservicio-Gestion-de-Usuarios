package com.edutechms1.edutechms1.controller;

import com.edutechms1.edutechms1.model.Usuario;
import com.edutechms1.edutechms1.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = ("Usuarios relacionados"))
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Operation(summary = "Crear Usuario", description = "Permite Crear Usuarios")
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }
    @Operation(summary = "Obtener", description = "Obtiene Usuarios por ID")
    @GetMapping("id/{id}")
    public Usuario obtener(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }
    @Operation(summary = "Actualizar", description = "Actualizar Usuarios")
    @PutMapping("id/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }
    @Operation(summary = "Eliminar", description = "Elimina Usuarios")
    @DeleteMapping("id/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
    @Operation(summary = "Obtener Usuarios", description = "Obtiene una lista con todos los usuarios")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }
    @Operation(summary = "Login", description = "Iniciar session con tu correo y contraseña")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String contrasena) {
        Usuario usuario = usuarioService.autenticar(correo, contrasena);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas o usuario inactivo");
    }

}

