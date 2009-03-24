<cfcomponent displayname="SteckerTest" extends="mxunit.framework.TestCase">

	<cffunction access="public" returntype="void" name="testencipher">
		<cfscript>	
			var testStecker = CreateObject("component","Stecker");
			var expected = 'A';
			assertEquals(expected,testStecker.encipher('A'),"encipher isn't working!");
			
			expected = 'B';
			assertEquals(expected,testStecker.encipher('B'),"encipher isn't working!");
			
			testStecker.addPairing("AB");
			expected = 'B';
			assertEquals(expected,testStecker.encipher('A'));
			
			expected = 'A';
			assertEquals(expected,testStecker.encipher('B'));
					
			// testing that the Stecker works both ways... i.e. D should encipher to K, and K should encipher to D..
			testStecker.addPairing("DK");
			expected = 'K';
			assertEquals(expected,testStecker.encipher('D'));
			
			expected = 'D';
			assertEquals(expected,testStecker.encipher('K'));
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testAddPairing">
		<cfscript>
			var testStecker = CreateObject("component","Stecker");
			var pairing = "AB";
			var pairings = "";
			pairings &= pairing;
			testStecker.addPairing(pairing);
			assertEquals(pairings,testStecker.getPairings(),"addPairing isn't working!");
		</cfscript>	
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testAddPairingShouldNotAllowDuplicates">
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testAddPairingShouldAllowOnlyTwoCharacterStrings">
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testRemovePairing">
		<cfscript>
			var testStecker = CreateObject("component","Stecker");
			pairings = "";		
			pairings &= "AB";
			
			testStecker.addPairing("AB");
			testStecker.addPairing("CD");
			testStecker.removePairing("CD");
			
			assertEquals(pairings.toString(),testStecker.getPairings(),"removePairing isn't working!");
			
			testStecker.addPairing("EF");
			testStecker.addPairing("GH");
			pairings &= "EF";
			testStecker.removePairing("GH");
			
			assertEquals(pairings.toString(),testStecker.getPairings(),"removePairing isn't working!");
		</cfscript>
	</cffunction>
	
</cfcomponent>