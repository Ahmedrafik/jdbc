package com.iocean.jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientDao {

	private Connection conn;
	private Statement stmt;
	
	public ClientDao(Connection conn, Statement stmt) {
		this.conn = conn;
		this.stmt = stmt;
	}
	
	public ClientDao(String url, String login, String pass){
		try {
			conn = DriverManager.getConnection(url, login, pass);
			stmt = conn.createStatement();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	
	public void createTableClient() throws SQLException{
		try{
			conn.setAutoCommit(false);
			stmt.executeUpdate("Drop client if exists book cascade;");
				
			stmt.executeUpdate("create table Client(id		serial 		constraint onekey PRIMARY KEY,"
					+ "name		varchar(50)	not null," + "firstname 	varchar(50)	not null,"
					+ "gender		gender," + "favorite serial references book(id));");
		}
		catch(Exception e){
			System.out.println(e);
			conn.rollback();
		}
		finally{
			conn.setAutoCommit(true);
		}
	}
	
	public void insertClient(Client c1){
		try {
			PreparedStatement ps = conn.prepareStatement("insert into client (name, firstname, gender) values(?,?,?::gender)");
			
			ps.setString(1, c1.getName());
			ps.setString(2, c1.getFirstname());
			ps.setObject(3, c1.getGender().name());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	
}
