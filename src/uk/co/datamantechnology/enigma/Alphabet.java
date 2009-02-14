package uk.co.datamantechnology.enigma;

public class Alphabet {
	
	public static final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;
	public static final int ASCII_A = 65;
	
	public static char goForwardThroughAlphabet(char from){
		int ascii = (int) from;
		ascii  =  ((ascii-ASCII_A+1) % NUMBER_OF_LETTERS_IN_ALPHABET)+ASCII_A;
		return (char) ascii;
	}
	
	public static char goForwardThroughAlphabet(char from, int numberOfLetters){
		char currentLetter = from;
		for (int i = 0; i<numberOfLetters; i++){
			currentLetter = goForwardThroughAlphabet(currentLetter);
		}
		return currentLetter;
	}
	
	public static char goBackThroughAlphabet(char from){
		int ascii = (int) from;
		ascii  =  ((ascii-ASCII_A-1 + NUMBER_OF_LETTERS_IN_ALPHABET) % NUMBER_OF_LETTERS_IN_ALPHABET)+ASCII_A;
		return (char) ascii;
	}
	
	public static char goBackThroughAlphabet(char from, int numberOfLetters){
		char currentLetter = from;
		if (numberOfLetters > 0){
			for (int i = 0; i<numberOfLetters; i++){
				currentLetter = goBackThroughAlphabet(currentLetter);
			}
		}
		return currentLetter;
	}

}
