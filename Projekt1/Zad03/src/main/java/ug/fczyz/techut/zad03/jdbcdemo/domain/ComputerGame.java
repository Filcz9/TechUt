package ug.fczyz.techut.zad03.jdbcdemo.domain;

import java.sql.Date;

public class ComputerGame {
	int id;
	String name;
	int ageRes;
	double price;
	Date releaseDate;
	public ComputerGame(String name, int ageRes, double price, Date releaseDate) {
		super();
		this.name = name;
		this.ageRes = ageRes;
		this.price = price;
		this.releaseDate = releaseDate;
	}
	public ComputerGame() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAgeRes() {
		return ageRes;
	}
	public void setAgeRes(int ageRes) {
		this.ageRes = ageRes;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
