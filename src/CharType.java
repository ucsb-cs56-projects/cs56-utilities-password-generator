public class CharType {

    private String type;
    private String characters;
    private boolean toBeIncluded;

    /**
       Default Constructor. Sets values to undefined. A default CharType is set to NOT be included.
    */
    public CharType() {

	this.characters = "";
	this.type = "not defined";
	this.toBeIncluded = false;
    }

    /**
       A constructor based on a String array.

       @param typeInfo An array of 2 Strings: Slot 0 is the name of the type. Slot 1 are the characters that make up that type.
     */
    public CharType(String[] typeInfo) {

	//typeInfo[0] is the TYPE
	//typeInfo[1] are the CHARACTERS of that type

	this.type = typeInfo[0];
	this.characters = typeInfo[1];
	this.toBeIncluded = false;
    }

    /**
       Constructor for each member variable in the class.
       
       @param type The name of the type.
       @param characters The characters that make up that type.
       @param toBeIncluded A boolean that determines if this type will be included or not.
    */
    public CharType(String type, String characters, boolean toBeIncluded) {
	this.type = type;
	this.characters = characters;
	this.toBeIncluded = toBeIncluded;
    }

    public String getType() {
	return this.type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getCharacters() {
	return this.characters;
    }

    public void setCharacters(String characters) {
	this.characters = characters;
    }

    public void setToBeIncluded(boolean toBeIncluded) {
	this.toBeIncluded = toBeIncluded;
    }

    public boolean getToBeIncluded() {
	return this.toBeIncluded;
    }

}
