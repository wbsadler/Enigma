<cfcomponent displayname="Configuration">
	
	<cfscript>
		variables.TOTAL_NUMBER_OF_ROTORS = 5;
		variables.TOTAL_NUMBER_OF_REFLECTORS = 2;
		variables.rotorWirings = ArrayNew(1);
		variables.notches = ArrayNew(1);
		variables.reflectorWirings = ArrayNew(1);
	</cfscript>

	<cffunction access="public" returntype="any" name="init" ouptut="false"> 
		<cfscript>
			variables.rotorWirings[1] = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
			variables.rotorWirings[2] = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
			variables.rotorWirings[3] = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
			variables.rotorWirings[4] = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
			variables.rotorWirings[5] = "VZBRGITYUPSDNHLXAWMJQOFECK";
			
			// for 6-8 of the naval enigma:-
			// TODO: refactor for naval enigma
			/*
			 * rotors[5].loadWiring("JPGVOUMFYQBENHZRDKASXLICTW");
			 * rotors[5].setNotch('Z','M');
			 * rotors[6].loadWiring("NZJHGRCXMYSWBOUFAIVLPEKQDT");
			 * rotors[6].setNotch('Z','M');
			 * rotors[7].loadWiring("FKQHTLXOCBJSPDZRAMEWNIUYGV");
			 * rotors[7].setNotch('Z','M');
			 */
			
			// The 4 rotor naval enigma could only use one of two 'thin' rotors Beta
			// and Gamma in the leftmost position:-
			/*
			 * m_betaRotor.loadWiring("LEYJVCNIXWPBQMDRTAKZGFUHOS");
			 * m_gammaRotor.loadWiring("FSOKANUERHMBTIYCWLQPZXVGJD");
			 */
			
			variables.notches[1] = 'Q';
			variables.notches[2] = 'E';
			variables.notches[3] = 'V';
			variables.notches[4] = 'J';
			variables.notches[5] = 'Z';
			
			// 'B' reflector:-
			variables.reflectorWirings[1] = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
			// 'C' reflector:-
			variables.reflectorWirings[2] = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
			// The 4 rotor naval enigma had different reflectors:-
			/*
			 * 'B' reflector:-
			 * m_reflectors[2].loadWiring("ENKQAUYWJICOPBLMDXZVFTHRGS");
			 * 
			 * 'C' reflector:-
			 * m_reflectors[3].loadWiring("RDOBJNTKVEHMLFCWZAXGYIPSUQ");
			 */
			return this;
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="any" name="getRotorWirings">
		<cfscript>
			return variables.rotorWirings;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="any" name="getNotches">
		<cfscript>
			return variables.notches;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="any" name="getReflectorWirings">
		<cfscript>
			return variables.reflectorWirings;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="any" name="getTotalNumberOfReflectors">
		<cfscript>		
			return variables.TOTAL_NUMBER_OF_REFLECTORS;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="any" name="getTotalNumberOfRotors">
		<cfscript>
			return variables.TOTAL_NUMBER_OF_ROTORS;
		</cfscript>
	</cffunction>
	
</cfcomponent>