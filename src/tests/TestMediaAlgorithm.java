package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import filmfinder.Database;
import filmfinder.Media;
import filmfinder.MediaAlgorithm;
import filmfinder.Media.Type;

public class TestMediaAlgorithm {

	@Test
	public void testCasting() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		Integer weight = 5;
		algo.setCastingWeight(weight);
		assertEquals("Erreur dans setCastingWeight()", weight, algo.getCastingWeight());
	}

	@Test
	public void testDirector() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		Integer weight = 5;
		algo.setDirectorWeight(weight);
		assertEquals("Erreur dans setDirectorWeight()", weight, algo.getDirectorWeight());
	}

	@Test
	public void testGenre() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		Integer weight = 5;
		algo.setGenresWeight(weight);
		assertEquals("Erreur dans setGenresWeight()", weight, algo.getGenreWeight());
		
	}

	@Test
	public void testRecommandation() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		Integer recommandations = 5;
		algo.setNbRecommandation(recommandations);
		assertEquals("Erreur dans setNbRecommandation()", recommandations, algo.getNbRecommandation());
	}

	@Test
	public void testDuration() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		Integer weight = 5;
		Integer duration = 10;
		algo.setDurationWeight(weight);
		assertEquals("Erreur dans setDurationWeight()", weight, algo.getDurationWeight());
		algo.setDurationShift(duration);
		assertEquals("Erreur dans setDurationShift()", duration, algo.getDurationShift());
	}

	@Test
	public void testType() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		algo.setType(Type.FILM);
		assertEquals("Erreur dans setType()", Type.FILM, algo.getType());
		algo.setType(Type.SERIE);
		assertEquals("Erreur dans setType()", Type.SERIE, algo.getType());
		algo.setType(Type.NONE);
		assertEquals("Erreur dans setType()", Type.NONE, algo.getType());
	}

	@Test
	public void testAlgorithme() throws IOException {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		database.loadDatabaseFile("films.txt");

		ArrayList<String> films = new ArrayList<String>();
		films.add("Fast & Furious 7");
		films.add("Fast & Furious 6");

		database.addFilmsSeen(films);
		ArrayList<Media> res = algo.execute();
		assertEquals("Erreur dans l'execution de l'algorithme", "Fast and Furious", res.get(0).getTitle());
	}
	// TODO: (Yoni) de même avec divergente :)

}