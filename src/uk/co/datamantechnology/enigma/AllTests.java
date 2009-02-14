package uk.co.datamantechnology.enigma;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for uk.co.datamantechnology.enigma");
		//$JUnit-BEGIN$
		suite.addTestSuite(AlphabetTest.class);
		suite.addTestSuite(RotorTest.class);
		suite.addTestSuite(EnigmaMachineTest.class);
		suite.addTestSuite(ConfigurationTest.class);
		suite.addTestSuite(SteckerTest.class);
		//$JUnit-END$
		return suite;
	}

}
