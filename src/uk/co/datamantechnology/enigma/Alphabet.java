package uk.co.datamantechnology.enigma;

public class Alphabet {
	
	public static final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;
	public static final int ASCII_A = 65;
	
	public static char goForwards(char from){
		int ascii = (int) from;
		ascii  =  ((ascii-ASCII_A+1) % NUMBER_OF_LETTERS_IN_ALPHABET)+ASCII_A;
		return (char) ascii;
	}
	
	public static char goForwards(char from, int numberOfLetters){
		char currentLetter = from;
		for (int i = 0; i<numberOfLetters; i++){
			currentLetter = goForwards(currentLetter);
		}
		return currentLetter;
	}
	
	public static char goBackwards(char from){
		int ascii = (int) from;
		ascii  =  ((ascii-ASCII_A-1 + NUMBER_OF_LETTERS_IN_ALPHABET) % NUMBER_OF_LETTERS_IN_ALPHABET)+ASCII_A;
		return (char) ascii;
	}
	
	public static char goBackwards(char from, int numberOfLetters){
		char currentLetter = from;
		if (numberOfLetters > 0){
			for (int i = 0; i<numberOfLetters; i++){
				currentLetter = goBackwards(currentLetter);
			}
		}
		return currentLetter;
	}
	
	
	public static int toLetterIndex(char letter){
		return (int)letter - ASCII_A;
	}
	
	public static char toLetter(int letterIndex){
		return (char)(letterIndex + ASCII_A);
	}

	public static int goForwards(int from, int numberOfLetters) {		
		return (NUMBER_OF_LETTERS_IN_ALPHABET + from + numberOfLetters) % NUMBER_OF_LETTERS_IN_ALPHABET;
	}
	
	public static int goBackwards(int from, int numberOfLetters) {		
		return goForwards(from,-numberOfLetters);
	}

}


