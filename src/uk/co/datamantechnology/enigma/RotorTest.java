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

		testRotor.setCurrentPosition('D');

		expected = 'E';
		actual = testRotor.encrypt('A');
		assertEquals(expected, actual);

		expected = 'Q';
		actual = testRotor.encrypt('G');
		assertEquals(expected, actual);

		testRotor.setCurrentPosition('A');

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
		
		testRotor.setCurrentPosition('G');
		testRotor.advance();
		expected = 'Z';
 		actual = testRotor.decrypt('V');
		assertEquals(expected, actual);

	}

	
	public void testGetIndicator(){
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		
		testRotor.setCurrentPosition('D');
		assertEquals("getIndicator ain't working!",'D',testRotor.getIndicator());
	}
	
	public void testIsAtNotchPosition(){
		Rotor testRotor = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
		for (int i = 0; i < 26; i++){
			if (i == 21){
				assertTrue("isAtNotchPosition is faulty! (" + i + ")",testRotor.isAtNotchPosition());
			}else{
				assertTrue("isAtNotchPosition is faulty! (" + i + ")",!testRotor.isAtNotchPosition());	
			}
			testRotor.advance();
		}
	}
	                   
	
}
