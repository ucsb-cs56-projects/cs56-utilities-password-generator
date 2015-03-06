import org.junit.Test;
import static org.junit.Assert.*;


public class PasswordTest extends Password{
	@Test
	public void test_initializeArrayList(){
		initializeArrayList(5);

		char [] a = new char[5];
		for (int i = 0; i < 5; i++) {
			a[i] = ' ';
		}

		assertArrayEquals(a, passwordArray);
	}


	@Test
	public void test_findEmptySpotInString() {
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
	


	@Test
	public void test_appendSpecialCharacters() {
		allowedSpecialCharacters = "";
		appendSpecialCharacters("}{()*@^&#*(");
		assertEquals("}{()*@^&#*(", allowedSpecialCharacters);
	}


	@Test
	public void test_isInRange() {
		assertFalse( isInRange(5,1,3) );
	}


	@Test
	public void test_getRandomNumber() {
		assertTrue( isInRange(getRandomNumber(1,100),1,100));
	}



	@Test
	public void test_passwordArrayToString() {
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



	/// STUB FINISH HIm,!!!!!!!

	@Test
	public void test_setPassLength(){
		setPassLength(3,10);
		assertTrue(isInRange(passLength, 3, 10));
	}


	@Test
	public void test_setMinDigits(){
		setMinDigits(4);
		assertEquals(numMinDigits, 4);
	}



	@Test
	public void test_setMaxDigits(){
		setMaxDigits(4);
		assertEquals(numMaxDigits, 4);
	}

	@Test
	public void test_setMaxUppercase(){
		setMaxUppercase(4);
		assertEquals(numMaxUpperCase, 4);
	}


	@Test
	public void test_setMinUppercase(){
		setMinUppercase(4);
		assertEquals(numMinUpperCase, 4);
	}


	@Test
	public void test_setMinSpecial(){
		setMinSpecial(4);
		assertEquals(numMinSpecial, 4);
	}


	@Test
	public void test_setMaxSpecial(){
		setMaxSpecial(4);
		assertEquals(numMaxSpecial, 4);
	}


	@Test
	public void test_setAllowedSpecialCharacters(){
		setAllowedSpecialCharacters("@!^#$");
		assertEquals(allowedSpecialCharacters, "@!^#$");
	}

	@Test
	public void test_generateMultiplePasswords(){
		//String [] a = generateMultiplePasswords()
	}

}