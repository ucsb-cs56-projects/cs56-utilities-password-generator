cs56-Password-Generator
=======================

Contributors:

* Vladimir Adam: https://foo.cs.ucsb.edu/cs56/issues/0000042/
* Ian Vernon and Evan Moelter (pair) https://github.com/ianvernon/cs56-utilities-password-generator
* Weihan (David) Wang: https://github.com/108498asdf/cs56-utilities-password-generator
* Vince Nicora and Arda Ungun (W15): https://github.com/vincenicoara/cs56-utilities-password-generator

JavaDoc: http://www.cs.ucsb.edu/~108498asdf//cs56/W11/issues/0000042/javadoc


project history
===============
```
 W14 | bkiefer13 4pm | 108498asdf | (jcneally) A file that creates random passwords with a given password length
```

High-Level Description(User's View)
===================================

This is a program that creates a random password with a given length of max and min. You can also input a list of special characters that can be added to the library of special characters that will be used to create the password.In the current state, I have implemented everything that it is suppose to do.


Some Internal Documentation(Developer's View)
=============================================

1. The main GUI code is located in PasswordGUI.java. 

2. This is the main function that generates the output. It also calls other functions to do stuff.
    ```java
	public String generate(String b,int min,int max)
	 {
	 // code here 
	 }
    ```
3. class Password has all the variables that are used in this project.  class PasswordGUI contains all the GUI swing elements.

How to run this project?
========================

just type: 

```
ant run
```





