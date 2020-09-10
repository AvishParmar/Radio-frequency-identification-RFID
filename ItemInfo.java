package Homework2;
/**
 * ItemInfo class that contains product information
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 * Recitation: Section 01
 */
public class ItemInfo {
	private final static int rfidLength = 9;
	private final static int shelfLength = 6;
	private final static int cartLength = 4;
	private String rfidTagNumber;
	private String name;
	private double price;
	private String originalLocation;
	private String currentLocation;
	
	/**
	 * Default ItemInfo constructor
	 */
	public ItemInfo() {
		
	}
	
	/**
	 * ItemInfo constructor
	 * @param name
	 * Name of item
	 * @param rfidTagNumber
	 * RFID of item
	 * @param originalLocation
	 * Original location of item
	 * @param price
	 * Price of item
	 * @throws InvalidRFIDException
	 * When RFID length is less than 9
	 * @throws InvalidLocationFormatException
	 * When original location of the item is not on a shelf or 
	 * the length of the original location is not 6
	 */
	public ItemInfo(String name, String rfidTagNumber, String originalLocation, double price) 
			throws InvalidRFIDException, InvalidLocationFormatException {
		setName(name);
		if(rfidTagNumber.length() == rfidLength) {
			if(rfidTagNumber.matches("[a-zA-Z0-9]*"))
				setRFIDTagNumber(rfidTagNumber);
			else
				throw new InvalidRFIDException(1);
		}
		else 
			throw new InvalidRFIDException();
		if(originalLocation.contains("s")) {
			if(originalLocation.length() == shelfLength) {
				setOriginalLocation(originalLocation);
			}
			else
				throw new InvalidLocationFormatException(3);
		}else
			throw new InvalidLocationFormatException(1);
		
		setPrice(price);
	}
	
	/**
	 * Sets the RFID of this item
	 * @param rfidTagNumber
	 * RFID of this item
	 */
	public void setRFIDTagNumber(String rfidTagNumber) {
		this.rfidTagNumber = rfidTagNumber;
	}
	/**
	 * Returns the RFID of this item
	 * @return rfidTagNumber
	 * RFID of this item
	 */
	public String getRFIDTagNumber() {
		return this.rfidTagNumber;
	}
	/**
	 * Set the name of this item
	 * @param name
	 * Name of this item
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the name of this item
	 * @return name
	 * Name of this item
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Set the price of this item
	 * @param price
	 * Price of this item
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * Returns the price of this item
	 * @return price
	 * Price of this item.
	 */
	public double getPrice() {
		return this.price;
	}
	/**
	 * Sets the original location of this item
	 * Also sets the intiial current location to original location
	 * @param originalLocation
	 * Original location of this item
	 */
	public void setOriginalLocation(String originalLocation) {
		this.originalLocation = originalLocation;
		this.currentLocation = originalLocation;
	}
	/**
	 * Returns original location of this item
	 * @return originalLocation
	 * Original location of this item
	 */
	public String getOriginalLocation() {
		return this.originalLocation;
	}
	/**
	 * Sets the current location of this item
	 * @param currentLocation
	 * Current location of this item.
	 * @throws InvalidLocationFormatException 
	 */
	public void setCurrentLocation(String currentLocation) throws InvalidLocationFormatException {
		if(currentLocation.contains("c")) {
			if(currentLocation.length() == cartLength) {
				this.currentLocation = currentLocation;
			}else
				throw new InvalidLocationFormatException(2);
		}
		else if(currentLocation.contains("s")){
			if(currentLocation.length() == shelfLength) {
				this.currentLocation = currentLocation;
			}else
				throw new InvalidLocationFormatException(3);	
		}
		else
			this.currentLocation = currentLocation;
	}
	/**
	 * Returns the current location of this item
	 * @return currentLocation
	 * Current location of this item
	 */
	public String getCurrentLocation() {
		return this.currentLocation;
	}
	/**
	 * Returns all information of this item in a string format
	 */
	public String toString() {
		String str = String.format("%-16s%-14s%-17s%-13s%.2f", getName(), getRFIDTagNumber(), 
				getOriginalLocation(), getCurrentLocation(), getPrice());
		return str;
	}
}
