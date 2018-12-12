package com.fczyz.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



@Entity
@NamedQueries({
		@NamedQuery(name = "Review.positive", query = "Select r from Review r Where r.positive = true")
})
public class Review {
	private long id;
	
	private String review;
	private double rating;
	private boolean positive;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public double getRating()
	{
		return rating;
	}
	public void setRating(double rating)
	{
		this.rating = rating;
	}
	public boolean getPositive()
	{
		return positive;
	}
	public void setPositive(boolean positive)
	{
		this.positive = positive;
	}
}