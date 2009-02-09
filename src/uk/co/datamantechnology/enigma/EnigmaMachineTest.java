package uk.co.datamantechnology.enigma;

import junit.framework.TestCase;

public class EnigmaMachineTest extends TestCase {
	
	private EnigmaMachine testEnigma = new EnigmaMachine();
	
	public EnigmaMachineTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEncrypt() {
		
		String plainText = "ABCDEFG";
		String expected = "BJELRQZ";
		testEnigma.setWheelOrder(1,2,3);
//		Ring Settings:      111 (or AAA)
		testEnigma.setIndicators("AAA");
//		Steckers:           None
			
		String actual = "";
		
		for (int i=0; i<plainText.length(); i++){
			actual = actual + testEnigma.encrypt(plainText.charAt(i));			
		}
		
		assertEquals("These two strings don't match!", expected, actual);

//		With the rotors I, II and III (from left to right), wide B-reflector, all ring settings in A-position, and start position AAA, typing AAAAA will produce the encoded sequence BDZGO.
//		http://en.wikipedia.org/wiki/Enigma_rotor_details
		
		testEnigma.setIndicators("AAA");
		plainText = "AAAAA";
		expected = "BDZGO";
		actual = "";
		
		for (int i=0; i<plainText.length(); i++){
			actual = actual + testEnigma.encrypt(plainText.charAt(i));			
		}
		
		System.out.println(plainText + " has been encrypted to: " + actual);
		
		assertEquals("These two strings don't match!", expected, actual);
		
	}
	
	public void testRotation() {
//		To see the anomaly in action, fit the rotors in the order 1,2,3 and set the indicators to 'EDV' 
//		(The ring settings dont matter as the turnover mechanism is on the alphabet rings, not the wheel cores). 
//		The first keystroke should move the indicator to 'EEW' and the next should move all 3 rotors, 
//		resulting in an indicator of 'FFX'. 
		
			
		
	}

}
