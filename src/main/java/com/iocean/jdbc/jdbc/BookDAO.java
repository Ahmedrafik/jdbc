package com.iocean.jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BookDAO {
	
	private Connection conn;
	private Statement stmt;
	
	public BookDAO(Connection conn, Statement stmt) {
		this.conn = conn;
		this.stmt = stmt;
	}
	
	public BookDAO(String url, String login, String pass){
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
	
	public void createTableBook() throws SQLException{
		try{
			conn.setAutoCommit(false);
			stmt.executeUpdate("Drop table if exists book cascade;");
				
			stmt.executeUpdate("create table Book(id		serial 		constraint firstkey PRIMARY KEY,"
						+ "title		varchar(50)	not null," + "author		varchar(50)	not null);");
		}
		catch(Exception e){
			System.out.println(e);
			conn.rollback();
		}
		finally{
			conn.setAutoCommit(true);
		}
	}
	
	
	public void insertBook(Book b1){
		try {
			PreparedStatement ps = conn.prepareStatement("insert into book (title, author) values(?,?)");
			
			ps.setString(1, b1.getTitle());
			ps.setString(2, b1.getAuthor());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
		
	
	
	

}
