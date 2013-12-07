package fi.CodeRevolution.kiesi.Models;


/**
 * Superclass Cost for shared attributes and methods to all different costs.
 * 
 * @author Petri Matilainen
 * @version 2.0
 * @since 11.11.2013
 */
public class Cost {

	// Attributes
	private int id;
	private int carId;
	private String date;
	private double price;
	private double kilometers;
	
	// Constructor
	public Cost(int id, int carId, String date, double price, double kilometers) {
		this.id = id;
		this.carId = carId;
		this.date = date;
		this.price = price;
		this.kilometers = kilometers;
		
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
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getKilometers() {
		return kilometers;
	}
	
	public void setKilometers(double kilometers) {
		this.kilometers = kilometers;
	}
	@Override
	public String toString()
	{
		return "Pvm : "+this.getDate()+", Hinta : "+this.getPrice();
	}
}
