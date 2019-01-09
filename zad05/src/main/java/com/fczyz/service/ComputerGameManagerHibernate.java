package com.fczyz.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fczyz.domain.ComputerGame;



@Component
@Transactional
public class ComputerGameManagerHibernate implements ComputerGameManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addComputerGame(ComputerGame computergame) {
		sessionFactory.getCurrentSession().persist(computergame);
	}
	
	@Override
	public void deleteComputerGame(ComputerGame computergame) {
	
		sessionFactory.getCurrentSession().delete(computergame);
	}


	@Override
	public List<ComputerGame> getAllComputerGames() {
		return (List<ComputerGame>) sessionFactory.getCurrentSession().getNamedQuery("computergame.all").list();
		
	}

	@Override
	public ComputerGame findByTitle(String title) {
		return (ComputerGame) sessionFactory.getCurrentSession().get(ComputerGame.class, title);
	
	}

	@Override
	public void updateComputerGame(ComputerGame computergames) {
		sessionFactory.getCurrentSession().saveOrUpdate(computergames);
		
	}

	@Override
	public void clearTable() {
		sessionFactory.getCurrentSession().createSQLQuery("TRUNCATE TABLE PROCESSOR RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		
	}

}
