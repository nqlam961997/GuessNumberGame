package cybersoft.java18.backend.guessnumber.service;

import cybersoft.java18.backend.guessnumber.model.Player;
import cybersoft.java18.backend.guessnumber.store.GameStore;
import cybersoft.java18.backend.guessnumber.store.GameStoreHolder;

public class GameService {
	private static GameService INSTANCE = null;
	private GameStore store;

	public GameService() {
		store = GameStoreHolder.getStore();
	}
	
	public static GameService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GameService();
		}
		return INSTANCE;
	}

	public Player addPlayer(String username, String password, String name) {		
		if (!isValidUser(username, password, name)) {
			return null;
		}
		
		boolean userExisted = store.getPlayers()
				.stream()
				.anyMatch(player -> player.getUsername().equals(username));

		if (userExisted) {
			return null;
		}
		
		Player player = new Player(username, password, name);

		store.getPlayers().add(player);
		
		return player;
	}

	private boolean isValidUser(String username, String password, String name) {
		if (username.isEmpty() || username == null) {
			return false;
		}
		if (password.isEmpty() || password == null) {
			return false;
		}
		if (name.isEmpty() || name == null) {
			return false;
		}
		return true;
	}

	public Player signIn(String username, String password) {
		return store.getPlayers().stream()
				.filter(player -> player.getUsername().equals(username) && player.getPassword().equals(password))
				.findFirst().orElse(null);
	}
	
	public class Result {
		public static final String GREATER_THAN = "Your number is greater than the result";
		public static final String LESS_THAN = "Your number is less than the result";
		public static final String PINGO = "Correctly!";

	}
}
