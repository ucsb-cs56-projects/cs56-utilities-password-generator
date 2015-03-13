import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 *  PasswordGUI represents a GUI for interacting with the Password class. 
 *	A Password contains digits, upper case letters, and special characters.
 *	One can set the length of the Password to be greater than or equal to 
 *	three. A Password is generated with the Generate button. 
 */

public class PasswordGUI {
	private JFrame frame;

	// the headers for each type ("ex: type of character")
	private JTextField typeText;
	private JTextField numText;

	// check boxes for each char type:
	private JCheckBox checkBox_upper;
	private JCheckBox checkBox_lower;
	private JCheckBox checkBox_digits;
	private JCheckBox checkBox_special;
	private JCheckBox checkBox_userSpecial;


	// text prompts for each char type (ex "uppercase:")
	private	JTextField text_uppercase;
	private	JTextField text_lowercase;
	private	JTextField text_digits;
	private	JTextField text_specialChars;



	// Min and Max fields for each char type
	private JTextField field_upperMin;
	private JTextField field_upperMax;

	private JTextField field_lowerMin;
	private JTextField field_lowerMax;

	private JTextField field_digitsMin;
	private JTextField field_digitsMax;

	private JTextField field_specialMin;
	private JTextField field_specialMax;

	private	JTextField text_userSpecialChars;
	private JTextField field_userSpecialChars;
	private JTextField text_leaveBlank;


	// buttons at the bottom of the window
	private JButton generateButton;
	private JButton copyButton;

	// outputfield that displays the generated password
	private JTextField outputText;

	// the password object that does the generation
	private Password p;


