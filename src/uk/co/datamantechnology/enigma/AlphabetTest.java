package uk.co.datamantechnology.enigma;

import junit.framework.TestCase;

public class AlphabetTest extends TestCase {

	public AlphabetTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGoForwardThroughAlphabet() {
		
		char expected = 'A';
		char actual = Alphabet.goForwardThroughAlphabet('Z');
		assertEquals(expected, actual);

		expected = 'B';
		actual = Alphabet.goForwardThroughAlphabet('A');
		assertEquals(expected, actual);

		expected = 'H';
		actual = Alphabet.goForwardThroughAlphabet('G');
		assertEquals(expected, actual);

		expected = 'Z';
		actual = Alphabet.goForwardThroughAlphabet('Y');
		assertEquals(expected, actual);
		
		expected = 'E';
		actual = Alphabet.goForwardThroughAlphabet('A',4);
		assertEquals(expected, actual);
		
		expected = 'L';
		actual = Alphabet.goForwardThroughAlphabet('A',11);
		assertEquals(expected, actual);
		
	}
	
	public void testGoBackThroughAlphabet() {
		
		char expected = 'Y';
		char actual = Alphabet.goBackThroughAlphabet('Z');
		assertEquals(expected, actual);

		expected = 'B';
		actual = Alphabet.goBackThroughAlphabet('C');
		assertEquals(expected, actual);

		expected = 'H';
		actual = Alphabet.goBackThroughAlphabet('I');
		assertEquals(expected, actual);

		expected = 'Z';
		actual = Alphabet.goBackThroughAlphabet('A');
		assertEquals(expected, actual);
		
		expected = 'W';
		actual = Alphabet.goBackThroughAlphabet('A',4);
		assertEquals(expected, actual);
		
		expected = 'L';
		actual = Alphabet.goBackThroughAlphabet('W',11);
		assertEquals(expected, actual);
		
	}


}
