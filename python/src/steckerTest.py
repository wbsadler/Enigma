import unittest
import stecker

class SteckerTest (unittest.TestCase):

    def testEncrypt(self):
        testStecker = stecker.Stecker()
        expected = 'A'
        assert  expected == testStecker.encrypt('A'), "Encrypt isn't working!"

        expected = 'B'
        assert expected == testStecker.encrypt('B'), "Encrypt isn't working!"

        testStecker.addPairing("AB")
        expected = 'B'
        assert expected == testStecker.encrypt('A'), "Encrypt isn't working!"

        expected = 'A'
        assert expected == testStecker.encrypt('B'), "Encrypt isn't working!"

        # testing that the Stecker works both ways... 
        # i.e. D should encrypt to K, and K should encrypt to D..
        testStecker.addPairing("DK")
        expected = 'K'
        assert expected == testStecker.encrypt('D'), "Encrypt isn't working!"

        expected = 'D'
        assert  expected == testStecker.encrypt('K'), "Encrypt isn't working!"

    def testAddPairing(self):
        testStecker = stecker.Stecker()
        pairing = "AB"
        pairings = ""
        pairings += pairing
        testStecker.addPairing(pairing)
        assert pairings == testStecker.getPairings(), "addPairing isn't working!"

    def testAddPairingShouldNotAllowDuplicates(self):
        pass

    def testAddPairingShouldAllowOnlyTwoCharacterStrings(self):
        pass

    def testRemovePairing(self):
        testStecker = stecker.Stecker()
        pairings = ""
        pairings += "AB"

        testStecker.addPairing("AB")
        testStecker.addPairing("CD")
        testStecker.removePairing("CD")
        assert pairings == testStecker.getPairings(), "removePairing isn't working!"

        testStecker.addPairing("EF")
        testStecker.addPairing("GH")
        pairings += "EF"
        testStecker.removePairing("GH")

        assert pairings == testStecker.getPairings(), "removePairing isn't working!"

def suite():
    return unittest.TestLoader().loadTestsFromTestCase(SteckerTest)

if __name__ == '__main__':
    unittest.main()
