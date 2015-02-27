import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 *  Password represents a Password. A Password contains digits, upper 
 *	case letters, and special characters. One is generated via Random 
 *  selection of different specified amounts of digits, upper case letters, 
 *  and passwords.
 *
 */
public class Password {
    protected int passLength;	
    protected int numMaxDigits;
    protected int numMinDigits;	
    protected int numMaxUpperCase;
    protected int numMinUpperCase;
    protected int numMinSpecial;
    protected int numMaxSpecial;
    protected String allowedSpecialCharacters;
    protected char[] passwordArray;
    protected Random random;
    

    /**
     *  default constructor. sets the minumum to 1 and the maximum to 100
     *	of digits, uppercase letters, and special characters.
     */
    public Password() {
		allowedSpecialCharacters = "";		
		
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
		System.out.println("\n\n" + p.generatePassword() + "\n\n");
    }


    /**
     *  Sets the array of characters comprising a password to a specified 
     *  length to contain a space character.
     *
     *  @param length length of the array of characters.
     */
    protected void initializeArrayList(int length) {
		passwordArray = new char[length];
		for (int i = 0; i < length; i++) {
			passwordArray[i] = ' ';
		}
    }


    /**
     *  gives  the index of an empty spot (a space) in passwordArray, the array of 
     *  characters in the password.
     *
     *  @return index of empty location in passwordArray
     */
    protected int findEmptySpotInString() {
        /*
            This function helps in the process of replacing a space character in
            the initialized array with an actual character determined by the user's
            recipe.

            Start the index of insertion, i, at a random spot along the Array.
            If a reaches the end of the string, it wraps around to the beginning.
            
            This is done so that the replacement occurs at a random index in
            the passwordArray.

         */

        int i = getRandomNumber(0, passLength);					
		for (int j = i; j < passLength+i; j++) {
			if (j == passLength)
				j = 0;
			if (passwordArray[j]  == ' ')
				return j;				
		}

		System.out.println("NEVER");
		return -1;		
    }





    /**
     *  generates the password
     *  @param length the length of the desired password
     */	
    public String generatePassword(String b,int min,int max) {
		appendSpecialCharacters(b);
	        setPassLength(min,max);

		initializeArrayList(passLength);
		int currentDigits = 0;
		int currentUppercase = 0;
		int currentSpecial = 0;

		int count = 0;
		for (int i = 0; i < numMinDigits; i++ ) {
			int a = getRandomNumber(48, 57);	
			int index = findEmptySpotInString();
			passwordArray[index] = (char)a;
			currentDigits++;
		}

		for (int i = 0; i < numMinUpperCase; i++ ) {
			int a = getRandomNumber(65, 90);	
			int index = findEmptySpotInString();
			passwordArray[index] = (char)a;
			currentUppercase++;
	    }	

		for (int i = 0; i < numMinSpecial; i++ ) {
			int a = getRandomNumber(0, allowedSpecialCharacters.length()-1);	
			int index = findEmptySpotInString();
			passwordArray[index] = (allowedSpecialCharacters.charAt(a));		
			currentSpecial++;
	    }	
			
		count+= currentDigits;
		count+= currentUppercase;
		count+= currentSpecial;	

		while (count < passLength) {
			int a = getRandomNumber(33,126);	
			int index = findEmptySpotInString();					
					
			if (isInRange(a,48,57)) {		// if its a digit
				if (currentDigits < numMaxDigits)	currentDigits++;
				else continue; 
		    }

			else if (isInRange(a,65,90)) {	// if its a UPPERCASE
				if (currentUppercase < numMaxUpperCase)	currentUppercase++;				
				else continue;
			}


			else if (isInRange(a,33,47) || isInRange(a,58,64)
				 || isInRange(a,91,96) 
				 || isInRange(a,123,126) ) // if it's a special character
			    {
				
				if (currentSpecial < numMaxSpecial) {
					if (allowedSpecialCharacters.contains(((char)a)+"") 
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
			passwordArray[index] = (char)a;					
	    }	

		return passwordArrayToString();
	}


    public String[] generateMultiplePasswords(String b, int min, int max, int numberOfPasswords){
        String [] passwordStringArray = new String [numberOfPasswords];

        for (int i = 0; i < numberOfPasswords; i++){
            passwordStringArray[i] = generatePassword(b, min, max);
        }

        return passwordStringArray;
    }




    /**
     *  calls generate method with passLength argument. used if no length provided by user
     *  @return String with password 
     */
    public void appendSpecialCharacters(String a) {
	    allowedSpecialCharacters= a + allowedSpecialCharacters;
	}
	

    // Generates a string with no special characters
    // CURRENTLY UNSURE HOW THIS IS USED
    public String generatePassword() {
    	return generatePassword("",numMinDigits,numMaxDigits);
    }


    /**
     *  checks whether a is in between min and max
	 *  @return true if a is in between min and max, false if not
     */
    protected boolean isInRange(int a, int min, int max) {
		if (a >= min && a <= max) return true;
		return false;
    }


    /**
     *  @param start lowest random number that can be generated
     *  @param end largest random number that can be generated
     *  @return a random number between start and end
     */
    protected int getRandomNumber(int start, int end) {
		int num = random.nextInt(end-start+1)+start;	
		return num;
    }	




    /**
     *  @return the generated password
     */
    public String passwordArrayToString() {
		String s = "";
		for (char c : passwordArray) {
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
    public void setMinUppercase(int num) {numMinUpperCase = num;}	


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
    public void setAllowedSpecialCharacters(String s) {allowedSpecialCharacters = s;}	
} // class Password





