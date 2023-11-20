package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.bill;
import util.Conexion;

public class billDao {

	private Conexion conexion;
	
	private static final String INSERT_USUARIO_SQL = "INSERT INTO bill(date, value, type, observation) VALUES(?, ?, ?, ?);";
	private static final String DELETE_USUARIO_SQL = "DELETE FROM bill WHERE id=?;";
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM bill WHERE id = ?;";
	private static final String SELECT_ALL_USUARIO = "SELECT * FROM bill;";
	
	
	
	

	public billDao() {
		
		this.conexion = Conexion.getConexion();
	}

	public void insert(bill bill) throws SQLException {
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedStatement.setDate(1, (Date) bill.getDate_bill());
			preparedStatement.setInt(1, bill.getValue());
			preparedStatement.setInt(2, bill.getType());
			preparedStatement.setString(3, bill.getObservation());
		
			
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
	
	
	
	
	public List<bill> selectAll(){
		
		List<bill> bill = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_USUARIO);
            ResultSet rs = conexion.query();
            
            while(rs.next()) {
            	int id = rs.getInt("id");
            	Date date = rs.getDate("Date");
            	int value = rs.getInt("value");
            	int type = rs.getInt("type");
            	String observation = rs.getString("observation");
            	bill.add(new bill(id, date, value, type, observation));
            }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return bill;
		
	}
	
	
	public bill select(int id){
		
	    bill bill = null;
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
            preparedStatement.setInt(1, id);
			
			ResultSet rs = conexion.query();
            
            while(rs.next()) {
            	
            	
            	Date date = rs.getDate("Date");
            	int value = rs.getInt("value");
            	int type = rs.getInt("type");
            	String observation = rs.getString("observation");
            	bill = new bill(date, value, type, observation);
            }
			
		}catch(SQLException e) {
			
		}
		
		
		return bill;
		
	}
	
}
