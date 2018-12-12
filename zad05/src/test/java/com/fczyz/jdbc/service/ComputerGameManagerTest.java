package com.fczyz.jdbc.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fczyz.domain.ComputerGame;
import com.fczyz.jdbcdemo.service.ComputerGameManagerJDBC;

public class ComputerGameManagerTest {
	
	
	ComputerGameManagerJDBC gameManager = new ComputerGameManagerJDBC();
	
	private final static String Title_1 = "GTA";

	private final static String Release_1 = "1998";
	private final static Double Price_1 = 39.99;
	private final static Boolean Multiplayer_1 = true;
	
	private final static String Title_2 = "Gotic";
	private final static String Release_2 = "1999";
	private final static Double Price_2 = 29.99;
	private final static Boolean Multiplayer_2 = true;
	
	private final static String Title_3 = "gra3";
	private final static String Release_3 = "1996";
	private final static Double Price_3 = 19.99;
	private final static Boolean Multiplayer_3 = true;
	
	private final static String Title_4 = "gra4";
	private final static String Release_4 = "1997";
	private final static Double Price_4 = 59.99;
	private final static Boolean Multiplayer_4 = false;
	

		
	ComputerGame computergame1 = new ComputerGame(Title_1, Release_1,Price_1,Multiplayer_1);
	ComputerGame computergame2 = new ComputerGame(Title_2, Release_2,Price_2,Multiplayer_2);
	ComputerGame computergame3 = new ComputerGame(Title_3, Release_3,Price_3,Multiplayer_3);
	ComputerGame computergame4 = new ComputerGame(Title_4, Release_4,Price_4,Multiplayer_4);

	
	@Test
	public void checkConnection(){
		assertNotNull(gameManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		ComputerGame computergame = new ComputerGame(Title_1, Release_1,Price_1,Multiplayer_1);
		
		gameManager.clearComputerGames();
		assertEquals(1,gameManager.addComputerGame(computergame));
		
		List<ComputerGame> computergames = gameManager.getAllComputerGames();
		ComputerGame gameRetrieved = computergames.get(0);
		
		assertEquals(Title_1, gameRetrieved.getTitle());
		assertEquals(Release_1, gameRetrieved.getRelease());
		assertEquals(Price_1, gameRetrieved.getPrice());
		assertEquals(Multiplayer_1, gameRetrieved.getMultiplayer());
		
	}
	
	@Test
	public void checkAddAll(){
		gameManager.clearComputerGames();		
		
		List<ComputerGame> game = new ArrayList<>();
		game.add(computergame1);
		game.add(computergame2);
		game.add(computergame3);
		game.add(computergame4);
		
		gameManager.addAllComputerGames(game);
		int size = gameManager.getAllComputerGames().size();
		
		//assertTrue(size == 0 || size == 4);
		assertEquals(size, 4);
		//assertThat(size, either(is(4)).or(is(0)));
		
	}

}
