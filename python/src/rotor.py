import alphabet
import configuration

class Rotor():

    def __init__(self, rotor):
        self.NUMBER_OF_LETTERS_IN_ALPHABET = 26
        self.wiring = "" # scrambled alphabet
        self.notch = "" # the letter next to the notch 
        self.ringSetting = 0 # ringStellung
        self.currentPosition = 0 # the position relative to the wiring
        cfg = configuration.Configuration()
        notches = cfg.get_notches()
        rotorWirings = cfg.get_rotor_wirings()
        self.setNotch(notches[rotor - 1])
        self.setWiring(rotorWirings[rotor - 1])
        self.setIndicator('A')
        self.setRingSetting('A')

    def setUp(self, wiring, notch):
        self.setWiring(wiring)
        self.setNotch(notch)
        self.setRingSetting('A')
        self.setIndicator('A')

    def getCurrentPosition(self):
        return self.currentPosition

    def setCurrentPosition(self, position):
        currentLetter = ""
        if str(position).isdigit():
            self.currentPosition = position
        else:
            currentLetter = alphabet.go_backwards(position, self.getRingSetting())
            self.setCurrentPosition(alphabet.to_letter_index(currentLetter))

    def setRingSetting(self, setting):
        if str(setting).isdigit():
            self.ringSetting = setting
        else:
            self.setRingSetting(alphabet.to_letter_index(setting))

    def getRingSetting(self):
        return self.ringSetting

    def getIndicator(self):
        currentLetter = alphabet.to_letter(self.getCurrentPosition())
        return alphabet.go_forwards(currentLetter, self.getRingSetting()).upper()

        def getWiring(self):
                return self.wiring

    def setWiring(self, wiring):
        self.wiring = wiring

    def setNotch(self, notch):
        self.notch = notch

    def getNotch(self):
        return self.notch

    def getAdjustedNotch(self):
        return alphabet.go_backwards(self.getNotch(), self.getRingSetting())

    def getAdjustedNotchPosition(self):
            return alphabet.to_letter_index(self.getAdjustedNotch())

    def encipher(self, clearCharacter):
        letterIndex = alphabet.to_letter_index(clearCharacter)
        # encipher the character taking into account the current position of the rotor  
        encipheredCharacter = self.getLetterAtPosition(letterIndex)
        print("\renciphering: " + clearCharacter);
        print("\r" + self.wiring);
        print("enciphered as: " + encipheredCharacter + "\r")
        # take care of the offset
        if self.getCurrentPosition() > 0:
            letterIndex = alphabet.to_letter_index(encipheredCharacter)
            letterIndex = alphabet.go_backwards(letterIndex, self.getCurrentPosition())
            encipheredCharacter = alphabet.to_letter(letterIndex)
        print("\r offset to :" + encipheredCharacter)
        return encipheredCharacter

    def decipher(self, encipheredLetter):
        # get position of letter taking into account the current position of the rotor
        decipheredLetterIndex = self.getPositionOfLetter(encipheredLetter)
        decipheredLetter = alphabet.to_letter(decipheredLetterIndex)
        # WriteOutput(encipheredLetter)
        # WriteOutput(decipheredLetter)
        # re-adjust output for any offset
        if self.getCurrentPosition() > 0:
            decipheredLetter = alphabet.go_backwards(decipheredLetter, self.getCurrentPosition())
        return decipheredLetter

    def getLetterAtPosition(self, letterIndex):
        wiringIndex = (letterIndex + self.getCurrentPosition()) % self.NUMBER_OF_LETTERS_IN_ALPHABET
        return self.wiring[wiringIndex]

    def getPositionOfLetter(self, character):
        # get the offset character
        offsetCharacter = alphabet.go_forwards(character, self.getCurrentPosition())
        wiringIndex = self.wiring.find(offsetCharacter)
        return wiringIndex

    def advance(self):
        self.setCurrentPosition((self.getCurrentPosition() + 1) % self.NUMBER_OF_LETTERS_IN_ALPHABET)

    def goBack(self):
        self.setCurrentPosition((self.getCurrentPosition() - 1 + self.NUMBER_OF_LETTERS_IN_ALPHABET) % self.NUMBER_OF_LETTERS_IN_ALPHABET)

    def isAtNotchPosition(self):
        return self.getIndicator() == self.getNotch()

    def setIndicator(self, letter):
        self.setCurrentPosition(letter)

