package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexion {


		private Connection con = null;
		private static Conexion db;
		private PreparedStatement preparedStatement;

		private final static String url = "jdbc:postgresql://localhost:5432/";
		private final static String dbName = "sistema";
		private final static String driver = "org.postgresql.Driver";
		private final static String userName = "postgres";
		private final static String password = "admin";

		public Conexion() {

			try {

				Class.forName(driver).newInstance();
				con = (Connection) DriverManager.getConnection(url + dbName, userName, password);
				System.out.println(con);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void cerrarConexion() {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		public static Conexion getConexion() {
			if (db == null) {
				db = new Conexion();
			}
			return db;
		}

		public ResultSet query() throws SQLException {
			ResultSet res = preparedStatement.executeQuery();

			return res;
		}

		public int execute() throws SQLException {
			int result = preparedStatement.executeUpdate();
			return result;
		}

		public Connection getCon() {
			return this.con;
		}

		public PreparedStatement setPreparedStatement(String sql) throws SQLException {
			this.preparedStatement = con.prepareStatement(sql);
			return this.preparedStatement;
		}
}

	

