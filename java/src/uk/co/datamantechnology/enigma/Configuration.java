package uk.co.datamantechnology.enigma;

public class Configuration {

	public static final int TOTAL_NUMBER_OF_ROTORS = 5;
	public static final int TOTAL_NUMBER_OF_REFLECTORS = 2;
	private String[] rotorWirings = new String[TOTAL_NUMBER_OF_ROTORS];
	private char[] notches = new char[TOTAL_NUMBER_OF_ROTORS];
	private String[] reflectorWirings = new String[TOTAL_NUMBER_OF_REFLECTORS];

	public Configuration() {
		rotorWirings[0] = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
		rotorWirings[1] = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
		rotorWirings[2] = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
		rotorWirings[3] = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
		rotorWirings[4] = "VZBRGITYUPSDNHLXAWMJQOFECK";

		// for 6-8 of the naval enigma:-
		// TODO: refactor for naval enigma
		/*
		 * rotors[5].loadWiring("JPGVOUMFYQBENHZRDKASXLICTW");
		 * rotors[5].setNotch('Z','M');
		 * rotors[6].loadWiring("NZJHGRCXMYSWBOUFAIVLPEKQDT");
		 * rotors[6].setNotch('Z','M');
		 * rotors[7].loadWiring("FKQHTLXOCBJSPDZRAMEWNIUYGV");
		 * rotors[7].setNotch('Z','M');
		 */

		// The 4 rotor naval enigma could only use one of two 'thin' rotors Beta
		// and Gamma in the leftmost position:-
		/*
		 * m_betaRotor.loadWiring("LEYJVCNIXWPBQMDRTAKZGFUHOS");
		 * m_gammaRotor.loadWiring("FSOKANUERHMBTIYCWLQPZXVGJD");
		 */

		notches[0] = 'Q';
		notches[1] = 'E';
		notches[2] = 'V';
		notches[3] = 'J';
		notches[4] = 'Z';

		// 'B' reflector:-
		reflectorWirings[0] = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
		// 'C' reflector:-
		reflectorWirings[1] = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
		// The 4 rotor naval enigma had different reflectors:-
		/*
		 * 'B' reflector:-
		 * m_reflectors[2].loadWiring("ENKQAUYWJICOPBLMDXZVFTHRGS");
		 * 
		 * 'C' reflector:-
		 * m_reflectors[3].loadWiring("RDOBJNTKVEHMLFCWZAXGYIPSUQ");
		 */
	}

	public String[] getRotorWirings() {
		return rotorWirings;
	}

	public char[] getNotches() {
		return notches;
	}
	
	public String[] getReflectorWirings(){
		return reflectorWirings;
	}

	public int getTotalNumberOfReflectors() {
		return TOTAL_NUMBER_OF_REFLECTORS;
	}

	public int getTotalNumberOfRotors() {
		return TOTAL_NUMBER_OF_ROTORS;
	}
}
