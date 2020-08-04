package br.com.desafio.conrado.test;

import static java.lang.Long.parseLong;
import static java.util.Objects.nonNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.desafio.conrado.model.DigitoUnico;
import br.com.desafio.conrado.model.Usuario;
import br.com.desafio.conrado.services.impl.RSAGeneratorServiceImpl;
import br.com.desafio.conrado.services.impl.UsuarioServiceImpl;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class DesafioConradoTest {

	@InjectMocks
	private UsuarioServiceImpl usuarioService;

	@InjectMocks
	private RSAGeneratorServiceImpl rsaGeneratorService;

	@Before
	public void init() {
		initMocks(this);
	}

	/**
	 * Valida regras de negócio do dígito único
	 */
	@Test
	public void validaRegrasRelacionadasAoCalculoNumeroUnico() {
		assertTrue(this.usuarioService.realizaCalculoDigitoUnico("9875", null).equals(29));
		assertTrue(this.usuarioService.realizaCalculoDigitoUnico("2", null).equals(2));
		assertTrue(this.usuarioService.realizaCalculoDigitoUnico("9875", "4").equals(116));
		assertNotEquals("Não são diferentes.", 118L,
				parseLong(this.usuarioService.realizaCalculoDigitoUnico("9875", "4").toString()));
		assertFalse(this.usuarioService.realizaCalculoDigitoUnico(null, null).equals(29));
		assertFalse(this.usuarioService.realizaCalculoDigitoUnico("1", "1").equals(2));
		assertFalse(this.usuarioService.realizaCalculoDigitoUnico("1", null).equals(2));
		assertFalse(this.usuarioService.realizaCalculoDigitoUnico(null, "1").equals(2));
		assertFalse(this.usuarioService.realizaCalculoDigitoUnico("", "").equals(2));
	}

	/**
	 * Valida calculos associado aos usuários.
	 */
	@Test
	public void validaSeFoiPassadoUsuarioParametrosParaCalculoDigitoUnico() {
		Usuario usuario = mock(Usuario.class);
		DigitoUnico digitoUnico = mock(DigitoUnico.class);
		List<DigitoUnico> listaDigitoUnico = Arrays.asList(digitoUnico);
		List<DigitoUnico> listaDigitoUnicoVazia = new ArrayList<>();
		when(usuario.getListaResultadosDigitosUnicosCalculados()).thenReturn(null);
		assertFalse(this.usuarioService.verificaSeFoiPassadoParametroParaCalculoAtravesDoCadastro(usuario));
		when(usuario.getListaResultadosDigitosUnicosCalculados()).thenReturn(listaDigitoUnico);
		assertTrue(this.usuarioService.verificaSeFoiPassadoParametroParaCalculoAtravesDoCadastro(usuario));
		when(usuario.getListaResultadosDigitosUnicosCalculados()).thenReturn(listaDigitoUnicoVazia);
		assertFalse(this.usuarioService.verificaSeFoiPassadoParametroParaCalculoAtravesDoCadastro(usuario));
	}

	/**
	 * Este teste não tem por finalidade validar a cobertura, mas o fluxo de geração das chaves, criptografia e descriptografia.
	 * */
	@Test
	public void testaFluxoDeGeracaoDeChaves() throws IOException, GeneralSecurityException {
		Usuario usuario = new Usuario("Conrado Teste", "conrado@teste.com");
		Map<Usuario, Object> keys;
		keys = this.rsaGeneratorService.generatePublicAndPrivateKeys(usuario);
		PublicKey chavePublica = null;
		PrivateKey chavePrivada = null;

		if (nonNull(keys)) {
			chavePublica = ((KeyPair) keys.get(usuario)).getPublic();
			chavePrivada = ((KeyPair) keys.get(usuario)).getPrivate();
		}

		Usuario usuarioCriptografado = this.rsaGeneratorService.encryptUser(usuario, chavePublica);
		Usuario usuarioDescriptografado = new Usuario();
		if (nonNull(usuarioCriptografado)) {
			usuarioDescriptografado = this.rsaGeneratorService.decryptUser(usuarioCriptografado, chavePrivada);
		}

		assertTrue(nonNull(usuarioDescriptografado));
	}
	
}