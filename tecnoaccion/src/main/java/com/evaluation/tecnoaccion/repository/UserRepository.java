package com.evaluation.tecnoaccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evaluation.tecnoaccion.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT coalesce(max(u.id), 0) FROM User u")
	Long getMaxId();

}
