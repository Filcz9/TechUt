package ug.fczyz.javaut.zad04.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.fczyz.javaut.zad04.domain.Review;

@Component
@Transactional
public class ReviewManagerHibernate implements ReviewManager {
	
	@Autowired
	SessionFactory session;
	
	@Override
	public void addOpinion(Review review) {
		session.getCurrentSession().persist(review);
		
	}

	@Override
	public Review getById(long id) {
		return (Review) session.getCurrentSession().get(Review.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Review> getAll() {
		return session.getCurrentSession().getNamedQuery("review.all").list();
	}

	@Override
	public void deleteOpinion(Review review) {
		session.getCurrentSession().delete(review);
		
	}

	@Override
	public void updateOpinion(Review review) {
		session.getCurrentSession().saveOrUpdate(review);
		
	}
	
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE REVIEW RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
	}
}
