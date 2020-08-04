package io.swagger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.swagger.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query(" SELECT usuario FROM Usuario usuario WHERE upper(usuario.nome) LIKE :parteDoNome ")
	List<Usuario> buscarUsuarioPorParteDoNome(@Param("parteDoNome") String parteDoNome);

}