	/*
	 	launches the JFrame, populates it with the password length field, 
		generate button, and generated password text field
	 */
	public void go() {
		frame = new JFrame();
		//frame.getContentPane().setLayout( 
		//				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new Password();


		// — — — ASSEMBLE ALL THE ELEMENTS — — — 




		// — — HEADER PANEL — — 
		// initiliaze the text boxes
		typeText = new JTextField("Type of Characters:", 15);
		typeText.setHorizontalAlignment(JTextField.CENTER);
		typeText.setEditable(false);

		numText = new JTextField("Number of Characters:", 15);
		numText.setHorizontalAlignment(JTextField.CENTER);
		numText.setEditable(false);

		// group them into a panel for the header
		JPanel typeAndNumPanel = new JPanel();
		typeAndNumPanel.add(typeText);
		typeAndNumPanel.add(numText);



		// — — Min/Max PANEL — — 
		// initiliaze the text boxes
		JTextField spacer = new JTextField("", 15);
		spacer.setBackground(null);
		spacer.setBorder(null);


		JTextField minMaxText = new JTextField("          min:      max:", 15);
		minMaxText.setHorizontalAlignment(JTextField.CENTER);
		minMaxText.setBackground(null);
		minMaxText.setBorder(null);
		minMaxText.setEditable(false);

		// group them into a panel for the header
		JPanel minMaxPanel = new JPanel();
		minMaxPanel.add(spacer);
		minMaxPanel.add(minMaxText);







		/* — — — RECIPE PANEL: — — — 
			each recipe panel is of the following format:
			<checkbox> <prompt> <min textfield> <max textfield>
			[  ] uppercase: [_(min)_] [_(max)_]
		*/


		// - - UPPERCASE PANEL — — 
		checkBox_upper = new JCheckBox();
		//checkBox_upper.addActionListener(new checkBoxUpper_listener() );
		checkBox_upper.addItemListener( new checkBox_Listener());


		text_uppercase = new JTextField("uppercase:", 15);
		text_uppercase.setBackground(null);
		text_uppercase.setBorder(null);
		text_uppercase.setEditable(false);

		// these are editable!!
		field_upperMin = new JTextField("", 3);
		field_upperMax = new JTextField("", 3);

		// combine the elements into a panel for the uppercase elements
		JPanel uppercasePanel = new JPanel();
		uppercasePanel.add(checkBox_upper);
		uppercasePanel.add(text_uppercase);
		uppercasePanel.add(field_upperMin);
		uppercasePanel.add(field_upperMax);






		// - - LOWERCASE PANEL — — 
		checkBox_lower = new JCheckBox();
		//checkBox_lower.addActionListener(new checkBoxLower_listener() );
		checkBox_lower.addItemListener( new checkBox_Listener());

		text_lowercase = new JTextField("lowercase:", 15);
		text_lowercase.setBackground(null);
		text_lowercase.setBorder(null);
		text_lowercase.setEditable(false);

		// these are editable!!
		field_lowerMin = new JTextField("", 3);
		field_lowerMax = new JTextField("", 3);

		// combine the elements into a panel for the uppercase elements
		JPanel lowercasePanel = new JPanel();
		lowercasePanel.add(checkBox_lower);
		lowercasePanel.add(text_lowercase);
		lowercasePanel.add(field_lowerMin);
		lowercasePanel.add(field_lowerMax);





		// - - DIGITS PANEL — — 
		checkBox_digits = new JCheckBox();
		//checkBox_digits.addActionListener(new checkBoxDigits_listener() );
		checkBox_digits.addItemListener( new checkBox_Listener());

		text_digits = new JTextField("digits:", 15);
		text_digits.setBackground(null);
		text_digits.setBorder(null);
		text_digits.setEditable(false);

		// these are editable!!
		field_digitsMin = new JTextField("", 3);
		field_digitsMax = new JTextField("", 3);

		// combine the elements into a panel for the uppercase elements
		JPanel digitsPanel = new JPanel();
		digitsPanel.add(checkBox_digits);
		digitsPanel.add(text_digits);
		digitsPanel.add(field_digitsMin);
		digitsPanel.add(field_digitsMax);





		// - - SPECIALS PANEL — — 
		checkBox_special = new JCheckBox();
		checkBox_special.addItemListener(new checkBox_Listener());


		text_specialChars = new JTextField("special characters:", 15);
		text_specialChars.setBackground(null);
		text_specialChars.setBorder(null);
		text_specialChars.setEditable(false);

		// these are editable!!
		field_specialMin = new JTextField("", 3);
		field_specialMax = new JTextField("", 3);

		// combine the elements into a panel for the uppercase elements
		JPanel specialPanel = new JPanel();
		specialPanel.add(checkBox_special);
		specialPanel.add(text_specialChars);
		specialPanel.add(field_specialMin);
		specialPanel.add(field_specialMax);



		// — — USER SPECIAL CHARS PANEL — — 

		checkBox_userSpecial = new JCheckBox();
		checkBox_userSpecial.addItemListener(new checkBox_Listener());

		text_userSpecialChars = new JTextField("use your own special characters: ");
			/*	CODE SMELL!!!!!
				the spaces at the beginning of the string are to ensure
				the alignment on the GUI. this is a stopgap measure
				and should be replaced.
			*/
		text_userSpecialChars.setBackground(null);
		text_userSpecialChars.setBorder(null);
		text_userSpecialChars.setEditable(false);


		field_userSpecialChars = new JTextField("", 5);


		text_leaveBlank = new JTextField("(if unchecked, default special characters are used)");
		text_leaveBlank.setBackground(null);
		text_leaveBlank.setBorder(null);
		text_leaveBlank.setEditable(false);
		text_leaveBlank.setHorizontalAlignment(JTextField.CENTER);


		JPanel userSpecial_promptAndField = new JPanel();
		userSpecial_promptAndField.add(checkBox_userSpecial);
		userSpecial_promptAndField.add(text_userSpecialChars);
		userSpecial_promptAndField.add(field_userSpecialChars);

		JPanel userSpecialPanel = new JPanel();
		userSpecialPanel.setLayout(new BoxLayout(userSpecialPanel, BoxLayout.Y_AXIS));
		userSpecialPanel.add(userSpecial_promptAndField);
		userSpecialPanel.add(text_leaveBlank);




		// GROUP ALL THE RECIPE PANELS TOGETHER
		JPanel recipePanel = new JPanel();
		recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
		recipePanel.add(uppercasePanel);
		recipePanel.add(lowercasePanel);
		recipePanel.add(digitsPanel);
		recipePanel.add(specialPanel);
		recipePanel.add(userSpecialPanel);







	


		/*  — — — BUTTONS AND OUTPUT — — —  
			1.	make a panel containing buttons
			2.	add them to a "macro panel", buttons&outPanel contaning
				the output field
		*/

		// BUTTONS
		generateButton = new JButton("Generate");
		generateButton.addActionListener(new generateButton_listener());

		copyButton = new JButton("Copy to Clipboard");
		copyButton.addActionListener(new copyButton_listener());

		// OUTPUT FIELD
		outputText = new JTextField("");
		outputText.setHorizontalAlignment(JTextField.CENTER);
		outputText.setEditable(false);



		// 1 - make a panel containing the buttons, side by side
		JPanel buttons_ComponentPanel = new JPanel();
		buttons_ComponentPanel.add(generateButton);
		buttons_ComponentPanel.add(copyButton);

		// 2 - add the buttons, and output field to a macropanel
		JPanel buttonsAndOutPanel = new JPanel();
		buttonsAndOutPanel.setLayout(new BoxLayout(buttonsAndOutPanel, BoxLayout.Y_AXIS));

		buttonsAndOutPanel.add(buttons_ComponentPanel);
		buttonsAndOutPanel.add(outputText);







		// — — — ADD THE MACRO PANELS TO THE FRAME, AND DISPLAY — — — 

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(typeAndNumPanel);
		mainPanel.add(minMaxPanel);
		mainPanel.add(recipePanel);

		frame.getContentPane().add(BorderLayout.NORTH, mainPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, buttonsAndOutPanel);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

	} // go()
	




