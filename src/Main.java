public class Main{
    
    public static void main(String[] args) {
	if(args.length >= 1 && args[0].equals("-cl")) {
	    CommandLine cl = new CommandLine();
	} else {
	    PasswordGUI p = new PasswordGUI();
	    p.go();
	}
    }

}
