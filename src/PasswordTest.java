import org.junit.Test;
import static org.junit.Assert.*;

/**
 *	Test class Password test, to test the Password class
 *
 *	@author Vince Nicoara
 *	@author Arda Ungun
 *	@version CS56, W15, lab07
 *	@see Password
 */
public class PasswordTest extends Password{


	/**
	 *	test the function initializes an Array of space characters
	 *	to the length specified by the parameter
	 *
	 *	@see Password#initializeArrayList(int length)
	 */
	@Test public void test_initializeArrayList(){
		initializeArrayList(5);

		char [] a = new char[5];
		for (int i = 0; i < 5; i++) {
			a[i] = ' ';
		}

		assertArrayEquals(a, passwordArray);
	}


	/**
	 *	Tests whether findEmptySpotInString() returns correct index of 
	 *	space character aka "empty spot"
	 *
	 *	@see Password#findEmptySpotInString()
	 */
	@Test public void test_findEmptySpotInString() {
		initializeArrayList(5);
		passLength=5;
		passwordArray[0] = '0';
		passwordArray[1] = '1';
		passwordArray[2] = '2';
		passwordArray[3] = '3';
		passwordArray[4] = ' ';
		assertEquals(4, findEmptySpotInString() );
	}



	/*	NOTE: no tests for generatePassword(...) because: 
		 - LENGTH of the the outputted password 
		   is covered by test_passLength()
		 - presence of SPECIAL CHARS is covered by test_appendSpecialChars()

	*/
	


	/**
	 *	Tests whether the allowedSpecialCharacters string of special 
	 *	characters determined by the user is properly modified by the
	 *	function.
	 *
	 *	@see Password#appendSpecialCharacters(String a)
	 */
	@Test public void test_appendSpecialCharacters() {
		allowedSpecialCharacters = "";
		appendSpecialCharacters("}{()*@^&#*(");
		assertEquals("}{()*@^&#*(", allowedSpecialCharacters);
	}



	/**
	 *	@see Password#isInRange(int a, int min, int max)
	 */
	@Test public void test_isInRange() {
		assertFalse( isInRange(5,1,3) );
	}


	/**
	 *	@see Password#getRandomNumber(int start, int end))
	 */
	@Test public void test_getRandomNumber() {
		assertTrue( isInRange(getRandomNumber(1,100),1,100));
	}


	/**
	 *	Tests whether the passwordArrayToString concatenates the Array
	 *	of characters to the password string.
	 *
	 *	@see Password#passwordArrayToString()
	 */
	@Test public void test_passwordArrayToString() {
		initializeArrayList(5);

		// make a dummy passwordArray
		passLength=5;
		passwordArray[0] = '0';
		passwordArray[1] = '1';
		passwordArray[2] = '2';
		passwordArray[3] = '3';
		passwordArray[4] = ' ';

		assertEquals("0123 ",passwordArrayToString());
	}



	/**
	 *	@see Password#setPassLength(int min,int max)
	 */
	@Test public void test_setPassLength(){
		setPassLength(3,10);
		assertTrue(isInRange(passLength, 3, 10));
	}
	

	/**
	 * @see Password#setMinDigits(int num)
	 */
	@Test public void test_setMinDigits(){
		setMinDigits(4);
		assertEquals(numMinDigits, 4);
	}


	/**
	 * @see Password#setMaxDigits(int num)
	 */
	@Test public void test_setMaxDigits(){
		setMaxDigits(4);
		assertEquals(numMaxDigits, 4);
	}


	/**
	 * @see Password#setMaxUppercase(int num)
	 */
	@Test public void test_setMaxUppercase(){
		setMaxUppercase(4);
		assertEquals(numMaxUpperCase, 4);
	}


	/**
	 * @see Password#setMinUppercase(int num)
	 */
	@Test public void test_setMinUppercase(){
		setMinUppercase(4);
		assertEquals(numMinUpperCase, 4);
	}



	/**
	 * @see Password#setMinSpecial(int num)
	 */
	@Test public void test_setMinSpecial(){
		setMinSpecial(4);
		assertEquals(numMinSpecial, 4);
	}


	/**
	 * @see Password#setMaxSpecial(int num)
	 */
	@Test public void test_setMaxSpecial(){
		setMaxSpecial(4);
		assertEquals(numMaxSpecial, 4);
	}


	@Test
	public void test_generateMultiplePasswords(){
		//String [] a = generateMultiplePasswords()
	}
}
