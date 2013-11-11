package fi.CodeRevolution.akhj.Models;

import java.util.Date;

/**
 * Superclass Cost for shared attributes and methods to all different costs.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class Cost {

	// Attributes
	private int id;
	private int carId;
	private Date date;
	private float price;
	private float kilometers;
	private Date created;
	private Date modified;
	
	// Constructor
	public Cost(int id, int carId, Date date, float price, float kilometers) {
		this.id = id;
		this.carId = carId;
		this.date = date;
		this.price = price;
		this.kilometers = kilometers;
		
		this.created = new Date();
		this.modified = new Date();
	}
	
	// Getters and setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCarId() {
		return carId;
	}
	
	public void setCarId(int carId) {
		this.carId = carId;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getKilometers() {
		return kilometers;
	}
	
	public void setKilometers(float kilometers) {
		this.kilometers = kilometers;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getModified() {
		return modified;
	}
	
	public void setModified(Date modified) {
		this.modified = modified;
	}
	
}
