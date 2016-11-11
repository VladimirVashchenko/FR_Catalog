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

		String novels = "CREATE TABLE IF NOT EXISTS 'novels' (" +
				"'novel_id'  integer PRIMARY KEY AUTOINCREMENT, " +
				"'book_id' integer, " +
				"'series_id' integer, " +
				"'novel_title' text UNIQUE, " +
				"'author' text, " +
				"'published' text, " +
				"'begins' text, " +
				"'ends' text, " +
				"'year' text, " +
				"'have_read' text, " +
				"FOREIGN KEY (book_id) REFERENCES books (book_id)," +
				"FOREIGN KEY (series_id) REFERENCES series (series_id))";

		String books = "CREATE TABLE IF NOT EXISTS 'books'(" +
				"'book_id' integer PRIMARY KEY AUTOINCREMENT, " +
				"'series_id' integer, " +
				"'book_number' integer, " +
				"'book_title' text NOT NULL, " +
				"FOREIGN KEY (series_id) REFERENCES series (series_id))";

		String series = "CREATE TABLE IF NOT EXISTS 'series'(" +
				"'series_id' integer PRIMARY KEY AUTOINCREMENT, " +
				"'series_title' text)";

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
}
