package com.evaluation.tecnoaccion.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "BET")
@EntityListeners(AuditingEntityListener.class)
@JsonPropertyOrder({ "id", "mount", "game_id" })
public class Bet implements Serializable {
	private static final long serialVersionUID = -1888393722399111737L;
	private Long id;
	private BigDecimal mount;
	private Integer gameId;
	private User user;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "MOUNT", nullable = false)
	@JsonProperty("mount")
	public BigDecimal getMount() {
		return mount;
	}

	public void setMount(BigDecimal mount) {
		this.mount = mount;
	}

	@Column(name = "GAME_ID", nullable = false)
	@JsonProperty("game_id")
	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bet() {
	}

	public Bet(BigDecimal mount, Integer gameId, User user) {
		super();
		this.mount = mount;
		this.gameId = gameId;
		this.user = user;
	}

	public Bet(BigDecimal mount, Integer gameId) {
		super();
		this.mount = mount;
		this.gameId = gameId;
	}

}
