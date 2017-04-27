<cfcomponent>
	<cfscript>
		this.name = "enigmaweb";
	</cfscript>

	<cffunction access="public" returntype="void" name="onRequest">
		<cfargument name="targetPage" type="String" required="true" >
		<cfscript>
			if (!StructKeyExists(variables,"attributes")){
				attributes = StructNew();
			}
			StructAppend(attributes,url,true);
			StructAppend(attributes,form,true);
		</cfscript>
		<cfinclude template="#arguments.targetPage#">
	</cffunction>
</cfcomponent>
