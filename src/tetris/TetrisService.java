package tetris;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TetrisService {

	public static void main(String[] args) {
		System.out.println(new TetrisService().getMaxScore());
	}

	public int getMaxScore() {

		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:tetris.db");
				Statement statement = connection.createStatement();) {
			Class.forName("org.sqlite.JDBC");

			String sql = "select COALESCE(max(score), 0) from score";
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public int insertScore(int score) {
		int count = -1;

		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:tetris.db");
				Statement statement = connection.createStatement();) {
			Class.forName("org.sqlite.JDBC");

			String sql = "insert into score values( ? )";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, score);
			count = ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return count;


	}

}





