<cfcomponent displayname="RotorTest" extends="mxunit.framework.TestCase">
	 
	<cffunction access="public" returntype="void" name="testThatSetWiringAcceptsNoStringWithDuplicates">
		<cfscript>
			// TODO: implement test
		</cfscript>
	</cffunction>
	
	
	<cffunction access="public" returntype="void" name="testSetCurrentPosition">
		<cfscript>
			testRotor = CreateObject("component","Rotor").setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
			testRotor.setCurrentPosition(0);
			assertTrue(testRotor.getCurrentPosition() == 0);
			
			testRotor.setCurrentPosition(1);
			assertTrue(testRotor.getCurrentPosition() == 1);
		</cfscript>		
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testGetCurrentPosition">
		<cfscript>
			testRotor = CreateObject("component","Rotor").setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
			assertTrue(testRotor.getCurrentPosition()>-1);
			assertTrue(testRotor.getCurrentPosition()<26);
		</cfscript>		
	</cffunction>

	<cffunction access="public" returntype="void" name="testencipher">
		<cfscript>
			// Rotor I - rotors[0]
			var testRotor = CreateObject("component","Rotor").setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
	
			var expected = 'B';
			var actual = testRotor.encipher('A');
			assertEquals(expected, actual);
	
			expected = 'D';
			actual = testRotor.encipher('B');
			assertEquals(expected, actual);
	
			expected = 'O';
			actual = testRotor.encipher('Z');
			assertEquals(expected, actual);
	
			testRotor.setIndicator('D');
	
			expected = 'E';
			actual = testRotor.encipher('A');
			assertEquals(expected, actual);
	
			expected = 'Q';
			actual = testRotor.encipher('G');
			assertEquals(expected, actual);
	
			testRotor.setIndicator('A');
	
			testRotor.advance();
			expected = 'C';
			actual = testRotor.encipher('A');
			assertEquals(expected, actual);
	
			testRotor.advance();
			expected = 'F';
			actual = testRotor.encipher('B');
			assertEquals(expected, actual);
	
			testRotor.advance();
			expected = 'I';
			actual = testRotor.encipher('C');
			assertEquals(expected, actual);
	
			testRotor.advance();
			expected = 'L';
			actual = testRotor.encipher('D');
			assertEquals(expected, actual);
		</cfscript>
	</cffunction>

	<cffunction access="public" returntype="void" name="testdecipher">
		<cfscript>
			// Rotor I - rotors[0]
			var testRotor = CreateObject("component","Rotor").setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
	
			var expected = 'A';
			var actual = testRotor.decipher('B');
			assertEquals(expected, actual);
	
			expected = 'L';
			actual = testRotor.decipher('V');
			assertEquals(expected, actual);
	
			expected = 'Q';
			actual = testRotor.decipher('I');
			assertEquals(expected, actual);
	
			testRotor.advance();
			expected = 'B';
			actual = testRotor.decipher('E');
			assertEquals(expected, actual);
	
			testRotor.advance();
			expected = 'J';
			actual = testRotor.decipher('T');
			assertEquals(expected, actual);
	
			testRotor.advance();
			expected = 'E';
			actual = testRotor.decipher('M');
			assertEquals(expected, actual);
			
			testRotor.setIndicator('G');
			testRotor.advance();
			expected = 'Z';
	 		actual = testRotor.decipher('V');
			assertEquals(expected, actual);
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testdecipherWithRingSetting">
		<cfscript>
			var expected = "";
			var actual = "";
			var testRotor = CreateObject("component","Rotor").setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
			testRotor.setRingSetting('B');
			testRotor.setIndicator('A');
			expected = 'U';
			actual = testRotor.decipher('B');
			assertEquals(expected, actual,"testdecipherWithRingSetting");
			
			expected = 'C';
			actual = testRotor.decipher('E');
			assertEquals(expected, actual);
		</cfscript>
	</cffunction>

	
	<cffunction access="public" returntype="void" name="testGetIndicator">
		<cfscript>
			testRotor = CreateObject("component","Rotor").setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');		
			testRotor.setIndicator('D');
			assertEquals('D',testRotor.getIndicator(),"getIndicator ain't working!");
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testIsAtNotchPosition">
		<cfscript>
			var i = 0;
			var testRotor = CreateObject("component","Rotor").setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
			assertEquals('V',testRotor.getNotch(),"Notch has not been set to initial value!");
			
			for (i = 0; i < 26; i++){
				if (i == 21){
					assertTrue(testRotor.isAtNotchPosition(),"isAtNotchPosition is faulty! (" & i & ")");
				}else{
					assertTrue(!testRotor.isAtNotchPosition(),"isAtNotchPosition is faulty(" & i & ")");	
				}
				testRotor.advance();
			}
			// the ring setting should not have any bearing on the notch position
			testRotor.setRingSetting('B');
			testRotor.setIndicator('A');
			for (i = 0; i < 26; i++){
				if (i == 21){
					assertTrue(testRotor.isAtNotchPosition(),"isAtNotchPosition is faulty! (" & i & ")");
				}else{
					assertTrue(!testRotor.isAtNotchPosition(),"isAtNotchPosition is faulty!(" & i & ")");	
				}
				testRotor.advance();
			}
		</cfscript>
	</cffunction>
	                   
	<cffunction access="public" returntype="void" name="testSetRingSetting">
		<cfscript>
			testRotor = CreateObject("component","Rotor").setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
			testRotor.setRingSetting('A');
			testRotor.setIndicator('A');
			assertEquals('A',testRotor.getIndicator(),"setRingSetting isn't working!");
			
			testRotor.setRingSetting('B');
			testRotor.setIndicator('A');
			assertEquals('A',testRotor.getIndicator(),"setRingSetting isn't working!");
			assertEquals('1',testRotor.getRingSetting(),"getRingSetting isn't working properly!");
			assertEquals('P',testRotor.encipher('A'),"encipher isn't responding to ringSetting properly!");		
			
			
			testRotor.setRingSetting('F');
			testRotor.setIndicator('A');
			assertEquals('A',testRotor.getIndicator(),"setRingSetting isn't working!");
			assertEquals('5',testRotor.getRingSetting(),"getRingSetting isn't working properly!");		
			
			testRotor.setRingSetting('B');
			testRotor.setIndicator('A');
			assertEquals('A',testRotor.getIndicator(),"setRingSetting isn't working!");
			assertEquals('C',testRotor.encipher('B'),"encipher isn't responding to ringSetting properly!");
		</cfscript> 
	</cffunction>
</cfcomponent>