#!/usr/bin/ruby
class Alphabet
  
  LETTERS_IN_ALPHABET = 26
  ASCII_A = "A"[0]
  
  def initialize()
  end
  
  def go_forwards(from_letter, letters=1)
    # move forwards through the alphabet using modulo arithmetic
    if from_letter.class == Fixnum
      return (LETTERS_IN_ALPHABET + from_letter + letters) % LETTERS_IN_ALPHABET
    else
      letter_index = to_letter_index(from_letter)
      letter_index = ((letter_index + letters + LETTERS_IN_ALPHABET) % LETTERS_IN_ALPHABET)
      return to_letter(letter_index)
    end 
  end
  
  def go_backwards(from_letter, number_of_letters=1)
    # move backwards (default 1 letter) using modulo arithmetic
    #
    return go_forwards(from_letter, -number_of_letters)
  end
  
  def to_letter_index(letter)
    # get the position of a letter in the alphabet e.g A->1, Z-26
    return letter[0] - ASCII_A
  end
  
  def to_letter(letter_index)
    # get the letter that corresponds to a position in the alphabet 
    # eg 1->A, 2->B
    return (letter_index + ASCII_A).chr
  end
end