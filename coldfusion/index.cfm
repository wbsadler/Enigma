<cfset enigma = CreateObject("java","enigma.EnigmaMachine")>

<cfdump var="#enigma#">

<cfscript>
	enigma.setWheelOrder(4,2,5);
	enigma.setRingSettings("GMY");
	enigma.setIndicators("DHO");
	enigma.setSteckerPairings("DNGRISKCQXTMPVHYFWBJ");
	messageIndicator = "GXS";
	decodedmessageIndicator = enigma.encrypt(messageIndicator);
</cfscript>

<cfdump var="#decodedmessageIndicator#">

<cfscript>
	plainText = "NQVLTYQFSEWWGJZGQHVSEIXIMYKCNWIEBMBATPPZTDVCUPKAY";
	enigma.setIndicators(decodedmessageIndicator);
	decoded = enigma.encrypt(plainText);
</cfscript>

<cfdump var="#plainText#">
<br /><br />
<cfdump var="#decoded#">

<hr />

<h2>ColdFusion</h2>

<cfset enigma = CreateObject("component","uk.co.datamantechnology.enigma.EnigmaMachine").init()>

<cfdump var="#enigma#">

<cfscript>
	enigma.setWheelOrder(4,2,5);
	enigma.setRingSettings("GMY");
	enigma.setIndicators("DHO");
	enigma.setSteckerPairings("DNGRISKCQXTMPVHYFWBJ");
	messageIndicator = "GXS";
	decodedmessageIndicator = enigma.encipher(messageIndicator);
</cfscript>

<cfdump var="#decodedmessageIndicator#">

<cfscript>
	plainText = "NQVLTYQFSEWWGJZGQHVSEIXIMYKCNWIEBMBATPPZTDVCUPKAY";
	enigma.setIndicators(decodedmessageIndicator);
	decoded = enigma.encipher(plainText);
</cfscript>

<cfdump var="#plainText#">
<br /><br />
<cfdump var="#decoded#">
