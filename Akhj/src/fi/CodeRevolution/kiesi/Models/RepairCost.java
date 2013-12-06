package fi.CodeRevolution.kiesi.Models;


/**
 * Class RepairCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class RepairCost extends Cost {
	
	// Attributes
	private String repairtarget;

	// Constructor
	public RepairCost(int id, int carId, String date, double price,
			double kilometers, String repairTarget) {
		super(id, carId, date, price, kilometers);
		this.repairtarget = repairTarget;
	}

	// Getters and setters
	public String getRepairTarget() {
		return repairtarget;
	}

	public void setRepairTarget(String repairTarget) {
		this.repairtarget = repairTarget;
	}

}
