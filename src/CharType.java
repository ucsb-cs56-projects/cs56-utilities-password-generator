public class CharType {

    private String type;
    private String characters;
    private boolean toBeIncluded;

    public CharType() {

	this.characters = "";
	this.type = "not defined";
	this.toBeIncluded = false;
    }

    public CharType(String[] typeInfo) {

	//typeInfo[0] is the TYPE
	//typeInfo[1] are the CHARACTERS of that type

	this.type = typeInfo[0];
	this.characters = typeInfo[1];
	this.toBeIncluded = false;
    }

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
