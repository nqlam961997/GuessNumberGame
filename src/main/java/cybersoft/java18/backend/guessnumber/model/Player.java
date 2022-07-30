package cybersoft.java18.backend.guessnumber.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String username;
	private String password;
	private String name;
	private List<Round> rounds;
	
	public Player(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
		rounds = new ArrayList<Round>();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public List<Round> getRounds() {
		return rounds;
	}
}
