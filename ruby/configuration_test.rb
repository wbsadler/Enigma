#!/usr/bin/ruby
require "Configuration"
require "test/unit"

class ConfigurationTest < Test::Unit::TestCase
  
  def setup()
    @TOTAL_NUMBER_OF_ROTORS = 5
    @NUMBER_OF_LETTERS_IN_ALPHABET = 26
    #  TOTAL_NUMBER_OF_REFLECTORS = 2
    @ASCII_A = 65
    @cfg = Configuration.new
  end
  
  def test_get_rotor_wirings_should_return_an_array
    rotorWirings = @cfg.get_rotor_wirings
    assert(rotorWirings.kind_of?(Array))
  end
  
  
  def test_get_rotor_wirings_should_return_correct_length
    rotorWirings = @cfg.get_rotor_wirings
    assert(rotorWirings.length == @TOTAL_NUMBER_OF_ROTORS,"Oops!  Rotors is not correct length " + @TOTAL_NUMBER_OF_ROTORS.to_s)
  end
  
  
  def test_get_rotor_wirings_should_return_array_of_scrambled_alphabet
    rotorWirings = @cfg.get_rotor_wirings()
    rotorWirings.each {
      |wiring| 
      assert_equal(wiring.to_s.length,@NUMBER_OF_LETTERS_IN_ALPHABET)
    }
  end


  def test_get_rotor_wirings_should_contain_no_duplicate_characters
    rotorWirings = @cfg.get_rotor_wirings()
    rotorWirings.each {
      |wiring|
      1.upto(@NUMBER_OF_LETTERS_IN_ALPHABET){
        |j|
         c = (j + @ASCII_A - 1).chr
         assert(wiring.count(c) <= 1)
      }
    }
  end

  def testGetRotorWiringsContainsAllLettersInAlphabet()
    rotorWirings = @cfg.get_rotor_wirings()
    rotorWirings.each {
      |wiring|
      1.upto(@NUMBER_OF_LETTERS_IN_ALPHABET){
        |j|
        c = (j + @ASCII_A - 1).chr
        puts wiring.to_s
        puts c
        puts wiring.index(c)
        assert(wiring.index(c) >= 0)
      }
    }
  end
      
                
#    def testGetTotalNumberOfReflectors(self):
#        assert self.cfg.get_total_number_of_reflectors() == \
#            self.TOTAL_NUMBER_OF_REFLECTORS, "Wrong number of reflectors!"
#
#    def testGetTotalNumberOfRotors(self):
#        assert self.cfg.get_total_number_of_rotors() == \
#            self.TOTAL_NUMBER_OF_ROTORS, "Wrong number of rotors"
#
end
#def suite():
#    return unittest.TestLoader().loadTestsFromTestCase(ConfigurationTest)
#
#if __name__ == '__main__':
#    unittest.main()
