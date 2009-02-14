package uk.co.datamantechnology.enigma;

import java.util.*;

public class EnigmaMachine {

	private int numberOfRotors = 3; // e.g. 3 = this a three rotor enigma
									// machine!
	int wheelOrder[]; // assuming a 3 rotor set up, from left to right we have
						// 2,1,0
	private Reflector reflector;
	private Rotor rotors[];
	private Stecker stecker;

	public EnigmaMachine() {
		setUp();
	}
	
	private void setUp() {
		this.rotors = new Rotor[getNumberOfRotors()];
		this.wheelOrder = new int[getNumberOfRotors()];
		this.reflector = new Reflector(1); // choosing the B reflector;
		this.stecker = new Stecker();
	}
	
	public void setRingSettings(String ringSettings){
		for (int i=0; i < ringSettings.length(); i++){
			this.setRingSetting(ringSettings.length()-i-1,ringSettings.charAt(i));
		}		
	}
	
	public void setRingSetting(int rotorNumber, char letter){
		this.rotors[rotorNumber].setRingSetting(letter);
	}
	
	public void setSteckerPairings(String pairings){
		this.stecker.setPairings(pairings);
	}
	
	public void setWheelOrder(int rotor1, int rotor2, int rotor3) {
		// TODO: refactor to be able to deal with more any number of rotors
		selectRotor(2, rotor1);
		selectRotor(1, rotor2);
		selectRotor(0, rotor3);
	}
	
	private void selectRotor(int wheelOrderPosition, int rotorNumber) {
		wheelOrder[wheelOrderPosition] = rotorNumber - 1;
		this.rotors[wheelOrderPosition] = new Rotor(rotorNumber);
	}

	public void moveRotor(int rotor, String direction) {
		if (direction == "up") {
			moveRotorUp(rotor);
		} else {
			moveRotorDown(rotor);
		}
	}

	public void setIndicators(String indicators) {
		for (int i = 0; i < getNumberOfRotors(); i++) {
			setIndicator(getNumberOfRotors() - i - 1, indicators.charAt(i));
		}
	}

	public String getIndicators(){
		String returnString = "";
		for (int i = getNumberOfRotors()-1; i >= 0 ; i--) {
			returnString+= getIndicator(i);
		}
		return returnString;
	}
	
	public String encrypt(String plainText){
		System.out.println("Encrypting: " + plainText);
		StringBuilder encypheredText = new StringBuilder();
		for (int i=0; i<plainText.length(); i++){
			encypheredText.append(encrypt(plainText.charAt(i)));			
		}
		System.out.println("Encyphered text : " + encypheredText.toString());
		return encypheredText.toString();
	}

	public char encrypt(char clearText) {
		char encypheredChar = clearText;
		
		// go through the stecker		
		encypheredChar = this.stecker.encrypt(clearText);
		
		// move the rotors on one
		advanceRotors();
		// go through the rotors in acsending order (encypher)
		for (int i = 0; i < getNumberOfRotors(); i++) {
			encypheredChar = this.rotors[i].encrypt(encypheredChar);
		}
		// go through the reflector
		encypheredChar = reflector.reflect(encypheredChar);

		// go back through the rotors in descending order (decypher)
		for (int i = getNumberOfRotors() - 1; i >= 0; i--) {
			encypheredChar = this.rotors[i].decrypt(encypheredChar);
		}
		
		// go back trhough the Stecker
		encypheredChar = this.stecker.encrypt(encypheredChar);

		System.out.println(clearText + " " + encypheredChar + " " + getIndicators());
		// return encrypted character
		return encypheredChar;
	}

	private void advanceRotors() {
		List<Integer> rotorsToAdvance = new ArrayList<Integer>();
		// check for notch positions
		for (int i = 0; i < getNumberOfRotors(); i++) {
			// right hand rotor moves on one postion every time;
			if (i == 0){
				rotorsToAdvance.add(i);
			// if preceding rotor is at notch position then this rotor moves on
			}else if (this.rotors[i - 1].isAtNotchPosition()){
				rotorsToAdvance.add(i);
			} else if (this.rotors[i].isAtNotchPosition() && i < (getNumberOfRotors()-1)){
				rotorsToAdvance.add(i);	
			}
		}
		for (int i = 0; i < rotorsToAdvance.size(); i++) {
			this.rotors[rotorsToAdvance.get(i)].advance();
		}
	}

	private void moveRotorUp(int rotor) {
		rotors[rotor].advance();
	}

	private void moveRotorDown(int rotor) {
		rotors[rotor].goBack();
	}

	private void setIndicator(int rotor, char letter) {
		this.rotors[rotor].setIndicator(letter);		
	}

	private char getIndicator(int rotor) {
		return this.rotors[rotor].getIndicator();
	}
	
	
	public void setNumberOfRotors(int numberOfRotors) {
		this.numberOfRotors = numberOfRotors;
	}

	public int getNumberOfRotors() {
		return numberOfRotors;
	}
}
