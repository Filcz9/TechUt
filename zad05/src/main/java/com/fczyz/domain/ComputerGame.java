package com.fczyz.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.HashSet;
import java.util.Set;


@Entity
@NamedQueries({
		@NamedQuery(name = "computergame.all", query = "Select all from ComputerGame all")
})
public class ComputerGame {
	private long id;
	private String title;
	private String release;
	private Double price;
	private Boolean multiplayer;
	private Set<Review> reviews = new HashSet<Review>();
	private Developer developer;
	private ActivationKey key;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public Double getPrice()
	{
		return price;
	}
	public void setPrice(Double price)
	{
		this.price = price;
	}
	public Boolean getMultiplayer()
	{
		return multiplayer;
	}
	public void setMultiplayer(Boolean multiplayer)
	{
		this.multiplayer = multiplayer;
	}

	@ManyToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	public Developer getDeveloper() {
		return developer;
}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
}
	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setOwners(Set<Review> reviews) {
		this.reviews = reviews;
}

	@OneToOne(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	public ActivationKey getActivationKey() {
		return key;
	}

	public void setActivationKey(ActivationKey key) {
		this.key = key;
}
}

