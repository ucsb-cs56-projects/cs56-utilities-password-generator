import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordGUI
{
    JButton button;
    JTextField lengthField;

    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                    Integer.parseInt(lengthField.getText());
                    if(Integer.parseInt(lengthField.getText()) <= 0)
                        {
                            passLength = 1;
                        }
                    else
                        {
                            passLength = Integer.parseInt(lengthField.getText());
                        }
                }
            catch(NumberFormatException e)
                {
                    lengthField.setText("");
                }

        }
    }

}
