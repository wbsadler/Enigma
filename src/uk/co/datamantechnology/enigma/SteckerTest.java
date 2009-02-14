package uk.co.datamantechnology.enigma;

import junit.framework.TestCase;

public class SteckerTest extends TestCase {
	
	public void testEncrypt(){
		Stecker testStecker = new Stecker() ;
		char expected = 'A';
		assertEquals("Encrypt isn't working!",expected,testStecker.encrypt('A'));
		
		expected = 'B';
		assertEquals("Encrypt isn't working!",expected,testStecker.encrypt('B'));
		
		testStecker.addPairing("AB");
		expected = 'B';
		assertEquals("Encrypt isn't working!",expected,testStecker.encrypt('A'));
		
		expected = 'A';
		assertEquals("Encrypt isn't working!",expected,testStecker.encrypt('B'));
				
//		TODO: test that the Stecker works both ways... i.e. D should encrypt to K, and K should encrypt to D?..
		testStecker.addPairing("DK");
		expected = 'K';
		assertEquals("Encrypt isn't working!",expected,testStecker.encrypt('D'));
		
	}
	
	public void testAddPairing(){
		Stecker testStecker = new Stecker();
		String pairing = "AB";
		StringBuilder pairings = new StringBuilder();
		pairings.append(pairing);
		testStecker.addPairing(pairing);
		assertEquals("addPairing isn't working!",pairings.toString(),testStecker.getPairings());	
	}
	
	public void testAddPairingShouldNotAllowDuplicates(){
				
	}
	
	public void testAddPairingShouldAllowOnlyTwoCharacterStrings(){

	}
	
	public void testRemovePairing(){
		Stecker testStecker = new Stecker();
		StringBuilder pairings = new StringBuilder();		
		pairings.append("AB");
		
		testStecker.addPairing("AB");
		testStecker.addPairing("CD");
		testStecker.removePairing("CD");
		
		assertEquals("removePairing isn't working!",pairings.toString(),testStecker.getPairings());
		
		testStecker.addPairing("EF");
		testStecker.addPairing("GH");
		pairings.append("EF");
		testStecker.removePairing("GH");
		
		assertEquals("removePairing isn't working!",pairings.toString(),testStecker.getPairings());
		
		
	}
	
}
