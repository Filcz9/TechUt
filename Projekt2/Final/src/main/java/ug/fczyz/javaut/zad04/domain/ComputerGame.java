package ug.fczyz.javaut.zad04.domain;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
		@NamedQuery(name="computergame.all",query="select m from ComputerGame m"),
		@NamedQuery(name="computergame.getAllOwners",query="select m.tracks from ComputerGame m where m.id = :id"),
		@NamedQuery(name="computergame.allComputergamesByCompany",query="select m from ComputerGame m JOIN m.developer mm where mm.name = :name"),
		@NamedQuery(name="computergame.allComputergamesByDateBetween",query="select m from ComputerGame m where m.date between :date1  and :date2"),
		@NamedQuery(name="computergame.allComputergamesTracksMoreEqualThan",query="select m from ComputerGame m JOIN m.tracks e group by m.id having count(e.id) >= :number"),
		@NamedQuery(name="computergame.allComputergamesByAgeRestriction",query="select m from ComputerGame m where m.ageRes = :number"),
		@NamedQuery(name="computergame.allComputergamesNameMatchString",query="select m from ComputerGame m where m.name LIKE :search")
})
public class ComputerGame {
	private long id;
	private String name;
	private int ageRes;
	private double price;
	private Date date;
	private Developer developer;
	private Details details;
	private Set<Track> tracks = new HashSet<Track>();
	private Set<Review> reviews = new HashSet<Review>();
	public ComputerGame() {
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
	public int getAgeRes() { return ageRes; }
	public void setAgeRes(int ageRes) {
		this.ageRes = ageRes;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date=(df.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setDate(Date date) {
		this.date=date;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	public Set<Track> getTracks() {
		return tracks;
	}
	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}
	@OneToMany(fetch=FetchType.EAGER)
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

}
