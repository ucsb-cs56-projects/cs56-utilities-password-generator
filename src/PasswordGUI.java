import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordGUI
{
    JButton button;
    JTextField lengthField;
    Password p;

    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	p = new Password();

        lengthField = new JTextField(3);
        button = new JButton("Generate");
        button.addActionListener(new ButtonListener());
        lengthField.addActionListener(new TextFieldListener());

        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.NORTH, lengthField);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    public class ButtonListener implements ActionListener
    {
	public void actionPerformed(ActionEvent event)
	{
	    
	}
    }
    public class TextFieldListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
                {
                    int length = Integer.parseInt(lengthField.getText());
		    p.setPassLength(length);
                }
            catch(NumberFormatException e)
                {
                    lengthField.setText("");
                }

        }
    }

}
