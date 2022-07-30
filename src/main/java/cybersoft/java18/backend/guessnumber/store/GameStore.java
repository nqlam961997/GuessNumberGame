package cybersoft.java18.backend.guessnumber.store;

import java.util.ArrayList;
import java.util.List;

import cybersoft.java18.backend.guessnumber.model.GuessCounter;
import cybersoft.java18.backend.guessnumber.model.Player;
import cybersoft.java18.backend.guessnumber.model.Round;

public class GameStore {
	private List<Player> players;
	private List<Round> rounds;
	private List<GuessCounter> guessCounters;
	
	GameStore() {
		players = new ArrayList<Player>();
		rounds = new ArrayList<Round>();
		guessCounters = new ArrayList<GuessCounter>();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public List<GuessCounter> getGuessCounters() {
		return guessCounters;
	}

}
