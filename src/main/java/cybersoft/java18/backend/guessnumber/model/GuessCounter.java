package cybersoft.java18.backend.guessnumber.model;

public class GuessCounter {
	private final int guessNumber;
	private String result;
	
	public GuessCounter(int guessNumber) {
		this.guessNumber = guessNumber;
	}

	public int getGuessNumber() {
		return guessNumber;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
