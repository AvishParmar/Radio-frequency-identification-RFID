package Homework2;
/**
 * ItemList class that handles all user operations
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 * Recitation: Section 01
 */
public class ItemList {
	private ItemInfoNode head;
	private ItemInfoNode tail;
	private ItemInfoNode cursor;
	/**
	 * ItemList default constructor
	 */
	public ItemList() {
		setHead(null);
		setTail(null);
		setCursor(null);
	}
	/**
	 * Sets the head reference of ItemList
	 * @param head
	 * Head of ItemList
	 */
	public void setHead(ItemInfoNode head) {
		this.head = head;
	}
	/**
	 * Returns the head reference of the list
	 * @return head
	 * Head of the list
	 */
	public ItemInfoNode getHead() {
		return this.head;
	}
	/**
	 * Sets the tail reference of the ItemList
	 * @param tail
	 * Tail of Itemlist
	 */
	public void setTail(ItemInfoNode tail) {
		this.tail = tail;
	}
	/**
	 * Returns the tail of the list
	 * @return tail
	 * Tail of ItemList
	 */
	public ItemInfoNode getTail() {
		return this.tail;
	}
	/**
	 * Sets the cursor reference of the ItemList
	 * @param cursor
	 * Cursor of ItemList
	 */
	public void setCursor(ItemInfoNode cursor) {
		this.cursor = cursor;
	}
	/**
	 * Returns the cursor reference of the ItemList
	 * @return cursor
	 * Cursor of ItemList
	 */
	public ItemInfoNode getCursor() {
		return this.cursor;
	}
	
	/**
	 * Inserts a new ItemInfoNode into ItemList
	 * @param name
	 * Name of product
	 * @param rfidTag
	 * RfidTag of product
	 * @param price
	 * Price of product
	 * @param initPosition
	 * Original location of product
	 * @throws InvalidRFIDException
	 * When RFID length is less than 9
	 * @throws InvalidLocationFormatException
	 * When original location length is less than 6
	 * 
	 * 
	 * ORDER OF COMPLEXITY:
	 * BEST CASE: O(1): When inserting the first and second node, there is no need to traverse.
	 * WORST CASE: O(N): When inserting the 3rd or nth node, cursor is used to traverse 
	 * 					 through the list to check if RFID matches with another product OR whether it is similar.
	 * 					 If it does not match it goes all the way to the end of the list,
	 * 					 thus traversing a total of n times.
	 */
	public void insertInfo(String name, String rfidTag, double price, String initPosition) 
			throws InvalidRFIDException, InvalidLocationFormatException {
		
		ItemInfoNode newNode = new ItemInfoNode(new ItemInfo(name, rfidTag, initPosition, price));
		
		if(this.head == null) {
			setHead(newNode);
			setTail(head);
			setCursor(head);
		
		}
		else if(head.equals(tail)) {
			head.setNext(newNode);
			newNode.setPrev(head);
			setTail(newNode);
			setCursor(tail);
		}
		else {
			setCursor(head);
			while(!(this.cursor.equals(tail))){
				if(newNode.getInfo().getRFIDTagNumber().equals(cursor.getInfo().getRFIDTagNumber())){
					newNode.setNext(cursor.getNext());
					newNode.setPrev(cursor);
					cursor.setNext(newNode);
					newNode.getNext().setPrev(newNode);
					break;
				}
				else if(newNode.getInfo().getRFIDTagNumber().compareTo(cursor.getInfo().getRFIDTagNumber() ) < 0) {
					newNode.setNext(cursor.getNext());
					newNode.setPrev(cursor);
					cursor.setNext(newNode);
					newNode.getNext().setPrev(newNode);
					break;
				}
				else
					cursor = cursor.getNext();
			}
			if(getCursor().equals(getTail())) {
				tail.setNext(newNode);
				newNode.setPrev(tail);
				tail = newNode;
			}
			
		}
		System.out.println("\nProduct added successfully");
	}
	
