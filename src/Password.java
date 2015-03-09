import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 *  Password represents a Password. A Password contains digits, upper 
 *	case letters, and special characters. One is generated via Random 
 *  selection of different specified amounts of digits, upper case letters, 
 *  and passwords.
 *
 */
public class Password {
	protected int numMinDigits = 1; 
	protected int numMaxDigits = 5;
	protected int numDigits;
	
	protected int numMinUppercase = 1; 
	protected int numMaxUppercase = 5;
	protected int numUppercase;

	protected int numMinLowercase = 1;
	protected int numMaxLowercase = 5;
	protected int numLowercase;

	protected int numMinSpecial = 1;
	protected int numMaxSpecial = 5;
	protected int numSpecial;


	protected String userSpecialChars;
	protected String defaultSpecialChars = "`~@#%^&*()-_=+[]{}\\|;:',.<>/?";
	protected ArrayList<Character> digitsArray = stringToCharArrayList("0123456789");
	protected ArrayList<Character> lowercaseArray = stringToCharArrayList("abcdefghijklmnopqrstuvwxyz");
	protected ArrayList<Character> uppercaseArray = stringToCharArrayList("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	protected ArrayList<Character> specialsArray = stringToCharArrayList(defaultSpecialChars);

	protected boolean includeDigits = false;
	protected boolean includeUpper = false;
	protected boolean includeLower = false;
	protected boolean includeSpecial = false;
	protected boolean includeUserSpecial = false;

	protected ArrayList<Character> passwordArray = new ArrayList<Character>();

	protected Random random = new Random();






	public String generatePassword() {
		passwordArray.removeAll(passwordArray);

		if (includeDigits) { 
			passwordArray.addAll(getNRandomCharsFromArray(numDigits,digitsArray)); 
		}

		if (includeUpper) { 
			passwordArray.addAll(getNRandomCharsFromArray(numUppercase,uppercaseArray));
		}
		
		if (includeLower) { 
			passwordArray.addAll(getNRandomCharsFromArray(numLowercase,lowercaseArray));
		}

		if (includeSpecial && !includeUserSpecial) { 
			passwordArray.addAll(getNRandomCharsFromArray(numSpecial,specialsArray));
		}

		if (includeSpecial && includeUserSpecial) {
			passwordArray.addAll( 
				getNRandomCharsFromArray(numSpecial, stringToCharArrayList(userSpecialChars))
							);        
		}

		/*  Now, passwordArray has a random amount (within the user-specified range) 
			of each TYPE of character specified by the user.

			However, due to the ordering of the code above, they would have been 
			added into the passwordArray in the order digits, upper, lower, special.
			Thus, an example would be: 830824BSKDHncmcn"}{()}

			Notice that each of the types of characters are still clumped together.

			So, we'll run the current state of the passwordArray through our
			getNRandomCharsFromArray function, but and ask it to draw the LENGTH of the
			passwordArray -- effectively scrambling the order of the characters.
		*/


		passwordArray = getNRandomCharsFromArray(passwordArray.size(), passwordArray);

		return passwordArrayToString();

	}
	




	protected ArrayList<Character> stringToCharArrayList(String inputStr) {
		ArrayList<Character> charArray = new ArrayList<Character>();
		for (char c : inputStr.toCharArray()) {
			charArray.add(c);
		}
		return charArray;
	} 




	protected ArrayList<Character> getNRandomCharsFromArray(int n, ArrayList<Character> charArrayIn) {
		ArrayList<Character> outArray = new ArrayList<Character>();
		if ( charArrayIn.size() <= 0 ) return outArray;

		// if user wants more character than are in the input string, call
		// the function again, and append the result to outArray recursively
		while (n > charArrayIn.size()) { 
			outArray.addAll( getNRandomCharsFromArray(charArrayIn.size(), charArrayIn));
			n = n - charArrayIn.size();
		}

		int index;
		for (int i = 0; i < n; i++) {
			index = getRandomNumber(0, charArrayIn.size());
			Character c = charArrayIn.get(index);
			charArrayIn.remove(index);
			outArray.add(c);
		}
	
	return outArray;
	}




	/**
	 *  Generates multiple passwords by calling the generatePassword() method.
	 *
	 *  @param numberOfPasswords the number of passwords to be generated
	 *
	 *  @return returns an array of length numberOfPasswordsof strings, which are passwords
	 */
	public String[] generateMultiplePasswords(int numberOfPasswords){
		String [] passwordStringArray = new String [numberOfPasswords];

		for (int i = 0; i < numberOfPasswords; i++){
			passwordStringArray[i] = generatePassword();
		}

		return passwordStringArray;
	}





	/**
	 *  @return the generated password as a string
	 */
	public String passwordArrayToString() {
		String s = "";
		for (Character c : passwordArray) {
			s+=c + "";
		}
		return s;
	}



	/**
	 *  @param start lowest random number that can be generated
	 *  @param end largest random number that can be generated
	 *  @return a random number between start and end
	 */
	protected int getRandomNumber(int start, int end) {
		int num = random.nextInt(end-start) + start;
		return num;
	}	


	
	/**
	 *  sets the minimum number of digits the password can have
	 *  @param num minimum number of digits
	 */
	public void setMinDigits(int num) {numMinDigits = num;}	

	/**
	 *  sets the maximum number of digits the password can have
	 *  @param num maximum number of digits
	 */
	public void setMaxDigits(int num) {numMaxDigits = num;}	

	// @@@@@@ ADD JAVADOC COMMENT!!!!
	public void setNumDigits(int min, int max) {
		numDigits = getRandomNumber(min, max);
	}




	/**
	 *  sets the minimum number of Uppercase letters the password can have
	 *  @param num the minimum number of upper case letters
	 */
	public void setMinUppercase(int num) {numMinUppercase = num;}	

	/**
	 *  sets the maximum number of Uppercase letters the password can have
	 *  @param num the maximum number of upper case letters
	 */
	public void setMaxUppercase(int num) {numMaxUppercase = num;}   



	// @@@@ ADD JAVADOC COMMENT!!!
	public void setNumUppercase(int min, int max) {
		numUppercase = getRandomNumber(min, max);
	}

	public int getNumUppercase() { return numUppercase; }



	/**
	 *  sets the minimum number of Uppercase letters the password can have
	 *  @param num the minimum number of upper case letters
	 */
	public void setMinLowercase(int num) {numMinLowercase = num;}   

	/**
	 *  sets the maximum number of Uppercase letters the password can have
	 *  @param num the maximum number of upper case letters
	 */
	public void setMaxLowercase(int num) {numMaxLowercase = num;}   

	// @@@@ ADD JAVADOC COMMENT!!!
	public void setNumLowercase(int min, int max) {
		numLowercase = getRandomNumber(min, max);
	}





	/**
	 *  sets the minimum number of special characters the password can have
	 *  @param num the minumum number of special characters
	 */	
	public void setMinSpecial(int num) {numMinSpecial = num;}	

	/**
	 *  sets the maximum number of special characters the password can have
	 *  @param num the maximum number of special characters
	 */
	public void setMaxSpecial(int num) {numMaxSpecial = num;}	

	// @@@@ ADD JAVADOC COMMENT!!!
	public void setNumSpecial(int min, int max) {
		numSpecial = getRandomNumber(min, max);
	}




	/**
	 *  sets the custom string of special characters that can be used in a
	 *  password; the string is used in place of the default set of special chars.
	 *  @param s the string containing characters that can be used as special characters
	 */
	public void setUserSpecialChars(String s) { userSpecialChars = s; }



	public void setIncludeDigits(boolean a) { includeDigits = a; }
	public void setIncludeUpper(boolean a) { includeUpper = a; }
	public void setIncludeLower(boolean a) { includeLower = a; }
	public void setIncludeSpecial(boolean a) { includeSpecial = a; }
	public void setIncludeUserSpecial(boolean a) { includeUserSpecial = a; }

	public boolean getIncludeDigits() { return includeDigits; }
	public boolean getIncludeUpper() { return includeUpper; }
	public boolean getIncludeLower() { return includeLower; }
	public boolean getIncludeSpecial() { return includeSpecial; }
	public boolean getIncludeUserSpecial() { return includeUserSpecial; }





} // class Password





