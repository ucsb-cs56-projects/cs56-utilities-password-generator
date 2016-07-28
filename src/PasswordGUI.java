import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.text.DefaultEditorKit;

/**
   PasswordGUI represents a GUI for interacting with the Password class. A Password contains digits, upper case letters, and special characters.
*/

public class PasswordGUI extends PasswordGenerator{
	private PasswordGenerator password;
	private JFrame frame;
    private JPanel northPanel;
    private JTextField passOutput;
    private JButton generateButton;
    private JButton copyButton;
    private JPanel southPanel;
    private JCheckBox uppercaseBox;
    private JCheckBox lowercaseBox;
    private JCheckBox digitsBox;
    private JCheckBox symbolsBox;
    
    //rename the 2 bellow, for the next ppl taking on the code, read it
    private JLabel includedCharsLabel;
    private JTextField includedChars;
    
    
    private JLabel passLengthLabel;
    private JLabel minLabel;
    private JSpinner minSpinner;
    private JLabel maxLabel;
    private JSpinner maxSpinner;
    //private ArrayList<CharType> types;
    
    private JSpinner ucSpinner, lcSpinner, dSpinner, sSpinner;
    
    //added textFeild for multiple passwords
    private JLabel genMultiplePwsLabel;
    private JTextField genMultiplePws;
    //added checkbox for file check
    private JCheckBox fileChkBox;
    //
    private String filePath="";
    
    /**
       Default No-Arg Constructor. Creates an array of all CharTypes.
    */
    public PasswordGUI(){
    	
/*    	String typeUpper = "uppercase letters", typeLower = "lowercase letters", typeDigits = "digits", typeSpecial = "special characters", typeSelected = "selected specials";

		String[][] charTypes = {{typeUpper, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"},
					{typeLower,"abcdefghijklmnopqrstuvwxyz"},
					{typeDigits,"0123456789"},
					{typeSpecial,"~`!@#$%^&*()-_+={[}]|\\?/\"':;<,>."},
					{typeSelected, ""}};
		types = new ArrayList<CharType>();
		for(int i = 0; i < charTypes.length; i++) {
		    types.add(new CharType(charTypes[i]));
		}*/
    	cleanAll();
    }
 
    /**
	Launches the JFrame, populates it with the generated password text field, generate and copy buttons, and widgets for choosing password specifications.
    */
   
