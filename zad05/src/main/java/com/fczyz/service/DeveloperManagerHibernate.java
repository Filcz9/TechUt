package com.fczyz.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.fczyz.domain.Developer;


@Component
@Transactional
public class DeveloperManagerHibernate implements DeveloperManager {

	@Autowired
	SessionFactory session;
	
	@Override
	public void addDeveloper(Developer developer) {
		session.getCurrentSession().persist(developer);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Developer> getAllDevelopers() {
		return session.getCurrentSession().getNamedQuery("Developer.all").list();
	}

	@Override
	public Developer getDeveloperById(long id) {
		return (Developer) session.getCurrentSession().get(Developer.class, id);
	}

	@Override
	public void deleteDeveloper(Developer developer) {
		session.getCurrentSession().delete(developer);
		
	}

	@Override
	public void updateDeveloper(Developer developer) {
		session.getCurrentSession().saveOrUpdate(developer);
	}

	@Override
	public Developer getDeveloperByName(String name) {
		return (Developer) session.getCurrentSession().getNamedQuery("developer.name").setString("name", name).uniqueResult();
	}
	
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE Developer RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
}


}
