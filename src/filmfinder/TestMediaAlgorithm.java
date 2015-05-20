package filmfinder;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import filmfinder.Media.Type;

public class TestMediaAlgorithm {

	@Test
	public void testCasting() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		algo.setCastingWeight(5);
		if (algo.getCastingWeight() != 5) {
			fail("Le poids du casting n'est pas bon");
		}
	}

	@Test
	public void testDirector() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		algo.setDirectorWeight(5);
		;
		if (algo.getDirectorWeight() != 5) {
			fail("Le poids du director n'est pas bon");
		}
	}

	@Test
	public void testGenre() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		algo.setGenresWeight(5);
		if (algo.getGenreWeight() != 5) {
			fail("Le poids du genre n'est pas bon");
		}
	}

	@Test
	public void testRecommandation() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		algo.setNbRecommandation(5);
		if (algo.getNbRecommandation() != 5) {
			fail("Le nombre de recommandations n'est pas bon");
		}
	}

	@Test
	public void testDuration() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		algo.setDurationWeight(5);
		if (algo.getDurationWeight() != 5) {
			fail("Le poids de la durée n'est pas bon");
		}
		algo.setDurationShift(15);
		if (algo.getDurationShift() != 15) {
			fail("Le décalage pour la durée n'est pas bon");
		}
	}

	@Test
	public void testType() {
		Database database = new Database();
		MediaAlgorithm algo = new MediaAlgorithm(database);
		algo.setType(Type.FILM);
		if (algo.getType() != Type.FILM) {
			fail("Le type n'est pas bon");
		}
		algo.setType(Type.SERIE);
		if (algo.getType() != Type.SERIE) {
			fail("Le type n'est pas bon");
		}
		algo.setType(Type.NONE);
		if (algo.getType() != Type.NONE) {
			fail("Le type n'est pas bon");
		}
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
		if (!res.get(0).getTitle().equals("Fast and Furious")) {
			fail("Problème dans l'algorithme");
		}
	}
	// TODO: (Yoni) de même avec divergente :)

}