package cybersoft.java18.backend.guessnumber.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import cybersoft.java18.backend.guessnumber.model.GameSession;

public class GameSessionRepository extends AbstractRepository<GameSession> {
	public GameSessionRepository() {
	}

	public void save(GameSession gameSession) {
		executeUpdate(connection -> {
			String query = "insert into game_session" +
					"(id, target_number, start_time, username, is_completed, is_active) " +
					"values(?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, gameSession.getId());
			statement.setInt(2, gameSession.getTargetNumber());
			statement.setTimestamp(3, Timestamp.from(
					gameSession.getStartTime().toInstant(ZoneOffset.of("+07:00")))
			);
			statement.setString(4, gameSession.getUsername());
			statement.setInt(5, gameSession.getIsCompleted() ? 1 : 0);
			statement.setInt(6, gameSession.isActive() ? 1 : 0);

			return statement.executeUpdate();
		});
	}

	public List<GameSession> findByUsername(String username) {
		return executeQuery(connection -> {
			String query = "select * from game_session where username=?";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, username);

			ResultSet result = statement.executeQuery();

			List<GameSession> gameSessionList = new ArrayList<>();

			if (!result.next()) return gameSessionList;

			while (result.next()) {
				gameSessionList.add(new GameSession()
						.id(result.getString("id"))
						.targetNumber(result.getInt("target_number"))
						.startTime(getDateTimeFromResultSet("start_time", result))
						.endTime(getDateTimeFromResultSet("end_time", result))
						.isCompleted(result.getInt("is_completed") == 1)
						.isActive(result.getInt("is_active") == 1)
						.username(result.getString("username"))
				);
			}

			return gameSessionList;
		});
	}

	public GameSession findById(String id) {
		return executeQuerySingle(connection -> {
			String query = "select * from game_session where id=?";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, id);

			ResultSet result = statement.executeQuery();

			if (!result.next()) return null;

			return new GameSession()
					.id(result.getString("id"))
					.targetNumber(result.getInt("target_number"))
					.startTime(getDateTimeFromResultSet("start_time", result))
					.endTime(getDateTimeFromResultSet("end_time", result))
					.isCompleted(result.getInt("is_completed") == 1)
					.isActive(result.getInt("is_active") == 1)
					.username(result.getString("username"));
		});
	}

	public int completeGame(String sessionId) {
		return executeUpdate(connection -> {
			String query = "update game_session set is_completed = 1, end_time=? where id=?";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setTimestamp(1, Timestamp.from(
					LocalDateTime.now().toInstant(ZoneOffset.of("+07:00")))
			);
			statement.setString(2, sessionId);

			return statement.executeUpdate();
		});
	}

	public int deactivateAllGame(String username) {
		return executeUpdate(connection -> {
			String query = "update game_session set is_active = 0 where username=?";

			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, username);

			return statement.executeUpdate();
		});
	}

	private LocalDateTime getDateTimeFromResultSet(String columnName, ResultSet result) {
		Timestamp time = null;

		try {
			time = result.getTimestamp(columnName);
		} catch (SQLException e) {
			return null;
		}

		if (time == null) {
			return null;
		} else {
			return time.toLocalDateTime();
		}
	}
}
