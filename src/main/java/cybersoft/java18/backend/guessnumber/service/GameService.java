package cybersoft.java18.backend.guessnumber.service;

import java.util.List;

import cybersoft.java18.backend.guessnumber.model.GameSession;
import cybersoft.java18.backend.guessnumber.model.Guess;
import cybersoft.java18.backend.guessnumber.repository.GuessRepository;
import cybersoft.java18.backend.guessnumber.repository.GameSessionRepository;

public class GameService {
	private static GameService INSTANCE = null;
	private final GameSessionRepository gameSessionRepository;
	private final GuessRepository guessRepository;

	public GameService() {
		gameSessionRepository = new GameSessionRepository();
		guessRepository = new GuessRepository();
	}

	public static GameService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GameService();
		}
		return INSTANCE;
	}

	public GameSession createGame(String username) {
		var round = new GameSession(username);
		round.setActive(true);
		// Deactive other game
		gameSessionRepository.deactivateAllGame(username);

		gameSessionRepository.save(round);

		return round;
	}

	public GameSession getCurrentGame(String username) {
		List<GameSession> games = gameSessionRepository.findByUsername(username);

		// get current active game, if there's no game -> create new one
		var activeGame = games.isEmpty()
				? createGame(username)
				: games.stream()
				.filter(GameSession::isActive)
				.findFirst()
				.orElseGet(() -> createGame(username));

		// get guess list and add to game
		activeGame.setGuess(guessRepository
				.findBySession(activeGame.getId()));

		return activeGame;
	}

	public GameSession skipAndPlayNewGame(String username) {
		return createGame(username);
	}

	public GameSession getGameSession(String id) {
		GameSession gameSession = gameSessionRepository.findById(id);
		gameSession.setGuess(guessRepository.findBySession(id));
		return gameSession;
	}

	public void saveGuess(Guess guess) {
		guessRepository.save(guess);
	}

	public void completeGame(String sessionId) {
		gameSessionRepository.completeGame(sessionId);
	}

	public class Result {
		public static final String GREATER_THAN = "Your number is greater than the result";
		public static final String LESS_THAN = "Your number is less than the result";
		public static final String PINGO = "Correctly!";

	}
}
