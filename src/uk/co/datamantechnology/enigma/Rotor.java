package uk.co.datamantechnology.enigma;


public class Rotor {
	
	private static Configuration cfg = new Configuration();

	public static final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;
	public static final int ASCII_A = 65;

	private String wiring; // scrambled alphabet
	private int currentPosition; // the position relative to the wiring
	private char notch; // the letter next to the notch 
	private int ringSetting; // ringStellung 

	public Rotor(int rotorNumber) {
		setNotch(cfg.getNotches()[rotorNumber-1]);
		setWiring(cfg.getRotorWirings()[rotorNumber-1]);
		setIndicator('A');
		setRingSetting('A');
	}

	public Rotor(String wiring, char notch) {
		setWiring(wiring);
		setNotch(notch);
		setRingSetting('A');
		setIndicator('A');
	}
		
	public int getCurrentPosition(){
		return currentPosition;
	}

	private void setCurrentPosition(char letter) {
		setCurrentPosition((int) Alphabet.goBackThroughAlphabet(letter,getRingSetting()) - ASCII_A);	
	}
	
	private void setCurrentPosition(int position) {
		this.currentPosition = position;
	}
	
	public void setRingSetting(char letter){
		setRingSetting((int)letter-ASCII_A);
	}
	
	private void setRingSetting(int position){
		this.ringSetting = position;
	}
	
	private int getRingSetting(){
		return this.ringSetting;		
	}
		
	public char getIndicator(){
		return Alphabet.goForwardThroughAlphabet((char)(getCurrentPosition()+ASCII_A), getRingSetting());
	}

	public String getWiring() {
		return wiring;
	}

	public void setWiring(String wiring) {
		this.wiring = wiring;
	}

	public void setNotch(char notch) {
		this.notch = notch;
	}

	public char getNotch() {
		return notch;
	}
		
	public char getAdjustedNotch(){
		return Alphabet.goBackThroughAlphabet(getNotch(), getRingSetting());
	}
	
	public int getAdjustedNotchPosition(){
		return (int)getAdjustedNotch()-ASCII_A;
	}


	public char encrypt(char clearCharacter) {
		char encryptedCharacter;
		int letterIndex = (int) clearCharacter - ASCII_A;
		// encrypt the character taking into account the current 
		encryptedCharacter = getLetterAtPosition(letterIndex);
		// take care of the offset
		if (getCurrentPosition() > 0) {			 
			letterIndex = (letterIndex + getCurrentPosition()) % NUMBER_OF_LETTERS_IN_ALPHABET;
			letterIndex = (int) encryptedCharacter - ASCII_A;
			letterIndex = ((NUMBER_OF_LETTERS_IN_ALPHABET + letterIndex - getCurrentPosition()) % NUMBER_OF_LETTERS_IN_ALPHABET) + ASCII_A;
			encryptedCharacter = (char) letterIndex;
		} 
		return encryptedCharacter;
	}

	public char decrypt(char encryptedChar) {
		char decryptedChar;
		if (getCurrentPosition() > 0) {
			// get the offset character
			char offsetCharacter = Alphabet.goForwardThroughAlphabet(encryptedChar, getCurrentPosition());
			int decryptedCharAscii = getPositionOfLetter(offsetCharacter) + ASCII_A;
			decryptedChar = (char) (decryptedCharAscii);
			// readjusting output for offset - move back through the alphabet
			decryptedChar = Alphabet.goBackThroughAlphabet(decryptedChar, getCurrentPosition()); 
		} else {
			int decryptedCharAsciiValue = getPositionOfLetter(encryptedChar) + ASCII_A;
			decryptedChar = (char) decryptedCharAsciiValue;
		}		
		return decryptedChar;
	}
	
	private char getLetterAtPosition(int letterIndex){
		int wiringIndex = (letterIndex + getCurrentPosition()) % NUMBER_OF_LETTERS_IN_ALPHABET;
		return this.wiring.charAt(wiringIndex);
	}

	private int getPositionOfLetter(char character){
		int wiringIndex = this.wiring.indexOf(character);
		return wiringIndex;
	}
	
	public void advance() {
		setCurrentPosition((getCurrentPosition() + 1) % NUMBER_OF_LETTERS_IN_ALPHABET);
	}
	
	public void goBack() {
		setCurrentPosition((getCurrentPosition() - 1 + NUMBER_OF_LETTERS_IN_ALPHABET) % NUMBER_OF_LETTERS_IN_ALPHABET);
	}

	public boolean isAtNotchPosition() {		 
		return (getIndicator() == this.notch);
	}

	public void setIndicator(char letter) {
		setCurrentPosition(letter);
	}

}
