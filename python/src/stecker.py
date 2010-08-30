
class Stecker():

    def __init__(self):
        self.pairings = ""

    def addPairing(self, pairing):
        # TODO: check that neither of the letters already exists in other
        self.pairings += pairing

    def removePairing(self, pairing):
        startPosition = self.pairings.find(pairing)
        if not startPosition == -1:
            self.pairings = self.pairings[:startPosition] + \
            self.pairings[startPosition + 2:]
        print self.pairings + '\r'

    def set_pairings(self, steckerPairings):
        self.pairings = steckerPairings

    def getPairings(self):
        return self.pairings

    def encrypt(self, clearText):
        encryptedCharacter = clearText;
        # loop over pairings to see if the letter is in a pairing
        if self.isSteckered(encryptedCharacter):
            position = self.pairings.find(encryptedCharacter)
            # if it's even swap with previous
            if position % 2 == 1:
                encryptedCharacter = self.pairings[position - 1]
            else:
                encryptedCharacter = self.pairings[position + 1]
        return encryptedCharacter

    def isSteckered(self, letter):
        return self.pairings.find(letter) >= 0

