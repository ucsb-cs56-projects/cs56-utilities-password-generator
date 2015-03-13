import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

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
	*	This test checks to see whether the function correctly transforms a 
	*	string into an ArrayList object.
	*
	*	@see Password#stringToCharArrayList()
	*/

	@Test public void test_stringToCharArrayList(){
		String a = "Test";
		ArrayList<Character> arraylist = new ArrayList<Character>();
		ArrayList<Character> answer = new ArrayList<Character>();
		arraylist.add(0, 'T');
		arraylist.add(1, 'e');
		arraylist.add(2, 's');
		arraylist.add(3, 't');

		answer = stringToCharArrayList(a);
		assertEquals(answer, arraylist);
	}

	/**
	* This test checks to see if the function takes the correct amount of randomly
	* extracted characters from the ArrayList
	*
	* @see Password#getNRandomCharsFromArray()
	*/

	@Test public void test_getNRandomCharsFromArray(){
		ArrayList<Character> arraylist = new ArrayList<Character>();
		arraylist.add(0, 'T');
		arraylist.add(1, 'e');
		arraylist.add(2, 's');
		arraylist.add(3, 't');
		assertEquals(4, getNRandomCharsFromArray(4, arraylist).size());
	}

	/**
	 *	Tests whether the passwordArrayToString concatenates the Array
	 *	of characters to the password string.
	 *
	 *	@see Password#passwordArrayToString()
	 */
	@Test public void test_passwordArrayToString() {

		passwordArray.add(0, 'T');
		passwordArray.add(1, 'e');
		passwordArray.add(2, 's');
		passwordArray.add(3, 't');

		assertEquals("Test",passwordArrayToString());
	}


	/**
	 * Tests to see if the range is functional
	 *	@see Password#getRandomNumber(int start, int end))
	 */
	@Test public void test_getRandomNumber() {

		assertTrue( isInRange(getRandomNumber(1,100),1,100));
	}

	//Helper function for the above test.
	protected boolean isInRange(int a, int min, int max) {
 		if (a >= min && a <= max) return true;
 		return false;
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
	 * @see PAssword#setNumDigits(int min, int max)
	 */

	@Test public void test_setNumDigits(){
		setNumDigits(4, 10);
		assertTrue(isInRange(numDigits, 4, 10));
	}

	/**
	 * @see Password#setMinUppercase(int num)
	 */
	@Test public void test_setMinUppercase(){
		setMinUppercase(4);
		assertEquals(numMinUppercase, 4);
	}

	/**
	 * @see Password#setMaxUppercase(int num)
	 */
	@Test public void test_setMaxUppercase(){
		setMaxUppercase(4);
		assertEquals(numMaxUppercase, 4);
	}

	/**
	 * @see Password#setNumUppercase(int min, int max)
	 */
	@Test public void test_setNumUppercase(){
		setNumUppercase(1 , 4);
		assertTrue(isInRange(numUppercase, 1, 4));
	}

	/**
	 * @see Password#setMinLowercase(int num)
	 */
	@Test public void test_setMinLowercase(){
		setMinLowercase(4);
		assertEquals(numMinLowercase, 4);
	}


	/**
	 * @see Password#setMaxLowercase(int num)
	 */
	@Test public void test_setMaxLowercase(){
		setMaxLowercase(4);
		assertEquals(numMaxLowercase, 4);
	}

	/**
	 * @see Password#setNumLowercase(int min, int max)
	 */
	@Test public void test_setNumLowercase(){
		setNumLowercase(1 , 4);
		assertTrue(isInRange(numLowercase, 1, 4));
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

	/**
	 * @see Password#setNumSpecial(int min, int max)
	 */
	@Test public void test_setNumSpecial(){
		setNumSpecial(1 , 4);
		assertTrue(isInRange(numSpecial, 1, 4));
	}

	/**
	 * @see Password#setUserSpecialChars(String s)
	 */
	@Test public void test_setUserSpecialChars(){
		setUserSpecialChars("!@#");
		assertEquals("!@#", userSpecialChars);
	}

	/**
	* @see Password#setIncludeDigits(boolean a)
	*/
	@Test public void test_setIncludeDigits(){
		setIncludeDigits(true);
		assertTrue(includeDigits);
	}

	/**
	* @see Password#setIncludeUpper(boolean a)
	*/
	@Test public void test_setIncludeUpper(){
		setIncludeUpper(true);
		assertTrue(includeUpper);
	}

	/**
	* @see Password#setIncludeLower(boolean a)
	*/
	@Test public void test_setIncludeLower(){
		setIncludeLower(true);
		assertTrue(includeLower);
	}

	/**
	* @see Password#setIncludeSpecial(boolean a)
	*/
	@Test public void test_setIncludeSpecial(){
		setIncludeSpecial(true);
		assertTrue(includeSpecial);
	}
	
	/**
	* @see Password#setIncludeUserSpecial(boolean a)
	*/
	@Test public void test_setIncludeUserSpecial(){
		setIncludeUserSpecial(true);
		assertTrue(includeUserSpecial);
	}

	/**
	* @see Password#getIncludeDigits()
	*/
	@Test public void test_getIncludeDigits(){
		setIncludeDigits(true);
		assertTrue(getIncludeDigits());
	}

	/**
	* @see Password#getIncludeUpper()
	*/
	@Test public void test_getIncludeUpper(){
		setIncludeUpper(true);
		assertTrue(getIncludeUpper());
	}

	/**
	* @see Password#getIncludeLower()
	*/
	@Test public void test_getIncludeLower(){
		setIncludeLower(true);
		assertTrue(getIncludeLower());
	}

	/**
	* @see Password#getIncludeSpecial()
	*/
	@Test public void test_getIncludeSpecial(){
		setIncludeSpecial(true);
		assertTrue(getIncludeSpecial());
	}

	/**
	* @see Password#getIncludeUserSpecial()
	*/
	@Test public void test_getIncludeUserSpecial(){
		setIncludeUserSpecial(true);
		assertTrue(getIncludeUserSpecial());
	}

}










