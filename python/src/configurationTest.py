import configuration
import unittest


class ConfigurationTest(unittest.TestCase):

    def setUp(self):
        self.TOTAL_NUMBER_OF_ROTORS = 5
        self.TOTAL_NUMBER_OF_REFLECTORS = 2
        self.NUMBER_OF_LETTERS_IN_ALPHABET = 26
        self.ASCII_A = 65
        self.cfg = configuration.Configuration()

    def testGetRotorWiringsShouldReturnAnArray(self):
        rotorWirings = self.cfg.get_rotor_wirings()
        assert type(rotorWirings).__name__ == "list"

    def testGetRotorWiringsShouldReturnCorrectlength(self):
        rotorWirings = self.cfg.get_rotor_wirings()
        assert len(rotorWirings) == self.TOTAL_NUMBER_OF_ROTORS, \
            "Oops!  Rotors is not length " + str(self.TOTAL_NUMBER_OF_ROTORS)

    def testGetRotorWiringsShouldReturnArrayOfScrambledAlphabet(self):
        rotorWirings = self.cfg.get_rotor_wirings()
        for i in range(len(rotorWirings)):
            assert len(rotorWirings[i]) == \
            self.NUMBER_OF_LETTERS_IN_ALPHABET, \
            "The " + str(i) + "th rotor does not have " + \
            str(self.NUMBER_OF_LETTERS_IN_ALPHABET) + " letters!"

    def testGetRotorWiringsShouldContainNoDuplicateCharacters(self):
        rotorWirings = self.cfg.get_rotor_wirings()
        for i in range(len(rotorWirings)):
            for j in range(self.NUMBER_OF_LETTERS_IN_ALPHABET):
                    c = chr(j + self.ASCII_A)
                    assert rotorWirings[i].find(c) == \
                        rotorWirings[i].rfind(c), c + \
                        " is duplicated in rotor " + str(i) + "!"

    def testGetRotorWiringsContainsAllLettersInAlphabet(self):
        rotorWirings = self.cfg.get_rotor_wirings()
        for i in range (len(rotorWirings) - 1):
            for j in range(self.NUMBER_OF_LETTERS_IN_ALPHABET):
                c = chr(j + self.ASCII_A)
                assert rotorWirings[i].find(c) > -1, c + " not found!"

    def testGetTotalNumberOfReflectors(self):
        assert self.cfg.get_total_number_of_reflectors() == \
            self.TOTAL_NUMBER_OF_REFLECTORS, "Wrong number of reflectors!"

    def testGetTotalNumberOfRotors(self):
        assert self.cfg.get_total_number_of_rotors() == \
            self.TOTAL_NUMBER_OF_ROTORS, "Wrong number of rotors"

