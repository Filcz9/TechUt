package ug.fczyz.techut.zad03.jdbcdemo.service;

import java.util.List;

import ug.fczyz.techut.zad03.jdbcdemo.domain.ComputerGame;

public interface ComputerGameService {
	void addComputerGame(ComputerGame computerGame);
	List<ComputerGame> getAllComputerGame();
	List<ComputerGame> getAllComputerGameAgeResMoreThanOrEqual(int ageRes);
	ComputerGame getComputerGameById(int id);
	ComputerGame getComputerGameByName(String name);
	void deleteAllComputerGames();
	boolean deleteComputerGameById(int id);
	boolean addAllComputerGames(List<ComputerGame> computerGames);
}
