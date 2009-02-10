package uk.co.datamantechnology.enigma;

import java.util.*;

public class EnigmaMachine {

	private int numberOfRotors = 3; // e.g. 3 = this a three rotor enigma
									// machine!
	int wheelOrder[]; // assuming a 3 rotor set up, from left to right we have
						// 2,1,0
	private Reflector reflector;
	private Rotor rotors[];

	public EnigmaMachine() {
		System.out.println("We're starting...");
		setUp();
		System.out.println("We've setup the rotors!");
	}
	
	private void setUp() {
		this.rotors = new Rotor[getNumberOfRotors()];
		this.wheelOrder = new int[getNumberOfRotors()];
		this.reflector = new Reflector(1); // choosing the B reflector;
	}
	
	public void setWheelOrder(int rotor1, int rotor2, int rotor3) {
		// TODO: refactor to be able to deal with more any number of rotors
		System.out.println("Setting Rotor 2 to " + rotor1);
		System.out.println("Setting Rotor 1 to " + rotor2);
		System.out.println("Setting Rotor 0 to " + rotor3);
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

	public char encrypt(char clearText) {
		char encypheredChar = clearText;
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

		// return encrypted character
		return encypheredChar;
	}

	private void advanceRotors() {
		List<Integer> rotorsToAdvance = new ArrayList<Integer>();
		
		System.out.println("\nADVANCING ROTORS");
		// check for notch positions
		for (int i = 0; i < getNumberOfRotors(); i++) {
			
			System.out.println("\nchecking i : " + i);
			// right hand rotor moves on one postion every time;
			if (i == 0){
				rotorsToAdvance.add(i);
			// if preceding rotor is at notch position then this rotor moves on
			}else if (this.rotors[i - 1].isAtNotchPosition()){
				System.out.println("\n Previous rotor at notch position");
				rotorsToAdvance.add(i);
			} else if (this.rotors[i].isAtNotchPosition() && i < (getNumberOfRotors()-1)){
				rotorsToAdvance.add(i);	
			}
		}
		for (int i = 0; i < rotorsToAdvance.size(); i++) {
			System.out.println("\nAdvancing rotor: " + rotorsToAdvance.get(i) + "\n\n");
			this.rotors[rotorsToAdvance.get(i)].advance();
		}
	}

	private void moveRotorUp(int rotor) {
		// TODO Auto-generated method stub
	}

	private void moveRotorDown(int rotor) {
		// TODO Auto-generated method stub
	}

	private void setIndicator(int rotor, char letter) {
		System.out.println("Setting indicator on rotor " + rotor + " to "
				+ letter);
		this.rotors[rotor].setCurrentPosition(letter);
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
