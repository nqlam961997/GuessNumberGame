package cybersoft.java18.backend.guessnumber.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cybersoft.java18.backend.guessnumber.model.Player;

public class PlayerRepository extends AbstractRepository<Player> {

	public Player findByUsernameAndPassword(String username, String password) {
		return executeQuerySingle(connection -> {
			String query = "select username, password, name " +
					"from player " +
					"where username = ? and password = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return new Player(
						resultSet.getString("username"),
						resultSet.getString("password"),
						resultSet.getString("name")
				);
			}
			return null;
		});
	}

	public boolean existedByUsername(String username) {
		Player player = executeQuerySingle(connection -> {
			String query = "select username from player where username = ?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return new Player(
						resultSet.getString("username"),
						resultSet.getString("password"),
						resultSet.getString("name")
				);
			}
			return null;
		});
		return player != null;
	}

	public void save(Player newUser) {
		executeUpdate(connection -> {
			String query = "insert into player(username, password, name) values(?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getName());

			return statement.executeUpdate();
		});
	}

}
