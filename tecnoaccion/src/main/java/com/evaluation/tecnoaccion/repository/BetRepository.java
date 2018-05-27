package com.evaluation.tecnoaccion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evaluation.tecnoaccion.model.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {
	@Query(value="SELECT b.* FROM Bet b ORDER BY b.id limit ?1 offset ?2", nativeQuery = true)
	public List<Bet> getBetList(int limit, int offset);
	
	@Query(value="SELECT b.* FROM Bet b WHERE b.game_id = ?3 ORDER BY b.id limit ?1 offset ?2", nativeQuery = true)
	public List<Bet> getBetList(int limit, int offset, int gameId);
}