	/**
	 * Removed all items whose current location is "out".
	 * 
	 * ORDER OF COMPLEXITY:
	 * BEST/WORSE/AVERAGE CASE: 
	 * O(N): Cursor is reset to head and it traverses through the entire list
	 *       to check if the cursor item's current location is "out". Regardless,
	 *       this loop keeps executing until cursor has analyzed the entire list,
	 *       in order words, until cursor has analyzed n time and at the end cursor == null.
	 */
	public void removeAllPurchased() {
		System.out.println("The following item(s) have been removed from the system.\n");
		
		System.out.println("                               Original        Current\r\n" + 
				"Item Name         RFID         Location        Location     Price\r\n" + 
				"---------       ---------     ---------        ---------   ------");
		
		cursor = head;
		
		while(cursor != null) {
			if(cursor.getInfo().getCurrentLocation().equals("out")) {
				if(cursor.getPrev() == null && cursor.getNext() == null) {
					System.out.println(cursor.toString());
					setHead(null);
					setTail(head);
					setCursor(head);
				}
				else if(cursor.getNext() == null) {
					System.out.println(cursor.toString());
					tail = cursor.getPrev();
					cursor = cursor.getNext();
				}
				else if(cursor.getPrev() == null){
					System.out.println(cursor.toString());
					head = cursor.getNext();
					cursor = cursor.getNext();
				}
				else if(cursor.getNext() != null && cursor.getPrev() != null) {
					cursor.getNext().setPrev(cursor.getPrev());
					cursor.getPrev().setNext(cursor.getNext());
				}
			}else
				cursor = cursor.getNext();
		}
		
	}
	/**
	 * Changes the location of the item with the given rfidTag from its current location(source)
	 * to new location(dest). Sets current location to dest if item is found.
	 * @param rfidTag
	 * RFID of the item
	 * @param source
	 * Current Location of the item
	 * @param dest
	 * New location of the item
	 * @return
	 * True: if item is found and is moved.
	 * False: if item is not found and nothing is moved.
	 * 
	 * ORDER OF COMPLEXITY:
	 * BEST CASE: O(1): If head meets the conditions set by the parameters the loop ends and true is returned.
	 * WORST CASE: O(N): Cursor traverses past the head or through the entire list to find the item and return true,
	 *                   or not find the item and return false.
	 * @throws InvalidLocationFormatException 
	 * When the destination is either a shelf or a cart and it contain 5 digits or 3 digits respectively.
	 */
	public boolean moveItem(String rfidTag, String source, String dest) throws InvalidLocationFormatException {
		
		cursor = head;
		
		while(cursor != null) {
			if(cursor.getInfo().getRFIDTagNumber().equals(rfidTag) && cursor.getInfo().getCurrentLocation().equals(source)) {
					cursor.getInfo().setCurrentLocation(dest);
					System.out.println(cursor.getInfo().getName()+" successfully moved to "+cursor.getInfo().getCurrentLocation());
					return true;
			}
			else
				cursor = cursor.getNext();
		}
		System.out.println("\nProduct with the given initials was not found in the list.");
		return false;
	}
	
	/**
	 * Prints the information of all items.
	 * 
	 * ORDER OF COMPLEXITY:
	 * BEST/WORSE/AVERAGE CASE: O(N): Must traverse through the entire list to print the information for all the nodes.
	 */
	public void printAll() {
		System.out.println("                               Original        Current\r\n" + 
				"Item Name         RFID         Location        Location     Price\r\n" + 
				"---------       ---------     ---------        ---------   ------");
	
		
		cursor = head;
		
		while(cursor != null) {
			System.out.println(cursor.toString());
		    cursor = cursor.getNext();
		}
	}
	/**
	 * Prints information of the item at the given location
	 * @param location
	 * Location of item
	 * 
	 * ORDER OF COMPLEXITY:
	 * BEST/WORSE/AVERAGE CASE: O(N): Cursor traverses through the entire list to check if each item is at the given location.
	 */
	public void printByLocation(String location) {
		System.out.println("                               Original        Current\r\n" + 
				"Item Name         RFID         Location        Location     Price\r\n" + 
				"---------       ---------     ---------        ---------   ------");
		
		cursor = head;
		
		while(cursor != null) {
			if(cursor.getInfo().getCurrentLocation().equals(location)) {
				System.out.println(cursor.toString());
				cursor = cursor.getNext();
			}else
				cursor = cursor.getNext();
		}
	}
	
