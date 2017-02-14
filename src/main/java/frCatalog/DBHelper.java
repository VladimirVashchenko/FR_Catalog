package frCatalog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by Administrator on 11.11.2016.
 */
public class DBHelper {
	private static DBHelper instance;
	private String dbPath, dbName, credentials;
	private Connection connection;

	private DBHelper() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
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
			String dbName = "db/catalog";
			connection = DriverManager.getConnection("jdbc:hsqldb:file:" + decodedPath + new String(dbName.getBytes("UTF-8"), "UTF-8")+";sql.syntax_mys=true", "sa", "");
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

// language=HSQLDB
		String author = "CREATE TABLE IF NOT EXISTS author (" +
				"id INTEGER NOT NULL, " +
				"first_name VARCHAR(50) NOT NULL, " +
				"second_name VARCHAR(50) NOT NULL, " +
				"surname VARCHAR(50) NOT NULL, " +
				"PRIMARY KEY (id),"+
				"UNIQUE (first_name, surname)" +
				")";


// language=HSQLDB
		String novel = "CREATE TABLE IF NOT EXISTS novel (" +
				"id INTEGER NOT NULL, " +
				"novel_title VARCHAR(100) NOT NULL UNIQUE, " +
				"published_ad VARCHAR(30) NOT NULL, " +
				"begins_dr VARCHAR(30) NOT NULL, " +
				"ends_dr VARCHAR(30) NOT NULL," +
				"PRIMARY KEY (id)" +
				")";

// language=HSQLDB
		String anthology = "CREATE TABLE IF NOT EXISTS anthology(" +
				"id INTEGER NOT NULL, " +
				"anthology_title VARCHAR(100) NOT NULL UNIQUE, " +
				"published_ad VARCHAR(100) NOT NULL," +
				"PRIMARY KEY (id)" +
				")";

// language=HSQLDB
		String series = "CREATE TABLE IF NOT EXISTS series(" +
				"id INTEGER NOT NULL, " +
				"series_title VARCHAR(100) NOT NULL UNIQUE," +
				"PRIMARY KEY (id)" +
				")";

// language=HSQLDB
		String singleNovels = "CREATE TABLE IF NOT EXISTS single_novels(" +
//				"id INTEGER NOT NULL, " +
				"novel_id INTEGER NOT NULL, " +
				"a_book BOOLEAN NOT NULL, " +
				"PRIMARY KEY (novel_id), " +
//				"PRIMARY KEY (id), " +
				"FOREIGN KEY (novel_id) REFERENCES novel (id)" +
				")";

// language=HSQLDB
		String authorNovel = "CREATE TABLE IF NOT EXISTS author_novel(" +
//				"id INTEGER NOT NULL, " +
				"author_id INTEGER NOT NULL, " +
				"novel_id INTEGER NOT NULL," +
				"PRIMARY KEY (author_id, novel_id)," +
//				"PRIMARY KEY (id)," +
				"FOREIGN KEY (author_id) REFERENCES author (id), " +
				"FOREIGN KEY (novel_id) REFERENCES novel (id), " +
				")";

// language=HSQLDB
		String anthologyNovel = "CREATE TABLE IF NOT EXISTS anthology_novel(" +
//				"id INTEGER NOT NULL, " +
				"anthology_id INTEGER NOT NULL, " +
				"novel_id INTEGER NOT NULL," +
				"PRIMARY KEY (anthology_id, novel_id)," +
//				"PRIMARY KEY (id)," +
				"FOREIGN KEY (anthology_id) REFERENCES anthology (id), " +
				"FOREIGN KEY (novel_id) REFERENCES novel (id), " +
				")";

// language=HSQLDB
		String seriesNovel = "CREATE TABLE IF NOT EXISTS series_novel(" +
//				"id INTEGER NOT NULL, " +
				"series_id INTEGER NOT NULL, " +
				"novel_id INTEGER NOT NULL, " +
				"novel_number INTEGER NOT NULL, " +
				"PRIMARY KEY (series_id, novel_id)," +
//				"PRIMARY KEY (id)," +
				"FOREIGN KEY (series_id) REFERENCES series (id), " +
				"FOREIGN KEY (novel_id) REFERENCES novel (id), " +
				")";

// language=HSQLDB
		String authorAnthology = "CREATE TABLE IF NOT EXISTS author_anthology(" +
//				"id INTEGER NOT NULL, " +
				"author_id INTEGER NOT NULL, " +
				"anthology_id INTEGER NOT NULL," +
				"PRIMARY KEY (author_id, anthology_id)," +
//				"PRIMARY KEY (id)," +
				"FOREIGN KEY (author_id) REFERENCES author (id), " +
				"FOREIGN KEY (anthology_id) REFERENCES anthology (id), " +
				")";

// language=HSQLDB
		String read = "CREATE TABLE IF NOT EXISTS read_novels(" +
//				"id INTEGER NOT NULL, " +
				"novel_id INTEGER NOT NULL, " +
				"read_yes_no BOOLEAN NOT NULL, " +
				"PRIMARY KEY (novel_id), " +
//				"PRIMARY KEY (id)," +
				"FOREIGN KEY (novel_id) REFERENCES novel (id) " +
				")";

		try {
			connection.createStatement().executeUpdate(author);
			connection.createStatement().executeUpdate(novel);
			connection.createStatement().executeUpdate(anthology);
			connection.createStatement().executeUpdate(series);

			connection.createStatement().executeUpdate(singleNovels);
			connection.createStatement().executeUpdate(authorNovel);
			connection.createStatement().executeUpdate(anthologyNovel);
			connection.createStatement().executeUpdate(seriesNovel);
			connection.createStatement().executeUpdate(authorAnthology);
			connection.createStatement().executeUpdate(read);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				"FROM novel n " +
				"JOIN single_novels sn ON n.novel_id = sn.novel_id " +
				"JOIN author_novel an ON n.novel_id = an.novel_id " +
				"JOIN author a ON a.author_id = an.author_id " +
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
		String statement = "INSERT OR IGNORE INTO author (first_name, second_name, surname) VALUES (?, ?, ?)";
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
