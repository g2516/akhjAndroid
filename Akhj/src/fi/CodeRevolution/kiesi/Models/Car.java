
package fi.CodeRevolution.kiesi.Models;

import java.util.ArrayList;

/**
 * Class Car with it's attributes and methods.
 * 
 * @author Petri Matilainen, Tupaq Castro
 * @version 2.0
 * @since 11.11.2013
 */
public class Car {

	/**
	 * ID number of the car.
	 */
	private int id;
	
	/**
	 * ID number of the car's owner.
	 */
	private int ownerId;
	
	/**
	 * Unique nickname for car (for example register number).
	 */
	private String name;
	
	/**
	 * Manufacturer's name.
	 */
	private String manufacturer;
	
	/**
	 * Model name.
	 */
	private String model;
	
	/**
	 * Motor type.
	 */
	private String motor;
	
	/**
	 * Manufacturing year.
	 */
	private int year;
	
	/**
	 * Gas consumption in liters / kilometer.
	 */
	private double consumption;

	/**
	 * Date the car was purchased.
	 */
	private String date;
	
	/**
	 * Purchase value of the car.
	 */
	private double price;
	
	/**
	 * Kilometers driven at the purchase moment.
	 */
	private double kilometers;
	
	/**
	 * ArrayList containing all inspection costs to this car
	 */
	private ArrayList<InspectionCost> inspectionCosts;
	
	/**
	 * ArrayList containing all insurance costs to this car
	 */
	private ArrayList<InsuranceCost> insuranceCosts;
	
	/**
	 * ArrayList containing all repair costs to this car
	 */
	private ArrayList<RepairCost> repairCosts;
	
	/**
	 * ArrayList containing all refill costs to this car
	 */
	private ArrayList<RefillCost> refillCosts;
	
	/**
	 * ArrayList containing all maintenance costs to this car
	 */
	private ArrayList<MaintenanceCost> maintenanceCosts;

	/**
	 * Constructor for a new Car-object.
	 * 
	 * @param id					id number of car
	 * @param ownerId				id number of car's owner
	 * @param name					nickname
	 * @param manufacturer			manufacturer's name
	 * @param model					model name
	 * @param motor					motor type/name
	 * @param year					purchase year
	 * @param consumption			consumption in liters/km
	 * @param purchaseDate			purchase date and time (yyyy-MM-dd HH:mm:ss)
	 * @param price
	 * @param kilometersOnPurchase
	 */
	public Car(int id, int ownerId, String name, String manufacturer, String model, String motor, 
			int year, double consumption, String purchaseDate, double price, double kilometersOnPurchase) {
		this.id = id;
		this.ownerId = ownerId;
		this.name = name;
		this.manufacturer = manufacturer;
		this.model = model;
		this.motor = motor;
		this.year = year;
		this.consumption = consumption;
		this.date =  purchaseDate;
		this.price = price;
		this.kilometers = kilometersOnPurchase;
		

		this.inspectionCosts=new ArrayList<InspectionCost>();
		this.insuranceCosts=new ArrayList<InsuranceCost>();
		this.repairCosts=new ArrayList<RepairCost>();
		this.refillCosts=new ArrayList<RefillCost>();
		this.maintenanceCosts=new ArrayList<MaintenanceCost>();
	}
	
	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String purchaseDate) {
		this.date = purchaseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getKilometers() {
		return kilometers;
	}

	public void setKilometers(double kilometersOnPurchase) {
		this.kilometers = kilometersOnPurchase;
	}

	public ArrayList<InspectionCost> getInspectionCosts() {
		return inspectionCosts;
	}

	public void setInspectionCosts(ArrayList<InspectionCost> inspectionCosts) {
		this.inspectionCosts = inspectionCosts;
	}

	public ArrayList<InsuranceCost> getInsuranceCosts() {
		return insuranceCosts;
	}

	public void setInsuranceCosts(ArrayList<InsuranceCost> insuranceCosts) {
		this.insuranceCosts = insuranceCosts;
	}

	public ArrayList<RepairCost> getRepairCosts() {
		return repairCosts;
	}

	public void setRepairCosts(ArrayList<RepairCost> repairCosts) {
		this.repairCosts = repairCosts;
	}

	public ArrayList<RefillCost> getRefillCosts() {
		return refillCosts;
	}

	public void setRefillCosts(ArrayList<RefillCost> refillCosts) {
		this.refillCosts = refillCosts;
	}

	public ArrayList<MaintenanceCost> getMaintenanceCosts() {
		return maintenanceCosts;
	}

	public void setMaintenanceCosts(ArrayList<MaintenanceCost> maintenanceCosts) {
		this.maintenanceCosts = maintenanceCosts;
	}
	@Override
	public String toString()
	{
		return "Valmistaja : "+this.getManufacturer()+", Malli : "+this.getModel();
	}
}
