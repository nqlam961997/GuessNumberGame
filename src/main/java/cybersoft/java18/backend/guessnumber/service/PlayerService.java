package cybersoft.java18.backend.guessnumber.service;

import cybersoft.java18.backend.guessnumber.model.Player;
import cybersoft.java18.backend.guessnumber.repository.PlayerRepository;

public class PlayerService {
    private static PlayerService INSTANCE = null;

    private final PlayerRepository playerRepository;

    private PlayerService() {
        this.playerRepository = new PlayerRepository();
    }

    public static PlayerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerService();
        }
        return INSTANCE;
    }

    public Player register(String username, String password, String name) {
        if (!isValidUser(username, password, name)) {
            return null;
        }

        boolean userExisted = playerRepository.existedByUsername(username);

        if (userExisted) {
            return null;
        }

        Player player = new Player(username, password, name);

        playerRepository.save(player);

        return player;
    }

    public Player signIn(String username, String password) {
        return playerRepository.findByUsernameAndPassword(username, password);
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
}
