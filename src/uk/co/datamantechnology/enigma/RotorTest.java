package uk.co.datamantechnology.enigma;

import junit.framework.TestCase;

public class RotorTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void ensureThatSetWiringAcceptsNoStringWithDuplicates() {
		// TODO: implement test
	}
	
	public void testGetCurrentPosition(){
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		assertTrue(testRotor.getCurrentPosition()>-1);
		assertTrue(testRotor.getCurrentPosition()<26);		
	}

	public void testEncrypt() {
		// Rotor I - rotors[0]
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		char expected, actual;

		expected = 'B';
		actual = testRotor.encrypt('A');
		assertEquals(expected, actual);

		expected = 'D';
		actual = testRotor.encrypt('B');
		assertEquals(expected, actual);

		expected = 'O';
		actual = testRotor.encrypt('Z');
		assertEquals(expected, actual);

		testRotor.setIndicator('D');

		expected = 'E';
		actual = testRotor.encrypt('A');
		assertEquals(expected, actual);

		expected = 'Q';
		actual = testRotor.encrypt('G');
		assertEquals(expected, actual);

		testRotor.setIndicator('A');

		testRotor.advance();
		expected = 'C';
		actual = testRotor.encrypt('A');
		assertEquals(expected, actual);

		testRotor.advance();
		expected = 'F';
		actual = testRotor.encrypt('B');
		assertEquals(expected, actual);

		testRotor.advance();
		expected = 'I';
		actual = testRotor.encrypt('C');
		assertEquals(expected, actual);

		testRotor.advance();
		expected = 'L';
		actual = testRotor.encrypt('D');
		assertEquals(expected, actual);
	}

	public void testDecrypt() {
		// Rotor I - rotors[0]
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		char expected, actual;

		expected = 'A';
		actual = testRotor.decrypt('B');
		assertEquals(expected, actual);

		expected = 'L';
		actual = testRotor.decrypt('V');
		assertEquals(expected, actual);

		expected = 'Q';
		actual = testRotor.decrypt('I');
		assertEquals(expected, actual);

		testRotor.advance();
		expected = 'B';
		actual = testRotor.decrypt('E');
		assertEquals(expected, actual);

		testRotor.advance();
		expected = 'J';
		actual = testRotor.decrypt('T');
		assertEquals(expected, actual);

		testRotor.advance();
		expected = 'E';
		actual = testRotor.decrypt('M');
		assertEquals(expected, actual);
		
		testRotor.setIndicator('G');
		testRotor.advance();
		expected = 'Z';
 		actual = testRotor.decrypt('V');
		assertEquals(expected, actual);
		
		
	}
	
	public void testDecryptWithRingSetting(){
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		
		testRotor.setRingSetting('B');
		testRotor.setIndicator('A');
		char expected = 'U';
		char actual = testRotor.decrypt('B');
		assertEquals("testDecryptWithRingSetting",expected, actual);
		
		expected = 'C';
		actual = testRotor.decrypt('E');
		assertEquals(expected, actual);
		
	}

	
	public void testGetIndicator(){
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');		
		testRotor.setIndicator('D');
		assertEquals("getIndicator ain't working!",'D',testRotor.getIndicator());
	}
	
	public void testIsAtNotchPosition(){
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		assertEquals("Notch has not been set to initial value!",'V',testRotor.getNotch());
		for (int i = 0; i < 26; i++){
			if (i == 21){
				assertTrue("isAtNotchPosition is faulty! (" + i + ")",testRotor.isAtNotchPosition());
			}else{
				assertTrue("isAtNotchPosition is faulty(" + i + ")",!testRotor.isAtNotchPosition());	
			}
			testRotor.advance();
		}
		testRotor.setRingSetting('F');
		testRotor.setIndicator('A');
		for (int i = 0; i < 26; i++){
			if (i == 21){
				assertTrue("isAtNotchPosition is faulty! (" + i + ")",testRotor.isAtNotchPosition());
			}else{
				assertTrue("isAtNotchPosition is faulty!(" + i+ ")",!testRotor.isAtNotchPosition());	
			}
			testRotor.advance();
		}
	}
	                   
	public void testSetRingSetting(){
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		testRotor.setRingSetting('A');
		testRotor.setIndicator('A');
		assertEquals("setRingSetting isn't working!",'A',testRotor.getIndicator());
		testRotor.setRingSetting('B');
		testRotor.setIndicator('A');
		assertEquals("setRingSetting isn't working!",'A',testRotor.getIndicator());
		assertEquals("encrypt isn't responding to ringSetting properly!",'P',testRotor.encrypt('A'));		
		testRotor.setIndicator('A');
		assertEquals("setRingSetting isn't working!",'A',testRotor.getIndicator());
		assertEquals("encrypt isn't responding to ringSetting properly!",'C',testRotor.encrypt('B'));
		
		// TODO: test that the notch position is revised on the ring setting! 
	}
}
