import unittest
import enigmamachine

class EnigmaMachineTest(unittest.TestCase):

    def setUp(self):
        self.testEnigma = enigmamachine.EnigmaMachine()

    def testEncrypt(self) :
        plainText = "ABCDEFG"
        expected = "BJELRQZ"
        self.testEnigma.set_wheel_order(1, 2, 3)
#        Ring Settings:      111 (or AAA)
        self.testEnigma.setIndicators("AAA")
#        Steckers:           None

        actual = self.testEnigma.encrypt(plainText)
        assert expected == actual, "These two strings don't match!\rexpected: " + expected + "\ractual: " + actual

#        With the rotors I, II and III (from left to right), wide B-reflector, all ring settings in A-position, and start position AAA, typing AAAAA will produce the encoded sequence BDZGO.
#        http:#en.wikipedia.org/wiki/Enigma_rotor_details

        self.testEnigma.setIndicators("AAA")
        plainText = "AAAAA"
        expected = "BDZGO"
        actual = self.testEnigma.encrypt(plainText)
        assert  expected == actual, "These two strings don't match! \rexpected: " + expected + "\ractual: " + actual

        # another encryption with different rotors
        self.testEnigma.set_wheel_order(3, 4, 5)
        self.testEnigma.setIndicators("AAA")
        plainText = "AAAAA"
        expected = "HNYNU"
        actual = self.testEnigma.encrypt(plainText)
        assert expected == actual, "These two strings don't match!\rexpected: " + expected + "\ractual: " + actual

    def testEncrypt2(self):
        # this is from Tony Sale's page www.codesandciphers.org.uk/enigma/example1.htm
        self.testEnigma.set_wheel_order(1, 2, 3)
        self.testEnigma.setIndicators("AAZ")
        plainText = "G"
        expected = "P"
        actual = self.testEnigma.encrypt(plainText)
        assert expected == actual, "These two strings don't match!\rexpected: " + expected + "\ractual: " + actual

    def testEncrypt3(self):
        self.testEnigma.set_wheel_order(2, 4, 3)
        self.testEnigma.setIndicators("DIV")
#        self.testEnigma.setIndicators("AAA")
        plainText = "AAAAA"
#        plainText = "AAAAAAAAAAAAAAAAAAAAAAAAA"
        expected = "YLOCX"
#        expected = "DCVJJFXFKPVMSOQPCPYRHNJVT"
        actual = self.testEnigma.encrypt(plainText)
        assert  expected == actual, "These two strings don't match!\rexpected: " + expected + "\ractual: " + actual

    def testEncrypt4(self):
        self.testEnigma.set_wheel_order(2, 3, 4)
        self.testEnigma.setIndicators("AAA")
        plainText = "AAAAAAAAAA"
        expected = "IFKKLKUDOP"
        actual = ""
        for i in range(len(plainText)):
            actual = actual + self.testEnigma.encrypt(plainText[i])
        assert expected == actual, "These two strings don't match!\rexpected: " + expected + "\ractual: " + actual

#    
#    Now you can try decrypting an actual German message using the German Enigma machine that was used by 'Hester', played by Kate Winslet, in the film Enigma.
#
#    The Enigma configuration for that day was:
#    IV II V | GMY | DN GR IS KC QX TM PV HY FW BJ |
#    and here is the intercepted message:
#    U8K DE C 1806 = 49 = DHO GXS =
#    NQVLT YQFSE WWGJZ GQHVS EIXIM YKCNW IEBMB ATPPZ TDVCU PKAY-
#
#    Click here and you will be taken to Tony Sale's on-line Enigma simulator where you can decrypt this message.
#
#    For full information about Tony Sale's work for the film Enigma go to these pages.
#    

    def testEncrypt5(self):
        self.testEnigma.set_wheel_order(4, 2, 5)
        self.testEnigma.set_ring_settings("GMY")
        self.testEnigma.setIndicators("DHO")
        self.testEnigma.set_stecker_pairings("DNGRISKCQXTMPVHYFWBJ")
        plainText = "GXS"
        expected = "RLP"
        actual = self.testEnigma.encrypt(plainText)
        assert expected == actual, "These two strings don't match!\rexpected: " + expected + "\ractual: " + actual

        self.testEnigma.setIndicators("RLP")
        plainText = "NQVLTYQFSEWWGJZGQHVSEIXIMYKCNWIEBMBATPPZTDVCUPKAY"
#        NQVLT YQFSE WWGJZ GQHVS EIXIM YKCNW IEBMB ATPPZ TDVCU PKAY
#        NQVLT YQFSE WWGJZ GQHVS EIXIM YCKNW IEBMB ATPPZ TDVCU PKAY

#        FLUGZEUGFUEHRERISTOFWYYXFUELLGRAFXFUELLGPAFXPOFOP

#        FLUGZ EUGFU EHRER ISTOF WYYXF UFHLG RAFXF UELLG PAFXP OFOP
#        FLUGZEUG FUEHRER IST OFW - X FUELLGRAF X FUELLGPAF X POFOP
#        FLUGZEUG FUEHRER IST OF WYY X FUFHLG RAF X FUELLGPAF X POFOP

#        FLUGZ EUGFU EHRER ISTOF WYYXF UELLG RAFXF UELLG PAFXP OFOP
#        
        expected = "FLUGZEUGFUEHRERISTOFWYYXFUELLGRAFXFUELLGPAFXPOFOP"
        actual = self.testEnigma.encrypt(plainText)
        assert expected == actual, "Full encryption ain't working!\rexpected: " + expected + "\ractual: " + actual

        # now try encyphering...
        self.testEnigma.setIndicators("RLP")
        plainText = actual
        expected = "NQVLTYQFSEWWGJZGQHVSEIXIMYKCNWIEBMBATPPZTDVCUPKAY"
        actual = self.testEnigma.encrypt(plainText)

    def testGetIndicators(self):
        self.testEnigma.set_wheel_order(1, 2, 3)
        self.testEnigma.setIndicators("AAA")
        assert  "AAA" == self.testEnigma.getIndicators(), "getIndicators ain't working " + self.testEnigma.getIndicators() + "\r"
        self.testEnigma.setIndicators("CGF")
        assert "CGF" == self.testEnigma.getIndicators(), "getIndicators ain't working"


    def testRotation(self) :
#        To see the anomaly in action, fit the rotors in the order 1,2,3 and set the indicators to 'EDV' 
#        (The ring settings don't matter as the turnover mechanism is on the alphabet rings, not the wheel cores). 
#        The first keystroke should move the indicator to 'EEW' and the next should move all 3 rotors, 
#        resulting in an indicator of 'FFX'. 

        self.testEnigma.set_wheel_order(1, 2, 3)
        self.testEnigma.setIndicators("EDV")
        self.testEnigma.encrypt('A')
        actual = self.testEnigma.getIndicators()
        expected = "EEW"
        assert expected == actual, "Rotation isn't working! expected= " + \
            expected + " actual= " + actual
        self.testEnigma.encrypt('B')
        assert "FFX" == self.testEnigma.getIndicators(), "Rotation isn't working!"

def suite():
    return unittest.TestLoader().loadTestsFromTestCase(EnigmaMachineTest)

if __name__ == '__main__':
    unittest.main()
