class Configuration():

    def __init__(self): 
        self.TOTAL_NUMBER_OF_ROTORS = 5
        self.TOTAL_NUMBER_OF_REFLECTORS = 2
        self.rotorWirings = []
        self.reflectorWirings = []
        self.notches = ['Q','E','V','J','Z']
        
        self.rotorWirings.append("EKMFLGDQVZNTOWYHXUSPAIBRCJ")
        self.rotorWirings.append("AJDKSIRUXBLHWTMCQGZNPYFVOE")
        self.rotorWirings.append("BDFHJLCPRTXVZNYEIWGAKMUSQO")
        self.rotorWirings.append("ESOVPZJAYQUIRHXLNFTGKDCMWB")
        self.rotorWirings.append("VZBRGITYUPSDNHLXAWMJQOFECK")
        
        # for 6-8 of the naval enigma:-
        # TODO: refactor for naval enigma
        #
        # rotors[5].loadWiring("JPGVOUMFYQBENHZRDKASXLICTW")
        # rotors[5].setNotch('Z','M')
        # rotors[6].loadWiring("NZJHGRCXMYSWBOUFAIVLPEKQDT")
        # rotors[6].setNotch('Z','M')
        # rotors[7].loadWiring("FKQHTLXOCBJSPDZRAMEWNIUYGV")
        # rotors[7].setNotch('Z','M')
        #
        
        # The 4 rotor naval enigma could only use one of two 'thin' rotors Beta
        # and Gamma in the leftmost position:-
        #
        # m_betaRotor.loadWiring("LEYJVCNIXWPBQMDRTAKZGFUHOS")
        # m_gammaRotor.loadWiring("FSOKANUERHMBTIYCWLQPZXVGJD")
        #
        
        
        # 'B' reflector:-
        self.reflectorWirings.append("YRUHQSLDPXNGOKMIEBFZCWVJAT")
        # 'C' reflector:-
        self.reflectorWirings.append("FVPJIAOYEDRZXWGCTKUQSBNMHL")
        # The 4 rotor naval enigma had different reflectors:-
        #
        # 'B' reflector:-
        # m_reflectors[2].loadWiring("ENKQAUYWJICOPBLMDXZVFTHRGS")
        #
        #'C' reflector:-
        #m_reflectors[3].loadWiring("RDOBJNTKVEHMLFCWZAXGYIPSUQ")
    
    def get_rotor_wirings(self):
        return self.rotorWirings
        
    def get_notches(self):
        return self.notches
        
    def get_reflector_wirings(self):
        return self.reflectorWirings
        
    def get_total_number_of_reflectors(self):
        return self.TOTAL_NUMBER_OF_REFLECTORS
    
    def get_total_number_of_rotors(self):
        return self.TOTAL_NUMBER_OF_ROTORS
            
        