	public class generateButton_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			testParamsAndGenerate(p);
		}
	}


	public class copyButton_listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			copyToClipboard(outputText.getText());
		}
	}

	public void copyToClipboard(String aString) {
		StringSelection stringSelection = new StringSelection(aString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection,stringSelection);
	}	



	public class checkBox_Listener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {

			Object source = e.getItemSelectable();
	 
			if (source == checkBox_upper) {
				p.setIncludeUpper(true);
			} else if (source == checkBox_lower) {
				p.setIncludeLower(true);
			} else if (source == checkBox_digits) {
				p.setIncludeDigits(true); 
			} else if (source == checkBox_special) {
				p.setIncludeSpecial(true);
			} else if (source == checkBox_userSpecial) {
				p.setIncludeUserSpecial(true);
			}

	 
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				if (source == checkBox_upper) {
					p.setIncludeUpper(false);
				} else if (source == checkBox_lower) {
					p.setIncludeLower(false);
				} else if (source == checkBox_digits) {
					p.setIncludeDigits(false); 
				} else if (source == checkBox_special) {
					p.setIncludeSpecial(false);
				} else if (source == checkBox_userSpecial) {
					p.setIncludeUserSpecial(false);
				}
			}
		}
	}




	/*
		Depending on whether the boolean includeUpper, includeLower, etc
		are true for each character type, 
			-	it sets the number of each desired char type (the number 
				is determined by the ranges given in the min and max field
				of each type)
			-	generates the password using passwordIn.generatePassword()
			-	it is vital that an existing Password object is passed to this
				function, since that specific instance of Password
				will be the one modified by the GUI. (otherwise, the default
				values of range and booleans would be used) 
	 */
	public void testParamsAndGenerate(Password passwordIn) {

		outputText.setText( "" );



		if ( passwordIn.getIncludeUpper() ) {
			String upperMin = field_upperMin.getText();
			String upperMax = field_upperMax.getText();

			if (couldBeInt(upperMin) && couldBeInt(upperMax) ) {
				passwordIn.setNumUppercase( 
						Integer.parseInt(upperMin),
						Integer.parseInt(upperMax) 
							);
			}
		}



		if ( passwordIn.getIncludeLower() ) {
			String lowerMin = field_lowerMin.getText();
			String lowerMax = field_lowerMax.getText();


			if (couldBeInt(lowerMin) && couldBeInt(lowerMax) ) {

				passwordIn.setNumLowercase( 
						Integer.parseInt(lowerMin),
						Integer.parseInt(lowerMax) 
							);
			}
		}


		if ( passwordIn.getIncludeDigits() ) {
			String digitsMin = field_digitsMin.getText();
			String digitsMax = field_digitsMax.getText();


			if (couldBeInt(digitsMin) && couldBeInt(digitsMax) ) {
				passwordIn.setNumDigits( 
						Integer.parseInt(digitsMin),
						Integer.parseInt(digitsMax) 
							);
			}
		}


		if ( passwordIn.getIncludeSpecial() ) {
			String specialMin = field_specialMin.getText();
			String specialMax = field_specialMax.getText();

			if (couldBeInt(specialMin) && couldBeInt(specialMax) ) {
				passwordIn.setNumSpecial( 
						Integer.parseInt(specialMin),
						Integer.parseInt(specialMax) 
							);
			}
		}



		if ( passwordIn.getIncludeUserSpecial() ) {

			String specialMin = field_specialMin.getText();
			String specialMax = field_specialMax.getText();

			if (couldBeInt(specialMin) && couldBeInt(specialMax) ) {
				passwordIn.setNumSpecial( 
						Integer.parseInt(specialMin),
						Integer.parseInt(specialMax) 
							);
			}
		
			passwordIn.setUserSpecialChars( field_userSpecialChars.getText() );

		}


		String pw = passwordIn.generatePassword();
		outputText.setText( pw ); 
	}



	// Helper function that tests whether othe inputted string is only made of
	// digit characters
	public boolean couldBeInt( String inStr ) throws IllegalArgumentException {
		if ( !inStr.equals(null) || inStr.equals("") ) {
			char [] charArray = inStr.toCharArray();

			for (int i = 0; i < charArray.length; i++) {
				if ( Character.isDigit(charArray[i]) == false ) return false;
			}
		}
		return true;
	}




	public static void main(String[] args) {
		PasswordGUI pwGUI = new PasswordGUI();
		pwGUI.go();
	}
}



