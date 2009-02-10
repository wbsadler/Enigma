package uk.co.datamantechnology.enigma;

public class Reflector {
	
	private static Configuration cfg = new Configuration();
	private static int totalNumberOfReflectors; // total number of reflectors
												// available to operators
	private String wiring;
	
	public Reflector(int reflectorNumber){
		loadWiring(cfg.getReflectorWirings()[reflectorNumber-1]);		
	}
	
	public static int getTotalNumberOfReflectors() {
		return totalNumberOfReflectors;
	}
	
	public void loadWiring(String wiring) {
		// TODO Auto-generated method stub
		this.wiring = wiring;
	}
	
	public char reflect(char entryLetter){
		int letterIndex = (int) entryLetter - 65;
		System.out.println(entryLetter + " is reflected to " + this.wiring.charAt(letterIndex));
		return this.wiring.charAt(letterIndex);
	}

}
