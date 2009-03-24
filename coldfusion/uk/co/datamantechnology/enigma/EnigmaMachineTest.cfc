<cfcomponent displayname="EnigmaMachineTest" extends="mxunit.framework.TestCase">
	
	
	<cffunction access="public" returntype="void" name="setUp">
		<cfset testEnigma = CreateObject("component","EnigmaMachine").init()>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testencipher">
		<cfscript>		
			var plainText = "ABCDEFG";
			var expected = "BJELRQZ";
			var actual = "";
			testEnigma.setWheelOrder(1,2,3);
	//		Ring Settings:      111 (or AAA)
			testEnigma.setIndicators("AAA");
	//		Steckers:           None
				
			actual = testEnigma.encipher(plainText);		
			/* assertEquals(expected, actual,"These two strings don't match!"); */
	
	//		With the rotors I, II and III (from left to right), wide B-reflector, all ring settings in A-position, and start position AAA, typing AAAAA will produce the encoded sequence BDZGO.
	//		http://en.wikipedia.org/wiki/Enigma_rotor_details
			
			testEnigma.setIndicators("AAA");
			plainText = "AAAAA";
			expected = "BDZGO";
			actual = testEnigma.encipher(plainText);
			/* assertEquals("These two strings don't match!", expected, actual); */
			
			// another encipherion with different rotors
			testEnigma.setWheelOrder(3,4,5);
			testEnigma.setIndicators("AAA");
			plainText = "AAAAA";
			expected = "HNYNU";
			actual = testEnigma.encipher(plainText);
			assertEquals(expected, actual,"These two strings don't match!");
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testencipher2">
		<cfscript>
			var plainText = "";
			var expected = "";
			var actual = "";
				
			// this is from Tony Sale's page www.codesandciphers.org.uk/enigma/example1.htm
			testEnigma.setWheelOrder(1,2,3);
			testEnigma.setIndicators("AAZ");
			plainText = "G";
			expected = "P";
			actual = testEnigma.encipher(plainText);
			assertEquals(expected, actual,"These two strings don't match!");
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testencipher3">
		<cfscript>
			var plainText = "";
			var expected = "";
			var actual = "";
			testEnigma.setWheelOrder(2,4,3);
			testEnigma.setIndicators("DIV");
	//		testEnigma.setIndicators("AAA");
			plainText = "AAAAA";
	//		String plainText = "AAAAAAAAAAAAAAAAAAAAAAAAA";
			expected = "YLOCX";
	//		String expected = "DCVJJFXFKPVMSOQPCPYRHNJVT";
			actual = testEnigma.encipher(plainText);
			assertEquals(expected, actual,"These two strings don't match!");
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testencipher4">
		<cfscript>
			var plainText = "";
			var expected = "";
			var actual = "";
			testEnigma.setWheelOrder(2,3,4);
			testEnigma.setIndicators("AAA");
			plainText = "AAAAAAAAAA";
			expected = "IFKKLKUDOP";
			for (i=0; i<plainText.length(); i++){
					actual &= testEnigma.encipher(plainText.charAt(i));			
			}
			assertEquals(expected, actual,"These two strings don't match!");
		</cfscript>
	</cffunction>
	
//	
//	Now you can try deciphering an actual German message using the German Enigma machine that was used by 'Hester', played by Kate Winslet, in the film Enigma.
//
//	The Enigma configuration for that day was:
//	IV II V | GMY | DN GR IS KC QX TM PV HY FW BJ |
//	and here is the intercepted message:
//	U8K DE C 1806 = 49 = DHO GXS =
//	NQVLT YQFSE WWGJZ GQHVS EIXIM YKCNW IEBMB ATPPZ TDVCU PKAY-
//
//	Click here and you will be taken to Tony Sale's on-line Enigma simulator where you can decipher this message.
//
//	For full information about Tony Sale's work for the film Enigma go to these pages.
//	
	
	<cffunction access="public" returntype="void" name="testencipher5">
		<cfscript>
			var actual = "";
			var plainText = "GXS";
			var expected = "RLP";
			testEnigma.setWheelOrder(4,2,5);
			testEnigma.setRingSettings("GMY");
			testEnigma.setIndicators("DHO");
			testEnigma.setSteckerPairings("DNGRISKCQXTMPVHYFWBJ");
			
			actual = testEnigma.encipher(plainText);
			assertEquals(expected, actual,"These two strings don't match!");
			
			testEnigma.setIndicators("RLP");
			plainText = "NQVLTYQFSEWWGJZGQHVSEIXIMYKCNWIEBMBATPPZTDVCUPKAY";
	//		NQVLT YQFSE WWGJZ GQHVS EIXIM YKCNW IEBMB ATPPZ TDVCU PKAY
	//		NQVLT YQFSE WWGJZ GQHVS EIXIM YCKNW IEBMB ATPPZ TDVCU PKAY
			
	//		FLUGZEUGFUEHRERISTOFWYYXFUELLGRAFXFUELLGPAFXPOFOP
			
	//		FLUGZ EUGFU EHRER ISTOF WYYXF UFHLG RAFXF UELLG PAFXP OFOP
	//		FLUGZEUG FUEHRER IST OFW - X FUELLGRAF X FUELLGPAF X POFOP
	//		FLUGZEUG FUEHRER IST OF WYY X FUFHLG RAF X FUELLGPAF X POFOP
			
	//		FLUGZ EUGFU EHRER ISTOF WYYXF UELLG RAFXF UELLG PAFXP OFOP
	//		
			expected = "FLUGZEUGFUEHRERISTOFWYYXFUELLGRAFXFUELLGPAFXPOFOP";
			actual = testEnigma.encipher(plainText);
			
			debug(actual);
			debug(expected);
			assertEquals(Len(expected),Len(actual));
			assertEquals(expected,actual);
			
			// now try encyphering...
			testEnigma.setIndicators("RLP");
			plainText = actual.toString();
			expected = "NQVLTYQFSEWWGJZGQHVSEIXIMYKCNWIEBMBATPPZTDVCUPKAY";
			actual = testEnigma.encipher(plainText);
		</cfscript>
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testGetIndicators">
		testEnigma.setWheelOrder(1,2,3);
		testEnigma.setIndicators("AAA");
		assertEquals("getIndicators ain't working","AAA",testEnigma.getIndicators());
		testEnigma.setIndicators("CGF");
		assertEquals("getIndicators ain't working","CGF",testEnigma.getIndicators());
	</cffunction>
	
	<cffunction access="public" returntype="void" name="testRotation">
		<cfscript>
	//		To see the anomaly in action, fit the rotors in the order 1,2,3 and set the indicators to 'EDV' 
	//		(The ring settings don't matter as the turnover mechanism is on the alphabet rings, not the wheel cores). 
	//		The first keystroke should move the indicator to 'EEW' and the next should move all 3 rotors, 
	//		resulting in an indicator of 'FFX'. 
			
			testEnigma.setWheelOrder(1,2,3);
			testEnigma.setIndicators("EDV");
			testEnigma.encipher('A');
			assertEquals("EEW",testEnigma.getIndicators(),"Rotation isn't working!");
			testEnigma.encipher('B');
			assertEquals("FFX",testEnigma.getIndicators(),"Rotation isn't working!");
		</cfscript>
	</cffunction>

</cfcomponent>