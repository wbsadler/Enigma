<cfcomponent displayname="AllTests" extends="mxunit.framework.TestSuite">

	<cffunction name="suite">
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
	</cffunction>

</cfcomponent>
