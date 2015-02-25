import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Testing

/**
 *
 *  Password represents a Password. A Password contains digits, upper 
 *	case letters, and special characters. One is generated via Random 
 *  selection of different specified amounts of digits, upper case letters, 
 *  and passwords.
 *
 */
public class Password {
    private int passLength;	
    private int numMaxDigits;
    private int numMinDigits;	
    private int numMaxUpperCase;
    private int numMinUpperCase;
    private int numMinSpecial;
    private int numMaxSpecial;
    private String allowedSpecialChracters;
    private char[] pwArray;
    private Random random;
    

    /**
     *  default constructor. sets the minumum to 1 and the maximum to 100
     *	of digits, uppercase letters, and special characters.
     */
    public Password() {
		allowedSpecialChracters = "`~@#%^&*()-_=+[]{}\\|;:',.<>/?";		
		
		numMaxDigits = 100;
		numMinDigits = 1;	
		
		numMaxUpperCase = 100;
		numMinUpperCase = 1;
		
		numMaxSpecial = 100;
		numMinSpecial = 1;		
		
		random = new Random(); 
	} // Password

    public static void main(String[] args) {
		Password p = new Password();
		System.out.println("\n\n" + p.generate() + "\n\n");
    }

    /**
     *  Sets the array of characters comprising a password to a specified 
     *  length to contain a space character.
     *
     *  @param length length of the array of characters.
     */
    private void initArrayList(int length) {
		pwArray = new char[length];
		for (int i = 0; i < length; i++) {
			pwArray[i] = ' ';
		}
    }


    /**
     *  gives  the index of an empty spot (a space) in pwArray, the array of 
     *  characters in the password.
     *
     *  @return index of empty location in pwArray
     */
    private int findSpotInString() {
		int a = getRandomNumber(0, passLength);					
		for (int j = a; j < passLength+a; j++) {
			if (j == passLength)
				j = 0;
			if (pwArray[j]  == ' ')
				return j;				
		}

		System.out.println("NEVER");
		return -1;		
    }





    /**
     *  generates the password
     *  @param length the length of the desired password
     */	
    public String generate(String b,int min,int max) {
		appendSpecialCharacters(b);
	        setPassLength(min,max);

		initArrayList(passLength);
		int currentDigits = 0;
		int currentUppercase = 0;
		int currentSpecial = 0;

		int count = 0;
		for (int i = 0; i < numMinDigits; i++ ) {
			int a = getRandomNumber(48, 57);	
			int index = findSpotInString();
			pwArray[index] = (char)a;
			currentDigits++;
		}

		for (int i = 0; i < numMinUpperCase; i++ ) {
			int a = getRandomNumber(65, 90);	
			int index = findSpotInString();
			pwArray[index] = (char)a;
			currentUppercase++;
	    }	

		for (int i = 0; i < numMinSpecial; i++ ) {
			int a = getRandomNumber(0, allowedSpecialChracters.length()-1);	
			int index = findSpotInString();
			pwArray[index] = (allowedSpecialChracters.charAt(a));		
			currentSpecial++;
	    }	
			
		count+= currentDigits;
		count+= currentUppercase;
		count+= currentSpecial;	

		while (count < passLength) {
			int a = getRandomNumber(33,126);	
			int index = findSpotInString();					
					
			if (isInBetween(a,48,57)) {		// if its a digit
				if (currentDigits < numMaxDigits)	currentDigits++;
				else continue; 
		    }

			else if (isInBetween(a,65,90)) {	// if its a UPPERCASE
				if (currentUppercase < numMaxUpperCase)	currentUppercase++;				
				else continue;
			}


			else if (isInBetween(a,33,47) || isInBetween(a,58,64)
				 || isInBetween(a,91,96) 
				 || isInBetween(a,123,126) ) // if it's a special character
			    {
				
				if (currentSpecial < numMaxSpecial) {
					if (allowedSpecialChracters.contains(((char)a)+"") 
					    == true)
					    {
						currentSpecial++;	
					}

					else continue;			
				}

				else continue;	
			}	


			// it's a lower case letter for which there is no limit
			count++;			
			pwArray[index] = (char)a;					
	    }	

		return toString();
	}




    /**
     *  calls generate method with passLength argument. used if no length provided by user
     *  @return String with password 
     */
    public void appendSpecialCharacters(String a) {
	    allowedSpecialChracters= a + allowedSpecialChracters;
	}
	
     public String generate() {
    	return generate("",numMinDigits,numMaxDigits);
    }


    /**
     *  checks whether a is in between min and max
	 *  @return true if a is in between min and max, false if not
     */
    private boolean isInBetween(int a, int min, int max) {
		if (a >= min && a <= max) return true;
		return false;
    }

    /**
     *  @param start lowest random number that can be generated
     *  @param end largest random number that can be generated
     *  @return a random number between start and end
     */
    private int getRandomNumber(int start, int end) {
		int num = random.nextInt(end-start+1)+start;	
		return num;
    }	




    /**
     *  @return the generated password
     */
    public String toString() {
		String s = "";
		for (char c : pwArray) {
			s+=c + "";
		}
		return s;
    }


    /**
     *  sets the password length to be num. if too small, makes length the 
     *  sum of the minimums of digits, upper case, and special characters.
     *
     *  @param size the size of the password
     */
    public void setPassLength(int min,int max) {
        passLength=getRandomNumber(min,max);
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


    /**
     *  sets the maximum number of UpperCase letters the password can have
     *  @param num the maximum number of upper case letters
     */
    public void setMaxUppercase(int num) {numMaxUpperCase = num;}	


    /**
     *  sets the minimum number of UpperCase letters the password can have
     *  @param num the minimum number of upper case letters
     */
    public void setMinUpperCase(int num) {numMinUpperCase = num;}	


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


    /**
     *  sets the allowed special characters that can be used in a password
     *  @param s the string containing characters that can be used as special characters
     */
    public void setAllowedSpecialCharacters(String s) {allowedSpecialChracters = s;}	
} // class Password





