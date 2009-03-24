<cfcomponent displayname="Stecker">

	<cfscript>
		variables.pairings = "";
	</cfscript>

	<cffunction access="public" returntype="Stecker" name="init" output="false">
		<cfscript>
			return this;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="addPairing" output="false">
		<cfargument type="string" name="pairing" required="true">
		<cfscript>
			// TODO: check that neither of the letters already exists in other
			pairings &= arguments.pairing;		
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="removePairing" output="false">
		<cfargument type="string" name="pairing" required="true">
		<cfscript>
			pairings =  ReplaceNoCase(pairings,arguments.pairing,"");
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="setPairings" output="false">
		<cfargument type="String" name="steckerPairings" required="true">
		<cfscript>
			this.pairings = arguments.steckerPairings;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="getPairings" output="false">
		<cfscript>
			return pairings.toString();
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="encipher" output="false">
		<cfargument type="String" name="clearText" required="true">
		<cfscript>
			var position = 0;
			var encipheredCharacter = clearText;
			// loop over pairings to see if the letter is in a pairing
			if (isSteckered(encipheredCharacter)) {
				position = pairings.toString().indexOf(encipheredCharacter);
				// if it's even swap with previous
				if (position % 2 == 1) {
					encipheredCharacter = pairings.charAt(position - 1);
				} else {
					encipheredCharacter = pairings.charAt(position + 1);
				}
			}
			return encipheredCharacter;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="boolean" name="isSteckered" output="false">
		<cfargument type="string" name="letter" required="true">
		<cfscript>
			return pairings.toString().indexOf(letter) != -1;
		</cfscript>
	</cffunction>
	
</cfcomponent>