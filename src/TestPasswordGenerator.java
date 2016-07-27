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
		System.out.println(pw+" -deafault constructor pw");
		int [] checkArray=new int[]{1,5,1,1};
		
		assertTrue(pw.length()>=8);
		assertTrue(Categorize(pw,checkArray));
		
	}
	
	
	//this test case verify user selected password
	//no upper case letters
	//minimum password length equals to lengths of each characters types
	@Test
	public void TestPasswordGenerator0_1_2_3(){
		int min=8, max=10;
		int [] checkArray=new int[]{0,1,2,3};
		ArrayList<CharType> pwFeature=new ArrayList<CharType>();
		CharType[] test=new CharType[copy.length];
		for (int i=0;i<copy.length;i++){
			test[i]=new CharType(copy[i],"",checkArray[i]>0);
			test[i].setLength(checkArray[i]);
			pwFeature.add(i, test[i]);
			checkArray[i]=i;
			
		}
		
		PasswordGenerator pwGen=new PasswordGenerator(min,max,pwFeature);
		pw=pwGen.generate();
		System.out.println(pw+ " -0_1_2_3");
		//System.out.println(pwGen.getLength());
		//check for the password with 0 upper, 1 lower, 2 digits, 3 special and min length of 5 and max length of 10
		assertTrue(pw.length()>=min);
		assertTrue(Categorize(pw,checkArray));
		
	}

	//this test case verify user selected password
	//No lower case letters
	//minimum password length equals to lengths of each characters types
	@Test
	public void TestPasswordGenerator3_0_1_2(){
		int min=6, max=10;
		int [] checkArray=new int[]{3,0,1,2};

		ArrayList<CharType> pwFeature=new ArrayList<CharType>();
		CharType[] test=new CharType[copy.length];
		for (int i=0;i<copy.length;i++){
			test[i]=new CharType(copy[i],"",checkArray[i]>0);
			test[i].setLength(checkArray[i]);
			pwFeature.add(i, test[i]);
			
		}
		
		PasswordGenerator pwGen=new PasswordGenerator(min,max,pwFeature);
		pw=pwGen.generate();
		System.out.println(pw+ " -3_0_1_2");
		test[0].toString();
		//System.out.println(pwGen.getLength());
		//check for the password with 0 upper, 1 lower, 2 digits, 3 special and min length of 5 and max length of 10
		assertTrue(pw.length()>=min);
		assertTrue(Categorize(pw,checkArray));
		
	}
	
	//this test case verify user selected password
	//No digits
	//minimum password length equals to lengths of each characters types
	@Test
	public void TestPasswordGenerator1_2_0_3(){
		int min=6, max=10;
		int [] checkArray=new int[]{1,2,0,3};
		ArrayList<CharType> pwFeature=new ArrayList<CharType>();
		CharType[] test=new CharType[copy.length];
		for (int i=0;i<copy.length;i++){
			test[i]=new CharType(copy[i],"",checkArray[i]>0);
			test[i].setLength(checkArray[i]);
			pwFeature.add(i, test[i]);
			//checkArray[i]=i;
			
		}
		
		PasswordGenerator pwGen=new PasswordGenerator(min,max,pwFeature);
		pw=pwGen.generate();
		System.out.println(pw+ " -1_2_0_3");
		//System.out.println(pwGen.getLength());
		//check for the password with 0 upper, 1 lower, 2 digits, 3 special and min length of 5 and max length of 10
		assertTrue(pw.length()>=min);
		assertTrue(Categorize(pw,checkArray));
		
	}
	
	//this test case verify user selected password
	//No Special characters
	//minimum password length equals to lengths of each characters types
	@Test
	public void TestPasswordGenerator3_2_1_0(){
		int min=6, max=10;
		int [] checkArray=new int[]{3,2,1,0};
		ArrayList<CharType> pwFeature=new ArrayList<CharType>();
		CharType[] test=new CharType[copy.length];
		
		for (int k=0;k<checkArray.length;k++){
			test[k]=new CharType(copy[k],"",checkArray[k]>0);
			test[k].setLength(checkArray[k]);
			pwFeature.add(k, test[k]);
			
			
		}
		
		PasswordGenerator pwGen=new PasswordGenerator(min,max,pwFeature);
		pw=pwGen.generate();
		System.out.println(pw+ " -3_2_1_0");
		//check for the password with 0 upper, 1 lower, 2 digits, 3 special and min length of 5 and max length of 10
		assertTrue(pw.length()>=min);
		assertTrue(Categorize(pw,checkArray));
		
	}
	
	
	//this test case verify user selected password
	//No special characters
	//minimum length of password does not equals to lengths of each characters
	@Test
	public void TestPasswordGeneratorMin3_2_1_0(){
		int min=8, max=10;
		int [] checkArray=new int[]{3,2,1,0};
		ArrayList<CharType> pwFeature=new ArrayList<CharType>();
		CharType[] test=new CharType[copy.length];
		
		for (int k=0;k<checkArray.length;k++){
			test[k]=new CharType(copy[k],"",checkArray[k]>0);
			test[k].setLength(checkArray[k]);
			pwFeature.add(k, test[k]);
			
		}
		
		PasswordGenerator pwGen=new PasswordGenerator(min,max,pwFeature);
		pw=pwGen.generate();
		System.out.println(pw+ " -min: " +min);
		//check for the password with 0 upper, 1 lower, 2 digits, 3 special and min length of 5 and max length of 10
		assertTrue(pw.length()>=min);
		assertTrue(Categorize(pw,checkArray));
		
	}
	
	public boolean Categorize(String pw, int[] check){
		boolean pass=true;
		//Character [] chars=new Character[pw.length()]
		int [] verifyArray=new int[]{0,0,0,0};
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
		
		//for (Integer lenCheck:check) for (Integer lenVerify: verifyArray)
		for (int i=0; i< check.length;i++){
			
			//System.out.println(verifyArray[i]);
			//System.out.println(check[i]);
			if (verifyArray[i]==0 && check[i]==0){
				continue;
			}
			else if(verifyArray[i]>=check[i]){
				continue;
			}else{
				System.out.println(verifyArray[i]+" "+i+" "+ check[i]);
				
				pass=false;
			}
			//pass=false;	
		}
		
		return pass;
		
	}

}//end of test class
