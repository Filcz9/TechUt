package ug.fczyz.javaut.zad04.service;

import java.util.List;

import ug.fczyz.javaut.zad04.domain.Review;

public interface ReviewManager {
	void addOpinion(Review review);
	Review getById(long id);
	List<Review> getAll();
	void deleteOpinion(Review review);
	void updateOpinion(Review review);
	void clearTable();
}
