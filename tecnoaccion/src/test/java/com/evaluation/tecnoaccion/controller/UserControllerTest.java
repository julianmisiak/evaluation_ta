package com.evaluation.tecnoaccion.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	HttpHeaders headers = new HttpHeaders();
	private HttpEntity<String> entity;
	ResponseEntity<String> response;

	private static String URL_USER = "/users";

	private Long getFirstOidUser() throws IOException {

		entity = new HttpEntity<String>(null, headers);
		response = restTemplate.exchange(createURLWithPort(URL_USER), HttpMethod.GET, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode idUserNode = root.get(0).get("id");

		return idUserNode.asLong();
	}

	@Test
	public void testGetUserById() throws IOException {
		Long idUser = getFirstOidUser();
		entity = new HttpEntity<String>(null, headers);
		response = restTemplate.exchange(createURLWithPort(URL_USER + "/" + idUser), HttpMethod.GET, entity, String.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testDeleteUserByOid() throws IOException {
		Long idUser = getFirstOidUser();
		response = restTemplate.exchange(createURLWithPort(URL_USER + "/" + idUser), HttpMethod.DELETE, entity, String.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
