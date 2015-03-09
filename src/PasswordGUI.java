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
    private JButton generateButton;
    private JButton copyButton;

    private JTextField lengthText;
    private JTextField minText;
    private JTextField maxText;


    private JTextField specialCharText;
    private JTextField minLengthField;
    private JTextField maxLengthField;
    private JTextField specialCharField;
    private Password p;
    private JFrame frame;
    private JTextField outputField;

    /**
     * 	launches the JFrame, populates it with the password length field, 
     *	generate button, and generated password text field
     */

    public void go() {
        frame = new JFrame();
		//frame.getContentPane().setLayout( 
		//				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new Password();


		// — — — INITIALIZE ALL THE ELEMENTS — — — 

		// PASSWORD LENGTH
		lengthText = new JTextField("Please enter the maximum and minimum length of the password.", 25);
		lengthText.setEditable(false);

		minText = new JTextField("min:", 3);
		minText.setEditable(false);

		maxText = new JTextField("max:", 3);
		maxText.setEditable(false);


	    minLengthField = new JTextField("", 5);
	    minLengthField.addActionListener(new TextFieldListener());

		maxLengthField = new JTextField("", 5);
		maxLengthField.addActionListener(new TextFieldListener());


		// SPECIAL CHAR
		specialCharText = new JTextField("Please enter special characters to be included:", 25);
		specialCharText.setEditable(false);


		specialCharField = new JTextField("", 5);
		specialCharField.addActionListener(new TextFieldListener());


		// BUTTONS
		generateButton = new JButton("Generate");
	    generateButton.addActionListener(new ButtonListener1());

		copyButton = new JButton("Copy to Clipboard");
		copyButton.addActionListener(new ButtonListener2());

		// OUTPUT FIELD
		outputField = new JTextField("");

	    




		
		// — — — ASSEMBLE THE PANELS FOR THE FRAME — — — 


		/*  — — — PASSWORD LENGTH — — — 
			1.	create componentPanels containing the min or max message and 
				respective field
		 	2.	group the min_componentPanel and the max_componentPanel into 
		 		their own minmax_panel
		 	3.	combine the long prompt "please enter..." and the minmax_panel
				into a "macro panel" lengthPanel 
		 */

		// 1 - max prompt and field 
		JPanel length_max_componentPanel = new JPanel();
		length_max_componentPanel.add(maxText);
		length_max_componentPanel.add(maxLengthField);

		// 1 cont - min prompt and field
		JPanel length_min_componentPanel = new JPanel();
		length_min_componentPanel.add(minText);
		length_min_componentPanel.add(minLengthField);

		// 2 - combine the min/max prompts and field into one minmax panel
		JPanel length_minmax_panel = new JPanel();
		length_minmax_panel.setLayout(new BoxLayout(length_minmax_panel, BoxLayout.Y_AXIS));
		length_minmax_panel.add(length_min_componentPanel);
		length_minmax_panel.add(length_max_componentPanel);

		// 3 - combine the long prompt and the minmax into one panel
		JPanel lengthPanel = new JPanel();
		lengthPanel.setLayout(new BoxLayout(lengthPanel, BoxLayout.Y_AXIS));
		lengthPanel.add(lengthText);
		lengthPanel.add(length_minmax_panel);






		/*  — — — SPECIAL CHARS — — —  
			1.	make a panel containing the text prompt on the left
				and the field on the right.
		*/

		JPanel specialCharPanel = new JPanel();
		specialCharPanel.setLayout(new BoxLayout(specialCharPanel, BoxLayout.Y_AXIS));

		specialCharPanel.add(specialCharText);
		specialCharPanel.add(specialCharField);





		/*  — — — BUTTONS AND OUTPUT — — —  
			1.	make a panel containing buttons
			2.	add them to a "macro panel", buttons&outPanel contaning
				the output field
		*/

		// 1 - make a panel containing the buttons, side by side
		JPanel buttons_ComponentPanel = new JPanel();
		buttons_ComponentPanel.add(generateButton);
		buttons_ComponentPanel.add(copyButton);

		// 2 - add the buttons, and output field to a macropanel
		JPanel buttonsAndOutPanel = new JPanel();
		buttonsAndOutPanel.setLayout(new BoxLayout(buttonsAndOutPanel, BoxLayout.Y_AXIS));
		
		buttonsAndOutPanel.add(buttons_ComponentPanel);
		buttonsAndOutPanel.add(outputField);

	    


		// — — — ADD THE MACRO PANELS TO THE FRAME, AND DISPLAY — — — 

		JPanel recipePanel = new JPanel();
		recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));
		recipePanel.add(lengthPanel);
		recipePanel.add(specialCharPanel);

		frame.getContentPane().add(BorderLayout.NORTH, recipePanel);
		frame.getContentPane().add(BorderLayout.SOUTH, buttonsAndOutPanel);

	    frame.setSize(450,250);
	    frame.setVisible(true);
	    frame.setResizable(false);

	} // go()
    


    /** 
	 *	inner class for Generate button
     */
    public class ButtonListener1 implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			onActionPerformed1();
		}
    }
    
    public class ButtonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			onActionPerformed2(outputField.getText());
		}
    }



    /**
     *  inner class for password length field
     */
    public class TextFieldListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
			onActionPerformed1();
		}
    }
    

    /**
     *  method that checks length field for valid input. if length field has 
     *  valid input, generates password with that length, and sets text of 
     *  generated password field with that password
     */
    public void onActionPerformed2(String aString) {
    	StringSelection stringSelection = new StringSelection(aString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection,stringSelection);
    }


    public void onActionPerformed1() {
		int min = 0;
        int max = 100;
        String b;

        try {
	        min = Integer.parseInt(minLengthField.getText());
	        max = Integer.parseInt(maxLengthField.getText());
			b=specialCharField.getText();

			if( min > max) { 
				outputField.setText("min can not be greater than max!");                   
			}

			else {  
				outputField.setText(p.generatePassword(b,min,max));
			}		
    	}

		catch(IllegalArgumentException e) {
			minLengthField.getText();
			maxLengthField.getText();
			specialCharField.getText();
		}
	}


    public static void main(String[] args) {
		PasswordGUI pwGUI = new PasswordGUI();
		pwGUI.go();
    }
}



