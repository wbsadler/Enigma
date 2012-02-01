#!/usr/bin/ruby
class Alphabet

  LETTERS_IN_ALPHABET = 26
  ASCII_A = "A"[0]
  
  def initialize()
  end

  def go_forwards(from, letters=1)
    if from.class == Fixnum
      (LETTERS_IN_ALPHABET + from + letters) % LETTERS_IN_ALPHABET
    else
      to_letter((get_position(from) + letters) % LETTERS_IN_ALPHABET)
    end
  end

  def go_backwards(letter, number_of_letters=1)
    go_forwards(letter, -number_of_letters)
  end

  def get_position(letter)
    letter[0] - ASCII_A
  end

  def to_letter(position)
    (position + ASCII_A).chr
  end
end