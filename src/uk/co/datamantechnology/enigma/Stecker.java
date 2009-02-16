package uk.co.datamantechnology.enigma;

public class Stecker {

	private StringBuilder pairings = new StringBuilder();

	public Stecker() {
	}

	public void addPairing(String pairing) {
		// TODO: check that neither of the letters already exists in other
		pairings.append(pairing);
	}

	public void removePairing(String pairing) {
		int startPosition = pairings.toString().indexOf(pairing);
		if (startPosition != -1) {
			pairings.delete(startPosition, startPosition + 2);
		}
	}

	public void setPairings(String steckerPairings) {
		this.pairings = new StringBuilder(steckerPairings);
	}

	public String getPairings() {
		return pairings.toString();
	}

	public char encrypt(char clearText) {
		char encryptedCharacter = clearText;
		// loop over pairings to see if the letter is in a pairing
		if (isSteckered(encryptedCharacter)) {
			int position = pairings.toString().indexOf(encryptedCharacter);
			// if it's even swap with previous
			if (position % 2 == 1) {
				encryptedCharacter = pairings.charAt(position - 1);
			} else {
				encryptedCharacter = pairings.charAt(position + 1);
			}
		}
		return encryptedCharacter;
	}

	public boolean isSteckered(char letter) {
		return pairings.toString().indexOf(letter) != -1;
	}

}
