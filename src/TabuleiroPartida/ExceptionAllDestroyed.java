package TabuleiroPartida;

public class ExceptionAllDestroyed extends Exception {
	private String losingPlayer;
	public ExceptionAllDestroyed(String boundPlayer) {
		super();
		losingPlayer = boundPlayer;
		
	}
	public String getLosingPlayer(){
		return losingPlayer;
	}

}
