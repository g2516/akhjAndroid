package fi.CodeRevolution.akhj.Models;

import java.util.Date;

/**
 * Class InsuranceCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class InsuranceCost extends Cost {

	// Attributes
	private String insuranceType;
	private String insuranceCompany;
	
	// Constructor
	public InsuranceCost(int id, int carId, Date date, float price,
			float kilometers, String insuranceType, String insuranceCompany) {
		super(id, carId, date, price, kilometers);
		this.insuranceType = insuranceType;
		this.insuranceCompany = insuranceCompany;
	}

	// Getters and setters
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	
}
