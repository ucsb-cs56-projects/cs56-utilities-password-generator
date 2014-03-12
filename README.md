cs56-Password-Generator
=======================
By Weihan Wang
JavaDoc: http://www.cs.ucsb.edu/~108498asdf//cs56/W11/issues/0000042/javadoc



High-Level Description(User's View)

This is a program that creates a random password with a given length of max and min. You can also input a list of special characters that can be added to the library of special characters that will be used to create the password.In the current state, I have implemented everything that it is suppose to do.


Some Internal Documentation(Developer's View)

1. The main GUI code is located in PasswordGUI.java. 

2.This is the main function that generates the output. it also calls other function to help it do staff.
	public String generate(String b,int min,int max)
	 {
	 // code here 
	 }

3.class Password has all the variables that are used in this project.
  class PasswordGUI contains all the GUI swing elements.

How to run this project?
    -just type "ant run".








