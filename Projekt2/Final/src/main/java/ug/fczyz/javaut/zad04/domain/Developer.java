package ug.fczyz.javaut.zad04.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="developer.all",query="Select m from Developer m"),
	@NamedQuery(name="developer.name",query="Select m from Developer m where m.name = :name")
})
public class Developer {
	private long id;
	private String name;
	
	public Developer() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Developer(String name) {
		super();
		this.name = name;
	}
	
}
