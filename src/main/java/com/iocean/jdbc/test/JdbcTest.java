package com.iocean.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.iocean.jdbc.jdbc.Book;
import com.iocean.jdbc.jdbc.Client;
import com.iocean.jdbc.jdbc.Gender;

public class JdbcTest {

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/jdbcexemple";

		try (Connection conn = DriverManager.getConnection(url, "postgres", "admin")) {
			conn.setAutoCommit(false);
			try (Statement stmt = conn.createStatement();
					PreparedStatement ps = conn.prepareStatement("insert into book (title, author) values(?,?)");
					PreparedStatement ps1 = conn.prepareStatement("insert into client (name, firstname, gender) values(?,?,?::gender)")){

				/* Création des tables book & client */

				stmt.executeUpdate("Drop table if exists achat cascade;");
				stmt.executeUpdate("Drop table if exists book cascade;");
				stmt.executeUpdate("Drop table if exists client cascade;");
				stmt.executeUpdate("Drop type if exists gender cascade;");

				stmt.executeUpdate("create table Book(id		serial 		constraint firstkey PRIMARY KEY,"
						+ "title		varchar(50)	not null," + "author		varchar(50)	not null);");

				stmt.executeUpdate("CREATE TYPE gender AS ENUM ('Male', 'Female');");

				stmt.executeUpdate("create table Client(id		serial 		constraint onekey PRIMARY KEY,"
						+ "name		varchar(50)	not null," + "firstname 	varchar(50)	not null,"
						+ "gender		gender," + "favorite serial references book(id));");

				stmt.executeUpdate("create table achat(id_client	serial	references	client(id),"
						+ "id_book	serial	references book(id))");

				/* Création des livres */

				Book book1 = new Book("L'île au enfants", "Casimir");
				Book book2 = new Book("Le tour du monde en 80 jours", "Victor Hugo");
				Book book3 = new Book("Ferrari toute une histoire", "Michael Schumacher");
				Book book4 = new Book("Avenger Rassemblement", "Marvel");
				Book book5 = new Book("Clash of Clan la solus", "Dr X");
				Book book6 = new Book("Star Wars Episode N", "Disney");
				Book book7 = new Book("Dragon Ball", "Akira Toriyama");

				ps.setString(1, book1.getTitle());
				ps.setString(2, book1.getAuthor());
				ps.executeUpdate();
				
				ps.setString(1, book2.getTitle());
				ps.setString(2, book2.getAuthor());
				ps.executeUpdate();
				
				ps.setString(1, book3.getTitle());
				ps.setString(2, book3.getAuthor());
				ps.executeUpdate();
				
				ps.setString(1, book4.getTitle());
				ps.setString(2, book4.getAuthor());
				ps.executeUpdate();
				
				ps.setString(1, book5.getTitle());
				ps.setString(2, book5.getAuthor());
				ps.executeUpdate();
				
				ps.setString(1, book6.getTitle());
				ps.setString(2, book6.getAuthor());
				ps.executeUpdate();
				
				ps.setString(1, book7.getTitle());
				ps.setString(2, book7.getAuthor());
				ps.executeUpdate();
				
				/* Création de Clients */
				
				Client client1 = new Client("Goldman", "Jean Jacques", Gender.Male);
				Client client2 = new Client("Neige", "Blanche", Gender.Female);
				Client client3 = new Client("Charmant", "Le Prince", Gender.Male);
				Client client4 = new Client("Potter", "Harry", Gender.Male);
				Client client5 = new Client("Castor", "Père", Gender.Male);

				ps1.setString(1, client1.getName());
				ps1.setString(2, client1.getFirstname());
				ps1.setObject(3, client1.getGender().name());
				ps1.executeUpdate();
				
				ps1.setString(1, client2.getName());
				ps1.setString(2, client2.getFirstname());
				ps1.setObject(3, client2.getGender().name());
				ps1.executeUpdate();
				
				ps1.setString(1, client3.getName());
				ps1.setString(2, client3.getFirstname());
				ps1.setObject(3, client3.getGender().name());
				ps1.executeUpdate();
				
				ps1.setString(1, client4.getName());
				ps1.setString(2, client4.getFirstname());
				ps1.setObject(3, client4.getGender().name());
				ps1.executeUpdate();
				
				ps1.setString(1, client5.getName());
				ps1.setString(2, client5.getFirstname());
				ps1.setObject(3, client5.getGender().name());
				ps1.executeUpdate();
			
				/* Création des achat */

				stmt.executeUpdate("insert into achat (id_client, id_book)" + "values(1, 4)");
				stmt.executeUpdate("insert into achat (id_client, id_book)" + "values(3, 2)");
				stmt.executeUpdate("insert into achat (id_client, id_book)" + "values(4, 6)");
				stmt.executeUpdate("insert into achat (id_client, id_book)" + "values(2, 1)");
				stmt.executeUpdate("insert into achat (id_client, id_book)" + "values(5, 5)");

				/* Gestion des livres préférés */

				stmt.executeUpdate("update client set favorite = 3" + "where client.id = 2;");
				stmt.executeUpdate("update client set favorite = 5" + "where client.id = 5;");
				stmt.executeUpdate("update client set favorite = 2" + "where client.id = 1;");
				stmt.executeUpdate("update client set favorite = 1" + "where client.id = 4;");
				stmt.executeUpdate("update client set favorite = 4" + "where client.id = 3;");

				conn.commit();

			} catch (Exception e) {
				System.out.println(e.getMessage());
				conn.rollback();
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
