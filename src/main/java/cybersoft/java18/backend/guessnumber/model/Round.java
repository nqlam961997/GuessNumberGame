package cybersoft.java18.backend.guessnumber.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Round {
	private static int idNum = 1;
	private static Random random = null;
	private String id;
	private List<GuessCounter> guessCounters;
	private LocalDateTime starTime;
	private LocalDateTime endTime;
	private Integer randomNumber;
	private boolean endGame;
	private String player; //username
	
	public Round(String username) {
		this.id = "GAME" + String.format("%05d", idNum++);
		this.randomNumber = getRandomInt();
		guessCounters = new ArrayList<GuessCounter>();
		starTime = LocalDateTime.now();
		this.player = username;
	}
	
	private int getRandomInt() {
		if (random == null) {
			random = new Random();
		}
		return random.nextInt(1000-1) + 1;
	}

	public String getId() {
		return id;
	}

	public List<GuessCounter> getGuessCounter() {
		return guessCounters;
	}

	public void setGuessCounter(List<GuessCounter> guessCounters) {
		this.guessCounters = guessCounters;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Integer getRandomNumber() {
		return randomNumber;
	}

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

}
