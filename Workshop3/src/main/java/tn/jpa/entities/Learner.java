package tn.jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the learner database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Learner.findAll", query="SELECT l FROM Learner l"),
	@NamedQuery(name="Learner.Email", query="select l from Learner l where l.email=?1")
})
public class Learner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int learnerId;

	private String city;

	private String email;

	private String name;

	public Learner() {
	}

	public int getLearnerId() {
		return this.learnerId;
	}

	public void setLearnerId(int learnerId) {
		this.learnerId = learnerId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}