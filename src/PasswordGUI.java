import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.text.DefaultEditorKit;

/**
   PasswordGUI represents a GUI for interacting with the Password class. A Password contains digits, upper case letters, and special characters.
*/

public class PasswordGUI extends Password{
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
    private JLabel includedCharsLabel;
    private JTextField includedChars;
    private JLabel passLengthLabel;
    private JLabel minLabel;
    private JSpinner minSpinner;
    private JLabel maxLabel;
    private JSpinner maxSpinner;
    private CharType[] types;

    /**
       Default No-Arg Constructor. Creates an array of all CharTypes.
    */
    public PasswordGUI(){
	String[][] charTypes = {{"uppercase letters", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"},
				{"lowercase letters","abcdefghijklmnopqrstuvwxyz"},
				{"digits","0123456789"},
				{"special characters","~`!@#$%^&*()-_+={[}]|\\?/\"':;<,>."}};
	types = new CharType[charTypes.length];
	for(int i = 0; i < types.length; i++) {
	    types[i] = new CharType(charTypes[i]);
	}
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

    includedCharsLabel = new JLabel("Include Special Characters:");
    includedChars = new JTextField("");

    passLengthLabel = new JLabel("Password Length");
    minLabel = new JLabel("Min");
    minSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 14, 1));
    maxLabel = new JLabel("Max");
    maxSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 14, 1));
   
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
                                 .addComponent(passLengthLabel))))
                   .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                         .addGroup(layout2.createSequentialGroup()
                               .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                                 .addComponent(digitsBox)
                                 .addComponent(symbolsBox)
				 .addComponent(includedChars)
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

    frame.setSize(420,242);
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
	    String currentType = "";
	    for(int i = 0; i < types.length; i++){
		currentType = types[i].getType();
		if(cb.isSelected() && currentType.equals("uppercase letters"))
		   types[i].setToBeIncluded(true);
	      	else if(!cb.isSelected() && currentType.equals("uppercase letters"))
	           types[i].setToBeIncluded(false);
	    }
      	}
    }
    
    /**
       Inner class for Lowercase CheckBox
    */

    public class BoxListener2 implements ItemListener {
	public void itemStateChanged(ItemEvent event) {
	    JCheckBox cb = (JCheckBox) event.getSource();
	    String currentType = "";
	    for(int i = 0; i < types.length; i++){
		currentType = types[i].getType();
		if(cb.isSelected() && currentType.equals("lowercase letters"))
		   types[i].setToBeIncluded(true);
	      	else if(!cb.isSelected() && currentType.equals("lowercase letters"))
	       	   types[i].setToBeIncluded(false);
	    }
      	}
    }
  
    /**
       Inner class for Digits CheckBox
    */

   public class BoxListener3 implements ItemListener {
	public void itemStateChanged(ItemEvent event) {
	    JCheckBox cb = (JCheckBox) event.getSource();
	    String currentType = "";
	    for(int i = 0; i < types.length; i++){
		currentType = types[i].getType();
		if(cb.isSelected() && currentType.equals("digits"))
		   types[i].setToBeIncluded(true);
	       	else if(!cb.isSelected() && currentType.equals("digits"))
	       	   types[i].setToBeIncluded(false);
	    }
      	}
    }
 
    /**
       Inner class for Symbols CheckBox
    */

    public class BoxListener4 implements ItemListener {
	public void itemStateChanged(ItemEvent event) {
	    JCheckBox cb = (JCheckBox) event.getSource();
	    String currentType = "";
	    for(int i = 0; i < types.length; i++){
		currentType = types[i].getType();
		if(cb.isSelected() && currentType.equals("special characters"))
		   types[i].setToBeIncluded(true);
	       	else if(!cb.isSelected() && currentType.equals("special characters"))
		  types[i].setToBeIncluded(false);
	    }
      	}
    }


    /**
       Method that replaces special characters default set with only specific special characters the user wants to include in their generated password.

       @param defaultSet The default set of special characters.
       @return The new set of special characters
    */

    private void replaceSpecialCharacters(){
	String input = includedChars.getText();
	String defaultSet;
	for(int i = 0; i < types.length; i++){
	    String currentType = types[i].getType();
	    if(currentType.equals("special characters")){
		defaultSet = types[i].getCharacters();
		if(verifySpecialCharacters(input, defaultSet))
		   types[i].setCharacters(input);
		else
		    JOptionPane.showMessageDialog(null, "One or more of the special characters are not valid");
	    }
	}
    }

    /**
       Method that checks min and max values for valid input. If valid, generates password within range of min and max
    */

 
    public void onActionPerformed1(){
	String include = includedChars.getText();
	int min = (int) minSpinner.getValue();
        int max = (int) maxSpinner.getValue();
        try{
	    if(min > max){
		JOptionPane.showMessageDialog(null, "Min cannot be greater than Max!");
	    }
	    else if(!include.equals("")){
		replaceSpecialCharacters();
		passOutput.setText(generate(types,min,max));
	    }
      	    else
		passOutput.setText(generate(types,min,max));
	}
	catch(IllegalArgumentException e)
        {
             passOutput.getText();//Stub?
        }
   
    }
    
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
    }
}
