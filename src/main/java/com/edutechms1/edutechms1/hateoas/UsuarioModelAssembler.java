package com.edutechms1.edutechms1.hateoas;

import com.edutechms1.edutechms1.controller.UsuarioControllerV2;
import com.edutechms1.edutechms1.model.Usuario;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(
            usuario,
            linkTo(methodOn(UsuarioControllerV2.class).obtener(usuario.getId())).withSelfRel(),
            linkTo(methodOn(UsuarioControllerV2.class).listar()).withRel("usuarios"),
            linkTo(methodOn(UsuarioControllerV2.class).actualizar(usuario.getId(), usuario)).withRel("actualizar"),
            linkTo(methodOn(UsuarioControllerV2.class).eliminar(usuario.getId())).withRel("eliminar")
        );
    }
}