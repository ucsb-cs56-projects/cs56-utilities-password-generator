import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;



import java.util.ArrayList;

public class TestPasswordGenerator {

	private String pw;
	private String[] copy = {  "uppercase letters","lowercase letters",  "digits", "special characters"};

	//verify constructor generated default password
	//1 upper,6 lower, 3 digits, 1 special

	@Test
	public void TestPasswordGeneratorConstructor(){
		
		PasswordGenerator pwGen=new PasswordGenerator();
		pw=pwGen.generate();
		System.out.println(pw);
		int [] checkArray=new int[]{1,6,3,1};
		
		assertTrue(Categorize(pw,checkArray));
	}
	
	
	//this test case verify user slected password
	@Test
	public void TestPasswordGenerator0_1_2_3(){
		int [] checkArray=new int[copy.length];
		ArrayList<CharType> pwFeature=new ArrayList<CharType>();
		CharType[] test=new CharType[copy.length];
		for (int i=0;i<copy.length;i++){
			test[i]=new CharType(copy[i],"",i>0);
			test[i].setLength(i);
			pwFeature.add(i, test[i]);
			checkArray[i]=i;
			
		}
		
		PasswordGenerator pwGen=new PasswordGenerator(5,10,pwFeature);
		pw=pwGen.generate();
		System.out.println(pw);
		//check for the password with 0 upper, 1 lower, 2 digits, 3 special and min length of 5 and max length of 10
		assertTrue(Categorize(pw,checkArray));
		
	}

	
	public boolean Categorize(String pw, int[] check){
		boolean pass=true;
		//Character [] chars=new Character[pw.length()]
		int [] verifyArray=new int[copy.length];
		char[] charArrayPw=pw.toCharArray();
		for (char c: charArrayPw){
			if ( Character.isUpperCase(c)){
				verifyArray[0]=verifyArray[0]+1;
			}
			else if(Character.isLowerCase(c)){
				verifyArray[1]=verifyArray[1]+1;
			}else if(Character.isDigit(c)){
				verifyArray[2]=verifyArray[2]+1;
			}else{
				verifyArray[3]=verifyArray[3]+1;
			}

		}
		System.out.println(check.length);
		//for (Integer lenCheck:check) for (Integer lenVerify: verifyArray)
		for (int i=0; i< check.length;i++){
			
			System.out.println(verifyArray[i]);
			System.out.println(check[i]);
			if ((check[i]==0) && (verifyArray[i]==0)){
				continue;
			}
			else if(verifyArray[i]>=check[i]){
				continue;
			}else{
				pass=false;
			}
			//pass=false;	
		}
		
		return pass;
		
	}

}//end of test class
