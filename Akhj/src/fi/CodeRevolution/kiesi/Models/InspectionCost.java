package fi.CodeRevolution.kiesi.Models;


/**
 * Class InspectionCost extending Cost-class.
 * 
 * @author Petri Matilainen
 * @version 1.0
 * @since 11.11.2013
 */
public class InspectionCost extends Cost {
	
	// Attributes
	private String inspectionstation;
	private String repairtarget;				// notifications & repairsuggestions
	
	// Constructor
	public InspectionCost(int id, int carId, String date, double price,
			double kilometers, String inspectionStation, String notes) {
		super(id, carId, date, price, kilometers);
		this.inspectionstation = inspectionStation;
		this.repairtarget = notes;
	}	

	public InspectionCost() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
	public String getInspectionStation() {
		return inspectionstation;
	}
	
	public void setInspectionStation(String inspectionStation) {
		this.inspectionstation = inspectionStation;
	}
	
	public String getNotes() {
		return this.repairtarget;
	}
	
	public void setNotes(String notes) {
		this.repairtarget = notes;
	}
		
}
