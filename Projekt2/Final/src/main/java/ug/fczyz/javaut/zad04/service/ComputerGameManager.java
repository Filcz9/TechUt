package ug.fczyz.javaut.zad04.service;

import java.util.List;

import ug.fczyz.javaut.zad04.domain.ComputerGame;
import ug.fczyz.javaut.zad04.domain.Track;

public interface ComputerGameManager {
	void addComputerGame(ComputerGame computergame);
	ComputerGame getById(long id);
	List<ComputerGame> getAllComputerGames();
	List<ComputerGame> getAllComputerGamesByCompanyName(String name);
	List<ComputerGame> getAllComputerGameWithDateBetween(String date1, String date2);
	List<Track> getAllOwners(long id);
	void deleteComputerGame(ComputerGame computergame);
	void deleteComputerGames(List<ComputerGame> computerGames);
	void updateComputerGame(ComputerGame computergame);
	void clearTable();
	List<ComputerGame> getallComputerGamesTracksMoreEqualThan(int number);
	List<ComputerGame> getallComputerGamesByAgeRestriction(int number);
	List<ComputerGame> getallComputerGamesNameMatchString(String match);
}
