package fr_catalog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 11.11.2016.
 */
public class DBHelper {
	private static DBHelper instance;
	private String dbPath, dbName, credentials;
	private Connection connection;

	private DBHelper() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DBHelper getInstance() {
		if (instance == null) {
			instance = new DBHelper();
		}
		return instance;
	}

	public void connectToDB() throws UnsupportedEncodingException {
		try {
			String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = null;
			try {
				decodedPath = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			decodedPath = decodedPath.substring(0, decodedPath.lastIndexOf("/") + 1);

			File db_folder = new File(decodedPath + "db");
			if (!db_folder.exists()) {
				db_folder.mkdirs();
			}
			String dbName = "db/catalog.sqlite";
			connection = DriverManager.getConnection("jdbc:sqlite:" + decodedPath + new String(dbName.getBytes("UTF-8"), "UTF-8"));
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void disconnectFromDB() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void createTables() {
		PreparedStatement statement = null;
		String authors = "CREATE TABLE IF NOT EXISTS 'authors' (" +
				"author_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"first_name TEXT NOT NULL, " +
				"second_name TEXT NOT NULL, " +
				"surname TEXT NOT NULL, " +
				"UNIQUE (first_name, surname)" +
				")";

		String novels = "CREATE TABLE IF NOT EXISTS 'novels' (" +
				"'novel_id'  INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'book_id' INTEGER, " +
				"author_id INTEGER NOT NULL, " +
				"'novel_title' TEXT NOT NULL UNIQUE, " +
				"'published_ad' TEXT NOT NULL, " +
				"'begins_dr' TEXT NOT NULL, " +
				"'ends_dr' TEXT NOT NULL, " +
				"'year_dr' TEXT NOT NULL, " +
				"'have_read' BOOLEAN NOT NULL, " +
				"FOREIGN KEY (book_id) REFERENCES books (book_id), " +
				"FOREIGN KEY (author_id) REFERENCES authors (author_id)" +
				")";

		String books = "CREATE TABLE IF NOT EXISTS 'books'(" +
				"'book_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'series_id' INTEGER, " +
				"'book_title' TEXT NOT NULL UNIQUE, " +
				"FOREIGN KEY (series_id) REFERENCES series (series_id))";

		String series = "CREATE TABLE IF NOT EXISTS 'series'(" +
				"'series_id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"'series_title' TEXT NOT NULL UNIQUE)";

		createDB(connection, statement, authors);
		createDB(connection, statement, novels);
		createDB(connection, statement, books);
		createDB(connection, statement, series);
	}

	private void createDB(Connection connection, PreparedStatement statement, String sql) {
		try {
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void insertAuthor(String firstName, String secondName, String surname) {
		String statement = "INSERT OR IGNORE INTO authors (first_name, second_name, surname) VALUES (?, ?, ?)";
		prepareThreeParameters(statement, connection, firstName, secondName, surname);
	}

	public void insertSeries(String series) {
		String statement = "INSERT OR IGNORE INTO series (series_title) VALUES (?)";
		prepareOneParameter(statement, connection, series);
	}

	public void insertBook(String book, String series) {

	}

	public void insertBookStandAlone(String book) {

	}

	public void insertNovel(String novel){

	}



	public void updateAuthor(String author) {

	}

	public void updateSeries(String series) {

	}

	public void updateBook(String book) {

	}

	public void updateNovel(String novel){

	}


	public void deleteAuthor(String author) {

	}

	public void deleteSeries(String series) {

	}

	public void deleteBook(String book) {

	}

	public void deleteNovel(String novel){

	}




	private void prepareOneParameter(String sql, Connection connection, String first) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, first);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
		}
	}




	private void prepareThreeParameters(String sql, Connection connection, String first, String second, String third) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, first);
			statement.setString(2, second);
			statement.setString(3, third);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(statement);
		}
	}

	private void closeStatement(PreparedStatement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
