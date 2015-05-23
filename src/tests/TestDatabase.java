package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import filmfinder.Database;

public class TestDatabase {

	Database database = new Database();
	
	@Test
	public void testLoadDatabaseFile() throws IOException {
		// TODO: (Yoni) vérifier que tout les films ont un titre, que 1 film
		// nommé (je
		// ne sais plus) n'a pas de synopsis
		// TODO: (Yoni) au moins un film a 2 directors, genre, casting...
		
		database.loadDatabaseFile("films.txt");
		assertEquals("Problème importation de la base de données avec fichier txt", 98, database.getFilmsNotSeen().size());
	}

	@Test
	public void testGetFilmsSeenModel() throws IOException {
		database.loadDatabaseFile("films.txt");
		ArrayList<String> filmsVus = new ArrayList<String>();
		filmsVus.add("Fast & Furious 7");
		filmsVus.add("Fast & Furious 6");
		database.addFilmsSeen(filmsVus);
		assertEquals("Problème importation films vus", filmsVus.size(), database.getFilmsSeenModel().getSize());
	}

	@Test
	public void testGetFilmsSeen() throws IOException {
		database.loadDatabaseFile("films.txt");
		ArrayList<String> filmsVus = new ArrayList<String>();
		filmsVus.add("Fast & Furious 7");
		filmsVus.add("Fast & Furious 6");
		database.addFilmsSeen(filmsVus);
		assertEquals("Problème importation films vus", filmsVus.size(), database.getFilmsSeen().size());
	}

	@Test
	public void testGetFilmsNotSeenModel() throws IOException {
		database.loadDatabaseFile("films.txt");
		ArrayList<String> filmsVus = new ArrayList<String>();
		filmsVus.add("Fast & Furious 7");
		filmsVus.add("Fast & Furious 6");
		database.addFilmsSeen(filmsVus);
		assertEquals("Problème getFilmsNotSeenModel", 98-filmsVus.size(), database.getFilmsNotSeenModel().getSize());
	}

	@Test
	public void testGetFilmsNotSeen() throws IOException {
		database.loadDatabaseFile("films.txt");
		ArrayList<String> filmsVus = new ArrayList<String>();
		filmsVus.add("Fast & Furious 7");
		filmsVus.add("Fast & Furious 6");
		database.addFilmsSeen(filmsVus);
		assertEquals("Problème getFilmsNotSeen", 98-filmsVus.size(), database.getFilmsNotSeen().size());
	}

	@Test
	public void testGetFileOutput() {
		String output = "test";
		database.setFileOutput(output);
		assertEquals("Problème getFileOutput", output, database.getFileOutput());
	}

}
