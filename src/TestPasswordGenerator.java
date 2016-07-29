import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;



import java.util.ArrayList;

/**
 * 
 * Test coverage for different possible situation of password Specification
 * Because of the similarity of many cases, not all possible cases are needed to be included in the test coverage
 * 
 * the tests mainly focus on testing the correctness of the methods that help generate the password
 * 
 * Summary of testing:
 * 		after generating a password String, P, using the given specification
 * 		check if P does indeed meet the specification, and that nothing "funny" happened
 * 		
 * @author JH Penger, Sunimal E.
 *
 */

public class TestPasswordGenerator extends PasswordGenerator{

	private String pw;
	private String[] copy = {  "uppercase letters","lowercase letters",  "digits", "special characters"};
	private String[] types = {  TYPE_UPPER,TYPE_LOWER, TYPE_DIGITS, TYPE_SPECIAL};
	

	//verify constructor generated default password
	//1 upper,5 lower, 1 digits, 1 special

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
	
	/**
	 * Test with all the character types enabled with length at least 1 in each types
	 */
	@Test
	public void TestPasswordGenerator1111_tttt_min6_max10(){
		//first set  the default password features in the CharTypes hash map
		PasswordGenerator pwGen=new PasswordGenerator();
		//set length of new password 
		pwGen.setLength(6,10);
		//then change the features CharType objects in Hash map
		//first set all the CharTypes to be true
		int i=0;
		int [] lengths=new int[]{1,1,1,1};
		char[] include=new char[] {'t','t','t','t'};
		for (String key : hmap.keySet()) {	
			hmap.get(key).setToBeIncluded(include[i]=='t');
			hmap.get(key).setLength(lengths[i]);
		}
		//then generate the new password
		String pwNew=pwGen.generate();
		//then verify the new password meets the requirements
		assertTrue(pwNew.length()>=6);
		assertTrue(CategorizeNew(pwNew,include ));
		
	}
	
	@Test
	public void TestPasswordGenerator5011_tttt_min10_max20(){
		//first set  the default password features in the CharTypes hash map
		PasswordGenerator pwGen=new PasswordGenerator();
		//set length of new password 
		pwGen.setLength(10,20);
		//then change the features CharType objects in Hash map
		//first set all the CharTypes to be true
		int i=0;
		int [] lengths=new int[]{5,0,1,1};
		char[] include=new char[] {'t','t','t','t'};
		for (String key : types) {	
			pwGen.hmap.get(key).setToBeIncluded((include[i]=='t'));
			pwGen.hmap.get(key).setLength(lengths[i]);
			i++;
		}

		//then generate the new password
		String pwNew=pwGen.generate();
		//then verify the new password meets the requirements
		assertTrue(pwNew.length()>=10);
		assertTrue(CategorizeNew(pwNew,include ));
		
	}
	
	@Test
	public void TestPasswordGenerator5011_fttt_min10_max20(){
		//first set  the default password features in the CharTypes hash map
		PasswordGenerator pwGen=new PasswordGenerator();
		//set length of new password 
		pwGen.setLength(10,20);
		//then change the features CharType objects in Hash map
		//first set all the CharTypes to be true
		int i=0;
		int [] lengths=new int[]{5,0,1,1};
		char[] include=new char[] {'f','t','t','t'};
		for (String key : types) {	
			pwGen.hmap.get(key).setToBeIncluded(include[i]=='t'?true:false);
			pwGen.hmap.get(key).setLength(lengths[i]);
			i++;
		}

		//then generate the new password
		String pwNew=pwGen.generate();
		//then verify the new password meets the requirements
		assertTrue(pwNew.length()>=10);
		assertTrue(CategorizeNew(pwNew,include ));
		
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
			
			if (verifyArray[i]==0 && check[i]==0){
				continue;
			}
			else if(verifyArray[i]>=check[i]){
				continue;
			}else{
				
				pass=false;
			}
		}
		
		return pass;	
	}
	
	//this function verifies password with given set of enabled characters
	public boolean CategorizeNew(String pw, char [] isEnabled){
		boolean pass=true;
		
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
		int i=0;
	//verify against the length and enabled character types
		for (char isIncluded : isEnabled){
			if (isIncluded=='f'){
				if (verifyArray[i]==0){
					i++;
					continue;
				}else{
					pass= false;
					break;
				}}
			else if (verifyArray[i]>=0){
				i++;
				continue;
				}
			else{
				pass=false;
				break;
			}
				
			}
		return pass;
		}
		


}//end of test class
