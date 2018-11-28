package com.fczyz.jdbcdemo.domain;

import java.util.Date;

public class ComputerGame {
	private long id;
	private String title;
	private String release;
	private Double price;
	private Boolean multiplayer;
	
	public ComputerGame()
	{		
	}
	public ComputerGame(String title, String release, Double price, Boolean multiplayer)
	{
		super();
		this.title = title;
		this.release = release;
		this.price = price;
		this.multiplayer = multiplayer;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
}
