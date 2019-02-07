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

import ug.fczyz.javaut.zad04.domain.Track;
import ug.fczyz.javaut.zad04.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class TrackManagerTest {
	
	final private String FIRSTNAME_1 = "Hans Zimmer";
	final private String FIRSTNAME_2 = "Jeremy Soule";
	
	final private String LASTNAME_1 = "Old Republic";
	final private String LASTNAME_2 = "Old Camp";
	
	final private String FIRSTNAME_3 = "John Williams";
	final private String LASTNAME_3 = "Duel of the fates";
	
	@Autowired
	TrackManager manager;

	@Test
	public void addTrackCheck()
	{
		Track track = new Track();
		track.setArtist(FIRSTNAME_3);
		track.setTrackName(LASTNAME_3);
		manager.addTrack(track);
		Track ret = manager.getById(3L);
		assertEquals(FIRSTNAME_3, ret.getArtist());
		assertEquals(LASTNAME_3, ret.getTrackName());
	}
	
	@Test
	public void getTrackByIdCheck()
	{
		Track ret = manager.getById(2L);
		assertEquals(FIRSTNAME_2, ret.getArtist());
		assertEquals(LASTNAME_2, ret.getTrackName());
	}
	
	@Test
	public void getAllTracksCheck()
	{
		List<Track> list = manager.getAll();
		assertEquals(2, list.size());
	}
	
	@Test
	public void deleteTrackCheck()
	{
		List<Track> list = manager.getAll();
		assertEquals(2, list.size());
		manager.deleteTrack(list.get(0));
		list = manager.getAll();
		assertEquals(1, list.size());
	}

	@Test
	public void updateTrackCheck() {
		
		Track ret = manager.getAll().get(0);
		ret.setArtist("Jakub");
		manager.updateTrack(ret);
		assertEquals("Jakub", manager.getAll().get(0).getArtist());
	}
	
	@Before
	public void fillDb()
	{
		Track track1 = new Track();
		track1.setArtist(FIRSTNAME_1);
		track1.setTrackName(LASTNAME_1);
		manager.addTrack(track1);
		
		Track track2 = new Track();
		track2.setArtist(FIRSTNAME_2);
		track2.setTrackName(LASTNAME_2);
		manager.addTrack(track2);
	}
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
