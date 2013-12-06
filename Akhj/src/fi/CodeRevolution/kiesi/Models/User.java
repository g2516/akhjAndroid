package fi.CodeRevolution.kiesi.Models;

import java.util.ArrayList;

/**
 * Class User with it's attributes and methods.
 * 
 * @author Petri Matilainen
 * @version 2.0
 * @since 11.11.2013
 */
public class User {
	
	// Attributes
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private ArrayList<Car> cars;
	// Constructor
	public User(int id, String email, String firstName, String lastName, 
			String password) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.cars=new ArrayList<Car>();
	}

	// Getters and setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}
	
}
