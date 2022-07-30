package cybersoft.java18.backend.guessnumber;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class GameTest {
	private Game game;
	
	private Consumer<Game> startGame = (game) -> game.start();
	
	@BeforeAll
	public void setupTest() {
		game = new Game();
	}
	
	@Test
	public void shouldStartedSuccessfully() {
		assertDoesNotThrow(() -> game.start());
	}
}
