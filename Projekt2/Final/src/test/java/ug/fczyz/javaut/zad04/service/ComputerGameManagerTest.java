package ug.fczyz.javaut.zad04.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ug.fczyz.javaut.zad04.domain.*;
import ug.fczyz.javaut.zad04.domain.Review;
import ug.fczyz.javaut.zad04.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ComputerGameManagerTest {

	@Autowired
	ComputerGameManager manager;
	@Autowired
	DetailsManager detailsmanager;
	@Autowired
	DeveloperManager developermanager;
	@Autowired
	TrackManager trackmanager;
	@Autowired
	ReviewManager reviewmanager;
	
	final private String NAME_1 = "GTA";
	final private String NAME_2 = "Gothic";
	final private String NAME_3 = "Wiedźmin";
	final private String NAME_4 = "Gothic 2";
	final private double PRICE = 99.99;
	final private int AGERES = 12;
	final private String DATE1 = "2011-02-11";
	final private String DATE2 = "2013-12-10";
	final private String DATE3 = "2015-10-09";
	
	final private String ARTIST_1 = "Hans Zimmer";
	final private String ARTIST_2 = "Jeremy Soule";
	
	final private String TRACK_1 = "Old Republic";
	final private String TRACK_2 = "Old Camp";
	
	final private String ARTIST_3 = "John Williams";
	final private String TRACK_3 = "Duel of the fates";
	
	final private String DETAILS_NAME_1 = "Gra rpg";
	final private String DETAILS_NAME_2 = "Gra sandboks";
	
	
	
	final private String DETAILS_NAME_3 = "Gra horror";

	
	final private String DEVELOPER_NAME_1 = "Rockstar";
	final private String DEVELOPER_NAME_2 = "Bioware";
	
	final private String REVIEW_VALUE_1 = "Świetna gra";
	final private String REVIEW_VALUE_2 = "Słaba 2/10";
	final private String REVIEW_VALUE_3 = "Nudny gameplay";
	final private String REVIEW_VALUE_4 = "Zmarnowałem tylko czas i pieniądze";
	final private String REVIEW_VALUE_5 = "Nie polecam!";
	final private String REVIEW_VALUE_6 = "Super!";
	@Test
	public void addComputerGameCheck()
	{
		ComputerGame newGame = new ComputerGame();
		newGame.setPrice(PRICE);
		newGame.setAgeRes(AGERES);
		newGame.setName(NAME_4);
		newGame.setDate(DATE1);
		manager.addComputerGame(newGame);
		ComputerGame cpu = manager.getById(4L);
		assertEquals(NAME_4, cpu.getName());
	}
	
	@Test
	public void getComputerGameByIdCheck()
	{
		ComputerGame retGame = manager.getById(1);
		assertEquals(NAME_1, retGame.getName());
	}
	
	@Test
	public void getAllComputerGameCheck()
	{
		List<ComputerGame> list = manager.getAllComputerGames();
		assertEquals(3, list.size());
	}
	
	@Test
	public void updateComputerGameCheck()
	{
		ComputerGame computerGame = manager.getAllComputerGames().get(0);
		computerGame.setName("UPDATE");
		manager.updateComputerGame(computerGame);
		ComputerGame retcpu = manager.getAllComputerGames().get(0);
		assertEquals("UPDATE", retcpu.getName());
		
	}
	
	@Test
	public void deleteComputerGameCheck()
	{
		ComputerGame ret = manager.getById(1L);
		List<ComputerGame> list = manager.getAllComputerGames();
		manager.deleteComputerGame(ret);
		List<ComputerGame> list2 = manager.getAllComputerGames();
		assertEquals(list.size()-1, list2.size());
	}
	
	@Test
	public void getAllTracksCheck()
	{
		ComputerGame computerGame = manager.getAllComputerGames().get(0);
		List<Track> list = manager.getAllOwners(computerGame.getId());
		assertEquals(computerGame.getTracks().size(), list.size());
	}
	
	@Test
	public void getAllComputerGameByProducerNameCheck()
	{
		List<ComputerGame> list = manager.getAllComputerGamesByCompanyName(DEVELOPER_NAME_1);
		assertEquals(2, list.size());
	}
	
	@Test
	public void getAllComputerGameByDateBetweenNameCheck()
	{
		List<ComputerGame> list = manager.getAllComputerGameWithDateBetween(DATE1, DATE2);
		assertEquals(2, list.size());
	}
	
	@Test
	public void getAllComputerGameTracksMoreThanCheck()
	{
		List<ComputerGame> list = manager.getallComputerGamesTracksMoreEqualThan(2);
		assertEquals(2, list.size());
	}
	@Test
	public void getAllComputerGameByAgeRestrictionCheck()
	{
		List<ComputerGame> list = manager.getallComputerGamesByAgeRestriction(12);
		assertEquals(3, list.size());
	}
	@Test
	public void getallComputerGameNameMatchStringCheck()
	{
		List<ComputerGame> list = manager.getallComputerGamesNameMatchString("i");
		assertEquals(2, list.size());
	}
	@Test
	public void deleteListOfMouses()
	{
		List<ComputerGame> list = manager.getAllComputerGames();
		manager.deleteComputerGames(list);
		List<ComputerGame> list2 = manager.getAllComputerGames();
		assertEquals(0, list2.size());
	}
	
	
	
	@Before
	public void fillDb()
	{
		Details details1 = new Details();
		details1.setName(DETAILS_NAME_1);
		detailsmanager.addDetails(details1);
		Details details2 = new Details();
		details2.setName(DETAILS_NAME_2);
		detailsmanager.addDetails(details2);
		Details details3 = new Details();
		details3.setName(DETAILS_NAME_3);
		detailsmanager.addDetails(details3);
		
		Developer developer1 = new Developer();
		developer1.setName(DEVELOPER_NAME_1);
		developermanager.addDeveloper(developer1);
		Developer developer2 = new Developer();
		developer2.setName(DEVELOPER_NAME_2);
		developermanager.addDeveloper(developer2);
		
		Track track1 = new Track();
		track1.setArtist(ARTIST_1);
		track1.setTrackName(TRACK_1);
		trackmanager.addTrack(track1);
		
		Track track2 = new Track();
		track2.setArtist(ARTIST_2);
		track2.setTrackName(TRACK_2);
		trackmanager.addTrack(track2);
		
		Track track3 = new Track();
		track3.setArtist(ARTIST_3);
		track3.setTrackName(TRACK_3);
		trackmanager.addTrack(track3);
		
		Review review1 = new Review(REVIEW_VALUE_1);
		Review review2 = new Review(REVIEW_VALUE_2);
		Review review3 = new Review(REVIEW_VALUE_3);
		reviewmanager.addOpinion(review3);
		reviewmanager.addOpinion(review2);
		reviewmanager.addOpinion(review1);
		
		Review review4 = new Review(REVIEW_VALUE_4);
		Review review5 = new Review(REVIEW_VALUE_5);
		Review review6 = new Review(REVIEW_VALUE_6);
		reviewmanager.addOpinion(review4);
		reviewmanager.addOpinion(review5);
		reviewmanager.addOpinion(review6);
		
		ComputerGame computerGame1 = new ComputerGame();
		computerGame1.setAgeRes(AGERES);
		computerGame1.setPrice(PRICE);
		computerGame1.setName(NAME_1);
		computerGame1.setDate(DATE1);
		computerGame1.setDetails(details1);
		computerGame1.setDeveloper(developer1);
		computerGame1.getTracks().add(track1);
		computerGame1.getTracks().add(track2);
		computerGame1.getReviews().add(review1);
		computerGame1.getReviews().add(review2);
		manager.addComputerGame(computerGame1);
		ComputerGame computerGame2 = new ComputerGame();
		computerGame2.setAgeRes(AGERES);
		computerGame2.setPrice(PRICE);
		computerGame2.setName(NAME_2);
		computerGame2.setDate(DATE2);
		computerGame2.setDetails(details2);
		computerGame2.setDeveloper(developer1);
		computerGame2.getTracks().add(track1);
		computerGame2.getReviews().add(review3);
		computerGame2.getReviews().add(review4);
		manager.addComputerGame(computerGame2);
		ComputerGame computerGame3 = new ComputerGame();
		computerGame3.setAgeRes(AGERES);
		computerGame3.setPrice(PRICE);
		computerGame3.setName(NAME_3);
		computerGame3.setDate(DATE3);
		computerGame3.setDetails(details3);
		computerGame3.setDeveloper(developer2);
		computerGame3.getTracks().add(track3);
		computerGame3.getTracks().add(track1);
		computerGame3.getReviews().add(review5);
		computerGame3.getReviews().add(review6);
		manager.addComputerGame(computerGame3);
	}
	
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
