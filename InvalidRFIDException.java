/**
 * InvalidRFIDException class that is invoked when an InvalidRFIDException is thrown.
 * @author Avish Parmar
 */
public class InvalidRFIDException extends Exception {
	/**
	 * InvalidRFIDException default constructor.
	 */
	public InvalidRFIDException() {
		System.out.print("\nRFID must be 9 characters long and must only "
				+ "contain alphabets and digits, please re-enter.\n");
	}
	
	public InvalidRFIDException(int x) {
		if(x == 1) {
			System.out.println("\nRFID must only contain letters and numbers, "
					+ "no special characters, please re-enter.\n");
		}
	}
}
