import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Password {
	protected static final String TYPE_UPPER = "uppercase letters", TYPE_LOWER = "lowercase letters",
			TYPE_DIGITS = "digits", TYPE_SPECIAL = "special characters", TYPE_SELECTED = "selected specials";

	protected static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
			LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz", NUMS = "0123456789",
			SPECIAL_CHARACTERS = "~`!@#$%^&*()-_+={[}]|\\?/\"':;<,>.";
	private int min, max, length;
	// protected ArrayList<CharType> types;

	protected static final CharType upperCase = new CharType(TYPE_UPPER, UPPER_CASE_LETTERS, false),
			lowerCase = new CharType(TYPE_LOWER, LOWER_CASE_LETTERS, false),
			digits = new CharType(TYPE_DIGITS, NUMS, false),
			special = new CharType(TYPE_SPECIAL, SPECIAL_CHARACTERS, false);

	protected HashMap<String, CharType> hmap = new HashMap<String, CharType>();

	/**
	 * Constructor
	 */
	public Password() {
		
		setUpTypes();
		setUpDefault();
	}

	/**
	 * Set the hash mp with key= types and value=CharType object instantiated with all the character ty 
	 * @param
	 * none
	 * @return
	 * none
	 */
	public void setUpTypes() {
		String[][] copy = { { TYPE_UPPER, "ABCDEFGHIJKLMNOPQRSTUVWXYZ" }, { TYPE_LOWER, "abcdefghijklmnopqrstuvwxyz" },
				{ TYPE_DIGITS, "0123456789" }, { TYPE_SPECIAL, "~`!@#$%^&*()-_+={[}]|\\?/\"':;<,>." },
				/*{ TYPE_SELECTED, "" }*/ };
		/*
		 * types = new ArrayList<CharType>(); for (String[] t : copy) {
		 * types.add(new CharType(t)); }
		 */
		for (String[] t : copy) {
			hmap.put(t[0], new CharType(t));
		}
	}
	
	/**
	 * Setting the to be included field in the CharTypes
	 * @param
	 * type of character to be included and isSlected includes that type
	 * @return
	 * none
	 */
	public void select(String type, boolean isSelected) {
		//System.out.println(type);
		CharType selected = hmap.get(type);
		//System.out.println(selected.getType());
		selected.setToBeIncluded(isSelected);
	}
	
	public void cleanAll(){
		for(String key : hmap.keySet()){
			clean(key);
		}
	}
	
	public void clean(String key){
		//hmap.get(key).setLength(0);
		hmap.get(key).setToBeIncluded(false);
		hmap.get(key).setLength(0);
	}
	
	
	/**
	 * Setting all the types to be included in the password
	 * @param
	 * 
	 * @return
	 * none
	 */
	
	public void selectAll() {
		/**
		 * Iterator i = hmap.entrySet().iterator(); while (i.hasNext()){
		 * Map.Entry curr = (Map.Entry)i.next(); ((CharType)
		 * curr.getValue()).setToBeIncluded(true); }
		 */
		for (String key : hmap.keySet()) {
			hmap.get(key).setToBeIncluded(true);
		}
	}

	/**
	 * Setting the types for default password
	 * @param
	 * 
	 * @return
	 * none
	 */
	public void setUpDefault() {
		selectAll();
		setTypeLength(TYPE_UPPER, 1);
		setTypeLength(TYPE_LOWER, 5);
		setTypeLength(TYPE_DIGITS, 1);
		setTypeLength(TYPE_SPECIAL, 1);

	}

	/**
	 * Sets the length of each char type
	 * @param
	 * type and length
	 * @return
	 * none
	 */
	public void setTypeLength(String type, int length) {
		CharType selected = hmap.get(type);
		selected.setLength(length);
	}

	/**
	 * getting the length of the each type
	 * @param
	 * type
	 * @return
	 * none
	 */
	public int getTypeLength(String type) {
		CharType selected = hmap.get(type);
		return selected.getLength();
	}

	/**
	 * Setting password length according to min and max
	 * @param
	 * min and max
	 * @return
	 * none
	 */
	public void setLength(int min, int max) {
		assert(min<=max);
		setMin(min);
		setMax(max);
		this.length = (int) (Math.random() * (max - min + 1) + min);
	}

	
	public int getLength() {
		return this.length;
	}

	public void setMin(int min) {
		//assert(true);
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
