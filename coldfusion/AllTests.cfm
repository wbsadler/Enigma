<cfparam name="attributes.output" default="extjs">

<cfscript>  
	testSuite = createObject("component","mxunit.framework.TestSuite").TestSuite();

	testSuite.addAll("uk.co.datamantechnology.enigma.AlphabetTest");
	testSuite.addAll("uk.co.datamantechnology.enigma.RotorTest");
	testSuite.addAll("uk.co.datamantechnology.enigma.EnigmaMachineTest");
	testSuite.addAll("uk.co.datamantechnology.enigma.ConfigurationTest");
	testSuite.addAll("uk.co.datamantechnology.enigma.SteckerTest");
	results = testSuite.run();
	WriteOutput(results.getResultsOutput(attributes.output));
	
</cfscript> 