#!/usr/bin/ruby
class Configuration

  def initialize()
    @TOTAL_NUMBER_OF_ROTORS = 5
    @TOTAL_NUMBER_OF_REFLECTORS = 2
    @rotorWirings = Array.new
    @reflectorWirings = Array.new
    @notches = ['Q','E','V','J','Z']
    
    @rotorWirings << "EKMFLGDQVZNTOWYHXUSPAIBRCJ"
    @rotorWirings << "AJDKSIRUXBLHWTMCQGZNPYFVOE"
    @rotorWirings << "BDFHJLCPRTXVZNYEIWGAKMUSQO"
    @rotorWirings << "ESOVPZJAYQUIRHXLNFTGKDCMWB"
    @rotorWirings << "VZBRGITYUPSDNHLXAWMJQOFECK"
    
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
    @reflectorWirings << "YRUHQSLDPXNGOKMIEBFZCWVJAT"
    # 'C' reflector:-
    @reflectorWirings << "FVPJIAOYEDRZXWGCTKUQSBNMHL"
    # The 4 rotor naval enigma had different reflectors:-
    #
    # 'B' reflector:-
    # m_reflectors[2].loadWiring("ENKQAUYWJICOPBLMDXZVFTHRGS")
    #
    #'C' reflector:-
    #m_reflectors[3].loadWiring("RDOBJNTKVEHMLFCWZAXGYIPSUQ")
  end
  
  def get_rotor_wirings()
    return @rotorWirings
  end
  
  #    def get_notches(self):
  #        return @notches
  #        
  #    def get_reflector_wirings(self):
  #        return @reflectorWirings
  #        
  #    def get_total_number_of_reflectors(self):
  #        return @TOTAL_NUMBER_OF_REFLECTORS
  #    
  #    def get_total_number_of_rotors(self):
  #        return @TOTAL_NUMBER_OF_ROTORS
  #            
  #        
end