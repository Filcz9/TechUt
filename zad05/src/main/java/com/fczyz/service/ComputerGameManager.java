package com.fczyz.service;


import java.util.List;

import com.fczyz.domain.ComputerGame;
import com.fczyz.domain.Review;

public interface ComputerGameManager {

	
	public void addComputerGame(ComputerGame computergame);
	public List<ComputerGame> getAllComputerGames();
	ComputerGame findByTitle(String title);
	public void deleteComputerGame(ComputerGame computergame);
	public void updateComputerGame(ComputerGame computergames);
	public void clearTable();
	//public void findByName(String name);
	//public void findOlderThan(Date string);
	//List<Review> getOwnedCars(Person person);
	//void sellCar(Long personId, Long carId);
}
