package com.evaluation.tecnoaccion.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.evaluation.tecnoaccion.model.Bet;
import com.evaluation.tecnoaccion.repository.BetRepository;

@RestController
public class BetController {
	@Autowired
	BetRepository betRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@GetMapping({ "/bets/limit/{limit}/offset/{offset}/game_id/{game_id}", "/bets/limit/{limit}/offset/{offset}" })
	private List<Bet> getBetList(@PathVariable(value = "limit") Integer limit,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "game_id", required = false) Optional<Integer> gameId) {

		LOGGER.info("BetController Parámetros-> Limit: " + limit + "-offset: " + offset + " game_id: "
				+ (gameId.isPresent() ? gameId.get() : ""));

		if (gameId.isPresent()) {
			return betRepository.getBetList(limit, offset, gameId.get());
		} else {
			return betRepository.getBetList(limit, offset);
		}
	}
}
