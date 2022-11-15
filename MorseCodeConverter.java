import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	//Static MorseCodeTree object that builds the tree
	private static MorseCodeTree mct = new MorseCodeTree();
	
	/*
	 * returns a string with all the data in the tree in LNR order with an space in between them. 
	 * Uses the toArrayList method in MorseCodeTree. It should return the data in this order:
	 * "h s v i f u e l r a p w j b d x n c k y t z g q m o"
	 * Note the extra space between j and b - that is because there is an empty string that is the root, and in the LNR traversal, the root would come between the right most child of the left tree (j) and the left most child of the right tree (b). This is used for testing purposes to make sure the MorseCodeTree has been built properly
	 */
	public static String printTree() {
		
		//String variable to hold the result
		String result = "";
		
		//Convert the tree into an array and loop through it, store each element into result
		for(String letter : mct.toArrayList()) {
			result += letter + " ";
		}

		//Return result and trim to eliminate any possible space at the end of the String
		return result.trim();
	}

	//Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	public static String convertToEnglish(File file) throws FileNotFoundException{

		try {
			
			Scanner input = new Scanner(file);
			String[] splitString;
			String result = "";

			
			while(input.hasNext()) {
				 //Split the line being read and store it as an array
				 splitString = input.nextLine().split(" ");
				 
				 //Check each element in the array and add its data to the result String
				 for(String s : splitString) 
						result += mct.fetch(s);
			}
			//Close scanner object
			input.close();
			
			//Return result
			return result;

		} catch(FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException();
		}
		
		
	}
	
	public static String convertToEnglish(String str) {
		String[] strArray = str.split(" ");
		String result = "";
		
		for(String s : strArray) {
			result += mct.fetch(s);
		}
		
		return result;
	}

}
