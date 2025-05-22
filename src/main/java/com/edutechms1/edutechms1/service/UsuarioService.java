package com.edutechms1.edutechms1.service;

import com.edutechms1.edutechms1.model.Usuario;
import com.edutechms1.edutechms1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(usuario.getNombre());
            existente.setCorreo(usuario.getCorreo());
            existente.setContrasena(usuario.getContrasena());
            existente.setRol(usuario.getRol());
            existente.setActivo(usuario.isActivo());
            return usuarioRepository.save(existente);
        }
        return null;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario autenticar(String correo, String contrasena) {
    
    Usuario usuario = usuarioRepository.findByCorreo(correo.trim());

    if (usuario == null) {
        System.out.println("Usuario no encontrado para correo: " + correo);
        return null;
    }

    System.out.println("Usuario encontrado: " + usuario.getCorreo());


    if (usuario.isActivo() && usuario.getContrasena().trim().equals(contrasena.trim())) {
        return usuario;
    }

    return null;
    }
    
}

