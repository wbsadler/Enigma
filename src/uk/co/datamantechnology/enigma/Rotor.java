package uk.co.datamantechnology.enigma;

public class Rotor {

	public static final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;
	String wiring;
	int currentPosition;
	char notch;
	boolean offset = false;

	public Rotor() {
	}

	public Rotor(int Initialosition) {
		setCurrentPosition(Initialosition);
	}

	public Rotor(String wiring, char notch) {
		setCurrentPosition(0);
		setNotch(notch);
		setWiring(wiring);
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(char letter) {
		setCurrentPosition((int)letter-65);
	}

	public String getWiring() {
		return wiring;
	}

	public void setWiring(String wiring) {
		this.wiring = wiring;
	}

	public char getNotch() {
		return notch;
	}

	public void setNotch(char notch) {
		this.notch = notch;
	}

	private void setCurrentPosition(int position) {
		this.currentPosition = position;
	}

	public char encrypt(char clearCharacter) {
		char encryptedCharacter;
		int letterIndex = (int) clearCharacter - 65;
		
		System.out.println("Encrypting currentPosition: " + this.currentPosition + " clearCharacter: " + clearCharacter + " letterIndex: " + letterIndex);

		if (this.offset) {
			encryptedCharacter = getLetterAtPosition(letterIndex); 
			letterIndex = (letterIndex + this.currentPosition) % NUMBER_OF_LETTERS_IN_ALPHABET;
			System.out.print(clearCharacter + " offset to " + (char)(letterIndex +65) + " encrypted to "
					+ encryptedCharacter + " with offset");
			letterIndex = (int) encryptedCharacter - 65;
			letterIndex = ((letterIndex - this.currentPosition) % NUMBER_OF_LETTERS_IN_ALPHABET) + 65;
			encryptedCharacter = (char) letterIndex;
			System.out.println(" passed on to position " + encryptedCharacter);
		} else {
			encryptedCharacter = getLetterAtPosition(letterIndex);
			System.out.println(clearCharacter + " encrypted to "
					+ encryptedCharacter);
		}
		return encryptedCharacter;
	}

	public char decrypt(char encryptedChar) {
		char decryptedChar;
		if (this.offset) {
			// move the encrypted character on one in the alphabet
			char offsetCharacter = Alphabet.goForwardThroughAlphabet(encryptedChar, this.currentPosition);
			int decryptedCharAscii = getPositionOfLetter(offsetCharacter) + 65;
			decryptedChar = (char) (decryptedCharAscii);
			System.out.print("Decrypting " + encryptedChar + " which activates the " + offsetCharacter + " terminal, converted to " + decryptedChar + "[" + decryptedCharAscii + "], ");
			// readjusting output for offset - move back the currentPosition + 1 
			decryptedChar = Alphabet.goBackThroughAlphabet(decryptedChar, this.currentPosition); 
			System.out.println("readjusted to " + decryptedChar);
		} else {
			int decryptedCharAsciiValue = getPositionOfLetter(encryptedChar) + 65;
			decryptedChar = (char) decryptedCharAsciiValue;
		}
		
		if (this.offset)
			this.offset = false;
		
		System.out.println(encryptedChar + " decrypted to " + decryptedChar);
		return decryptedChar;
		
	}
	
	private char getLetterAtPosition(int letterIndex){
		int wiringIndex = (letterIndex + this.currentPosition) % NUMBER_OF_LETTERS_IN_ALPHABET;
		return this.wiring.charAt(wiringIndex);
	}

	private int getPositionOfLetter(char character){
		int wiringIndex = this.wiring.indexOf(character);
		System.out.print("Character " + character + " is at position " + wiringIndex + ". ");
		return wiringIndex;
	}
	
	public void advance() {
		this.currentPosition++;
		this.currentPosition = this.currentPosition
				% NUMBER_OF_LETTERS_IN_ALPHABET;
		this.offset = true;
	}

	public boolean isAtNotchPosition() {
		return (this.currentPosition == getPositionOfLetter(notch));
	}

}
