<cfcomponent displayname="Reflector">
	
	<cfscript>
		variables.cfg = CreateObject("component","Configuration").init();
		variables.totalNumberOfReflectors = 2; // total number of reflectors available to operators
		variables.wiring = "";
	</cfscript>
	
	<cffunction access="public" returntype="Reflector" name="init" output="false">
		<cfargument type="numeric" name="reflectorNumber" required="true">
		<cfscript>
			var reflectorWirings = cfg.getReflectorWirings(); 
			loadWiring(reflectorWirings[arguments.reflectorNumber]);
			return this;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="numeric" name="getTotalNumberOfReflectors" output="false">
		<cfscript>
			return variables.totalNumberOfReflectors;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="loadWiring" output="false">
		<cfargument type="string" name="wiring" required="true">
		<cfscript>
			variables.wiring = arguments.wiring;
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="string" name="reflect" output="false">
		<cfargument type="string" name="entryLetter" required="true">
		<cfscript>
			letterIndex = Asc(entryLetter) - 65;
			return variables.wiring.charAt(letterIndex);
		</cfscript>
	</cffunction>	

</cfcomponent>