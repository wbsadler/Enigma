<cfcomponent displayname="EnigmaMachine">
	
	<cfscript>
		variables.numberOfRotors = 3; // e.g. 3 = this a three rotor enigma machine!
		variables.wheelOrder = ArrayNew(1); // assuming a 3 rotor set up, from left to right we have 3,2,1
		variables.Reflector = "";
		variables.Alphabet = CreateObject("component","Alphabet").init();
	</cfscript>
	
	<cffunction access="public" returntype="EnigmaMachine" name="init">
		<cfscript>
			var i = 0;
			for (i=1; i <= variables.numberOfRotors; i++){
				variables.rotors[i] = CreateObject("component","Rotor").init(i);
			}			
			setWheelOrder(1,2,3);
			setRingSettings("AAA");
			variables.Reflector = CreateObject("component","Reflector").init(1); // choosing the B reflector;
			variables.stecker = CreateObject("component","Stecker").init();
			return this;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="setRingSettings" output="false">
		<cfargument type="string" name="ringSettings" required="false">
		<cfscript>
			var i=0;
			for (i=1; i <= Len(arguments.ringSettings); i++){
				setRingSetting(Len(arguments.ringSettings)-i+1,Mid(arguments.ringSettings,i,1));
			}		
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="getRingSettings" output="false">
		<cfscript>
			var ringSettings = "";
			var i=0;
			for (i=variables.numberOfRotors; i > 0; i--){
				ringSettings &= variables.Alphabet.toLetter(variables.rotors[i].getRingSetting()); 
			}		
			return ringSettings;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="setRingSetting" output="true">
		<cfargument type="numeric" name="rotorNumber" required="true">
		<cfargument type="string" name="letter" required="true">
		<cfscript>
			variables.rotors[arguments.rotorNumber].setRingSetting(arguments.letter);
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="setSteckerPairings" output="false">
		<cfargument type="String" name="pairings" required="true">
		<cfscript>
			variables.stecker.setPairings(pairings);
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="getSteckerPairings" output="false">
		<cfscript>
			return variables.stecker.getPairings();
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="setWheelOrder" output="false">
		<cfargument type="numeric" name="rotor1" required="true">
		<cfargument type="numeric" name="rotor2" required="true">
		<cfargument type="numeric" name="rotor3" required="true">
		<cfscript>
			// TODO: refactor to be able to deal with more any number of rotors
			selectRotor(3, rotor1);
			selectRotor(2, rotor2);
			selectRotor(1, rotor3);
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="getWheelOrder" output="false">
		<cfscript>
			var i = 0;
			var wheelOrderString = "";
			for (i=variables.numberOfRotors; i > 0; i--){
				wheelOrderString &= variables.wheelOrder[i];
			}
			return wheelOrderString;
		</cfscript>
	</cffunction>
	
	<cffunction access="private" returntype="void" name="selectRotor" output="false">
		<cfargument type="numeric" name="wheelOrderPosition" required="true">
		<cfargument type="numeric" name="rotorNumber" required="true">
		<cfscript>	
			variables.wheelOrder[wheelOrderPosition] = rotorNumber;
			variables.rotors[wheelOrderPosition] = CreateObject("component","Rotor").init(rotorNumber);
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="moveRotor" output="false">
		<cfargument type="numeric" name="rotor" required="true">
		<cfargument type="string" name="direction" required="true">
		<cfscript>
			if (direction == "up") {
				moveRotorUp(rotor);
			} else {
				moveRotorDown(rotor);
			}
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="setIndicators" output="false">
		<cfargument type="string" name="indicators" required="true">
		<cfscript>
			var i = 0;
			for (i = 0; i < getNumberOfRotors(); i++) {
				setIndicator(getNumberOfRotors() - i, indicators.charAt(i));
			}
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="getIndicators" output="false">
		<cfscript>
			var returnString = "";
			var i = 0;
			for (i = getNumberOfRotors(); i > 0 ; i--) {
				returnString&= getIndicator(i);
			}
			return returnString;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="encipher" output="false">
		<cfargument type="string" name="plainText" required="true">
		<cfscript>
			var i=0;
			// WriteOutput("Enciphering: " & plainText & "<br /><br />");
			encypheredText = "";
			for (i=1; i<=Len(plainText); i++){
				encypheredText &= encipherCharacter(Mid(plainText,i,1));			
			}
			// WriteOutput("Encyphered text : " & encypheredText.toString() & "<br /><br />");
			return encypheredText.toString();
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="encipherCharacter" output="false">
		<cfargument type="string" name="clearText" required="true">
		<cfscript>
			var i = 0;
			encypheredChar = arguments.clearText;
			// WriteOutput(arguments.clearText);
			// go through the stecker		
			encypheredChar = variables.stecker.encipher(clearText);
			// WriteOutput(encypheredChar);
			// move the rotors on one
			advanceRotors();
			// go through the rotors in acsending order (encypher)
			for (i = 1; i <= getNumberOfRotors(); i++) {
				encypheredChar = variables.rotors[i].encipher(encypheredChar);
			}
			// go through the reflector
			encypheredChar = reflector.reflect(encypheredChar);
	
			// go back through the rotors in descending order (decypher)
			for (i = getNumberOfRotors(); i > 0; i--) {
				encypheredChar = variables.rotors[i].decipher(encypheredChar);
			}
			
			// go back trhough the Stecker
			encypheredChar = variables.stecker.encipher(encypheredChar);
	
			// WriteOutput(clearText & " " & encypheredChar & " " & getIndicators() & "<br/>");
			// return enciphered character
			return encypheredChar;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="advanceRotors" output="false">
		<cfscript>
			var i = 0;
			var rotorsToAdvance = ArrayNew(1);
			// check for notch positions
			for (i = 1; i <= getNumberOfRotors(); i++) {
				// right hand rotor moves on one postion every time;
				if (i == 1){
					ArrayAppend(rotorsToAdvance,i);
				// if preceding rotor is at notch position then this rotor moves on
				}else if (variables.rotors[i - 1].isAtNotchPosition()){
					ArrayAppend(rotorsToAdvance,i);
				} else if (variables.rotors[i].isAtNotchPosition() && i < (getNumberOfRotors())){
					ArrayAppend(rotorsToAdvance,i);	
				}
			}
			if (ArrayLen(rotorsToAdvance) > 0){ 
				for (i = 1; i <= ArrayLen(rotorsToAdvance); i++) {
					variables.rotors[rotorsToAdvance[i]].advance();
				}
			}
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="moveRotorUp" output="false">
		<cfargument type="numeric" name="rotor" required="true">
		<cfscript>
			variables.rotors[arguments.rotor].advance();
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="moveRotorDown" output="false">
		<cfargument type="numeric" name="rotor" required="true">
		<cfscript>
			variables.rotors[arguments.rotor].goBack();
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="setIndicator" output="false">
		<cfargument type="numeric" name="rotor" required="true">
		<cfargument type="string" name="letter" required="true">
		<cfscript>
			variables.rotors[arguments.rotor].setIndicator(arguments.letter);		
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="getIndicator" output="false">
		<cfargument type="numeric" name="rotor" required="true">
		<cfscript>
			return variables.rotors[arguments.rotor].getIndicator();
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="setNumberOfRotors" output="false">
		<cfargument type="numeric" name="numberOfRotors" required="true">
		<cfscript>
			variables.numberOfRotors = arguments.numberOfRotors;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="numeric" name="getNumberOfRotors" output="false">
		<cfscript>
			return variables.numberOfRotors;
		</cfscript>
	</cffunction>
	
</cfcomponent>