/**
 * InvalidLocationFormatExcpetion that is invoked when an InvalidLocationFormatException is thrown.
 * @author Avish Parmar
 */
public class InvalidLocationFormatException extends Exception {
	/**
	 * InvalidLocationFormatException default constructor
	 */
	public InvalidLocationFormatException() {
		System.out.print("\nLocation format with the given letter indice "
				+ "is incorrect! Please re-enter.\n");
		
	}
	/**
	 * InvalidLocationFormatException constructor
	 * @param x
	 * Variant of InvalidLocationFormatException
	 */
	public InvalidLocationFormatException(int x) {
		if(x == 1) {
			System.out.println("\nOriginal location must be on the shelf and "
					+ "must contain an initial s! Please re-enter.\n");
		}
		else if(x == 2) {
			System.out.println("\nA cart number must only have 3 digits! Please re-enter.\n");
		}
		else if(x == 3) {
			System.out.println("\nA shelf number must only have 5 digits! Please re-enter.\n");
		}
		
	}
}
