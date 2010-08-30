import unittest
import alphabetTest
import configurationTest
import enigmamachineTest
import steckerTest
import rotorTest


if __name__ == "__main__":
    result = unittest.TestResult()
    suite1 = alphabetTest.suite()
    suite2 = configurationTest.suite()
    suite3 = enigmamachineTest.suite()
    suite4 = rotorTest.suite()
    suite5 = steckerTest.suite()
    alltests = unittest.TestSuite([suite1, suite2, suite3, suite4, suite5])
    alltests.run(result)

    print("====================")
    print("Was successful: " + str(result.wasSuccessful()))
    print("\rTests run: " + str(result.testsRun))
