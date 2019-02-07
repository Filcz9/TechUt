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

import ug.fczyz.javaut.zad04.domain.Developer;
import ug.fczyz.javaut.zad04.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DeveloperManagerTest {
	
	final private String NAME_1 = "Bioware";
	final private String NAME_2 = "Rockstar";
	final private String NAME_3 = "Piranha";
	final private String NAME_4 = "Cd Projekt";
	@Autowired
	DeveloperManager manager;

	@Test
	public void addDeveloperCheck()
	{
		Developer developer = new Developer();
		developer.setName(NAME_3);
		manager.addDeveloper(developer);
		Developer ret = manager.getDeveloperById(3L);
		assertEquals(NAME_3, ret.getName());
	}
	
	@Test
	public void getDeveloperIdCheck()
	{
		Developer retDeveloper = manager.getDeveloperById(2L);
		assertEquals(NAME_2, retDeveloper.getName());
	}
	
	@Test
	public void getAllDevelopersCheck()
	{
		List<Developer> list = manager.getAllDevelopers();
		assertEquals(2, list.size());
	}
	
	@Test
	public void deleteDeveloperCheck()
	{
		List<Developer> list = manager.getAllDevelopers();
		assertEquals(2, list.size());
		manager.deleteDeveloper(list.get(0));
		list = manager.getAllDevelopers();
		assertEquals(1, list.size());
	}
	
	@Test
	public void updateDeveloperCheck() {
		
		Developer retDeveloper = manager.getDeveloperByName(NAME_2);
		retDeveloper.setName(NAME_4);
		manager.updateDeveloper(retDeveloper);
		assertEquals(NAME_4, manager.getDeveloperByName(NAME_4).getName());
	}
	
	@Test
	public void getDeveloperByNameCheck()
	{
		Developer retDeveloper = manager.getDeveloperByName(NAME_1);
		assertEquals(NAME_1, retDeveloper.getName());
	}
	
	@Before
	public void fillDb()
	{
		Developer developer = new Developer();
		developer.setName(NAME_1);
		manager.addDeveloper(developer);
		Developer developer2 = new Developer();
		developer2.setName(NAME_2);
		manager.addDeveloper(developer2);
	}
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
