package cybersoft.java18.backend.guessnumber.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class GameSession implements Serializable {
	private static Random random = null;
	private String id;
	private int targetNumber;
	private List<Guess> guess;
	private LocalDateTime startTime;
	private String username;
	private LocalDateTime endTime;
	private boolean isCompleted;
	private boolean isActive;

	public GameSession() {
	}
	public GameSession(String username) {
		this.id = "GAME" + String.format("%05d", getRandomInt(100_000));
		this.targetNumber = getRandomInt(1_000);
		this.guess = new ArrayList<>();
		this.startTime = LocalDateTime.now();
		this.username = username;
	}

	private static int getRandomInt(int max) {
		if (random == null)
			random = new Random();

		return random.nextInt(max - 1) + 1;
	}

	public String getId() {
		return id;
	}

	public List<Guess> getGuess() {
		return guess;
	}

	public void setGuess(List<Guess> guesses) {
		this.guess = guesses;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Integer getTargetNumber() {
		return targetNumber;
	}

	public boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public GameSession id(String id) {
		this.id = id;
		return this;
	}

	public GameSession targetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
		return this;
	}

	public GameSession startTime(LocalDateTime startTime) {
		this.startTime = startTime;
		return this;
	}

	public GameSession endTime(LocalDateTime endTime) {
		this.endTime = endTime;
		return this;
	}

	public GameSession isCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
		return this;
	}

	public GameSession isActive(boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	public GameSession username(String username) {
		this.username = username;
		return this;
	}

	@Override
	public String toString() {
		return String.format("[id: %s, active: %s]", this.id, this.isActive);
	}
}
