<cfcomponent displayname="Alphabet">

	<cffunction access="public" returntype="Alphabet" name="init" output="false">
		<cfscript>
			variables.NUMBER_OF_LETTERS_IN_ALPHABET = 26;
			variables.ASCII_A = 65;
			return this;
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="any" name="goForwards" output="false">
		<cfargument type="any" name="from" required="true">
		<cfargument type="numeric" name="numberOfLetters" default="1">
		<cfscript>
			var letterIndex = 0;
			if (IsNumeric(arguments.from)){
				return (variables.NUMBER_OF_LETTERS_IN_ALPHABET + arguments.from + arguments.numberOfLetters) % variables.NUMBER_OF_LETTERS_IN_ALPHABET;
			}else{
				letterIndex = ((toLetterIndex(arguments.from) + arguments.numberOfLetters + variables.NUMBER_OF_LETTERS_IN_ALPHABET) % variables.NUMBER_OF_LETTERS_IN_ALPHABET);
				return toLetter(letterIndex);
			}
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="any" name="goBackwards">
		<cfargument type="any" name="from" required="true">
		<cfargument type="numeric" name="numberOfLetters" default="1">
		<cfscript>
			return goForwards(arguments.from,-arguments.numberOfLetters);
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="numeric" name="toLetterIndex">
		<cfargument type="string" name="letter" required="true">
		<cfscript>
			return Asc(arguments.letter) - variables.ASCII_A;
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="string" name="toLetter">
		<cfargument type="numeric" name="letterIndex" required="true">
		<cfscript>
			return Chr(arguments.letterIndex + variables.ASCII_A);
		</cfscript>
	</cffunction>

</cfcomponent>
