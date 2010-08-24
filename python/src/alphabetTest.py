import alphabet
import unittest

class alphabetTest(unittest.TestCase):

    def setUp(self):
        pass

    def testGoForwardThroughalphabet(self):
        expected = 'A'
        actual = alphabet.go_forwards('Z')
        self.assertEquals(expected, actual)

        expected = 'B'
        actual = alphabet.go_forwards('A')
        self.assertEquals(expected, actual)

        expected = 'H'
        actual = alphabet.go_forwards('G')
        self.assertEquals(expected, actual)

        expected = 'Z'
        actual = alphabet.go_forwards('Y')
        self.assertEquals(expected, actual)

        expected = 'E'
        actual = alphabet.go_forwards('A', 4)
        self.assertEquals(expected, actual)

        expected = 'L'
        actual = alphabet.go_forwards('A', 11)
        self.assertEquals(expected, actual)

    def test_go_back_through_alphabet(self):
        expected = 'Y'
        actual = alphabet.go_backwards('Z')
        self.assertEquals(expected, actual)

        expected = 'B'
        actual = alphabet.go_backwards('C')
        self.assertEquals(expected, actual)

        expected = 'H'
        actual = alphabet.go_backwards('I')
        self.assertEquals(expected, actual)

        expected = 'Z'
        actual = alphabet.go_backwards('A')
        self.assertEquals(expected, actual)

        expected = 'W'
        actual = alphabet.go_backwards('A', 4)
        self.assertEquals(expected, actual)

        expected = 'L'
        actual = alphabet.go_backwards('W', 11)
        self.assertEquals(expected, actual)

        expected = 'A'
        actual = alphabet.go_backwards('A', 0)
        self.assertEquals(expected, actual)


    def test_to_letter_index(self):
        expected = 0
        actual = alphabet.to_letter_index('A')
        self.assertEquals(expected, actual)

        expected = 1
        actual = alphabet.to_letter_index('B')
        self.assertEquals(expected, actual)

        expected = 25
        actual = alphabet.to_letter_index('Z')
        self.assertEquals(expected, actual)

    def test_to_letter(self):
        expected = 'A'
        actual = alphabet.to_letter(0)
        self.assertEquals(expected, actual)

        expected = 'M'
        actual = alphabet.to_letter(12)
        self.assertEquals(expected, actual)

        expected = 'Z'
        actual = alphabet.to_letter(25)
        self.assertEquals(expected, actual)


    def test_go_forwards(self):
        expected = 1
        actual = alphabet.go_forwards(0, 1)
        self.assertEquals(expected, actual)

        expected = 3
        actual = alphabet.go_forwards(25, 4)
        self.assertEquals(expected, actual)

        expected = 24
        actual = alphabet.go_forwards(0, -2)
        self.assertEquals(expected, actual)

if __name__ == "__main__":
    unittest.main()
