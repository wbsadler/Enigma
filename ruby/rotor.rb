require "alphabet"
require "configuration"

class Rotor
#  
#  def __init__(rotor)
#    self.NUMBER_OF_LETTERS_IN_A= 26
#    self.wiring = "" # scrambled alphabet
#    self.notch = "" # the letter next to the notch 
#    self.ringSetting = 0 # ringStellung
#    self.currentPosition = 0 # the position relative to the wiring
#    cfg = configuration.Configuration()
#    notches = cfg.get_notches()
#    rotorWirings = cfg.get_rotor_wirings()
#    self.setNotch(notches[rotor - 1])
#    self.setWiring(rotorWirings[rotor - 1])
#    self.setIndicator('A')
#    self.setRingSetting('A')
#  end
#  
#  def setUp(wiring,notch)
#    self.setWiring(wiring)
#    self.setNotch(notch)
#    self.setRingSetting('A')
#    self.setIndicator('A')
#  end
#  
#  def getCurrentPosition()
#    return self.currentPosition
#  end
#  
#  def setCurrentPosition(position)
#    currentLetter = ""
#    if str(position).isdigit()
#      self.currentPosition = position
#    else
#      currentLetter = alphabet.go_backwards(position, self.getRingSetting())
#    end
#    self.setCurrentPosition(alphabet.to_letter_index(currentLetter))
#  end
#  
#  def setRingSetting(setting)
#    if str(setting).isdigit():
#      self.ringSetting = setting
#    else
#      self.setRingSetting(alphabet.to_letter_index(setting))
#    end
#  end
#  
#  
#  def getRingSetting()
#    return self.ringSetting
#  end    
#  
#  def getIndicator()
#    currentLetter = alphabet.to_letter(self.getCurrentPosition())
#    return alphabet.go_forwards(currentLetter, self.getRingSetting()).upper()
#  end
#  
#  def getWiring()
#    return self.wiring
#  end
#  
#  def setWiring(wiring):
#    self.wiring = wiring
#  end
#  
#  def setNotch( notch):
#    self.notch = notch
#  end
#  
#  def getNotch()
#    return self.notch
#  end
#  
#  def getAdjustedNotch()
#    return alphabet.go_backwards(self.getNotch(), self.getRingSetting())
#  end
#  
#  def getAdjustedNotchPosition()
#    return alphabet.to_letter_index(self.getAdjustedNotch())
#  end
#  
#  def encipher( clearCharacter):
#    letterIndex = alphabet.to_letter_index(clearCharacter)
#    # encipher the character taking into account the current position of the rotor  
#    encipheredCharacter = self.getLetterAtPosition(letterIndex)
#    print("\renciphering: " + clearCharacter);
#    print("\r" + self.wiring);
#    print("enciphered as: " + encipheredCharacter + "\r")
#    # take care of the offset
#    if self.getCurrentPosition() > 0:
#      letterIndex = alphabet.to_letter_index(encipheredCharacter)
#    end
#    letterIndex = alphabet.go_backwards(letterIndex, self.getCurrentPosition())
#    encipheredCharacter = alphabet.to_letter(letterIndex)
#    print("\r offset to :" + encipheredCharacter)
#    return encipheredCharacter
#  end
#  
#  
#  def decipher( encipheredLetter):
#    # get position of letter taking into account the current position of the rotor
#    decipheredLetterIndex = self.getPositionOfLetter(encipheredLetter)
#    decipheredLetter = alphabet.to_letter(decipheredLetterIndex)
#    # WriteOutput(encipheredLetter)
#    # WriteOutput(decipheredLetter)
#    # re-adjust output for any offset
#    if self.getCurrentPosition() > 0:
#      decipheredLetter = alphabet.go_backwards(decipheredLetter, self.getCurrentPosition())
#    end
#    return decipheredLetter
#  end
#  
#  def getLetterAtPosition( letterIndex):
#    wiringIndex = (letterIndex + self.getCurrentPosition()) % self.NUMBER_OF_LETTERS_IN_ALPHABET
#    return self.wiring[wiringIndex]
#  end
#  
#  def getPositionOfLetter( character):
#    # get the offset character
#    offsetCharacter = alphabet.go_forwards(character, self.getCurrentPosition())
#    wiringIndex = self.wiring.find(offsetCharacter)
#    return wiringIndex
#  end
#  
#  def advance()
#    self.setCurrentPosition((self.getCurrentPosition() + 1) % self.NUMBER_OF_LETTERS_IN_ALPHABET)
#  end
#  
#  def goBack()
#    self.setCurrentPosition((self.getCurrentPosition() - 1 + self.NUMBER_OF_LETTERS_IN_ALPHABET) % self.NUMBER_OF_LETTERS_IN_ALPHABET)
#  end
#  
#  def isAtNotchPosition()
#    return self.getIndicator() == self.getNotch()
#  end
#  
#  def setIndicator( letter):
#    self.setCurrentPosition(letter)
#  end
#  
end