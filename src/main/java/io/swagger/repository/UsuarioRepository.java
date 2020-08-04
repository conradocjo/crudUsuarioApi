package io.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.swagger.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
