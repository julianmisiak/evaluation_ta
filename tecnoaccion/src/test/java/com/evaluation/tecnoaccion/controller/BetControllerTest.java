package com.evaluation.tecnoaccion.controller;

import static org.junit.Assert.assertEquals;

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
public class BetControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	HttpHeaders headers = new HttpHeaders();
	private HttpEntity<String> entity;
	ResponseEntity<String> response;
	@LocalServerPort
	private int port;
	private static String URL_USER = "/users";

	@Test
	public void testBets() throws IOException {
		Integer limit = 2;
		Integer offset = 0;
		String URL_BETS = "/bets/limit/" + limit + "/offset/" + offset;

		response = restTemplate.exchange(createURLWithPort(URL_BETS), HttpMethod.GET, entity, String.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testBetsAndGameId() throws IOException {
		Integer limit = 2;
		Integer offset = 0;
		Long idUser = getFirstOidUser();
		String URL_BETS = "/bets/limit/" + limit + "/offset/" + offset + "/game_id/" + idUser;

		response = restTemplate.exchange(createURLWithPort(URL_BETS), HttpMethod.GET, entity, String.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	private Long getFirstOidUser() throws IOException {

		entity = new HttpEntity<String>(null, headers);
		response = restTemplate.exchange(createURLWithPort(URL_USER), HttpMethod.GET, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode idUserNode = root.get(0).get("id");

		return idUserNode.asLong();
	}
}
