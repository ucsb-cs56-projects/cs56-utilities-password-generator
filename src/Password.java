import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.ArrayList;

/**

   Password represents a Password. A Password contains digits, upper case letters, and special characters. One is generated via Random selection of different specified amounts of digits, upper case letters, and passwords

*/

public class Password 
{

    /**
       Generates a password String.

       How the algorithm works:
       * Filter all CharTypes that are flagged to be not included.
       * Generate a random length 'L' between a provided minimum and maximum bound.
       * Starting with the empty string, concatenate a random character from a random
         CharType 'L' times.
       * Shuffle this string of characters. The password is done.

       @param types An array of all CharTypes in the generator.
       @param min The minimum length of the password. The password will never have less characters than this.
       @param max The maximum length of the password. The password will never have more characters than this.
       
       @return The generated password String.
    */
    public String generate(CharType[] types, int min, int max) {

	types = filterCharTypes(types);

	if(types.length <= 0) {
	    if(this instanceof CommandLine) {
		System.out.println("You put \"no\" for every character type.");
		System.out.println("Therefore, no passwords can be generated.");
		System.exit(0);
	    } else {
		javax.swing.JOptionPane.showMessageDialog(null, "You put \"no\" for every character type.\nTherefore, no password can be generated.");
		return "";
	    }
	}
	
	int totalLength = min + (int)(Math.random()* ((max-min)+1));
	String password = "";

	for(int i = 0; i < totalLength; i++) {
	    password += getRandomChar(types);
	}

	return shuffle(password);
    }

    /**
       Loops through an array of CharTypes and removes all of which that are flagged to not be included in the password.

       @param types The list of CharTypes to be filtered.

       @return The filtered list of CharTypes, all of which to be included in the password.

     */
    public CharType[] filterCharTypes(CharType[] types) {

	ArrayList<CharType> list = new ArrayList<CharType>();

	for(int i = 0; i < types.length; i++) {
	    if(types[i].getToBeIncluded()) {
		list.add(types[i]);
	    }
	}

	return list.toArray(new CharType[list.size()]);
	
    }

    /**
       Pick a random character of a random CharType.

       First, a random CharType is chosen from an array. Then, a random character is chosen from a String of all characters that pertain to the CharType.
       
       @param types The array of CharTypes we want to pick from.
       @return The character that was randomly selected.
     */
    public char getRandomChar(CharType[] types) {
	
	Random random = new Random();
	int randomTypeIndex = random.nextInt(types.length);
	CharType randomType = types[randomTypeIndex];
	String chars = randomType.getCharacters();
	int whichChar = random.nextInt(chars.length());

	return chars.charAt(whichChar);

    }

    /**
       Shuffles a string of characters.

       @param input The unshuffled String.
       @return The shuffled String.
     */
    public String shuffle(String input) {

	ArrayList<Character> characters = new ArrayList<Character>();
	char[] chArr = input.toCharArray();
	for(int i = 0; i < chArr.length; i++) {
	    characters.add(chArr[i]);
	}

	//we're using a list so we can keep track of how many characters we've inserted.
	//when we insert a character from the list into the new string, take it out of the list
	//to prevent inserting it again
	String output = "";
	while(characters.size()>0) {
	    int whichChar = (int)(Math.random()*characters.size());
	    output += characters.get(whichChar);
	    characters.remove(whichChar);
	}

	return output;

    }

    /**
       Verifies that the special characters entered by the user are true, by comparing them to the default set.

       @param input The special characters entered by the user.
       @param defaultSet The default set of special characters.
       @return True if the input is good, false if it's bad.
     */
    private boolean verifySpecialCharacters(String input, String defaultSet) {
	if(input.equals("")) {
	    return true;
	}
	for(int i = 0; i < input.length(); i++) {
	    if(defaultSet.indexOf(input.charAt(i)) < 0)
		return false;
	}
	return true;
    }
    
}
