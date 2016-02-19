public class CommandLine extends Password {

    Scanner scanner;

    public CommandLine() {
	scanner = new Scanner(System.in);
    }

    public void go() {

	printTitle();

	String[][] charTypes = {{"uppercase", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"},
				{"lowercase","abcdefghijklmnopqrstuvwxyz"},
				{"digits","0123456789"}
				{"special","`~@#%^&*()-_=+[]{}\\|;:',.<>/?"}};
	CharType[] types = new CharType[charTypes.length];
	
	for(int i = 0; i < types.length; i++) {
	    CharType[i] = new CharType(charTypes[i]);
	    String currentType = types[i].getType();
	    if(askFor(currentType)) {
		types[i].setMin(getBound(currentType, false));
		types[i].setMax(getBound(currentType, true));
		if(currentType.equals("special")) {
		    //replace special characters if the user wants to.
		    types[i].setCharacters(replaceSpecialCharacters(types[i].getCharacters()));
		}
	    }
	}

	generate(types);

    }

    private void printTitle() {

	System.out.println("--------PASSWORD GENERATOR--------");
	System.out.println("-------COMMAND LINE VERSION-------");
	
    }

    private boolean askFor(String type) {
	String str;
	do {
	    System.out.print("Would you like to include " + type + " characters in the password? (y or n) ");
	    str = scanner.nextLine();
	    if(str.equals("y") || str.equals("yes")) {
		return true;
	    } else if(str.equals("n") || str.equals("no")) {
		return false;
	    } else {
		System.out.println("You must say 'y' or 'n'");
	    }
	} while(!str.equals("y") && !str.equals("n"));
    }

    private int getBound(String type, boolean isMax) throws NumberFormatException {
	String boundStr;
	int bound;
	if(isMax) {
	    bound = "maximum";
	} else {
	    bound = "minimum";
	}

	System.out.print("Please enter the " + bound + " number of " + type + " characters: ");
	
	while(true) { //breaks when input is correct
	    try {
		boundStr = scanner.nextLine();
		bound = Integer.parseInt(boundStr);
		break;
	    } catch(NumberFormatException nfe) {
		System.err.println("ERROR: You must enter an INTEGER! \"" + boundStr + "\" is not an integer.");
	    }
	}

	return bound;
	
    }

    private String replaceSpecialCharacters(String defaultSet) {

	String input;
	
	System.out.println("If you'd like to use specific special characters in your password, type them below.");
	System.out.println("Type nothing if you want to use the default set.");
	System.out.println("If you'd like to see a list of special characters included by default, type \"list\"");
        do {
	    System.out.print("Characters to add: ");
	    input = scanner.nextLine();
	    if(!verifySpecialCharacters(input, defaultSet)) {
		System.err.println("Characters must be a subset of the default set.");
		continue;
	    }
	    if(input.equals("list")) {
		System.out.println("Default special characters: " + defaultSet);
		continue;
	    }
	} while(false);

	if(input.equals("")) {
	    return input;
	} else {
	    return defaultSet;
	}
	
    }

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
