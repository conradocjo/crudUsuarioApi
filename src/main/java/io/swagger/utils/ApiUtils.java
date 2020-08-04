package io.swagger.utils;

import java.net.URI;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ApiUtils {

	/**
	 * Adiciona ao Header da requisição a URI.
	 */
	public static MultiValueMap<String, String> getHeaderLocation() {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("location", location.getPath());
		return headers;
	}

}
