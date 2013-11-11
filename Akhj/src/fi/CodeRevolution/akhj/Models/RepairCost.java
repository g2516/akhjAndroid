package fi.CodeRevolution.akhj.Models;

import java.util.Date;

/**
 * Class RepairCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class RepairCost extends Cost {
	
	// Attributes
	private String repairTarget;

	// Constructor
	public RepairCost(int id, int carId, Date date, float price,
			float kilometers, String repairTarget) {
		super(id, carId, date, price, kilometers);
		this.repairTarget = repairTarget;
	}

	// Getters and setters
	public String getRepairTarget() {
		return repairTarget;
	}

	public void setRepairTarget(String repairTarget) {
		this.repairTarget = repairTarget;
	}

}
