package ug.fczyz.javaut.zad04.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.fczyz.javaut.zad04.domain.Details;
@Component
@Transactional
public class DetailsManagerHibernate implements DetailsManager {
	
	@Autowired
	SessionFactory session;
	
	@Override
	public void addDetails(Details details) {
		session.getCurrentSession().persist(details);
		
	}

	@Override
	public Details getById(long id) {
		return (Details) session.getCurrentSession().get(Details.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Details> getAll() {
		return session.getCurrentSession().getNamedQuery("details.all").list();
	}

	@Override
	public void deleteDetails(Details details) {
		session.getCurrentSession().delete(details);
		
	}

	@Override
	public void updateDetails(Details details) {
		session.getCurrentSession().saveOrUpdate(details);
		
	}
	
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE DETAILS RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
	}
}
