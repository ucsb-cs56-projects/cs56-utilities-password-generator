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
    private JTextField passwordLengthText;
    private JTextField specialCharText;
    private JTextField minPasswordLengthField;
    private JTextField maxPasswordLengthField;
    private JTextField specialCharField;
    private Password p;
    private JFrame frame;
    private JTextField passwordOutputField;

    /**
     * 	launches the JFrame, populates it with the password length field, 
     *	generate button, and generated password text field
     */

    public void go() {
        frame = new JFrame();
		frame.getContentPane().setLayout( 
						new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new Password();

		passwordLengthText = new JTextField("Please enter the max, min length of the password (First Box:min Second Box:max):", 30);
		specialCharText = new JTextField("Please enter a list of special character to be included:(On the third line)", 10);
	    	minPasswordLengthField = new JTextField("");
		maxPasswordLengthField = new JTextField("");
		specialCharField = new JTextField("");
			generateButton = new JButton("Generate");
		copyButton = new JButton("Copy to Clipboard");
		passwordOutputField = new JTextField("");
	        generateButton.addActionListener(new ButtonListener1());
		copyButton.addActionListener(new ButtonListener2());
	        minPasswordLengthField.addActionListener(new TextFieldListener());
		maxPasswordLengthField.addActionListener(new TextFieldListener());
		specialCharField.addActionListener(new TextFieldListener());


		frame.getContentPane().add(BorderLayout.NORTH, passwordLengthText);
		passwordLengthText.setEditable(false);
		frame.getContentPane().add(BorderLayout.NORTH, specialCharText);
		specialCharText.setEditable(false);
		frame.getContentPane().add(BorderLayout.NORTH, minPasswordLengthField);
		frame.getContentPane().add(BorderLayout.NORTH, maxPasswordLengthField);
		frame.getContentPane().add(BorderLayout.NORTH, specialCharField);
	        frame.getContentPane().add(BorderLayout.CENTER, generateButton);
		frame.getContentPane().add(BorderLayout.CENTER, copyButton); 
		generateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		copyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(BorderLayout.SOUTH, passwordOutputField);
	        frame.setSize(530,300);
	        frame.setVisible(true);
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
			onActionPerformed2(passwordOutputField.getText());
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
	        min = Integer.parseInt(minPasswordLengthField.getText());
	        max = Integer.parseInt(maxPasswordLengthField.getText());
			b=specialCharField.getText();

			if( min > max) { 
				passwordOutputField.setText("min can not be greater than max!");                   
			}

			else {  
				passwordOutputField.setText(p.generatePassword(b,min,max));
			}		
    	}

		catch(IllegalArgumentException e) {
			minPasswordLengthField.getText();
			maxPasswordLengthField.getText();
			specialCharField.getText();
		}
	}


    public static void main(String[] args) {
		PasswordGUI pwGUI = new PasswordGUI();
		pwGUI.go();
    }
}



