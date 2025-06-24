package com.edutechms1.edutechms1.controller;

import com.edutechms1.edutechms1.model.Usuario;
import com.edutechms1.edutechms1.service.UsuarioService;
import com.edutechms1.edutechms1.hateoas.UsuarioModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/usuarios")
@Tag(name = "Usuarios", description = "Usuarios relacionados")
public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @Operation(summary = "Crear Usuario", description = "Permite Crear Usuarios")
    @PostMapping
    public EntityModel<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return assembler.toModel(nuevoUsuario);
    }

    @Operation(summary = "Obtener", description = "Obtiene Usuarios por ID")
    @GetMapping("id/{id}")
    public EntityModel<Usuario> obtener(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return assembler.toModel(usuario);
    }

    @Operation(summary = "Actualizar", description = "Actualizar Usuarios")
    @PutMapping("id/{id}")
    public EntityModel<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        return assembler.toModel(actualizado);
    }

    @Operation(summary = "Eliminar", description = "Elimina Usuarios")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener Usuarios", description = "Obtiene una lista con todos los usuarios")
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> listar() {
        List<EntityModel<Usuario>> usuarios = usuarioService.listarUsuarios()
            .stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            usuarios,
            linkTo(methodOn(UsuarioControllerV2.class).listar()).withSelfRel()
        );
    }

    @Operation(summary = "Login", description = "Iniciar sesión con tu correo y contraseña")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String correo, @RequestParam String contrasena) {
        Usuario usuario = usuarioService.autenticar(correo, contrasena);
        if (usuario != null) {
            return ResponseEntity.ok(assembler.toModel(usuario));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas o usuario inactivo");
    }
}