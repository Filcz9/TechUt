package ug.fczyz.javaut.zad04.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ug.fczyz.javaut.zad04.domain.Track;
@Component
@Transactional
public class TrackManagerHibernate implements TrackManager {
	
	@Autowired
	SessionFactory session;
	
	@Override
	public void addTrack(Track track) {
		session.getCurrentSession().persist(track);
		
	}

	@Override
	public Track getById(long id) {
		return (Track) session.getCurrentSession().get(Track.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Track> getAll() {
		return session.getCurrentSession().getNamedQuery("track.all").list();
	}

	@Override
	public void deleteTrack(Track track) {
		session.getCurrentSession().delete(track);
		
	}

	@Override
	public void updateTrack(Track track) {
		session.getCurrentSession().saveOrUpdate(track);
		
	}
	
	public void clearTable()
	{
		session.getCurrentSession().createSQLQuery("TRUNCATE TABLE TRACK RESTART IDENTITY AND COMMIT NO CHECK").executeUpdate();
	}
}
