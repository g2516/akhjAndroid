package fi.CodeRevolution.kiesi.Models;


/**
 * Class InsuranceCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class InsuranceCost extends Cost {

	// Attributes
	private String insurancetype;
	private String insurancecompany;
	
	// Constructor
	public InsuranceCost(int id, int carId, String date, double price,
			double kilometers, String insuranceType, String insuranceCompany) {
		super(id, carId, date, price, kilometers);
		this.insurancetype = insuranceType;
		this.insurancecompany = insuranceCompany;
	}

	// Getters and setters
	public String getInsuranceType() {
		return insurancetype;
	}

	public void setInsuranceType(String insuranceType) {
		this.insurancetype = insuranceType;
	}
	
	public String getInsuranceCompany() {
		return insurancecompany;
	}
	
	public void setInsuranceCompany(String insuranceCompany) {
		this.insurancecompany = insuranceCompany;
	}
	
}
