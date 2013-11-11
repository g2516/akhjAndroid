package fi.CodeRevolution.akhj.Models;

import java.util.Date;

/**
 * Class InspectionCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class InspectionCost extends Cost {
	
	// Attributes
	private String inspectionStation;
	private String notes;				// notifications & repairsuggestions
	
	// Constructor
	public InspectionCost(int id, int carId, Date date, float price,
			float kilometers, String inspectionStation, String notes) {
		super(id, carId, date, price, kilometers);
		this.inspectionStation = inspectionStation;
		this.notes = notes;
	}	

	// Getters and setters
	public String getInspectionStation() {
		return inspectionStation;
	}
	
	public void setInspectionStation(String inspectionStation) {
		this.inspectionStation = inspectionStation;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
