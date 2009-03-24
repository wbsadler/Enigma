package uk.co.datamantechnology.enigma;


public class Rotor {
	
	private static Configuration cfg = new Configuration();

	public static final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;

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
		char currentLetter = Alphabet.goBackwards(letter,getRingSetting());
		setCurrentPosition(Alphabet.toLetterIndex(currentLetter));
	}
	
	private void setCurrentPosition(int position) {
		this.currentPosition = position;
	}
	
	public void setRingSetting(char letter){
		setRingSetting(Alphabet.toLetterIndex(letter));
	}
	
	private void setRingSetting(int position){
		this.ringSetting = position;
	}
	
	private int getRingSetting(){
		return this.ringSetting;		
	}
		
	public char getIndicator(){
		char currentLetter = Alphabet.toLetter(getCurrentPosition());
		return Alphabet.goForwards(currentLetter, getRingSetting());
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
		return Alphabet.goBackwards(getNotch(), getRingSetting());
	}
	
	public int getAdjustedNotchPosition(){
		return Alphabet.toLetterIndex(getAdjustedNotch());
	}


	public char encrypt(char clearCharacter) {
		// System.out.print(clearCharacter);
		char encryptedCharacter;
		int letterIndex = Alphabet.toLetterIndex(clearCharacter);
		// encrypt the character taking into account the current position of the rotor  
		encryptedCharacter = getLetterAtPosition(letterIndex);
		// System.out.print(encryptedCharacter);
		// take care of the offset
		if (getCurrentPosition() > 0) { 
			letterIndex = Alphabet.toLetterIndex(encryptedCharacter);
			letterIndex = Alphabet.goBackwards(letterIndex,getCurrentPosition()); 
			encryptedCharacter = Alphabet.toLetter(letterIndex);
			// System.out.print(encryptedCharacter);
		}else{
			// System.out.print(encryptedCharacter);
		} 
		// System.out.print(" ");
		return encryptedCharacter;
	}

	public char decrypt(char encryptedLetter) {
		// System.out.print(encryptedLetter);
		char decryptedLetter;
		// get position of letter taking into account the current position of the rotor
		int decryptedLetterIndex = getPositionOfLetter(encryptedLetter);
		decryptedLetter = Alphabet.toLetter(decryptedLetterIndex);
		// System.out.print(decryptedLetter);
		// re-adjust output for any offset
		if (getCurrentPosition() > 0) {
			decryptedLetter = Alphabet.goBackwards(decryptedLetter, getCurrentPosition());
			// System.out.print(decryptedLetter);
		}else{
			// System.out.print(decryptedLetter);
		}		
		// System.out.print(" ");
		return decryptedLetter;
	}
	
	private char getLetterAtPosition(int letterIndex){
		int wiringIndex = (letterIndex + getCurrentPosition()) % NUMBER_OF_LETTERS_IN_ALPHABET;
		return this.wiring.charAt(wiringIndex);
	}

	private int getPositionOfLetter(char character){
		// get the offset character
		char offsetCharacter = Alphabet.goForwards(character, getCurrentPosition());
		int wiringIndex = this.wiring.indexOf(offsetCharacter);
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
