import java.util.Scanner;
/**
 * DepartmentStore class that acts as the main method of the program
 * @author Avish Parmar
 */
public class DepartmentStore {
	/**
	 * Main method
	 * @param args
	 * Arguments passed.
	 */
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		ItemList itemList = new ItemList();
		System.out.println("     _________________________________"
				+"    \n    |MAIN MENU                        |"
				+ "   \n    |C - Clean store                  |\r\n" + 
				"    |I - Insert an item into the list | \r\n" + 
				"    |L - List by location             |\r\n" + 
				"    |M - Move an item in the store    |\r\n" + 
				"    |O - Checkout                     |\r\n" + 
				"    |P - Print all items in store     | \r\n" + 
				"    |R - Print by RFID tag number     |\r\n" + 
				"    |U - Update inventory system      |\r\n" + 
				"    |Q - Exit the program.            |\r\n"
				+ "    |_________________________________|");

		System.out.print("Please selection an option: ");
		
		do {
			
			String selection = stdin.nextLine();
			
			if(selection.equalsIgnoreCase("C")) {
				
				try {
					itemList.cleanStore();
				} catch (InvalidLocationFormatException e) {}
				
				System.out.print("\nPlease selection an option: ");
			}
			else if(selection.equalsIgnoreCase("I")) {
				
				System.out.print("Enter the name: ");
				String name = stdin.nextLine();
				System.out.print("Enter the RFID: ");
				String rfid = stdin.nextLine();
				System.out.print("Enter the original location: ");
				String originalLocation = stdin.nextLine();
				System.out.print("Enter the price: ");
				double price = stdin.nextDouble();
				
				try {
					itemList.insertInfo(name, rfid, price, originalLocation);
				} catch (InvalidRFIDException e) {} 
				catch (InvalidLocationFormatException e) {}
				
				System.out.print("\nPlease selection an option: ");
			}
			else if(selection.equalsIgnoreCase("L")) {
				
				System.out.print("Enter the location: ");
				String location = stdin.nextLine();
				
				itemList.printByLocation(location);
				
				
				System.out.print("\nPlease selection an option: ");
			}
			else if(selection.equalsIgnoreCase("M")) {
				System.out.print("Enter the RFID: ");
				String rfid = stdin.nextLine();
				System.out.print("Enter the original location: ");
				String originalLocation = stdin.nextLine();
				System.out.print("Enter the new location: ");
				String newLocation = stdin.nextLine();
				
				try {
					itemList.moveItem(rfid, originalLocation, newLocation);
				}catch (InvalidLocationFormatException ex){
					
				}
				
				System.out.print("\nPlease selection an option: ");
			}
			else if(selection.equalsIgnoreCase("O")) {
				
				System.out.print("Enter the cart number: ");
				String cartNumber = stdin.nextLine();
				
				double price = 0;
				try {
					price = itemList.checkOut(cartNumber);
				} catch (InvalidLocationFormatException e) {}
				
				System.out.println("\nThe total cost for all merchandise in cart "+cartNumber.substring(1)+" was $"+price);
				System.out.print("\nPlease selection an option: ");
			}
			else if(selection.equalsIgnoreCase("P")) {
				
				itemList.printAll();
				
				System.out.print("\nPlease selection an option: ");
			}
			else if(selection.equalsIgnoreCase("R")) {
				System.out.print("Enter the RFID: ");
				String rfid = stdin.nextLine();
				
				itemList.printByRFID(rfid);
				
				System.out.print("\nPlease selection an option: ");
			}
			else if(selection.equalsIgnoreCase("U")) {
				
				itemList.removeAllPurchased();
				
				System.out.print("\nPlease selection an option: ");
			}
			else if(selection.equalsIgnoreCase("Q")) {
				System.out.println("\nProgram terminating normally...");
				stdin.close();
				System.exit(1);
			}
			
		}while(true);
		
	}

}
