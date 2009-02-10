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
		
		// another encryption with different rotors
		
		testEnigma.setWheelOrder(3,4,5);
		testEnigma.setIndicators("AAA");
		plainText = "AAAAA";
		expected = "HNYNU";
		actual = "";
		
		for (int i=0; i<plainText.length(); i++){
			actual = actual + testEnigma.encrypt(plainText.charAt(i));			
		}
		
		System.out.println(plainText + " has been encrypted to: " + actual);
		
		assertEquals("These two strings don't match!", expected, actual);
		
		// another "random" set u
		
		
	}
	
	public void testEncrypt2() {
		// this is from Tony Sale's page www.codesandciphers.org.uk/enigma/example1.htm
		testEnigma.setWheelOrder(1,2,3);
		testEnigma.setIndicators("AAZ");
		String plainText = "G";
		String expected = "P";
		String actual = "";
		
		for (int i=0; i<plainText.length(); i++){
			actual = actual + testEnigma.encrypt(plainText.charAt(i));			
		}
		
		System.out.println(plainText + " has been encrypted to: " + actual);
		
		assertEquals("These two strings don't match!", expected, actual);
		
	}
	
	public void testEncrypt3(){
		testEnigma.setWheelOrder(2,4,3);
		testEnigma.setIndicators("DIV");
//		testEnigma.setIndicators("AAA");
		String plainText = "AAAAA";
//		String plainText = "AAAAAAAAAAAAAAAAAAAAAAAAA";
		String expected = "YLOCX";
//		String expected = "DCVJJFXFKPVMSOQPCPYRHNJVT";
		String actual = "";
		
		for (int i=0; i<plainText.length(); i++){
			actual = actual + testEnigma.encrypt(plainText.charAt(i));			
		}
		
		System.out.println(plainText + " has been encrypted to: " + actual);
		
		assertEquals("These two strings don't match!", expected, actual);
	
	}
	
	
	public void testEncrypt4(){
		testEnigma.setWheelOrder(2,3,4);
		testEnigma.setIndicators("AAA");
		String plainText = "AAAAAAAAAA";
		String expected = "IFKKLKUDOP";
		String actual = "";
		for (int i=0; i<plainText.length(); i++){
			actual = actual + testEnigma.encrypt(plainText.charAt(i));			
		}
		System.out.println(plainText + " has been encrypted to: " + actual);
		assertEquals("These two strings don't match!", expected, actual);
	}
	
	
	
	
	public void testGetIndicators(){
		testEnigma.setWheelOrder(1,2,3);
		testEnigma.setIndicators("AAA");
		assertEquals("getIndicators ain't working","AAA",testEnigma.getIndicators());
		testEnigma.setIndicators("CGF");
		assertEquals("getIndicators ain't working","CGF",testEnigma.getIndicators());
	}
	
	
	public void testRotation() {
//		To see the anomaly in action, fit the rotors in the order 1,2,3 and set the indicators to 'EDV' 
//		(The ring settings don't matter as the turnover mechanism is on the alphabet rings, not the wheel cores). 
//		The first keystroke should move the indicator to 'EEW' and the next should move all 3 rotors, 
//		resulting in an indicator of 'FFX'. 
		
		testEnigma.setWheelOrder(1,2,3);
		testEnigma.setIndicators("EDV");
		testEnigma.encrypt('A');
		assertEquals("Rotation isn't working!","EEW",testEnigma.getIndicators());
		testEnigma.encrypt('B');
		assertEquals("Rotation isn't working!","FFX",testEnigma.getIndicators());
		
	}

}
