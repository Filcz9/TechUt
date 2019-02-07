package ug.fczyz.techut.zad03.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import ug.fczyz.techut.zad03.jdbcdemo.domain.ComputerGame;

import java.sql.Date;

public class ComputerGameServiceJDBC implements ComputerGameService {
	final static String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	
	private Connection connection =  null;
	   private java.sql.Statement statement = null;

	private final String CREATE_TABLE_SQL = "CREATE TABLE ComputerGame(id int GENERATED BY DEFAULT AS IDENTITY, name varchar(50) ,buttons int, price double, releaseDate Date)";
	private PreparedStatement insertStmt;
	private PreparedStatement selectAllStmt;
	private PreparedStatement selectAllButtonsMoreThanOrEqualStmt;
	private PreparedStatement selectByIdStmt;
	private PreparedStatement selectByNameStmt;
	private PreparedStatement deleteAllStmt;
	private PreparedStatement deleteByIdStmt;

	 
	public ComputerGameServiceJDBC() {
	
		try {
			connection = DriverManager.getConnection(URL);
		    statement = connection.createStatement();
	
		ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
		boolean tableExists = false;
		while(rs.next())
		{
			if("ComputerGame".equalsIgnoreCase(rs.getString("table_name")))
			{
				tableExists = true;
				break;
			}
		}
		if(!tableExists) {
			statement.execute(CREATE_TABLE_SQL);
		}
			
		insertStmt = connection.prepareStatement("INSERT INTO ComputerGame(name,buttons,price,releaseDate) VALUES (?,?,?,?)");
		selectAllStmt = connection.prepareStatement("SELECT * FROM ComputerGame");
		selectByIdStmt = connection.prepareStatement("SELECT id,name,buttons,price,releaseDate from ComputerGame where id=?");
		deleteAllStmt = connection.prepareStatement("DELETE FROM ComputerGame");
		deleteByIdStmt = connection.prepareStatement("DELETE FROM ComputerGame where id=?");
		selectByNameStmt = connection.prepareStatement("SELECT id,name,buttons,price,releaseDate from ComputerGame where name=?");
		selectAllButtonsMoreThanOrEqualStmt = connection.prepareStatement("SELECT id,name,buttons,price,releaseDate from ComputerGame where buttons >=?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void addComputerGame(ComputerGame computerGame)
	{
	
		try {
			insertStmt.setString(1, computerGame.getName());
			insertStmt.setInt(2, computerGame.getAgeRes());
			insertStmt.setDouble(3, computerGame.getPrice());
			computerGame.getReleaseDate().setYear(computerGame.getReleaseDate().getYear()-1900);
			insertStmt.setDate(4, computerGame.getReleaseDate());
			insertStmt.executeUpdate();
			
		} catch(SQLException ex)
		{
			ex.printStackTrace();
		}
	}

	public boolean addAllComputerGames(List<ComputerGame> computerGames) {
		try {
			connection.setAutoCommit(false);
			for(ComputerGame computerGame : computerGames)
			{
				insertStmt.setString(1, computerGame.getName());
				insertStmt.setInt(2, computerGame.getAgeRes());
				insertStmt.setDouble(3, computerGame.getPrice());
				//computerGame.getRelease().setYear(computerGame.getRelease().getYear()-1900);
				insertStmt.setDate(4, computerGame.getReleaseDate());
				
				insertStmt.executeUpdate();
			}
			connection.commit();
			
			return true;
		} catch (Exception e) {
			System.out.println("Przerwano dodawanie");
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {		
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	public List<ComputerGame> getAllComputerGame()
	{
	
		List<ComputerGame> list = new ArrayList<ComputerGame>();
		try {
			ResultSet rs = selectAllStmt.executeQuery();
		
		while(rs.next())
		{
			ComputerGame computerGame = new ComputerGame();
			int mouseid = rs.getInt("id");
			String name = rs.getString("name");
			int buttons =  rs.getInt("buttons");
			double price = rs.getDouble("price");
			Date releaseDate = rs.getDate("releaseDate");
			computerGame.setName(name);
			computerGame.setAgeRes(buttons);
			computerGame.setReleaseDate(releaseDate);
			computerGame.setPrice(price);
			computerGame.setId(mouseid);
			list.add(computerGame);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ComputerGame> getAllComputerGameAgeResMoreThanOrEqual(int buttons)
	{
		
		ResultSet rs;
		List<ComputerGame> list = new ArrayList<>();
		try {
			selectAllButtonsMoreThanOrEqualStmt.setInt(1, buttons);
			rs = selectAllButtonsMoreThanOrEqualStmt.executeQuery();
		while(rs.next())
		{
			ComputerGame computerGame = new ComputerGame();
			int mouseid = rs.getInt("id");
			String name = rs.getString("name");
			int bbuttons =  rs.getInt("buttons");
			double price = rs.getDouble("price");
			Date releaseDate = rs.getDate("releaseDate");
			computerGame.setName(name);
			computerGame.setAgeRes(bbuttons);
			computerGame.setReleaseDate(releaseDate);
			computerGame.setPrice(price);
			computerGame.setId(mouseid);
			list.add(computerGame);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ComputerGame getComputerGameById(int id)
	{
		ComputerGame computerGame = null;
		try {
			selectByIdStmt.setInt(1, id);
		ResultSet rs = selectByIdStmt.executeQuery();
		computerGame = new ComputerGame();
		while(rs.next())
		{
			int mouseid = rs.getInt("id");
			String name = rs.getString("name");
			int buttons =  rs.getInt("buttons");
			double price = rs.getDouble("price");
			Date releaseDate = rs.getDate("releaseDate");
			computerGame.setName(name);
			computerGame.setAgeRes(buttons);
			computerGame.setReleaseDate(releaseDate);
			computerGame.setPrice(price);
			computerGame.setId(mouseid);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerGame;
	}
	public ComputerGame getComputerGameByName(String name)
	{
		ComputerGame computerGame = null;
		try {
			selectByNameStmt.setString(1, name);
		ResultSet rs = selectByNameStmt.executeQuery();
		computerGame = new ComputerGame();
		while(rs.next())
		{
			int mouseid = rs.getInt("id");
			int buttons =  rs.getInt("buttons");
			double price = rs.getDouble("price");
			Date releaseDate = rs.getDate("releaseDate");
			computerGame.setName(name);
			computerGame.setAgeRes(buttons);
			computerGame.setReleaseDate(releaseDate);
			computerGame.setPrice(price);
			computerGame.setId(mouseid);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computerGame;
	}
	public void deleteAllComputerGames(){
		try {
			deleteAllStmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public boolean deleteComputerGameById(int id) {
		try {
			deleteByIdStmt.setInt(1, id);
			int result = deleteByIdStmt.executeUpdate();
			if(result>0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
}
