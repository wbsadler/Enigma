<cfparam name="attributes.output" default="extjs">

<cfscript>
	testSuite = createObject("component","mxunit.framework.TestSuite").TestSuite();
	testSuite.addAll("enigma.AlphabetTest");
	testSuite.addAll("enigma.RotorTest");
	testSuite.addAll("enigma.EnigmaMachineTest");
	testSuite.addAll("enigma.ConfigurationTest");
	testSuite.addAll("enigma.SteckerTest");
	results = testSuite.run();
	WriteOutput(results.getResultsOutput(attributes.output));
</cfscript>
