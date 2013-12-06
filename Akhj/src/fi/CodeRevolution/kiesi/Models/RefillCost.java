package fi.CodeRevolution.kiesi.Models;


/**
 * Class RefillCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class RefillCost extends Cost{

	// Attributes
	private double litres;

	// Constructor
	public RefillCost(int id, int carId, String date, double price,
			double kilometers, double litres) {
		super(id, carId, date, price, kilometers);
		this.litres = litres;
	}
	
	// Getters and setters
	public double getLitres() {
		return litres;
	}

	public void setLitres(double litres) {
		this.litres = litres;
	}

}
