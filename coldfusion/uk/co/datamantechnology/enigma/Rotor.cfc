<cfcomponent displayname="Rotor">

	<cfscript>
		variables.NUMBER_OF_LETTERS_IN_ALPHABET = 26;
		variables.wiring = ""; // scrambled alphabet
		variables.currentPosition = 0; // the position relative to the wiring
		variables.notch = ""; // the letter next to the notch 
		variables.ringSetting = 0; // ringStellung
		variables.Alphabet = CreateObject("component","Alphabet").init(); 	
	</cfscript>

	<cffunction name="init">
		<cfargument type="numeric" name="rotorNumber" required="true">
		<cfscript>	
			var cfg = CreateObject("component","Configuration").init();
			var notches = cfg.getNotches();
			var rotorWirings = cfg.getRotorWirings();
			setNotch(notches[arguments.rotorNumber]);
			setWiring(rotorWirings[arguments.rotorNumber]);
			setIndicator('A');
			setRingSetting('A');
			return this;
		</cfscript>
	</cffunction>
	
	<cffunction name="setUp">
		<cfargument type="string" name="wiring" required="true">
		<cfargument type="string" name="notch" required="true">
		<cfscript>
			setWiring(wiring);
			setNotch(notch);
			setRingSetting('A');
			setIndicator('A');
			return this;
		</cfscript>
	</cffunction>
		
	<cffunction name="getCurrentPosition">
		<cfscript>
			return variables.currentPosition;
		</cfscript>
	</cffunction>

	<cffunction name="setCurrentPosition">
		<cfargument type="any" name="position" required="true">
		<cfscript>
			var currentLetter = "";
			if (IsNumeric(arguments.position)){
				variables.currentPosition = arguments.position;
			}else{
				currentLetter = variables.Alphabet.goBackwards(arguments.position,getRingSetting());
				setCurrentPosition(variables.Alphabet.toLetterIndex(currentLetter));
			}			
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="any" name="setRingSetting">
		<cfargument type="any" name="setting" required="true">
		<!--- <cftrace text="settingRingSetting: #arguments.setting#"> --->
		<cfscript>
			if (IsNumeric(arguments.setting)){
				variables.ringSetting = arguments.setting;			
			}else{
				setRingSetting(Alphabet.toLetterIndex(arguments.setting));
			}
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="numeric" name="getRingSetting">
		<cfscript>
			return variables.ringSetting;
		</cfscript>
	</cffunction>
		
	<cffunction access="public" returntype="string" name="getIndicator">
		<cfscript>
			var currentLetter = Alphabet.toLetter(getCurrentPosition());
			return UCase(Alphabet.goForwards(currentLetter, getRingSetting()));
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="string" name="getWiring">
		<cfscript>
			return wiring;
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="void" name="setWiring">
		<cfargument type="string" name="wiring">
		<cfscript>
			variables.wiring = wiring;
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="void" name="setNotch">
		<cfargument type="string" name="notch" required="true">
		<cfscript>
			variables.notch = arguments.notch;
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="string" name="getNotch">
		<cfscript>
			return variables.notch;
		</cfscript>
	</cffunction>
		
	<cffunction access="public" returntype="string" name="getAdjustedNotch">
		<cfscript>
			return Alphabet.goBackwards(getNotch(),getRingSetting());
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="numeric" name="getAdjustedNotchPosition">
		<cfscript>
			return Alphabet.toLetterIndex(getAdjustedNotch());
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="encipher">
		<cfargument type="string" name="clearCharacter" required="true">
		<cfscript>
			var letterIndex = Alphabet.toLetterIndex(arguments.clearCharacter);
			// encipher the character taking into account the current position of the rotor  
			var encipheredCharacter = getLetterAtPosition(letterIndex);
			// take care of the offset
			if (getCurrentPosition() > 0) { 
				letterIndex = Alphabet.toLetterIndex(encipheredCharacter);
				letterIndex = Alphabet.goBackwards(letterIndex,getCurrentPosition()); 
				encipheredCharacter = Alphabet.toLetter(letterIndex);
			} 
			return encipheredCharacter;
		</cfscript>
	</cffunction>

	<cffunction  access="public" returntype="any" name="decipher">
		<cfargument type="string" name="encipheredLetter" required="true">
		<cfscript>
			// get position of letter taking into account the current position of the rotor
			var decipheredLetterIndex = getPositionOfLetter(encipheredLetter);
			var decipheredLetter = Alphabet.toLetter(decipheredLetterIndex);
			// re-adjust output for any offset
			if (getCurrentPosition() > 0) {
				decipheredLetter = Alphabet.goBackwards(decipheredLetter, getCurrentPosition()); 
			}		
			return decipheredLetter;
		</cfscript>
	</cffunction>
	
	<cffunction access="private" returntype="string" name="getLetterAtPosition">
		<cfargument type="numeric" name="letterIndex" required="true">
		<cfscript>
			var wiringIndex = (arguments.letterIndex + getCurrentPosition()) % variables.NUMBER_OF_LETTERS_IN_ALPHABET;
			return variables.wiring.charAt(wiringIndex);
		</cfscript>
	</cffunction>

	<cffunction  access="public" returntype="numeric" name="getPositionOfLetter">
		<cfargument type="String" name="character" required="true">
		<cfscript>
			// get the offset character
			var offsetCharacter = Alphabet.goForwards(character, getCurrentPosition());
			var wiringIndex = variables.wiring.indexOf(offsetCharacter);
			return wiringIndex;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="advance">
		<cfscript>
			setCurrentPosition((getCurrentPosition() + 1) % variables.NUMBER_OF_LETTERS_IN_ALPHABET);
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void"  name="goBack">
		<cfscript>
			setCurrentPosition((getCurrentPosition() - 1 + variables.NUMBER_OF_LETTERS_IN_ALPHABET) % variables.NUMBER_OF_LETTERS_IN_ALPHABET);
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="boolean" name="isAtNotchPosition">
		<cfscript>
			return (getIndicator() == getAdjustedNotch());
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="void" name="setIndicator">
		<cfargument type="String" name="letter" required="true">
		<cfscript>
			setCurrentPosition(letter);
		</cfscript>
	</cffunction>

</cfcomponent>