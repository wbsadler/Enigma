package uk.co.datamantechnology.enigma;

public class Alphabet {
	
	public static final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;
	public static final int ASCII_A = 65;
	
	public static char goForwards(char from){
		return goForwards(from, 1);
	}
	
	public static char goForwards(char from, int numberOfLetters){
		int letterIndex = toLetterIndex(from);
		letterIndex =  (letterIndex + numberOfLetters + NUMBER_OF_LETTERS_IN_ALPHABET) % NUMBER_OF_LETTERS_IN_ALPHABET;
		return toLetter(letterIndex);
	}
	
	public static char goBackwards(char from){
		return goBackwards(from,1);
	}
	
	public static char goBackwards(char from, int numberOfLetters){
		return goForwards(from,-numberOfLetters);
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


