#!/usr/bin/ruby
require "test/unit"
require "alphabet"
class AlphabetTest < Test::Unit::TestCase
  
  def testGoForwardThroughalphabet()
    alphabet = Alphabet.new()
    expected = 'A'
    actual = alphabet.go_forwards('Z')
    assert(expected==actual)
    
    expected = 'B'
    actual = alphabet.go_forwards('A')
    assert(expected==actual)
    
    expected = 'H'
    actual = alphabet.go_forwards('G')
    assert(expected==actual)
    
    expected = 'Z'
    actual = alphabet.go_forwards('Y')
    assert(expected==actual)
    
    expected = 'E'
    actual = alphabet.go_forwards('A', 4)
    assert(expected==actual)
    
    expected = 'L'
    actual = alphabet.go_forwards('A', 11)
    assert(expected==actual)
  end
  
  def test_go_back_through_alphabet()
    alphabet = Alphabet.new()
    expected = 'Y'
    actual = alphabet.go_backwards('Z')
    assert(expected==actual)
    
    expected = 'B'
    actual = alphabet.go_backwards('C')
    assert(expected==actual)
    
    expected = 'H'
    actual = alphabet.go_backwards('I')
    assert(expected==actual)
    
    expected = 'Z'
    actual = alphabet.go_backwards('A')
    assert(expected==actual)
    
    expected = 'W'
    actual = alphabet.go_backwards('A', 4)
    assert(expected==actual)
    
    expected = 'L'
    actual = alphabet.go_backwards('W', 11)
    assert(expected==actual)
    
    expected = 'A'
    actual = alphabet.go_backwards('A', 0)
    assert(expected==actual)
  end    
  
  def test_get_position()
    alphabet = Alphabet.new()
    expected = 0
    actual = alphabet.get_position('A')
    assert(expected==actual)
    
    expected = 1
    actual = alphabet.get_position('B')
    assert(expected==actual)
    
    expected = 25
    actual = alphabet.get_position('Z')
    assert(expected==actual)
    
  end
  
  def test_to_letter()
    alphabet = Alphabet.new()
    expected = 'A'
    actual = alphabet.to_letter(0)
    assert(expected==actual)
    
    expected = 'M'
    actual = alphabet.to_letter(12)
    assert(expected==actual)
    
    expected = 'Z'
    actual = alphabet.to_letter(25)
    assert(expected==actual)
  end
  
  def test_go_forwards()
    alphabet = Alphabet.new()
    expected = 1
    actual = alphabet.go_forwards(1, 0)
    assert(expected==actual,"Ouch!  go_forwards(0,1) didn't return 1! expected: " + expected.to_s() + " actual: " + actual.to_s())
    
    expected = 3
    actual = alphabet.go_forwards(25, 4)
    assert(expected==actual)
    
    expected = 24
    actual = alphabet.go_forwards(0, -2)
    assert(expected==actual)
  end
  
end