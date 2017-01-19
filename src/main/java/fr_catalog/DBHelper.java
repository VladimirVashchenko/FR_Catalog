package fr_catalog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;

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
				"author_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
				"first_name TEXT NOT NULL, " +
				"second_name TEXT NOT NULL, " +
				"surname TEXT NOT NULL, " +
				"UNIQUE (first_name, surname)" +
				")";

		String singleNovels = "CREATE TABLE IF NOT EXISTS 'single_novels'(" +
				"'novel_id' INTEGER NOT NULL, " +
				"'a_book' BOOLEAN NOT NULL, " +
				"FOREIGN KEY (novel_id) REFERENCES novels (novel_id)" +
				")";

		String novels = "CREATE TABLE IF NOT EXISTS 'novels' (" +
				"'novel_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
				"'novel_title' TEXT NOT NULL UNIQUE, " +
				"'published_ad' TEXT NOT NULL, " +
				"'begins_dr' TEXT NOT NULL, " +
				"'ends_dr' TEXT NOT NULL" +
				")";

		String anthologies = "CREATE TABLE IF NOT EXISTS 'anthologies'(" +
				"'anthology_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
				"'anthology_title' TEXT NOT NULL UNIQUE, " +
				"'published_ad' TEXT NOT NULL" +
				");";

		String series = "CREATE TABLE IF NOT EXISTS 'series'(" +
				"'series_id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
				"'series_title' TEXT NOT NULL UNIQUE)";

		String authorNovel = "CREATE TABLE IF NOT EXISTS 'author_novel'(" +
				"'author_id' INTEGER NOT NULL, " +
				"'novel_id' INTEGER NOT NULL," +
				"FOREIGN KEY (author_id) REFERENCES authors (author_id), " +
				"FOREIGN KEY (novel_id) REFERENCES novels (novel_id)," +
				"UNIQUE (author_id, novel_id)" +
				");";

		String anthologyNovel = "CREATE TABLE IF NOT EXISTS 'anthology_novel'(" +
				"'anthology_id' INTEGER NOT NULL, " +
				"'novel_id' INTEGER NOT NULL," +
				"FOREIGN KEY (anthology_id) REFERENCES anthologies (anthology_id), " +
				"FOREIGN KEY (novel_id) REFERENCES novels (novel_id)" +
				"UNIQUE (anthology_id, novel_id)" +
				");";

		String seriesNovel = "CREATE TABLE IF NOT EXISTS 'series_novel'(" +
				"'series_id' INTEGER NOT NULL, " +
				"'novel_id' INTEGER NOT NULL, " +
				"'number' INTEGER NOT NULL, " +
				"FOREIGN KEY (series_id) REFERENCES series (series_id), " +
				"FOREIGN KEY (novel_id) REFERENCES novels (novel_id), " +
				"UNIQUE (series_id, novel_id)" +
				");";

		String authorAnthology = "CREATE TABLE IF NOT EXISTS 'author_anthology'(" +
				"'author_id' INTEGER NOT NULL, " +
				"'anthology_id' INTEGER NOT NULL," +
				"FOREIGN KEY (author_id) REFERENCES authors (author_id), " +
				"FOREIGN KEY (anthology_id) REFERENCES anthologies (anthology_id)" +
				"UNIQUE (author_id, anthology_id)" +
				");";

		String read = "CREATE TABLE IF NOT EXISTS 'read'(" +
				"novel_id INTEGER NOT NULL UNIQUE, " +
				"'read' BOOLEAN NOT NULL" +
				");";

		createDB(connection, statement, authors);
		createDB(connection, statement, singleNovels);
		createDB(connection, statement, novels);
		createDB(connection, statement, anthologies);
		createDB(connection, statement, series);
		createDB(connection, statement, authorNovel);
		createDB(connection, statement, anthologyNovel);
		createDB(connection, statement, seriesNovel);
		createDB(connection, statement, authorAnthology);
		createDB(connection, statement, read);
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

	public void selectNovels(boolean isBook){
		String select = "SELECT " +
				"n.novel_id, n.novel_title, n.published_ad, n.begins_dr, n.ends_dr, " +
				"a.author_id, a.first_name, a.second_name, a.surname, " +
				"sn.a_book, " +
				"r.read " +
				"FROM novels n " +
				"JOIN single_novels sn ON n.novel_id = sn.novel_id " +
				"JOIN author_novel an ON n.novel_id = an.novel_id " +
				"JOIN authors a ON a.author_id = an.author_id " +
				"JOIN read r ON sn.novel_id = r.novel_id " +
				"WHERE a_book = ?";
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {

			statement = connection.prepareStatement(select);
			statement.setInt(1, isBook ? 1: 0);
			rs = statement.executeQuery();
			connection.commit();

			while (rs.next()) {
//				salt = rs.getString("salt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRsStatement(statement, rs);
		}
//		return salt;
	}

	public void insertAuthor(String firstName, String secondName, String surname) {
		String statement = "INSERT OR IGNORE INTO authors (first_name, second_name, surname) VALUES (?, ?, ?)";
		prepareThreeParameters(statement, connection, firstName, secondName, surname);
	}

	public void insertSeries(String series) {
		String statement = "INSERT OR IGNORE INTO series (series_title) VALUES (?)";
		prepareOneParameter(statement, connection, series);
	}

	public void insertBook(String book, int seriesId) {

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

	private void closeRsStatement(Statement statement, ResultSet rs) {
		try {
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