	/**
	 * Prints information of the item based on the given RFID
	 * @param rfidTagNumber
	 * RFID of the item
	 * 
	 * ORDER OF COMPLEXITY:
	 * BEST/WORSE/AVERAGE CASE: O(N): Cursor traverses through the entire list to check if each item has the given RFID.
	 */
	public void printByRFID(String rfidTagNumber) {
		System.out.println("                               Original        Current\r\n" + 
				"Item Name         RFID         Location        Location     Price\r\n" + 
				"---------       ---------     ---------        ---------   ------");
		
		cursor = head;
		
		while(cursor != null) {
			if(cursor.getInfo().getRFIDTagNumber().equals(rfidTagNumber)) {
				System.out.println(cursor.toString());
				cursor = cursor.getNext();
			}else
				cursor = cursor.getNext();
		}
	}
	/**
	 * Sets the current location of all items to their original location.
	 * 
	 * ORDER OF COMPLEXITY:
	 * BEST/WORSE/AVERAGE CASE: 
	 * O(N): Cursor traverses through the entire list to check the current location of each item
	 *       is equal to the item's original location. 
	 *       SPECIAL CASES: 
	 *       If the item's current location is "out" or is in a cart (contains initial c) the item is skipped.
	 * @throws InvalidLocationFormatException 
	 * If original location is not on a shelf (initial s) or if it is on a shelf, it does not consist of 5 digits.
	 */
	public void cleanStore() throws InvalidLocationFormatException {
		System.out.println("The following item(s) have been moved back to their original locations.\n");
		
		System.out.println("                               Original        Current\r\n" + 
				"Item Name         RFID         Location        Location     Price\r\n" + 
				"---------       ---------     ---------        ---------   ------");
		
		cursor = head;
		
		while(cursor != null) {
			if(!(cursor.getInfo().getOriginalLocation().equals(cursor.getInfo().getCurrentLocation()))){
				if(cursor.getInfo().getCurrentLocation().equals("out") || cursor.getInfo().getCurrentLocation().contains("c")) {
					cursor = cursor.getNext();
				}else {
					System.out.println(cursor.toString());
					cursor.getInfo().setCurrentLocation(cursor.getInfo().getOriginalLocation());
					cursor = cursor.getNext();
				}
			}
			else
				cursor = cursor.getNext();
		}
	}
	/**
	 * For all items whose current location is in the given cart (has initial c)
	 * the current location is changed to "out" to emphasize that the customer 
	 * has checked out of the store.
	 * 
	 * @param cartNumber
	 * Current location of the item
	 * @return
	 * Price of all items that are in that cart.
	 * 
	 * ORDER OF COMPLEXITY:
	 * BEST/WORSE/AVERAGE CASE:
	 * O(N): Cursor traverses through the entire list and analyzes each item's current location
	 *       to see if it matches the given cart number. 
	 *       Regardless of whether an item is found, the cursor always traverses through the entire list.
	 * 
	 * @throws InvalidLocationFormatException
	 * Does not apply to this method since the current location is being set to out.
	 * Only applies if the current location is being set to a new shelf or cart and the format entered is invalid
	 */
	public double checkOut(String cartNumber) throws InvalidLocationFormatException {
		int totalCost = 0;
		
		System.out.println("                               Original        Current\r\n" + 
				"Item Name         RFID         Location        Location     Price\r\n" + 
				"---------       ---------     ---------        ---------   ------");
		
		cursor = head;
		
		while(cursor != null) {
			if(cursor.getInfo().getCurrentLocation().equals(cartNumber)) {
				System.out.println(cursor.toString());
				totalCost += cursor.getInfo().getPrice();
				cursor.getInfo().setCurrentLocation("out");
			}else
				cursor = cursor.getNext();
		}
		return totalCost;
		
	}

	
}
