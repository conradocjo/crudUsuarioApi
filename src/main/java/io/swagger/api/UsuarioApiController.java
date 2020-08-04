package io.swagger.api;

import static io.swagger.utils.ApiUtils.getHeaderLocation;
import static io.swagger.utils.TratativasUteis.isListNotEmpty;
import static java.util.Objects.nonNull;

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

	@SuppressWarnings("unused")
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
			return new ResponseEntity<Usuario>(usuario, getHeaderLocation(), HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Erro: ", e);
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Void> deleteUser(
			@ApiParam(value = "Id do usuário que será deletado.", required = true) @PathVariable("id") Long id) {
		try {
			if (nonNull(id)) {
				Usuario usuario = this.usuarioRepository.findOne(id);
				if (nonNull(usuario)) {
					this.usuarioRepository.delete(usuario);
				} else {
					throw new Exception("Usuario não encontrado.");
				}
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Usuario>> getAllUsers() {
		try {
			List<Usuario> usuarios = this.usuarioRepository.findAll();
			if (isListNotEmpty(usuarios)) {
				return new ResponseEntity<List<Usuario>>(usuarios, getHeaderLocation(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("ERRO: ", e);
			return new ResponseEntity<List<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<List<Usuario>> getUserByPartName(
			@ApiParam(value = "Parametro, consiste em parte do nome para filtro de usuários no sistema.", required = true) @PathVariable("parteDoNome") String parteDoNome) {
		try {
			if (nonNull(parteDoNome)) {
				List<Usuario> usuarios = this.usuarioRepository
						.buscarUsuarioPorParteDoNome("%".concat(parteDoNome.toUpperCase()).concat("%"));
				if (isListNotEmpty(usuarios)) {
					return new ResponseEntity<List<Usuario>>(usuarios, getHeaderLocation(), HttpStatus.OK);
				} else {
					return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
				}
			}
		} catch (Exception e) {
			log.error("Erro: ", e);
			return new ResponseEntity<List<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

	public ResponseEntity<Usuario> updateUser(
			@ApiParam(value = "Usuáario à ser atualizado", required = true) @Valid @RequestBody Usuario body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				if (nonNull(body)) {
					Usuario usuario = this.usuarioRepository.save(body);
					return new ResponseEntity<Usuario>(usuario, getHeaderLocation(), HttpStatus.OK);
				}
			} catch (Exception e) {
				log.error("ERRO: ", e);
				return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<Usuario>(HttpStatus.NOT_IMPLEMENTED);
	}

}
