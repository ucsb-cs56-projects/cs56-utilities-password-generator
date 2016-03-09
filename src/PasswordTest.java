import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Random;

/**
 *The test class Password Test, to test the Password class
 *
 *@author Giovanni Rojas, Troy Larson
 *@version CS56, W16, Lab07
 *@see Password
 */

public class PasswordTest{
    /**    
    @Test public void testNoArgConstructor(){
	Password p1 = new Password();
	Password p2 = new Password();
	assertEquals(p1, p2);
    }

       @Test public void testThreeArgGenerate(){
    }
   
    @Test public void testAppendSpecialCharacters(){
	Password p1 = new Password();
	p1.appendSpecialCharacters("#$%");
	assertEquals("#$%", p1.allowedSpecialChracters);
    }
    @Test public void testNoArgGenerate(){
    }
   
   
    @Test public void testToString(){
	Password p1 = new Password();
	p1.generate("*", 2, 6);
	assertEquals();
    }


    @Test public void testSetPassLength(){
	Password p1 = new Password();
	int low = 2;
	int high = 10;
	assertTrue("Error, passLength is too low", low <= 0);//stub
	assertTrue("Error, passLength is too high", high >= 0);//stub
    }

    
    @Test public void testSetMinDigits(){
        Password p1 = new Password();
        p1.setMinDigits(5);
        assertEquals(5, p1.getMinDigits());
    }


    @Test public void testSetMaxDigits(){
	Password p1 = new Password();
	p1.setMaxDigits(6);
	assertEquals(6, p1.getMaxDigits());
    }

    @Test public void testSetMaxUpperCase(){
	Password p1 = new Password();
        p1.setMaxUpperCase(7);
        assertEquals(7, p1.getMaxUpperCase());
    }

    @Test public void testSetMinUpperCase(){
	Password p1 = new Password();
        p1.setMinUpperCase(8);
        assertEquals(8, p1.getMinUpperCase());
    }

    @Test public void testSetMinSpecial(){ 
	Password p1 = new Password();
        p1.setMinSpecial(9);
        assertEquals(9, p1.getMinSpecial());
    }

    @Test public void testSetMaxSpecial(){
	Password p1 = new Password();
        p1.setMaxSpecial(10);
        assertEquals(10, p1.getMaxSpecial());
    }

    @Test public void testSetAllowedSpecialCharacters(){
    }

    @Test public void testArrayArgGenerate(){
    }

    @Test public void testShuffle(){
    }
    */

}
