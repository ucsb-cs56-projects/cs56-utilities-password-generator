import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class PasswordGenerator extends Password {

	public PasswordGenerator() {
		// TODO Auto-generated constructor stub
		
		/*for (String key: hmap.keySet()) {
		    if (hmap.get(key).isIncluded()){
		    	int i = hmap.get(key).getLength();
		    	password += getUniqueRandom(key, i);
		    }
		}*/
		
	}
	
	public String generate(){
		String password = "";
		password += corePasswordUnsorted();
		password = appendRandom(password);
		password = shuffle(password);
		return password;
	}
	public String getUniqueRandom (String type, int selectedLength){
		Random random = new Random();
		String retval = "";
		String currChars = hmap.get(type).getCharacters();
		int size = currChars.length();
		Set<Integer> randSet = new LinkedHashSet<Integer>();
	    while( randSet.size() < selectedLength ) {
	        randSet.add(random.nextInt(size));
	    }
	    for (int i : randSet){
	    	retval += currChars.charAt(i); 
	    }
	    return retval;
	}
	public String corePasswordUnsorted(){
		String corePass = "";
		for (String key: hmap.keySet()) {
		    if (hmap.get(key).isIncluded()){
		    	int i = hmap.get(key).getLength();
		    	corePass += getUniqueRandom(key, i);
		    }
		}
		return corePass;
	}
	public String appendRandom(String currPass){
		String pass = currPass;
		int difference = getLength() - pass.length();
		if(difference > 0){
			pass += getRandom(difference);
		}
		
		return pass;
	}
	
	public String getRandom(int num){
		Random random = new Random();
		String retval = "";
		for (int i = 0; i < num; i++){
			int rand = (int)(Math.random()*(hmap.size()));
			int j = 0;
			for (String key: hmap.keySet()) {
				if (!hmap.get(key).isIncluded()){
					i--;
					break;
				}
				else if (rand == j){
					String curr = hmap.get(key).getCharacters();
					int l = curr.length();
					int pos = (int)(Math.random()*l);
					retval += curr.charAt(pos);
				}
			    j++;
			}
			
		}
		return retval;
	}
	public String shuffle(String pass){
		String shuffled = "";
		ArrayList <String> pArray = new ArrayList<String>();
		for (int i = 0; i < pass.length(); i++){
			pArray.add(new Character(pass.charAt(i)).toString());
		}
		Collections.shuffle(pArray);
		for (String s : pArray){
			shuffled += s;
		}
		return shuffled;
	}
	public static void main(String[] args){
		PasswordGenerator s = new PasswordGenerator();
		System.out.println(s.generate());
	}
}
