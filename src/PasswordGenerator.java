import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * 
 * This class is responsible for generating a password using the specification defined in Password Class
 * 
 * Quick Summary of Steps:
 * 1. generate the core of the password base on the given specifications using CorePassword()
 * 2. check if the core password meet a randomized length between the user defined min and max length of the password
 * 	  if not, add randomly a bunch of characters from Types that are included until it reaches the correct length
 * 3. Randomize the password using Shuffle(): shuffle(Strign s) takes a string, puts it in to an arrayList and uses
 * 	  Collections.shuffle() to randomize the arraylist, then convert the ArrayList back to a string and spit out the
 *    String as a randomized password.
 * 
 * @author penger, Sunimal E
 *
 */
public class PasswordGenerator extends Password {

	/**
	 * no arg Constructor
	 * 
	 * @param
	 * 
	 * @return none
	 */
	public PasswordGenerator() {
		super();
	}
	/**
	 * Constructor with arguments
	 * 
	 * @param
	 * 
	 * @return 
	 * @throws IOException 
	 * @throws IndexOutOfBoundsException 
	 */
	public PasswordGenerator(int min, int max, ArrayList<CharType> userSpec) {
		super();
		if (min <= 0 || max <=0 || min > max){
			System.out.println("invalid min,max");
			return;
		}
		else{
		super.setMin(min);
		super.setMax(max);
		super.setLength(min, max);
		for (CharType t : userSpec) {
			
			select(t.getType(), t.isIncluded());
			
			setTypeLength(t.getType(), t.getLength());
		}
		}
	}

	/**
	 * Generate the password
	 * 
	 * @param none
	 * @return password string
	 */
	public String generate() {
		String password = "";
		password += corePassword();
		password = appendRandom(password);
		password = shuffle(password);
		return password;
	}

	/**
	 * Randomlly include characters with specified length
	 * Done by using a HashSet (no duplicates); therefore all randomized and unique for a stronger password;
	 * 
	 * @param 
	 * type and length of type
	 * @return String of chars added in order of password class features
	 */
	public String getUniqueRandom(String type, int selectedLength) {
		Random random = new Random();
		String retval = "";
		String currChars = hmap.get(type).getCharacters();
		int size = currChars.length();
		Set<Integer> randSet = new LinkedHashSet<Integer>();
		while (randSet.size() < selectedLength) {
			randSet.add(random.nextInt(size));
		}
		for (int i : randSet) {
			retval += currChars.charAt(i);
		}
		return retval;
	}

	/**
	 * initial password with characters in order
	 * 
	 * @param none
	 * @return String of password in order of characters
	 */
	public String corePassword() {
		String corePass = "";
		for (String key : hmap.keySet()) {
			if (hmap.get(key).isIncluded()) {
				int i = hmap.get(key).getLength();
				corePass += getUniqueRandom(key, i);
			}
		}
		return corePass;
	}

	/**
	 * append characters to the end of the string if the min length requirement
	 * does not meet
	 * 
	 * @param 
	 * string of password crated
	 * @return
	 * strign with appended characters
	 */
	public String appendRandom(String currPass) {
		String pass = currPass;
		int difference = getLength() - pass.length();
		if (difference > 0) {
			try {
				pass += getRandom(difference);
			} catch (IOException e) {
				System.out.println("nothng is selected");
			}
		}

		return pass;
	}

	/**
	 * return x amount of randomized characters
	 * first randomly selects a type, then randomly select a character in that type
	 * only select something if the type is set to be included, if not chooses another type randomly
	 * once it has selected the x amount of random characters from types that are included return them as a string
	 * 
	 * @param num
	 * @return
	 * @throws IOException
	 */
	public String getRandom(int num) throws IOException{
		//Random random = new Random();
		String retval = "";
		String add = "";
		for (int i = 0; i < num; i++) {
			int rand = (int) (Math.random() * (hmap.size()));
			int j = 0;
			int x = 0;
			//check if nothing is selcted
			for (String key: hmap.keySet()){
				if (hmap.get(key).isIncluded()){
					x++;
				}
			}
			if(x==0) {
				throw new IOException();
			}
			for (String key : hmap.keySet()) {
				if (rand == j && hmap.get(key).isIncluded()) {
					//System.out.println("is it included:?"+hmap.get(key).isIncluded());
					String curr = hmap.get(key).getCharacters();
					int l = curr.length();
					int pos = (int) (Math.random() * l);
					add = new Character(curr.charAt(pos)).toString();
					break;
				}
				j++;
			}
			if (add.length() == 0){
				i --;
			}
			retval+=add;
		}
		return retval;
	}

	/**
	 * Shuffle the characters in the password string
	 * 
	 * @param 
	 * String of pass
	 *  
	 * @return
	 * password string
	 */
	public String shuffle(String pass) {
		String shuffled = "";
		ArrayList<String> pArray = new ArrayList<String>();
		for (int i = 0; i < pass.length(); i++) {
			pArray.add(new Character(pass.charAt(i)).toString());
		}
		Collections.shuffle(pArray);
		for (String s : pArray) {
			shuffled += s;
		}
		return shuffled;
	}
	
	/**
	 * cleans the settins for the password to all typelengths = 0, nothing is selected
	 */
	public void cleanAll(){
		for(String key : hmap.keySet()){
			clean(key);
		}
	}
	
	public void clean(String key){
		hmap.get(key).setToBeIncluded(false);
		hmap.get(key).setLength(0);
	}


	public static void main(String[] args) {

		PasswordGenerator s = new PasswordGenerator();
		s.setLength(8,30);
		System.out.println("length "+ s.getLength());
		System.out.println("length "+ s.getLength()+" Password: "+s.generate());
	}
}
