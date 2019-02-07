package ug.fczyz.javaut.zad04.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.fczyz.javaut.zad04.domain.ComputerGame;
import ug.fczyz.javaut.zad04.domain.Track;

@Component
@Transactional
public class ComputerGameManagerHibernate implements ComputerGameManager {

	@Autowired
	SessionFactory session;
	
	@Override
	public void addComputerGame(ComputerGame computergame) {
		session.getCurrentSession().persist(computergame);
	}

	@Override
	public ComputerGame getById(long id) {
		return (ComputerGame) session.getCurrentSession().get(ComputerGame.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerGame> getAllComputerGames() {
		return (List<ComputerGame>) session.getCurrentSession().getNamedQuery("computergame.all").list();
	}

	@Override
	public void deleteComputerGame(ComputerGame computergame) {
		session.getCurrentSession().delete(computergame);
	}

	@Override
	public void updateComputerGame(ComputerGame computergame) {
		session.getCurrentSession().saveOrUpdate(computergame);
	}
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE TRACK RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE DETAILS RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE DEVELOPER RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE COMPUTERGAME_TRACK RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE REVIEW RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE COMPUTERGAME_REVIEW RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE COMPUTERGAME RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerGame> getAllComputerGamesByCompanyName(String name) {
		return (List<ComputerGame>) session.getCurrentSession().getNamedQuery("computergame.allComputergamesByCompany").setString("name", name).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Track> getAllOwners(long id) {
		return (List<Track>) session.getCurrentSession().getNamedQuery("computergame.getAllOwners").setLong("id", id).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerGame> getallComputerGamesTracksMoreEqualThan(int number) {
		return (List<ComputerGame>) session.getCurrentSession().getNamedQuery("computergame.allComputergamesTracksMoreEqualThan").setInteger("number", number).list();
	}

	@Override
	public List<ComputerGame> getallComputerGamesByAgeRestriction(int number) {
		return (List<ComputerGame>) session.getCurrentSession().getNamedQuery("computergame.allComputergamesByAgeRestriction").setInteger("number", number).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerGame> getallComputerGamesNameMatchString(String match) {
		return (List<ComputerGame>) session.getCurrentSession().getNamedQuery("computergame.allComputergamesNameMatchString").setString("search", "%"+match+"%").list();
	}

	@Override
	public void deleteComputerGames(List<ComputerGame> computerGames) {
		for(ComputerGame ret : computerGames)
			session.getCurrentSession().delete(ret);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComputerGame> getAllComputerGameWithDateBetween(String date1, String date2) {
		Date date1p = null;
		Date date2p = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1p=(df.parse(date1));
			date2p=(df.parse(date2));
			return (List<ComputerGame>) session.getCurrentSession().getNamedQuery("computergame.allComputergamesByDateBetween").setDate("date1", date1p).setDate("date2", date2p).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
