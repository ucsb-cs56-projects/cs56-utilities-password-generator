import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
   PasswordGUI represents a GUI for interacting with the Password class. A Password contains digits, upper case letters, and special characters. One can set the length of the Password to be greater than or equal to three. A Password is generated with the Generate button. 
*/

public class PasswordGUI
{
    private JButton button1;
    private JButton button2;
    private JTextField greeting1;
    private JTextField greeting2;
    private JTextField lengthField1;
    private JTextField lengthField2;
    private JTextField lengthField3;
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

	greeting1 = new JTextField("Please enter the max,min length of the password (First Box:min Second Box:max):", 30);
	greeting2 = new JTextField("Please enter a list of special character to be included:(On the third line)", 10);
        lengthField1 = new JTextField("");
	lengthField2 = new JTextField("");
	lengthField3 = new JTextField("");
        button1 = new JButton("Generate");
	button2 = new JButton("Copy to Clipboard");
	passwordOutputField = new JTextField("");
        button1.addActionListener(new ButtonListener());
	button2.addActionListener(new ButtonListener());
        lengthField1.addActionListener(new TextFieldListener());
	lengthField2.addActionListener(new TextFieldListener());
	lengthField3.addActionListener(new TextFieldListener());

	frame.getContentPane().add(BorderLayout.NORTH, greeting1);
	greeting1.setEditable(false);
	frame.getContentPane().add(BorderLayout.NORTH, greeting2);
	greeting2.setEditable(false);
	frame.getContentPane().add(BorderLayout.NORTH, lengthField1);
	frame.getContentPane().add(BorderLayout.NORTH, lengthField2);
	frame.getContentPane().add(BorderLayout.NORTH, lengthField3);
        frame.getContentPane().add(BorderLayout.CENTER, button1);
	frame.getContentPane().add(BorderLayout.CENTER, button2); 
	button1.setAlignmentX(Component.CENTER_ALIGNMENT);
	button2.setAlignmentX(Component.CENTER_ALIGNMENT);
	frame.getContentPane().add(BorderLayout.SOUTH, passwordOutputField);
        frame.setSize(550,300);
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
	int min = 0;
        int max = 100;
        try
	    {
		min = Integer.parseInt(lengthField1.getText());
		max = Integer.parseInt(lengthField2.getText());
	    }
	catch(NumberFormatException e)
	    {
		lengthField1.setText("");
		lengthField2.setText("");
		return;
	    }
	passwordOutputField.setText(p.generate(min,max));
    }

    public static void main(String[] args)
    {
	PasswordGUI pwGUI = new PasswordGUI();
	pwGUI.go();
    }

}
