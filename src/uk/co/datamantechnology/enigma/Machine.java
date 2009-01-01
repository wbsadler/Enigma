package uk.co.datamantechnology.enigma;

import java.util.*;

public class Machine {

	public static final int TOTAL_NUMBER_OF_ROTORS = 5; // total number of
														// rotors available to
														// operators
	public static final int TOTAL_NUMBER_OF_REFLECTORS = 2; // total number of
															// reflectors
															// available to
															// operators
	public static final int NUMBER_OF_ROTORS = 3; // e.g. 3 = this a three rotor
													// enigma machine!

	Rotor rotors[] = new Rotor[TOTAL_NUMBER_OF_ROTORS];
	Reflector reflectors[] = new Reflector[TOTAL_NUMBER_OF_REFLECTORS];

	int wheelOrder[] = new int[NUMBER_OF_ROTORS]; // assuming a 3 rotor set up,
													// from left to right we
													// have 2,1,0
	int reflector;

	public Machine() {
		System.out.println("We're starting...");
		setUp();
		System.out.println("We've setup the rotors!");
	}

	public void setWheelOrder(int rotor1, int rotor2, int rotor3) {
		// TODO: refactor to be able to deal with more any number of rotors
		System.out.println("Setting Rotor 2 to " + rotor1);
		selectRotor(2, rotor1 - 1);
		System.out.println("Setting Rotor 1 to " + rotor2);
		selectRotor(1, rotor2 - 1);
		System.out.println("Setting Rotor 0 to " + rotor3);
		selectRotor(0, rotor3 - 1);
	}

	public void moveRotor(int rotor, String direction) {
		if (direction == "up") {
			moveRotorUp(rotor);
		} else {
			moveRotorDown(rotor);
		}
	}

	public void setIndicators(String indicators) {
		for (int i = 0; i < NUMBER_OF_ROTORS; i++) {
			setIndicator(NUMBER_OF_ROTORS - i - 1, indicators.charAt(i));
		}
	}

	public char encrypt(char clearText) {
		// move the rotors on one
		char encypheredChar = clearText;
		advanceRotors();
		// go through the rotors in acsending order (encypher)
		for (int i = 0; i < NUMBER_OF_ROTORS; i++) {
			encypheredChar = rotors[wheelOrder[i]].encrypt(encypheredChar);
		}
		// go through the reflector
		encypheredChar = reflectors[reflector].reflect(encypheredChar);
		
		// go back through the rotors in descending order (decypher)
		for (int i = NUMBER_OF_ROTORS-1; i >= 0; i--) {
			encypheredChar = rotors[wheelOrder[i]].decrypt(encypheredChar);
		}
		
		// return encrypted character
		return encypheredChar;
	}

	private void advanceRotors(){
		List <Integer> rotorsToAdvance = new ArrayList<Integer>();
	    
		//check for notch positions
		for (int i = 0; i < NUMBER_OF_ROTORS; i++) {
			// right hand rotor moves on one postion every time;
			// if preceding rotor is at notch position then it also moves on
			if (i==0 || rotors[wheelOrder[i-1]].isAtNotchPosition())
				rotorsToAdvance.add(i);
		}
		
		for (int i = 0; i < rotorsToAdvance.size(); i++){
			rotors[wheelOrder[rotorsToAdvance.get(i)]].advance();
		}
		
	}

	private void initialiseReflectors() {
		for (int i = 0; i < TOTAL_NUMBER_OF_REFLECTORS; i++) {
			reflectors[i] = new Reflector();
		}
		// 'B' reflector:-
		reflectors[0].loadWiring("YRUHQSLDPXNGOKMIEBFZCWVJAT");
		// 'C' reflector:-
		reflectors[1].loadWiring("FVPJIAOYEDRZXWGCTKUQSBNMHL");
		// The 4 rotor naval enigma had different reflectors:-
		/*
		 * 'B' reflector:-
		 * m_reflectors[2].loadWiring("ENKQAUYWJICOPBLMDXZVFTHRGS");
		 * 
		 * 'C' reflector:-
		 * m_reflectors[3].loadWiring("RDOBJNTKVEHMLFCWZAXGYIPSUQ");
		 */
		this.reflector = 0; // cbhoosing the B reflector;

	}

	private void initialiseRotors() {
		rotors[0] = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 'Q');
		rotors[1] = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 'E');
		rotors[2] = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		rotors[3] = new Rotor("ESOVPZJAYQUIRHXLNFTGKDCMWB", 'J');
		rotors[4] = new Rotor("VZBRGITYUPSDNHLXAWMJQOFECK", 'Z');

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

	}

	private void moveRotorUp(int rotor) {
		// TODO Auto-generated method stub

	}

	private void moveRotorDown(int rotor) {
		// TODO Auto-generated method stub

	}

	private void selectRotor(int rotor, int wheelOrderPosition) {
		wheelOrder[wheelOrderPosition] = rotor;
	}

	private void setIndicator(int rotor, char letter) {
		System.out.println("Setting indicator on rotor " + rotor + " to "
				+ letter);
		rotors[wheelOrder[rotor]].setCurrentPosition(letter);
	}

	private void setUp() {
		initialiseRotors();
		initialiseReflectors();
		setWheelOrder(1, 2, 3); // just to get it up and running
		setIndicators("ABC");
	}
}
