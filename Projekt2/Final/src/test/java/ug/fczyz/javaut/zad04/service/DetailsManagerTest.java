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

import ug.fczyz.javaut.zad04.domain.Details;
import ug.fczyz.javaut.zad04.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DetailsManagerTest {
	
	final private String NAME_1 = "Gra rpg";
	final private String NAME_2 = "Gra horror";
	final private String NAME_3 = "Gra sandboks";

	
	@Autowired
	DetailsManager manager;

	@Test
	public void addDetailsCheck()
	{
		Details detailsl = new Details();
		detailsl.setName(NAME_3);
		manager.addDetails(detailsl);
		Details ret = manager.getById(3L);
		assertEquals(NAME_3, ret.getName());
	}
	
	@Test
	public void getDetailsByIdCheck()
	{
		Details ret = manager.getById(2L);
		assertEquals(NAME_2, ret.getName());
	}
	
	@Test
	public void getAllDetailsCheck()
	{
		List<Details> list = manager.getAll();
		assertEquals(2, list.size());
	}
	
	@Test
	public void deleteDetailCheck()
	{
		List<Details> list = manager.getAll();
		assertEquals(2, list.size());
		manager.deleteDetails(list.get(0));
		list = manager.getAll();
		assertEquals(1, list.size());
	}
	
	@Test
	public void updateDetailsCheck() {
		
		Details ret = manager.getAll().get(0);
		ret.setName("Detail Update");
		manager.updateDetails(ret);
		assertEquals("Detail Update", manager.getAll().get(0).getName());
	}
	
	@Before
	public void fillDb()
	{
		Details details1 = new Details();
		details1.setName(NAME_1);
		manager.addDetails(details1);
		
		Details details2 = new Details();
		details2.setName(NAME_2);
		manager.addDetails(details2);
	}
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
