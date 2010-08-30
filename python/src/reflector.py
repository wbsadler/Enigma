import configuration
import alphabet

class Reflector():

    def __init__(self, reflectorNumber):
        self.cfg = configuration.Configuration()
        self.loadWiring(self.cfg.get_reflector_wirings()[reflectorNumber - 1])

    def loadWiring(self, wiring):
        self.wiring = wiring

    def reflect(self, entryLetter):
        letterIndex = alphabet.to_letter_index(entryLetter)
        return self.wiring[letterIndex];
