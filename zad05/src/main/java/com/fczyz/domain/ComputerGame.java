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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Review> getRewievs() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
