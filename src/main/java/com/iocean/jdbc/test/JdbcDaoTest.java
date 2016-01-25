package com.iocean.jdbc.test;

import com.iocean.jdbc.jdbc.*;

public class JdbcDaoTest {

	public static void main(String[] args) {
		
		/* Initialisation des lien à la base de donnée */
		String url = "jdbc:postgresql://localhost:5432/jdbcexemple";
		String login = "postgres";
		String pass = "admin";

		/* Création des objets DAO lien entre java et BD */
		BookDAO book = new BookDAO(url, login, pass);
		ClientDao client = new ClientDao(url, login, pass);
		
		try{
			
			/* Création de la table Book */
			
			book.createTableBook();
						
			/* Création des livres */

			Book book1 = new Book("L'île au enfants", "Casimir");
			Book book2 = new Book("Le tour du monde en 80 jours", "Victor Hugo");
			Book book3 = new Book("Ferrari toute une histoire", "Michael Schumacher");
			Book book4 = new Book("Avenger Rassemblement", "Marvel");
			Book book5 = new Book("Clash of Clan la solus", "Dr X");
			Book book6 = new Book("Star Wars Episode N", "Disney");
			Book book7 = new Book("Dragon Ball", "Akira Toriyama");
			
			/* Insertion des livres en bd */
			
			book.insertBook(book1);
			book.insertBook(book2);
			book.insertBook(book3);
			book.insertBook(book4);
			book.insertBook(book5);
			book.insertBook(book6);
			book.insertBook(book7);
		
			/* Création de la table Client */
			
			client.createTableClient();
						
			/* Création des Clients */
		/*	
			Client client1 = new Client("Goldman", "Jean Jacques", Gender.Male);
			Client client2 = new Client("Neige", "Blanche", Gender.Female);
			Client client3 = new Client("Charmant", "Le Prince", Gender.Male);
			Client client4 = new Client("Potter", "Harry", Gender.Male);
			Client client5 = new Client("Castor", "Père", Gender.Male);
			*/
			/* Insertion des clients en bd */
			/*
			client.insertClient(client1);
			client.insertClient(client2);
			client.insertClient(client3);
			client.insertClient(client4);
			client.insertClient(client5);
			
*/
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
}