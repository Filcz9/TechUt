package com.fczyz.jdbcdemo.service;

import java.sql.Date;
import java.util.List;

import com.fczyz.jdbcdemo.domain.ComputerGame;

public interface ComputerGameManager {

	
	public int addComputerGame(ComputerGame computergame);
	public List<ComputerGame> getAllComputerGames();
	
	/* batch insert - transactional */
	public void addAllComputerGames(List<ComputerGame> computergames);
	//public void findByName(String name);
	//public void findOlderThan(Date string);
}
