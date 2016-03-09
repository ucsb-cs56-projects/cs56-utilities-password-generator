import java.util.Scanner;
import java.io.Console;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CommandLine extends Password {

    Scanner scanner;
    CharType[] types;

    /**
       Default Constructor.

       Creates an array of all CharTypes, and initializes a Scanner that will read input throughout the program.
     */
    public CommandLine() {
	scanner = new Scanner(System.in);
	String[][] charTypes = {{"uppercase letters", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"},
				{"lowercase letters","abcdefghijklmnopqrstuvwxyz"},
				{"digits","0123456789"},
				{"special characters","`~@#%^&*()-_=+[]{}\\|;:',.<>/?"}};
	types = new CharType[charTypes.length];
	for(int i = 0; i < types.length; i++) {
	    types[i] = new CharType(charTypes[i]);
	}
    }

    /**
       Starts the process of the Command Line program.
       
       Think of it as a main() function for the Command Line program. Based on what the user wants to do (GUI or Command Line), we either run this function or the GUI's go() function.

     */
    public void go() {

	printTitle();
	
	for(int i = 0; i < types.length; i++) {
	    String currentType = types[i].getType();
	    if(askFor(currentType)) {
		types[i].setToBeIncluded(true);
		if(currentType.equals("special characters")) {
		    //replace special characters if the user wants to.
		    types[i].setCharacters(replaceSpecialCharacters(types[i].getCharacters()));
		}
	    } else {
		types[i].setToBeIncluded(false);
	    }
	}

	int min;
	int max;
	do {
	    min = getBound(false);
	    max = getBound(true);
	    if(min > max) {
		System.err.println("ERROR: The minimum bound can't be greater than the maximum bound!");
	    }
	} while(min > max);

	int numPasswords = askForFile();
	if(numPasswords > 0){
	    try {
		String fileName = writePasswordsToFile(numPasswords, min, max);
		System.out.println("Passwords saved to " + fileName);
	    } catch(IOException e) {
		System.err.println("ERROR: Writing to a file failed.");
		e.printStackTrace();
	    }

	} else {
	    String pw = generate(types, min, max);
	    System.out.println("Here is your password: " + pw);
	}
    }

    /**
       Prints a title when the program is initialized.
     */
    private void printTitle() {

	System.out.println("--------PASSWORD GENERATOR--------");
	System.out.println("-------COMMAND LINE VERSION-------");
	
    }

    /**
       Asks the user if they want to include a certain type in their password.

       @param type The name of the CharType that you are asking to include.
       @return True if they answer yes, False if not.
     */
    private boolean askFor(String type) {
	String str;
	Console console = System.console();
	do {
	    System.out.println("Would you like to include " + type + " in your password? (y or n) ");
	    str = scanner.nextLine();
	    if(str.equals("y") || str.equals("yes")) {
		return true;
	    } else if(str.equals("n") || str.equals("no")) {
		return false;
	    } else {
		System.out.println("You must say 'y' or 'n'");
	    }
	} while(!str.equals("y") && !str.equals("n"));

	return false;

    }

    /**
       Gets a boundary value, either minimum or maximum.
       @param isMax True if you are getting the maximum value from the user, false if you are getting the minimum value from the user.
       @return The integer boundary entered by the user.
     */
    private int getBound(boolean isMax) {
	String boundStr;
	String boundIn = "";
	int bound;
	if(isMax) {
	    boundStr = "maximum";
	} else {
	    boundStr = "minimum";
	}

	System.out.println("Please enter the " + boundStr + " number of characters: ");
	
	while(true) { //breaks when input is correct
	    try {
		boundIn = scanner.nextLine();
		bound = Integer.parseInt(boundIn);
		if(bound <= 0) {
		    System.out.println("Your bound must be a positive number");
		    continue;
		}
		break;
	    } catch(NumberFormatException nfe) {
		System.err.println("ERROR: You must enter an INTEGER! \"" + boundIn + "\" is not an integer.");
	    }
	}

	return bound;
	
    }

    /**
       We want the option to only include certain special characters in the Password.
       Gets any special characters (that are a subset of the default set of special characters).

       @param defaultSet The default set of special characters.
       @return The new set of special characters.
     */
    private String replaceSpecialCharacters(String defaultSet) {

	String input;
	
	System.out.println("If you'd like to use specific special characters in your password, type them below.");
	System.out.println("Type nothing if you want to use the default set.");
	System.out.println("If you'd like to see a list of special characters included by default, type \"list\"");
        do {
	    System.out.println("Characters to add: ");
	    input = scanner.nextLine();
	    if(input.equals("list")) {
		System.out.println("Default special characters: " + defaultSet);
		continue;
	    }
	    if(!verifySpecialCharacters(input, defaultSet)) {
		System.err.println("Characters must be a subset of the default set.");
		continue;
	    }
	    break;
	} while(true);

	if(input.equals("")) {
	    return defaultSet;
	} else {
	    return input;
	}
	
    }


    /**
       Asks if the user wants to enter a file by accepting the number of passwords they want to generate.
       
       @return A positive integer representing the number of passwords to be written to a file.
     */
    private int askForFile() {
	String str = "";
	int numPasswords;
	System.out.println("If you want to write multiple passwords to a .txt file, type the number of passwords you'd like to generate: ");
	while(true) { //breaks when input is correct
	    try {
		str = scanner.nextLine();
		if(str.equals("")) {
		    numPasswords = 0;
		    break;
		}
		numPasswords = Integer.parseInt(str);
		if(numPasswords < 0) {
		    System.err.println("ERROR: You can't generate a negative number of passwords.");
		    System.out.println("Please enter a positive number: ");
		    continue;
		}
		break;
	    } catch(NumberFormatException nfe) {
		System.err.println("ERROR: You must enter an INTEGER! \"" + str + "\" is not an integer.");
	    }
	}
	return numPasswords;
    }

    /**
       Generates a set number of passwords to a file. The file is created and saved in this function.

       @param numPasswords The number of passwords to be generated to a file. (This number is always a positive integer greater than 0)
       @param min The minimum bound of the password length.
       @param max The maximum bound of the password length.
     */
    private String writePasswordsToFile(int numPasswords, int min, int max) throws IOException {

	//make a file with the name "Passwords_MM-DD-YYYY_HH-MM-SS.txt"
	String[] passwords = new String[numPasswords];
	for(int i = 0; i < numPasswords; i++) {
	    passwords[i] = generate(types,min,max);
	}

	DateFormat df = new SimpleDateFormat("MM-dd-yyyy'_'HH-mm-ss");
	Date date = new Date();
	String dateString = df.format(date);
	String fileName = "Passwords_"+dateString+".txt";
	File file = new File(fileName);
	if(!file.exists()) {
	    file.createNewFile();
	}
	FileWriter fw = new FileWriter(file.getAbsoluteFile());
	BufferedWriter bw = new BufferedWriter(fw);
	for(int i = 0; i < numPasswords; i++) {
	    bw.write((i+1) + ") " + passwords[i] + "\n");
	}
	bw.close();
	return fileName;
    }
	
}
