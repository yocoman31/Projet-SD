package filmfinder;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class TestDatabase {

	Database database = new Database();

	// TODO: (Yoni) ajouter des assert
	@Test
	public void testLoadFile() throws IOException {
		database.loadDatabaseFile("films.txt");
		// TODO: (Yoni) vérifier que tout les films ont un titre, que 1 film
		// nommé (je
		// ne sais plus) n'a pas de synopsis
		// TODO: (Yoni) au moins un film a 2 directors, genre, casting...
		if (database.getFilmsNotSeen().isEmpty()) {
			fail("Problème dans l'importation de la base de données, pas de films");
		}
		if (database.getFilmsNotSeen().size() > 98) {
			fail("Problème dans l'importation de la base de données, il y a des films en trop");
		}
		if (database.getFilmsNotSeen().size() < 98) {
			fail("Problème dans l'importation de la base de données, il manque des films");
		}
	}

	@Test
	public void testLoadJSONFile() throws IOException {
		database.loadDatabaseFile("films.json");
		// TODO: (Yoni) vérifier que tout les films ont un titre, que 1 film
		// nommé (je
		// ne sais plus) n'a pas de synopsis
		// TODO: (Yoni) au moins un film a 2 directors, genre, casting...
		if (database.getFilmsNotSeen().isEmpty()) {
			fail("Problème dans l'importation de la base de données, pas de films");
		}
		if (database.getFilmsNotSeen().size() > 98) {
			fail("Problème dans l'importation de la base de données, il y a des films en trop");
		}
		if (database.getFilmsNotSeen().size() < 98) {
			fail("Problème dans l'importation de la base de données, il manque des films");
		}
	}

	@Test
	public void testAddFilmsSeen() throws IOException {
		database.loadDatabaseFile("films.txt");
		ArrayList<String> films = new ArrayList<String>();
		films.add("Fast & Furious 7");
		films.add("Fast & Furious 6");

		database.addFilmsSeen(films);
		if (database.getFilmsSeen().size() != films.size()) {
			fail("Problème dans l'importation des films vus");
		}
	}

}
