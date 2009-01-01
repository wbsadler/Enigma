package uk.co.datamantechnology.enigma;

public class Reflector {
	String wiring;

	public void loadWiring(String wiring) {
		// TODO Auto-generated method stub
		this.wiring = wiring;
	}
	
	public char reflect(char entryLetter){
		int letterIndex = (int) entryLetter - 65;
		System.out.println(entryLetter + " is reflected to " + this.wiring.charAt(letterIndex));
		return this.wiring.charAt(letterIndex);
	}

}
