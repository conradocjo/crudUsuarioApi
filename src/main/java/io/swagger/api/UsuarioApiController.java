package io.swagger.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Usuario;
import io.swagger.repository.UsuarioRepository;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-08-03T22:31:34.730Z")

@Controller
public class UsuarioApiController implements UsuarioApi {

	private static final Logger log = LoggerFactory.getLogger(UsuarioApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@org.springframework.beans.factory.annotation.Autowired
	public UsuarioApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Usuario> createUser(
			@ApiParam(value = "Cadastro de Usuário", required = true) @Valid @RequestBody Usuario body) {
		try {
			Usuario usuario = this.usuarioRepository.save(new Usuario(body.getNome(), body.getUsuario(),
					body.getSenha(), body.getEmail(), body.getTelefone()));
			return ResponseEntity.ok().body(usuario);
		} catch (Exception e) {
			log.error("Erro: ", e);
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Void> deleteUser(
			@ApiParam(value = "Id do usuário que será deletado.", required = true) @PathVariable("id") String id) {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<List<Usuario>> getAllUsers() {
		try {
			List<Usuario> usuarios = this.usuarioRepository.findAll();
			return ResponseEntity.ok().body(usuarios);
		} catch (Exception e) {
			log.error("ERRO:", e);
			return new ResponseEntity<List<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<List<Usuario>> getUserByPartName(
			@ApiParam(value = "Parametro, consiste em parte do nome para filtro de usuários no sistema.", required = true) @PathVariable("parteDoNome") String parteDoNome) {
		try {
//			List<Usuario> usuarios = Arrays
//					.asList(new Usuario("Conrado", "CJOLIVEIRA", "koasker", "conrado.teste@api.com", "999999999"));
			return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<List<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<Usuario> updateUser(
			@ApiParam(value = "Usuáario à ser atualizado", required = true) @Valid @RequestBody Usuario body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<Usuario>(objectMapper.readValue(
						"{  \"senha\" : \"senha\",  \"telefone\" : \"telefone\",  \"nome\" : \"nome\",  \"usuario\" : \"usuario\",  \"id\" : 0,  \"email\" : \"email\",  \"status\" : \"ATIVO\"}",
						Usuario.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Usuario>(HttpStatus.NOT_IMPLEMENTED);
	}

}
