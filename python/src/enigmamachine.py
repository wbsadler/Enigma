import rotor
import reflector
import stecker

class EnigmaMachine():

    def __init__(self):
        self.rotors = [1, 2, 3]
        self.reflector = reflector.Reflector(1) # choosing the B reflector
        self.stecker = stecker.Stecker()

    def set_ring_settings(self, ringSettings):
        for i in range(len(ringSettings)):
            self.set_ring_setting(len(ringSettings) - i - 1, ringSettings[i])

    def set_ring_setting(self, rotor_number, letter):
        self.rotors[rotor_number].set_ring_setting(letter)

    def set_stecker_pairings(self, pairings):
        self.stecker.set_pairings(pairings)

    def set_wheel_order(self, rotor1, rotor2, rotor3):
        # TODO: refactor to be able to deal with any number of rotors
        self.select_rotor(2, rotor1)
        self.select_rotor(1, rotor2)
        self.select_rotor(0, rotor3)

    def select_rotor(self, wheel_position, rotor_number):
        self.rotors[wheel_position] = rotor.Rotor(rotor_number)

    def moveRotor(self, rotor, direction):
        if (direction == "up"):
            self.moveRotorUp(rotor)
        else:
            self.moveRotorDown(rotor)

    def setIndicators(self, indicators):
        for i in range(self.getNumberOfRotors()):
            self.setIndicator(self.getNumberOfRotors() - i - 1, indicators[i]);

    def getIndicators(self):
        returnString = "";
        print("Number of rotors is: " + str(self.getNumberOfRotors()) + "\r")
        for i in range(self.getNumberOfRotors() - 1, -1, -1):
            returnString += self.getIndicator(i)
        print("Indicators :" + returnString + "\r")
        return returnString;

    def encrypt(self, plainText):
        print ("Encrypting: " + plainText)
        encypheredText = ""
        for i in range(len(plainText)):
            encypheredText += self.encrypt_character(plainText[i])
        print ("Encyphered text : " + encypheredText)
        return encypheredText

    def encrypt_character(self, clearText) :
        encypheredChar = clearText
        print("ENCRYPTING: " + clearText + "\r=============")
        # go through the stecker
        encypheredChar = self.stecker.encrypt(clearText)
        print(encypheredChar + " gone through stecker\r")
        # move the rotors on one
        self.advanceRotors()
        # go through the rotors in acsending order (encypher)
        for i in range(self.getNumberOfRotors()):
            encypheredChar = self.rotors[i].encipher(encypheredChar)
            print(encypheredChar + " gone through rotor" + str(i) + "\r")
        # go through the reflector
        encypheredChar = self.reflector.reflect(encypheredChar)
        print(encypheredChar + " gone through reflector\r")


        # go back through the rotors in descending order (decypher)
        for i in range(self.getNumberOfRotors() - 1, -1, -1):
            encypheredChar = self.rotors[i].decipher(encypheredChar)
            print(encypheredChar + " gone back through rotor" + str(i) + "\r")

        # go back trhough the Stecker
        encypheredChar = self.stecker.encrypt(encypheredChar)
        print(encypheredChar + " back out the stekcer\r")

        # System.out.println()
        # System.out.println(clearText + " " + encypheredChar + " " + getIndicators())
        # return encrypted character
        return encypheredChar

    def advanceRotors(self):
        rotorsToAdvance = []
        # check for notch positions
        for i in range(self.getNumberOfRotors() - 1, -1, -1):
            # right hand rotor moves on one postion every time
            if (i == 0):
                rotorsToAdvance.append(i)
            # if preceding rotor is at notch position then this rotor moves on
            elif (self.rotors[i - 1].isAtNotchPosition()):
                rotorsToAdvance.append(i)
            elif self.rotors[i].isAtNotchPosition() and i < (self.getNumberOfRotors() - 1):
                rotorsToAdvance.append(i)
        for i in range(len(rotorsToAdvance)):
            self.rotors[rotorsToAdvance[i]].advance()

    def moveRotorUp(self, rotor):
        self.rotors[rotor].advance()

    def moveRotorDown(self, rotor):
        self.rotors[rotor].goBack()

    def setIndicator(self, rotor, letter):
        self.rotors[rotor].setIndicator(letter)

    def getIndicator(self, rotor):
        return self.rotors[rotor].getIndicator()


    def setNumberOfRotors(self, numberOfRotors):
        self.numberOfRotors = numberOfRotors

    def getNumberOfRotors(self):
        return len(self.rotors)
