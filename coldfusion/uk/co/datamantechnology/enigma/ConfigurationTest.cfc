<cfcomponent displayname="ConfigurationTest" extends="mxunit.framework.TestCase">

	<cfscript>
		variables.TOTAL_NUMBER_OF_ROTORS = 5;
		variables.TOTAL_NUMBER_OF_REFLECTORS = 2;
		variables.NUMBER_OF_LETTERS_IN_ALPHABET = 26;
		variables.ASCII_A = 65;
		variables.cfg = CreateObject("component","Configuration").init();
	</cfscript>
	
	<cffunction access="public" returntype="void" name="testGetRotorWiringsShouldReturnAnArray">
		<cfscript>
			var rotorWirings = cfg.getRotorWirings();
			var b = IsArray(rotorWirings);
			assertTrue(b,"Oops!  Rotors is not an array!");
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testGetRotorWiringsShouldReturnCorrectArrayLength">
		<cfscript>
			var rotorWirings = cfg.getRotorWirings();
			assertTrue(ArrayLen(rotorWirings) == variables.TOTAL_NUMBER_OF_ROTORS,"Oops!  Rotors is not length " & variables.TOTAL_NUMBER_OF_ROTORS);
		</cfscript>
	</cffunction>
	
	
	<cffunction access="public" returntype="void" name="testGetRotorWiringsShouldReturnArrayOfScrambledAlphabet">
		<cfscript>
			var rotorWirings = cfg.getRotorWirings();
			var i = 0;
			for (i=1; i < ArrayLen(rotorWirings); i++){
				assertTrue(rotorWirings[i].length() == NUMBER_OF_LETTERS_IN_ALPHABET,"The " & i & "th rotor does not have " & NUMBER_OF_LETTERS_IN_ALPHABET & " letters!");
			}
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testGetRotorWiringsShouldContainNoDuplicateCharacters">
		<cfscript>
			var rotorWirings = cfg.getRotorWirings();
			var i = 0;
			var j = 0;
			for (i=1; i < ArrayLen(rotorWirings); i++){
				for (j=0; j < NUMBER_OF_LETTERS_IN_ALPHABET; j++){
					c = Chr(j+ASCII_A); 
					assertTrue(rotorWirings[i].indexOf(c) == rotorWirings[i].lastIndexOf(c),c & " is duplicated in rotor " & i & "!");
				}
			}
		</cfscript>
	</cffunction>
		
	<cffunction access="public" returntype="void" name="testGetRotorWiringsContainsAllLettersInAlphabet">
		<cfscript>
			var rotorWirings = cfg.getRotorWirings();
			var i = 0;
			var j = 0;
			for (i=1; i <= ArrayLen(rotorWirings); i++){
				for (j=0; j < NUMBER_OF_LETTERS_IN_ALPHABET; j++){
					c = Chr(j+ASCII_A); 
					assertTrue(rotorWirings[i].indexOf(j+ASCII_A) > -1, c & " not found!");
				}
			}
		</cfscript>
	</cffunction>
	<cffunction access="public" returntype="void" name="testGetTotalNumberOfReflectors">
		<cfscript>
			assertTrue(cfg.getTotalNumberOfReflectors() == TOTAL_NUMBER_OF_REFLECTORS,"Wrong number of reflectors!");
		</cfscript>		
	</cffunction>

	<cffunction access="public" returntype="void" name="testGetTotalNumberOfRotors">
		<cfscript>
			assertTrue(cfg.getTotalNumberOfRotors() == TOTAL_NUMBER_OF_ROTORS,"Wrong number of rotors");
		</cfscript>
	</cffunction>
</cfcomponent>