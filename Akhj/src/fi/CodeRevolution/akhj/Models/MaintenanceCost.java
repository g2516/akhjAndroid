package fi.CodeRevolution.akhj.Models;

import java.util.Date;

/**
 * Class MaintenanceCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class MaintenanceCost extends Cost {

	// Attributes
	private String maintenanceTarget;

	// Constructor
	public MaintenanceCost(int id, int carId, Date date, float price,
			float kilometers, String maintenanceTarget) {
		super(id, carId, date, price, kilometers);
		this.maintenanceTarget = maintenanceTarget;
	}
	
	// Getters and setters
	public String getMaintenanceTarget() {
		return maintenanceTarget;
	}

	public void setMaintenanceTarget(String maintenanceTarget) {
		this.maintenanceTarget = maintenanceTarget;
	}
	
}
