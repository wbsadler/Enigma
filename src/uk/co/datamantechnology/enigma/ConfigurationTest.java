package uk.co.datamantechnology.enigma;

import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {
	
	public static final int TOTAL_NUMBER_OF_ROTORS = 5;
	public static final int TOTAL_NUMBER_OF_REFLECTORS = 2;
	public static final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;
	public static final int ASCII_A = 65;
	private Configuration cfg = new Configuration();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetRotorWiringsShouldReturnAnArray(){
		String[] rotorWirings = cfg.getRotorWirings();
		boolean b = rotorWirings.getClass().isArray();
		assertTrue("Oops!  Rotors is not an array!",b);
	}
	
	public void testGetRotorWiringsShouldReturnCorrectArrayLength(){
		String[] rotorWirings = cfg.getRotorWirings();
		assertTrue("Oops!  Rotors is not length " + TOTAL_NUMBER_OF_ROTORS,rotorWirings.length == TOTAL_NUMBER_OF_ROTORS);
	}
	
	
	public void testGetRotorWiringsShouldReturnArrayOfScrambledAlphabet(){
		String[] rotorWirings = cfg.getRotorWirings();
		for (int i=0; i < rotorWirings.length; i++){
			assertTrue("The " + i + "th rotor does not have " + NUMBER_OF_LETTERS_IN_ALPHABET + " letters!",rotorWirings[i].length() == NUMBER_OF_LETTERS_IN_ALPHABET);
		}
	}
	
	public void testGetRotorWiringsShouldContainNoDuplicateCharacters(){
		String[] rotorWirings = cfg.getRotorWirings();
		for (int i=0; i < rotorWirings.length; i++){
			for (int j=0; j < NUMBER_OF_LETTERS_IN_ALPHABET; j++){
				char c = (char) (j+ASCII_A); 
				assertTrue(c + " is duplicated in rotor " + i + "!",rotorWirings[i].indexOf(c) == rotorWirings[i].lastIndexOf(c));
			}
		}
	}
		
	public void testGetRotorWiringsContainsAllLettersInAlphabet(){
		String[] rotorWirings = cfg.getRotorWirings();
		for (int i=0; i < rotorWirings.length; i++){
			for (int j=0; j < NUMBER_OF_LETTERS_IN_ALPHABET; j++){
				char c = (char) (j+ASCII_A); 
				assertTrue(c + " not found!",rotorWirings[i].indexOf(j+ASCII_A) > -1);
			}
		}
	}
		
	public void testGetTotalNumberOfReflectors(){
		assertTrue("Wrong number of reflectors!",cfg.getTotalNumberOfReflectors() == TOTAL_NUMBER_OF_REFLECTORS);		
	}
	
	public void testGetTotalNumberOfRotors(){
		assertTrue("Wrong number of rotors",cfg.getTotalNumberOfRotors() == TOTAL_NUMBER_OF_ROTORS);
	}
	
	

}
