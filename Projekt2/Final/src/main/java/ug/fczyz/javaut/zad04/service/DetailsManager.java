package ug.fczyz.javaut.zad04.service;

import java.util.List;

import ug.fczyz.javaut.zad04.domain.Details;

public interface DetailsManager {
	void addDetails(Details details);
	Details getById(long id);
	List<Details> getAll();
	void deleteDetails(Details details);
	void updateDetails(Details details);
	void clearTable();
}
