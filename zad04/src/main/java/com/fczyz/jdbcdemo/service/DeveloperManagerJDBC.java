package com.fczyz.jdbcdemo.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fczyz.jdbcdemo.domain.ComputerGame;

public class DeveloperManagerJDBC implements ComputerGameManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableComputerGame = "CREATE TABLE Developer(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20) UNIQUE, nip int,)";

	private PreparedStatement addDeveloperStmt;
	private PreparedStatement deleteAllDevelopersStmt;
	private PreparedStatement getAllDevelopersStmt;

	private Statement statement;

	public DeveloperManagerJDBC() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Developer".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableComputerGame);

			addDeveloperStmt = connection.prepareStatement("INSERT INTO Developer (name, nip) VALUES (?, ?)");
			deleteAllDevelopersStmt = connection.prepareStatement("DELETE FROM ComputerGame");
			getAllDevelopersStmt = connection.prepareStatement("SELECT id, title, release, price, multiplayer FROM ComputerGame");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void clearComputerGames() {
		try {
			deleteAllComputerGamesStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int addComputerGame(ComputerGame computergame) {
		int count = 0;
		try {
			addComputerGameStmt.setString(1, computergame.getTitle());
			addComputerGameStmt.setString(2,  computergame.getRelease());
			addComputerGameStmt.setDouble(3, computergame.getPrice());
			addComputerGameStmt.setBoolean(4, computergame.getMultiplayer());

			count = addComputerGameStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<ComputerGame> getAllComputerGames() {
		List<ComputerGame> computergames = new ArrayList<ComputerGame>();

		try {
			ResultSet rs = getAllComputerGamesStmt.executeQuery();

			while (rs.next()) {
				ComputerGame g = new ComputerGame();
				g.setId(rs.getInt("id"));
				g.setTitle(rs.getString("title"));
				g.setRelease(rs.getString("release"));
				g.setPrice(rs.getDouble("price"));
				g.setMultiplayer(rs.getBoolean("multiplayer"));
				computergames.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computergames;
	}

	@Override
	public void addAllComputerGames(List<ComputerGame> computergames) {

		try {
			connection.setAutoCommit(false);
			for (ComputerGame computergame : computergames) {
				addComputerGameStmt.setString(1, computergame.getTitle());
				addComputerGameStmt.setString(2, computergame.getRelease());
				addComputerGameStmt.setDouble(3, computergame.getPrice());
				addComputerGameStmt.setBoolean(4, computergame.getMultiplayer());
				addComputerGameStmt.executeUpdate();
			}
			connection.commit();
			
		} catch (SQLException exception) {
			
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				//!!!! ALARM
			}
		}

	}
	/*public void findByName(String name)
	{
		List<ComputerGame> computergames = new ArrayList<ComputerGame>();
		try
		{
			
		}
		catch()
		{}
		
	}
	public void findOlderThan(Date date)
	{
	
	}*/

}
