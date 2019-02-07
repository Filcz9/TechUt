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

import ug.fczyz.javaut.zad04.domain.Review;
import ug.fczyz.javaut.zad04.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ReviewManagerTest {
	
	final private String VALUE_1 = "Słaba gra 2/10";
	final private String VALUE_2 = "Świeny gameplay";
	final private String VALUE_3 = "Bardzo nostalgiczna";
	
	@Autowired
	ReviewManager manager;

	@Test
	public void addReviewCheck()
	{
		Review review = new Review();
		review.setValue(VALUE_3);
		manager.addOpinion(review);
		Review ret = manager.getById(3L);
		assertEquals(VALUE_3, ret.getValue());
	}
	
	@Test
	public void getReviewByIdCheck()
	{
		Review ret = manager.getById(2L);
		assertEquals(VALUE_2, ret.getValue());
	}
	
	@Test
	public void getAllReviewsCheck()
	{
		List<Review> list = manager.getAll();
		assertEquals(2, list.size());
	}
	
	@Test
	public void deleteReviewCheck()
	{
		List<Review> list = manager.getAll();
		assertEquals(2, list.size());
		manager.deleteOpinion(list.get(0));
		list = manager.getAll();
		assertEquals(1, list.size());
	}
	
	@Test
	public void updateReviewCheck() {
		
		Review ret = manager.getAll().get(0);
		ret.setValue("Review Update");
		manager.updateOpinion(ret);
		assertEquals("Review Update", manager.getAll().get(0).getValue());
	}
	
	@Before
	public void fillDb()
	{
		Review review1 = new Review();
		review1.setValue(VALUE_1);
		manager.addOpinion(review1);
		
		Review review2 = new Review();
		review2.setValue(VALUE_2);
		manager.addOpinion(review2);
	}
	@After
	public void deleteAll(){
		manager.clearTable();
	}
}