    public void go(){
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Password Generator");

   
    northPanel = new JPanel();
    southPanel = new JPanel();
    
    passOutput = new JTextField("");
    passOutput.setHorizontalAlignment(JTextField.CENTER);
    Font bigFont = passOutput.getFont().deriveFont(Font.PLAIN, 40f);
    passOutput.setFont(bigFont);
    passOutput.setCaretColor(Color.WHITE);
    InputMap im = (InputMap) UIManager.get("TextField.focusInputMap");
    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.META_DOWN_MASK), DefaultEditorKit.copyAction);
	
    generateButton = new JButton("Generate Password");
    generateButton.addActionListener(new ButtonListener1());
    copyButton = new JButton("Copy to Clipboard");
    copyButton.addActionListener(new ButtonListener2());

    uppercaseBox = new JCheckBox("Uppercase (A...Z)");
    uppercaseBox.addItemListener(new BoxListener1());
    
    lowercaseBox = new JCheckBox("Lowercase (a...z)");
    lowercaseBox.addItemListener(new BoxListener2());
    
    digitsBox = new JCheckBox("Digits (0...9)");
    digitsBox.addItemListener(new BoxListener3());
    
    symbolsBox = new JCheckBox("Symbols (e.g.%!#~+)");
    symbolsBox.addItemListener(new BoxListener4());
    
    //rename these for next legacy project, read the code and find out
    includedCharsLabel = new JLabel("Atleast how many of each (UpperCase, LowerCase, Digits, Special) "
    		+ "format (ulds) where u,l,d,s are ints");
    includedChars = new JTextField("");

    passLengthLabel = new JLabel("Password Length");
    minLabel = new JLabel("Min");
    minSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
    maxLabel = new JLabel("Max");
    maxSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 25, 1));
    //add action listener for file check box
    fileChkBox =new JCheckBox("Select a file");
    fileChkBox.addItemListener(new BoxListener5());
    //text field for multiple passwords
    genMultiplePwsLabel = new JLabel("Number of passwords:");
    genMultiplePws = new JTextField("");
    
   
    GroupLayout layout1 = new GroupLayout(northPanel);
    layout1.setAutoCreateGaps(true);
    layout1.setAutoCreateContainerGaps(true);
    layout1.setHorizontalGroup(layout1.createSequentialGroup()
                   .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                         .addComponent(passOutput)
                         .addGroup(layout1.createSequentialGroup()
                               .addComponent(generateButton)
                               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                               .addComponent(copyButton))));
   
    layout1.setVerticalGroup(layout1.createSequentialGroup()
                 .addComponent(passOutput)
                 .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                       .addComponent(generateButton)
                       .addComponent(copyButton)));

    GroupLayout layout2 = new GroupLayout(southPanel);
           layout2.setAutoCreateGaps(true);
    layout2.setAutoCreateContainerGaps(true);

    layout2.setHorizontalGroup(layout2.createSequentialGroup()
                   .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                         .addGroup(layout2.createSequentialGroup()
                               .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                 .addComponent(uppercaseBox)
                                 .addComponent(lowercaseBox)
                                 .addComponent(includedCharsLabel)
                                 //
                                 .addComponent(genMultiplePwsLabel)
                                 .addComponent(passLengthLabel))))
                   .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                         .addGroup(layout2.createSequentialGroup()
                               .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                 .addComponent(digitsBox)
                                 .addComponent(symbolsBox)
                                 .addComponent(includedChars)
                                 //
                                 .addComponent(fileChkBox)
                                 .addComponent(genMultiplePws)
                                 .addGroup(layout2.createSequentialGroup()
                                       .addComponent(minLabel)
                                       .addComponent(minSpinner)
                                       .addComponent(maxLabel)
                                       .addComponent(maxSpinner))))));
                  
            
    layout2.setVerticalGroup(layout2.createSequentialGroup()
                   .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                         .addComponent(uppercaseBox)
                         .addComponent(digitsBox))
                 .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                       .addComponent(lowercaseBox)
                       .addComponent(symbolsBox))
                 .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                       .addComponent(includedCharsLabel)
                       .addComponent(includedChars))
                 //
                 .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                		 .addComponent(fileChkBox))
                 .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                		 
                		 .addComponent(genMultiplePwsLabel)
                		 .addComponent(genMultiplePws))
                 .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                         .addComponent(passLengthLabel)
                         .addComponent(minLabel)
                         .addComponent(minSpinner)
                         .addComponent(maxLabel)
                       .addComponent(maxSpinner)));
                          
    northPanel.setLayout(layout1);
    southPanel.setLayout(layout2);
    frame.getContentPane().add(BorderLayout.NORTH, northPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, southPanel);

    //frame.setSize(420,242);
    frame.setSize(1200,900);
    frame.setLocationRelativeTo(null);
    
    frame.setVisible(true);
    }

    
    /**
       Inner class for Generate Password button
    */
    
    public class ButtonListener1 implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    onActionPerformed1();
	}
    }
    
    /**
       Inner class for Copy to Clipboard button
    */

   
    public class ButtonListener2 implements ActionListener {
	public void actionPerformed(ActionEvent event) {
	    onActionPerformed2(passOutput.getText());
	}
    }

    /**
       Inner class for Uppercase CheckBox
    */

   
    public class BoxListener1 implements ItemListener {
	public void itemStateChanged(ItemEvent event) {
	    JCheckBox cb = (JCheckBox) event.getSource();


	    	checkSelected(cb, TYPE_UPPER);
      	}
		
    }
    

    public void checkSelected(JCheckBox cb, CharType passed, String type){
    	if (cb.isSelected() && passed.getType().equals(type))
    		passed.setToBeIncluded(true);
    	else if (!cb.isSelected() && passed.getType().equals(type))
    		passed.setToBeIncluded(false);
    }
    public void checkSelected(JCheckBox cb, String passedType){
    	/*for (CharType type : types){
    		if (cb.isSelected() && type.getType().equals(passedType))
        		type.setToBeIncluded(true);
        	else if (!cb.isSelected() && type.getType().equals(passedType))
        		type.setToBeIncluded(false);
    	}*/
    	for (String key : hmap.keySet()){
    		if (cb.isSelected() && key.equals(passedType)){
    			hmap.get(key).setToBeIncluded(true);
    			
    		}
    		else if (!cb.isSelected() && key.equals(passedType)){
    			hmap.get(key).setToBeIncluded(false);
    		}
    	}
    }
    /**
       Inner class for Lowercase CheckBox
    */

    public class BoxListener2 implements ItemListener {
	public void itemStateChanged(ItemEvent event) {
	    JCheckBox cb = (JCheckBox) event.getSource();

	    	checkSelected(cb, TYPE_LOWER);
      	}
    }
  
    /**
       Inner class for Digits CheckBox
    */

   public class BoxListener3 implements ItemListener {
	public void itemStateChanged(ItemEvent event) {
	    JCheckBox cb = (JCheckBox) event.getSource();


	    	checkSelected(cb, TYPE_DIGITS);
	    
      	}
    }
 
    /**
       Inner class for Symbols CheckBox
    */
   
    public class BoxListener4 implements ItemListener {
	public void itemStateChanged(ItemEvent event) {
	    JCheckBox cb = (JCheckBox) event.getSource();

	    	checkSelected(cb, TYPE_SPECIAL);
      	}
    }
    /*
    public void copyFromDefault(){
    	String defaultSpecial = getDefaultSpecial();
    	for (CharType type : types){
    		if (type.getType().equals(typeSelected)){
    			type.setCharacters(defaultSpecial);
    		}
    	}
    }
    public String getDefaultSpecial(){
    	String defaultSpecial = null;
    	for (CharType type : types){
    		if (type.getType().equals(typeSpecial)){
    			defaultSpecial = type.getCharacters();
    		}
    	}
    	return defaultSpecial;
    }*/
    
    /**
     * Inner class for write to a file CheckBox
     */
    
    public class BoxListener5 implements ItemListener {
    	public void itemStateChanged(ItemEvent event){
 
    		String choosertitle="";
    		JFileChooser chooser;
    	    //int result;
    	    
    	    JCheckBox cb = (JCheckBox) event.getSource();
    	    filePath="";
    	    
    	    if (cb.isSelected()){
    	    	
        	    chooser = new JFileChooser(); 
        	    chooser.setCurrentDirectory(new java.io.File("."));
        	    chooser.setDialogTitle(choosertitle);
        	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	    //
        	    // disable the "All files" option.
        	    //
        	    chooser.setAcceptAllFileFilterUsed(false);
        	    //    
        	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
        	      //System.out.println("getCurrentDirectory(): "  +  chooser.getCurrentDirectory());
        	      //System.out.println("getSelectedFile() : " +  chooser.getSelectedFile());
        	      //filePath= filePath + chooser.getCurrentDirectory();
        	      filePath= filePath+ chooser.getSelectedFile();
        	      System.out.println("getSelectedDir() : " + filePath);

        	      }
        	    else {
        	      System.out.println("No Selection ");
        	      }
    	    }

    	}
    	
    }

    /**
       Method that replaces special characters default set with only specific special characters the user wants to include in their generated password.

       @param defaultSet The default set of special characters.
       @return The new set of special characters
    */
    /*
    private void replaceSpecialCharacters(){
		String input = includedChars.getText();

		String defaultSpecial = getDefaultSpecial();
		for (CharType type : types){
			if (type.getType().equals(typeSelected) && !input.equals("") && verifySpecialCharacters(input,defaultSpecial)){

				type.setCharacters(input);
			}
			else if (type.getType().equals(typeSelected)&& !verifySpecialCharacters(input,defaultSpecial)){

				JOptionPane.showMessageDialog(null, "One or more of the spcial characters are not valid");
			}
		}
    }*/
    private void setUpTypeLength() throws IOException{
    	String input = includedChars.getText();
    	//make this more "generic" i.e. not by index and not by 4 diff cases
    	if (input.equals(""))return;
    	if (input.length() == 4){
    	hmap.get(TYPE_UPPER).setLength(Integer.parseInt(input.substring(0, 1)));
    	hmap.get(TYPE_LOWER).setLength(Integer.parseInt(input.substring(1, 2)));
    	hmap.get(TYPE_DIGITS).setLength(Integer.parseInt(input.substring(2, 3)));
    	hmap.get(TYPE_SPECIAL).setLength(Integer.parseInt(input.substring(3, 4)));
    	}
    	else{
    		throw new IOException();
    	}
    }
    
    
    
    /*public void includeSpecial(CharType passed, String input, boolean choice){
    	passed.setToBeIncluded(choice);
    	passed.setCharacters(input);
   		for (CharType type : types){
   			if (type.getType().equals(typeSpecial) && choice){
   				type.setToBeIncluded(false);
    		}
    	}
    }*/

    public void setPasswordLength(){
    	int min = (int) minSpinner.getValue();
		int max = (int) maxSpinner.getValue();
		setLength(min,max);
    }

    /**
       Method that checks min and max values for valid input. If valid, generates password within range of min and max
    */

 
	public void onActionPerformed1() {
		String include = includedChars.getText();
		int min = (int) minSpinner.getValue();
		int max = (int) maxSpinner.getValue();
		setPasswordLength();
		try {
			if (min > max) {
				JOptionPane.showMessageDialog(null, "Min cannot be greater than Max!");
			} /*else if (!include.equals("")) {
				replaceSpecialCharacters();
				// passOutput.setText(generate(types,min,max));
				// }
				// else
				// passOutput.setText(generate(types,min,max));
			}*/
			// find out if the user wants to create a file with passwords and
			// then go an read the number of passwords
			if (filePath.equals("")) {
				try {
					setUpTypeLength();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,  "input specifying length of types must be exactly 4 integers. or leave it blank");
					return;
				}
				System.out.println("single password");
				passOutput.setText(generate());
				//PasswordGenerator s = new PasswordGenerator(min, max, )
				//setUpTypeLength();
				//password.selectAll();
				//passOutput.setText(generate());
			} /*else {
				// call numOfPws
				int numPasswords = getNumOfPws();
				// call writePwsToFile
				try {
					String fileName = writePasswordsToFile(numPasswords, min, max);
					// just set the text box with first password
					passOutput.setText(fileName);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, " Error: Cannot create the file");
				}

			}
	*/
		} catch (IllegalArgumentException e) {
			passOutput.getText();// Stub?
		}

	}
    
    /**
    User selected a write to file generates pws .
    
    @return A positive integer representing the number of passwords to be written to a file.
     */
	private int getNumOfPws() {
		
		int numPasswords=0;
		
		while(true){	
		try{
				String input = genMultiplePws.getText();
				if (input.equals("")){
					numPasswords=1;
					break;
				}	
				numPasswords=Integer.parseInt(input);
				
				if (numPasswords<0){
					JOptionPane.showMessageDialog(null, " Error: negative number");
					continue;
				}
				break;
				}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, " Error: Not an integer");
					break;
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
	/*
	 private String writePasswordsToFile(int numPasswords, int min, int max) throws IOException {

		//make a file with the name "Passwords_MM-DD-YYYY_HH-MM-SS.txt"
		String[] passwords = new String[numPasswords];
		for(int i = 0; i < numPasswords; i++) {
		    passwords[i] = generate();
		}
		
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy'_'HH-mm-ss");
		Date date = new Date();
		String dateString = df.format(date);
		
		String fileName = filePath + "\\Passwords_"+dateString+".txt";
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
	*/
    
    /**
       Method that copies content of passOutput's text field and adds to clipboard
    */

    
   public void onActionPerformed2(String aString){
	StringSelection stringSelection = new StringSelection(aString);
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	clipboard.setContents(stringSelection,stringSelection);
    }

    
    public static void main(String[] args){
    PasswordGUI passwordGUI = new PasswordGUI();
    passwordGUI.go();
   System.out.println( passwordGUI.generate());
    }
}
