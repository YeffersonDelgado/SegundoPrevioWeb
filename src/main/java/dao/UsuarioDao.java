package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.User;
import util.Conexion;

public class UsuarioDao {

	private Conexion conexion;
	
	private static final String INSERT_USUARIO_SQL = "INSERT INTO user(username, password, email) VALUES(?, ?, ?);";
	private static final String DELETE_USUARIO_SQL = "DELETE FROM user WHERE id=?;";
	private static final String UPDATE_USUARIO_SQL = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?;";
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM user WHERE id = ?;";
	private static final String SELECT_ALL_USUARIO = "SELECT * FROM user;";
	
	
	
	

	public UsuarioDao() {
		
		this.conexion = Conexion.getConexion();
	}

	public void insert(User user) throws SQLException {
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getEmail());
		
			
			conexion.execute();
			

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_USUARIO_SQL);
			preparedStatement.setInt(1, id);
			conexion.execute();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void update(User user) throws SQLException {
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setInt(4, user.getId());
			conexion.execute();

		} catch(SQLException e) {
			
		}
	}
	
	
	public List<User> selectAll(){
		
		List<User> usuarios = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_USUARIO);
            ResultSet rs = conexion.query();
            
            while(rs.next()) {
            	int id = rs.getInt("id");
            	String nombre = rs.getString("nombre");
            	String email = rs.getString("email");
            	String pais = rs.getString("pais");
            	usuarios.add(new User(id, nombre, pais, email));
            }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return usuarios;
		
	}
	
	
	public User select(int id){
		
	    User user = null;
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
            preparedStatement.setInt(1, id);
			
			ResultSet rs = conexion.query();
            
            while(rs.next()) {
            	
            	String nombre = rs.getString("nombre");
            	String email = rs.getString("email");
            	String pais = rs.getString("pais");
            	user = new User(id, nombre, pais, email);
            }
			
		}catch(SQLException e) {
			
		}
		
		
		return user;
		
	}
	
}
