
package fi.CodeRevolution.akhj.Models;

import java.util.Date;

/**
 * Class Car with it's attributes and methods.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class Car {

	/**
	 * ID number of the car.
	 */
	private int id;
	
	/**
	 * ID number of the car's owner.
	 */
	private int ownerId;
	
	/**
	 * Unique nickname for car (for example register number).
	 */
	private String name;
	
	/**
	 * Manufacturer's name.
	 */
	private String manufacturer;
	
	/**
	 * Model name.
	 */
	private String model;
	
	/**
	 * Motor type.
	 */
	private String motor;
	
	/**
	 * Manufacturing year.
	 */
	private int year;
	
	/**
	 * Gas consumption in liters / kilometer.
	 */
	private float consumption;

	/**
	 * Date the car was purchased.
	 */
	private Date purchaseDate;
	
	/**
	 * Purchase value of the car.
	 */
	private float price;
	
	/**
	 * Kilometers driven at the purchase moment.
	 */
	private float kilometersOnPurchase;
	
	/**
	 * Date-time when created on database.
	 */
	private Date created;
	
	/**
	 * Date-time when modified at database.
	 */
	private Date modified;
	

	/**
	 * Constructor for a new Car-object.
	 * 
	 * @param id					id number of car
	 * @param ownerId				id number of car's owner
	 * @param name					nickname
	 * @param manufacturer			manufacturer's name
	 * @param model					model name
	 * @param motor					motor type/name
	 * @param year					purchase year
	 * @param consumption			consumption in liters/km
	 * @param purchaseDate			purchase date and time (yyyy-MM-dd HH:mm:ss)
	 * @param price
	 * @param kilometersOnPurchase
	 */
	public Car(int id, int ownerId, String name, String manufacturer, String model, String motor, 
			int year, float consumption, Date purchaseDate, float price, float kilometersOnPurchase) {
		this.id = id;
		this.ownerId = ownerId;
		this.name = name;
		this.manufacturer = manufacturer;
		this.model = model;
		this.motor = motor;
		this.year = year;
		this.consumption = consumption;
		this.purchaseDate =  purchaseDate;
		this.price = price;
		this.kilometersOnPurchase = kilometersOnPurchase;
		
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

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getConsumption() {
		return consumption;
	}

	public void setConsumption(float consumption) {
		this.consumption = consumption;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getKilometersOnPurchase() {
		return kilometersOnPurchase;
	}

	public void setKilometersOnPurchase(float kilometersOnPurchase) {
		this.kilometersOnPurchase = kilometersOnPurchase;
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
