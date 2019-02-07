package ug.fczyz.javaut.zad04.service;

import java.util.List;

import ug.fczyz.javaut.zad04.domain.Track;

public interface TrackManager {
	void addTrack(Track track);
	Track getById(long id);
	List<Track> getAll();
	void deleteTrack(Track track);
	void updateTrack(Track track);
	void clearTable();
}
