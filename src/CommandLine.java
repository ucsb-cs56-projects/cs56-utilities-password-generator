public class CommandLine extends Password {

    public void go() {

	printTitle();

	String[] charTypes = {"uppercase", "lowercase", "digits", "special"};
	CharType[] types = new CharType[charTypes.length];
	
	for(int i = 0; i < types.length; i++) {
	    CharType[i] = new CharType(charTypes[i]);
	    String currentType = types[i].getType();
	    if(askFor(currentType)) {
		types[i].setMin(getBound(currentType, false));
		types[i].setMax(getBound(currentType, true));
	    }
	}

    }

    private void printTitle() {

	System.out.println("--------PASSWORD GENERATOR--------");
	System.out.println("-------COMMAND LINE VERSION-------");
	
    }

    private boolean askFor(String type) {
	String str;
	Scanner s = new Scanner(System.in);
	do {
	    System.out.print("Would you like to include " + type + " characters in the password? (y or n) ");
	    str = s.nextLine();
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
	Scanner s = new Scanner(System.in);
	if(isMax) {
	    bound = "maximum";
	} else {
	    bound = "minimum";
	}

	System.out.print("Please enter the " + bound + " number of " + type + " characters: ");
	
	while(true) { //breaks when input is correct
	    try {
		boundStr = s.nextLine();
		bound = Integer.parseInt(boundStr);
		break;
	    } catch(NumberFormatException nfe) {
		System.err.println("ERROR: You must enter an INTEGER! \"" + boundStr + "\" is not an integer.");
	    }
	}

	return bound;
	
    }
	
}
