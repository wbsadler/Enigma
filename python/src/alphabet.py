ASCII_A = ord("A")
LETTERS_IN_ALPHABET = 26

def go_forwards(from_letter, letters=1):
    """ move forwards through the alphabet using modulo arithmetic 
    """
    if type(from_letter).__name__ == "int":
        return (LETTERS_IN_ALPHABET + from_letter + letters) % \
            LETTERS_IN_ALPHABET
    else:
        letter_index = to_letter_index(from_letter)
        letter_index = ((letter_index + letters + LETTERS_IN_ALPHABET) % \
                        LETTERS_IN_ALPHABET)
        return to_letter(letter_index)

def go_backwards(from_letter, number_of_letters=1):
    """ move backwards (default 1 letter) using modulo arithmetic
    """
    return go_forwards(from_letter, -number_of_letters)

def to_letter_index(letter):
    """ get the position of a letter in the alphabet e.g A->1, Z-26
    """
    return ord(letter) - ASCII_A

def to_letter(letter_index):
    """ get the letter that corresponds to a position in the alphabet 
        e.g. 1->A, 2->B
    """
    return chr(letter_index + ASCII_A)

