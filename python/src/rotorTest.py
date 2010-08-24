import rotor
import unittest

class RotorTest (unittest.TestCase):

    def testThatSetWiringAcceptsNoStringWithDuplicates(self):
        pass #TODO: implementation needed

    def testSetCurrentPosition(self):
        testRotor = rotor.Rotor()
        testRotor.setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')
        testRotor.setCurrentPosition(0)
        assert testRotor.getCurrentPosition() == 0
        testRotor.setCurrentPosition(1)
        assert testRotor.getCurrentPosition() == 1

    def testGetCurrentPosition(self):
        testRotor = rotor.Rotor()
        testRotor.setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')
        assert testRotor.getCurrentPosition() > -1
        assert testRotor.getCurrentPosition() < 26

    def testencipher(self):
        # Rotor I - rotors[0]
        testRotor = rotor.Rotor()
        testRotor.setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')

        expected = 'B'
        actual = testRotor.encipher('A')
        assert expected == actual

        expected = 'D'
        actual = testRotor.encipher('B')
        assert expected == actual

        expected = 'O'
        actual = testRotor.encipher('Z')
        assert expected == actual

        testRotor.setIndicator('D')

        expected = 'E'
        actual = testRotor.encipher('A')
        assert expected == actual

        expected = 'Q'
        actual = testRotor.encipher('G')
        assert expected == actual

        testRotor.setIndicator('A')

        testRotor.advance()
        expected = 'C'
        actual = testRotor.encipher('A')
        assert expected == actual

        testRotor.advance()
        expected = 'F'
        actual = testRotor.encipher('B')
        assert expected == actual

        testRotor.advance()
        expected = 'I'
        actual = testRotor.encipher('C')
        assert expected == actual

        testRotor.advance()
        expected = 'L'
        actual = testRotor.encipher('D')
        assert expected == actual

    def testdecipher(self):
        # Rotor I - rotors[0]
        testRotor = rotor.Rotor()
        testRotor.setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')

        expected = 'A'
        actual = testRotor.decipher('B')
        assert expected == actual

        expected = 'L'
        actual = testRotor.decipher('V')
        assert expected == actual

        expected = 'Q'
        actual = testRotor.decipher('I')
        assert expected == actual

        testRotor.advance()
        expected = 'B'
        actual = testRotor.decipher('E')
        assert expected == actual

        testRotor.advance()
        expected = 'J'
        actual = testRotor.decipher('T')
        assert expected == actual

        testRotor.advance()
        expected = 'E'
        actual = testRotor.decipher('M')
        assert expected == actual

        testRotor.setIndicator('G')
        testRotor.advance()
        expected = 'Z'
        actual = testRotor.decipher('V')
        assert expected == actual



    def testdecipherWithRingSetting(self):

            testRotor = rotor.Rotor()
            testRotor.setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')
            testRotor.setRingSetting('B')
            testRotor.setIndicator('A')
            expected = 'U'
            actual = testRotor.decipher('B')
            assert expected == actual, "testdecipherWithRingSetting"

            expected = 'C'
            actual = testRotor.decipher('E')
            assert expected == actual




    def testGetIndicator(self):
        testRotor = rotor.Rotor()
        testRotor.setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')
        testRotor.setIndicator('D')
        assert 'D' == testRotor.getIndicator(), "getIndicator ain't working!"

    def testIsAtNotchPosition(self):
        i = 0
        testRotor = rotor.Rotor()
        testRotor.setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')
        assert 'V' == testRotor.getNotch(), "Notch has not been set to initial value !"

        for i in range(26):
            if (i == 21):
                assert testRotor.isAtNotchPosition(), "isAtNotchPosition is faulty ! (" + str(i) + ")"
            else:
                assert not testRotor.isAtNotchPosition(), "isAtNotchPosition is faulty(" + str(i) + ")"
            testRotor.advance()

        # the ring setting should not have any bearing on the notch position
        testRotor.setRingSetting('B')
        testRotor.setIndicator('A')
        for i in range(26):
            if (i == 21):
                assert testRotor.isAtNotchPosition(), "isAtNotchPosition is faulty ! (" + str(i) + ")"
            else:
                assert not testRotor.isAtNotchPosition(), "isAtNotchPosition is faulty ! (" + str(i) + ")"
            testRotor.advance()


    def testSetRingSetting(self):

        testRotor = rotor.Rotor()
        testRotor.setUp("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V')
        testRotor.setRingSetting('A')
        testRotor.setIndicator('A')
        assert 'A' == testRotor.getIndicator(), "setRingSetting isn't working!"

        testRotor.setRingSetting('B')
        testRotor.setIndicator('A')
        assert 'A' == testRotor.getIndicator(), "setRingSetting isn't working ! "
        assert 1 == testRotor.getRingSetting(), "getRingSetting isn't working properly!"
        assert 'P' == testRotor.encipher('A'), "encipher isn't responding to ringSetting properly ! "

        testRotor.setRingSetting('F')
        testRotor.setIndicator('A')
        assert 'A' == testRotor.getIndicator(), "setRingSetting isn't working!"
        assert 5 == testRotor.getRingSetting(), "getRingSetting isn't working properly ! "

        testRotor.setRingSetting('B')
        testRotor.setIndicator('A')
        assert 'A' == testRotor.getIndicator(), "setRingSetting isn't working!"
        assert 'C' == testRotor.encipher('B'), "encipher isn't responding to ringSetting properly ! "

