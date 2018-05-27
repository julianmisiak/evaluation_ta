package com.evaluation.tecnoaccion.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.evaluation.tecnoaccion.model.User;
import com.evaluation.tecnoaccion.repository.UserRepository;
import com.evaluation.tecnoaccion.service.UserPost;

@RestController
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public ResponseEntity<?> getAllUser() {
		List<User> userList = userRepository.findAll();
		return ResponseEntity.ok().body(userList);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long userId) {
		LOGGER.info("getUserById Parámetros-> user_Id: " + userId);
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return ResponseEntity.ok().body(user.get());
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/users")
	private User addUser(@Valid @RequestBody UserPost userPost) {
		LOGGER.info("addUser Parámetros-> user: " + userPost.toString());
		User user = new User();
		user.setFirstName(userPost.getFirstName());
		user.setLastName(userPost.getLastName());
		user.setEmail(userPost.getEmail());

		return userRepository.save(user);
	}

	@DeleteMapping("/users/{id}")
	private ResponseEntity<?> removeUser(@PathVariable(value = "id") Long userId) {
		LOGGER.info("removeUser Parámetros-> userId: " + userId);
		userRepository.deleteById(userId);
		return ResponseEntity.ok().build();
	}
}
