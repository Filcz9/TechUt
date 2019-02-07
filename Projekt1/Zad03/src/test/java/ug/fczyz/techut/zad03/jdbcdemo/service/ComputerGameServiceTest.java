package ug.fczyz.techut.zad03.jdbcdemo.service;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import ug.fczyz.techut.zad03.jdbcdemo.domain.ComputerGame;

@SuppressWarnings("deprecation")
public class ComputerGameServiceTest {
	ComputerGameServiceJDBC service = new ComputerGameServiceJDBC();
	
	private final String GAME1_NAME = "Gothic";
	private final int GAME1_AGERES = 12;
	private final double GAME1_PRICE = 250.99;
	private final Date GAME1_RELEASEDATE = new Date(2009,05,07);
	
	private final String GAME2_NAME = "GTA";
	private final int GAME2_AGERES = 16;
	private final double GAME2_PRICE = 270.99;
	private final Date GAME2_RELEASEDATE = new Date(2014,10,12);

	private final String GAME3_NAME = "Wiedźmin";
	private final int GAME3_AGERES = 12;
	private final double GAME3_PRICE = 50.59;
	private final Date GAME3_RELEASEDATE = new Date(2012,01,04);
	
	private final String GAME4_NAME = "Kotor";
	private final int GAME4_AGERES = 18;
	private final double GAME4_PRICE = 30.99;
	private final Date GAME4_RELEASEDATE = new Date(2005,06,07);
	
	@Test
	public void checkConnection() {
		assertNotNull(service.getConnection());
	}
	
	@Test
	public void checkAddMouse() {
		ComputerGame computerGame4 = new ComputerGame(GAME4_NAME, GAME4_AGERES, GAME4_PRICE, GAME4_RELEASEDATE);
		service.addComputerGame(computerGame4);
		ComputerGame retComputerGame = service.getComputerGameById(service.getComputerGameByName(GAME4_NAME).getId());
		assertEquals(GAME4_NAME, retComputerGame.getName());
		assertEquals(GAME4_PRICE, retComputerGame.getPrice(),0);
		assertEquals(GAME4_AGERES, retComputerGame.getAgeRes());
		assertEquals(GAME4_RELEASEDATE, retComputerGame.getReleaseDate());
		
	}
	
	@Test
	public void checkAddAllMouses()
	{
		
		List<ComputerGame> computerGameList = new ArrayList<ComputerGame>();
		
		computerGameList.add(new ComputerGame("GAME1", GAME1_AGERES, GAME1_PRICE, GAME1_RELEASEDATE));
		
		computerGameList.add(new ComputerGame("GAME2", GAME1_AGERES, GAME1_PRICE, GAME1_RELEASEDATE));
	
		service.addAllComputerGames(computerGameList);

		int size = service.getAllComputerGame().size();
		assertEquals(5, size);
	
	}
	
	@Test
	public void checkGetMouseByName()
	{
		ComputerGame retProcessor = service.getComputerGameByName(GAME2_NAME);
		assertEquals(GAME2_NAME, retProcessor.getName());
	}
	
	@Test
	public void checkDeleteAllMouses()
	{
		service.deleteAllComputerGames();
		int size = service.getAllComputerGame().size();
		assertEquals(0, size);
	}
	
	@Test
	public void checkGetAllMousesAgeResMoreThanOrEqual() {
		List<ComputerGame> computerGames = service.getAllComputerGameAgeResMoreThanOrEqual(12);
		assertEquals(3, computerGames.size());
	}
	
	@Test
	public void checkDeleteById() {
		ComputerGame computerGameByName = service.getComputerGameByName(GAME2_NAME);
		service.deleteComputerGameById(computerGameByName.getId());

		List<ComputerGame> computerGameList = service.getAllComputerGame();
		assertEquals(2, computerGameList.size());
		for(ComputerGame computerGame : computerGameList)
		{
			assertNotEquals(computerGameByName.getId(), computerGame.getId());
		}
	}
	
	@Before
	public void fillDb()
	{
		service.deleteAllComputerGames();
		ComputerGame computerGame1 = new ComputerGame(GAME1_NAME, GAME1_AGERES, GAME1_PRICE, GAME1_RELEASEDATE);
		ComputerGame computerGame2 = new ComputerGame(GAME2_NAME, GAME2_AGERES, GAME2_PRICE, GAME2_RELEASEDATE);
		ComputerGame computerGame3 = new ComputerGame(GAME3_NAME, GAME3_AGERES, GAME3_PRICE, GAME3_RELEASEDATE);

		service.addComputerGame(computerGame1);
		service.addComputerGame(computerGame2);
		service.addComputerGame(computerGame3);
	}
	
}
