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
		char actual = Alphabet.goForwards('Z');
		assertEquals(expected, actual);

		expected = 'B';
		actual = Alphabet.goForwards('A');
		assertEquals(expected, actual);

		expected = 'H';
		actual = Alphabet.goForwards('G');
		assertEquals(expected, actual);

		expected = 'Z';
		actual = Alphabet.goForwards('Y');
		assertEquals(expected, actual);
		
		expected = 'E';
		actual = Alphabet.goForwards('A',4);
		assertEquals(expected, actual);
		
		expected = 'L';
		actual = Alphabet.goForwards('A',11);
		assertEquals(expected, actual);
		
	}
	
	public void testGoBackThroughAlphabet() {
		
		char expected = 'Y';
		char actual = Alphabet.goBackwards('Z');
		assertEquals(expected, actual);

		expected = 'B';
		actual = Alphabet.goBackwards('C');
		assertEquals(expected, actual);

		expected = 'H';
		actual = Alphabet.goBackwards('I');
		assertEquals(expected, actual);

		expected = 'Z';
		actual = Alphabet.goBackwards('A');
		assertEquals(expected, actual);
		
		expected = 'W';
		actual = Alphabet.goBackwards('A',4);
		assertEquals(expected, actual);
		
		expected = 'L';
		actual = Alphabet.goBackwards('W',11);
		assertEquals(expected, actual);
		
		expected = 'A';
		actual = Alphabet.goBackwards('A',0);
		assertEquals(expected, actual);
		
	}

	public void testToLetterIndex(){
		int expected = 0;
		int actual = Alphabet.toLetterIndex('A');
		assertEquals(expected, actual);
		
		expected = 1;
		actual = Alphabet.toLetterIndex('B');
		assertEquals(expected, actual);
		
		expected = 25;
		actual = Alphabet.toLetterIndex('Z');
		assertEquals(expected, actual);
		
	}
	
	public void testToLetter(){
		char expected = 'A';
		char actual = Alphabet.toLetter(0);
		assertEquals(expected,actual);
		
		expected = 'M';
		actual = Alphabet.toLetter(12);
		assertEquals(expected,actual);
		
		expected = 'Z';
		actual = Alphabet.toLetter(25);
		assertEquals(expected,actual);
	}

	
	public void testGoForwards(){
		int expected = 1;
		int actual = Alphabet.goForwards(0,1);
		assertEquals(expected,actual);
		
		expected = 3;
		actual = Alphabet.goForwards(25,4);
		assertEquals(expected,actual);
		
		expected = 24;
		actual = Alphabet.goForwards(0,-2);
		assertEquals(expected,actual);
	}
	
	
}
