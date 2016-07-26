import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Password {
	protected static final String TYPE_UPPER = "uppercase letters", TYPE_LOWER = "lowercase letters",
			TYPE_DIGITS = "digits", TYPE_SPECIAL = "special characters", TYPE_SELECTED = "selected specials";
	
	protected static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ", LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz",
			NUMS = "0123456789", SPECIAL_CHARACTERS = "~`!@#$%^&*()-_+={[}]|\\?/\"':;<,>.";
	private int min, max, length;
	//protected ArrayList<CharType> types;
	
	protected static final CharType upperCase = new CharType(TYPE_UPPER, UPPER_CASE_LETTERS, false),
			lowerCase = new CharType(TYPE_LOWER, LOWER_CASE_LETTERS, false),
			digits = new CharType(TYPE_DIGITS, NUMS, false),
			special = new CharType(TYPE_SPECIAL, SPECIAL_CHARACTERS, false);
	
	
	protected HashMap<String, CharType> hmap = new HashMap<String, CharType>();
	
	public Password() {
		setUpTypes();
		setUpDefault();
	}

	public void setUpTypes() {
		String[][] copy = { { TYPE_UPPER, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" },
				{ TYPE_LOWER, "abcdefghijklmnopqrstuvwxyz" }, { TYPE_DIGITS, "0123456789" },
				{ TYPE_SPECIAL, "~`!@#$%^&*()-_+={[}]|\\?/\"':;<,>." }, { TYPE_SELECTED, "" } };
		/*types = new ArrayList<CharType>();
		for (String[] t : copy) {
			types.add(new CharType(t));
		}*/
		for (String [] t : copy){
			hmap.put(t[0], new CharType(t));
		}
	}

	public void select(String type, boolean isSelected) {
		CharType selected = hmap.get(type);
		selected.setToBeIncluded(isSelected);
	}

	public void selectAll() {
		/**Iterator i = hmap.entrySet().iterator();
		while (i.hasNext()){
			Map.Entry curr =  (Map.Entry)i.next();
			((CharType) curr.getValue()).setToBeIncluded(true);
		}*/
		for (String key: hmap.keySet()) {
		    hmap.get(key).setToBeIncluded(true);
		}
	}
	
	public void setUpDefault(){
		selectAll();
		setTypeLength(TYPE_UPPER,1);
		setTypeLength(TYPE_LOWER,6);
		setTypeLength(TYPE_DIGITS,3);
		setTypeLength(TYPE_SPECIAL,1);
		
	}

	public void setTypeLength(String type, int length) {
		CharType selected = hmap.get(type);
		selected.setLength(length);
	}
	public int getTypeLength(String type){
		CharType selected = hmap.get(type);
		return selected.getLength();
	}
	
	public void setLength(int min, int max){
		this.length = (int) (Math.random() * (max - min) + min + 1);
	}
	public int getLength() {
		return this.length;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMin() {
		return this.min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMax() {
		return this.max;
	}
}
