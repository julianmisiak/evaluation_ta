package com.evaluation.tecnoaccion.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "USER", uniqueConstraints={@UniqueConstraint(columnNames = {"FIRST_NAME", "LAST_NAME"})}) 

@EntityListeners(AuditingEntityListener.class)
@JsonPropertyOrder({ "id", "email", "firstName", "lastName", "bet" })
public class User implements Serializable  {
	private static final long serialVersionUID = -2284845619785198586L;
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private List<Bet> betList = new ArrayList<Bet>();
	
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
	
	@Column(name = "EMAIL", nullable = false)
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "FIRST_NAME", nullable = false)
	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "LAST_NAME", nullable = false)
	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonProperty("bet")
	@JsonManagedReference
	public List<Bet> getBetList() {
		return betList;
	}
	public void setBetList(List<Bet> betList) {
		this.betList = betList;
	}
	
	public User(){}
	
	public User(String email, String firstName, String lastName, List<Bet> betList) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.betList = betList;
	}
	
	public User(String email, String firstName, String lastName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
