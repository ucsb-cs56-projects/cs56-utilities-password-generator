import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
   PasswordGUI represents a GUI for interacting with the Password class. A Password contains digits, upper case letters, and special characters. One can set the length of the Password to be greater than or equal to three. A Password is generated with the Generate button. 
*/

public class PasswordGUI
{
    private JButton button;
    private JTextField greeting;
    private JTextField lengthField;
    private Password p;
    private JFrame frame;
    private JTextField passwordOutputField;

    /**
       launches the JFrame, populates it with the password length field, generate button, and generated password text field
    */

    public void go(){
        frame = new JFrame();
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	p = new Password();

	greeting = new JTextField("Please enter the length of the password (>= 3 characters):", 30);
        lengthField = new JTextField("");
        button = new JButton("Generate");
	passwordOutputField = new JTextField("");
        button.addActionListener(new ButtonListener());
        lengthField.addActionListener(new TextFieldListener());

	frame.getContentPane().add(BorderLayout.NORTH, greeting);
	greeting.setEditable(false);
	frame.getContentPane().add(BorderLayout.NORTH, lengthField);
        frame.getContentPane().add(BorderLayout.CENTER, button); 
	button.setAlignmentX(Component.CENTER_ALIGNMENT);
	frame.getContentPane().add(BorderLayout.SOUTH, passwordOutputField);
        frame.setSize(400,300);
        frame.setVisible(true);
    }
    
    /** 
	inner class for Generate button
    */

    public class ButtonListener implements ActionListener
    {
	public void actionPerformed(ActionEvent event)
	{
	    onActionPerformed();
	}
    }
    /**
       inner class for password length field
    */

    public class TextFieldListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
	    onActionPerformed();
	}
    }
    
    /**
       method that checks length field for valid input. if length field has valid input, generates password with that length, and sets text of generated password field with that password
    */

    public void onActionPerformed() 
    {
	int length = 0;
	try
	    {
		length = Integer.parseInt(lengthField.getText());
	    }
	catch(NumberFormatException e)
	    {
		lengthField.setText("");
		return;
	    }
	passwordOutputField.setText(p.generate(length));
    }

    public static void main(String[] args)
    {
	PasswordGUI pwGUI = new PasswordGUI();
	pwGUI.go();
    }

}
