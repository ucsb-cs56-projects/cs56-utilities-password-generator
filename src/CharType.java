public class CharType {

    private String type;
    private String characters;
    private int min;
    private int max;

    public CharType() {

	this.characters = "";
	this.type = "not defined";
	this.min = 0;
	this.max = 0;

    }

    public CharType(String[] typeInfo) {

	//typeInfo[0] is the TYPE
	//typeInfo[1] are the CHARACTERS of that type

	this.type = typeInfo[0];
	this.characters = typeInfo[1];
	this.min = 0;
	this.max = 0;
	
    }

    public CharType(String type, String characters, int min, int max) {

	this.type = type;
	this.characters = characters;
	this.min = min;
	this.max = max;

    }

    public String getType() {
	return this.type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getCharacter() {
	return this.characters;
    }

    public void setCharacters(String characters) {
	this.characters = characters;
    }

}
