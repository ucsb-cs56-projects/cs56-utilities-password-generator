public class PasswordMain{
    
    public static void main(String[] args) {
	if(args.length >= 1 && args[0].equals("-cl")) {
	    CommandLine cl = new CommandLine();
	    cl.go();
	} else {
	    PasswordGUI p = new PasswordGUI();
	    p.go();
	}
    }

}
