package com.fczyz.jdbc.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fczyz.jdbcdemo.domain.ComputerGame;
import com.fczyz.jdbcdemo.service.ComputerGameManagerJDBC;

public class ComputerGameManagerTest {
	
	
	ComputerGameManagerJDBC personManager = new ComputerGameManagerJDBC();
	
	private final static String Title_1 = "GTA";
	private final static Date Release_1 = new Date(2008);
	private final static Double Price_1 = 39.99;
	private final static Boolean Multiplayer_1 = true;
	
	private final static String Title_2 = "Gotic";
	private final static Date Release_2 = new Date(2004);
	private final static Double Price_2 = 29.99;
	private final static Boolean Multiplayer_2 = true;
	
	private final static String Title_3 = "gra3";
	private final static Date Release_3 = new Date(2006);
	private final static Double Price_3 = 19.99;
	private final static Boolean Multiplayer_3 = true;
	
	private final static String Title_4 = "gra4";
	private final static Date Release_4 = new Date(2000);
	private final static Double Price_4 = 59.99;
	private final static Boolean Multiplayer_4 = false;
	

		
	ComputerGame computergame1 = new ComputerGame(Title_1, Release_1,Price_1,Multiplayer_1);
	ComputerGame computergame2 = new ComputerGame(Title_2, Release_2,Price_2,Multiplayer_2);
	ComputerGame computergame3 = new ComputerGame(Title_3, Release_3,Price_3,Multiplayer_3);
	ComputerGame computergame4 = new ComputerGame(Title_4, Release_4,Price_4,Multiplayer_4);

	
	@Test
	public void checkConnection(){
		assertNotNull(personManager.getConnection());
	}
	
	//@Test
	public void checkAdding(){
		
		ComputerGame computergame = new ComputerGame(Title_1, Release_1,Price_1,Multiplayer_1);
		
		personManager.clearComputerGames();
		assertEquals(1,personManager.addComputerGame(computergame));
		
		List<ComputerGame> computergames = personManager.getAllComputerGames();
		ComputerGame personRetrieved = computergames.get(0);
		
		assertEquals(Title_1, personRetrieved.getName());
		assertEquals(Release_1, personRetrieved.getYob());
		
	}
	
	@Test
	public void checkAddAll(){
		personManager.clearComputerGames();		
		
		List<ComputerGame> persons = new ArrayList<>();
		persons.add(person1);
		persons.add(person2);
		//persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		
		personManager.addAllComputerGames(persons);
		int size = personManager.getAllComputerGames().size();
		
		//assertTrue(size == 0 || size == 4);
		
		assertThat(size, either(is(4)).or(is(0)));
		
	}

}
