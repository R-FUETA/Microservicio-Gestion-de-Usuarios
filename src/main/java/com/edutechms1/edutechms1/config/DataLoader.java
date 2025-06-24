package com.edutechms1.edutechms1.config;

import com.edutechms1.edutechms1.model.Rol;
import com.edutechms1.edutechms1.model.Usuario;
import com.edutechms1.edutechms1.repository.UsuarioRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Profile("dev") // Solo se ejecuta si el perfil activo es "dev"
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final Rol[] roles = Rol.values(); // arreglo con todos los roles
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 50; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre(faker.name().fullName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setContrasena(faker.internet().password());

            // Elegir un rol aleatorio del enum
            Rol rolAleatorio = roles[random.nextInt(roles.length)];
            usuario.setRol(rolAleatorio);

            usuario.setActivo(true);
            usuarioRepository.save(usuario);
        }
    }
}
