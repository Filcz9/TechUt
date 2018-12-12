package com.fczyz.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fczyz.domain.ComputerGame;
import com.fczyz.domain.Review;


@Component
@Transactional
public class ComputerGameManagerJDBC implements ComputerGameManager {

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
		computergame.setId(null);
		sessionFactory.getCurrentSession().persist(computergame);
	}
	
	@Override
	public void deleteComputerGame(ComputerGame computergame) {
		computergame = (ComputerGame) sessionFactory.getCurrentSession().get(ComputerGame.class,
				computergame.getId());
		
		
		sessionFactory.getCurrentSession().delete(computergame);
	}

	@Override
	public List<Review> getPositiveReview() {
		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
				person.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Car> cars = new ArrayList<Car>(person.getCars());
		return cars;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getAllClients() {
		return sessionFactory.getCurrentSession().getNamedQuery("person.all")
				.list();
	}

	@Override
	public Person findClientByPin(String pin) {
		return (Person) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin).uniqueResult();
	}


	@Override
	public Long addNewCar(Car car) {
		car.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(car);
	}

	@Override
	public void sellCar(Long personId, Long carId) {
		Person person = (Person) sessionFactory.getCurrentSession().get(
				Person.class, personId);
		Car car = (Car) sessionFactory.getCurrentSession()
				.get(Car.class, carId);
		car.setSold(true);
		person.getCars().add(car);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Car> getAvailableCars() {
		return sessionFactory.getCurrentSession().getNamedQuery("car.unsold")
				.list();
	}
	@Override
	public void disposeCar(Person person, Car car) {

		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
				person.getId());
		car = (Car) sessionFactory.getCurrentSession().get(Car.class,
				car.getId());

		Car toRemove = null;
		// lazy loading here (person.getCars)
		for (Car aCar : person.getCars())
			if (aCar.getId().compareTo(car.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			person.getCars().remove(toRemove);

		car.setSold(false);
	}

	@Override
	public Car findCarById(Long id) {
		return (Car) sessionFactory.getCurrentSession().get(Car.class, id);
	}

}
