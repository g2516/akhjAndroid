package fi.CodeRevolution.akhj.Models;

import java.util.Date;

/**
 * Class RefillCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class RefillCost extends Cost{

	// Attributes
	private float litres;

	// Constructor
	public RefillCost(int id, int carId, Date date, float price,
			float kilometers, float litres) {
		super(id, carId, date, price, kilometers);
		this.litres = litres;
	}
	
	// Getters and setters
	public float getLitres() {
		return litres;
	}

	public void setLitres(float litres) {
		this.litres = litres;
	}

}
