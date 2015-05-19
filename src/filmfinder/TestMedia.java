package filmfinder;

import static org.junit.Assert.*;

import org.junit.Test;

import filmfinder.Media.Type;

public class TestMedia {
	
	@Test
	public void testMedia() {
		String titre = "Test titre";
		Integer year = 2000;
		Type type = Type.FILM;
		String synopsis = "C'est l'histoire d'un test qui fonctionnait à merveille";
		String[] directors = {"director1", "director2", "director3"};
		String[] casting = {"acteur1", "acteur2", "acteur3"};
		String[] genres = {"genre1", "genre2", "genre3"};
		Integer duration = 120;
		Media media = new Media(titre, year, type, synopsis, directors, casting, genres, duration);
		if (!media.getTitle().equals(titre)) {
			fail("Problème dans l'ajout du titre");
		}
		if (media.getYear() != year) {
			fail("Problème dans l'ajout de l'année");
		}
		if (media.getYear() != year) {
			fail("Problème dans l'ajout de l'année");
		}
		if (!media.getSynopsis().equals(synopsis)) {
			fail("Problème dans l'ajout du synopsis");
		}
		if (!media.getDirectors().equals(directors)) {
			fail("Problème dans l'ajout des directeurs");
		}
		if (!media.getCasting().equals(casting)) {
			fail("Problème dans l'ajout du casting");
		}
		if (!media.getGenres().equals(genres)) {
			fail("Problème dans l'ajout des genres");
		}
		if (media.getDuration() != duration) {
			fail("Problème dans l'ajout de la durée");
		}
	}
	
	@Test
	public void testTitle() {
		Media media = new Media();
		String titre = "Test titre";
		media.setTitle(titre);
		if (!media.getTitle().equals(titre)) {
			fail("Problème dans l'ajout du titre");
		}
	}
	
	@Test
	public void testYear() {
		Media media = new Media();
		Integer year = 2000;
		media.setYear(year);
		if (media.getYear() != year) {
			fail("Problème dans l'ajout de l'année");
		}
	}

	@Test
	public void testType() {
		Media media = new Media();
		Type type = Type.FILM;
		media.setType(type);
		if (!media.getType().equals(type)) {
			fail("Problème dans l'ajout du type");
		}
	}
	
	@Test
	public void testSynopsis() {
		Media media = new Media();
		String synopsis = "C'est l'histoire d'un test qui fonctionnait à merveille";
		media.setSynopsis(synopsis);
		if (!media.getSynopsis().equals(synopsis)) {
			fail("Problème dans l'ajout du synopsis");
		}
	}
	
	@Test
	public void testDirectors() {
		Media media = new Media();
		String[] directors = {"director1", "director2", "director3"};
		media.setDirectors(directors);
		if (!media.getDirectors().equals(directors)) {
			fail("Problème dans l'ajout des directors");
		}
	}
	
	@Test
	public void testCasting() {
		Media media = new Media();
		String[] casting = {"acteur1", "acteur2", "acteur3"};
		media.setCasting(casting);
		if (!media.getCasting().equals(casting)) {
			fail("Problème dans l'ajout du casting");
		}
	}

	@Test
	public void testGenres() {
		Media media = new Media();
		String[] genres = {"genre1", "genre2", "genre3"};
		media.setGenres(genres);
		if (!media.getGenres().equals(genres)) {
			fail("Problème dans l'ajout des genres");
		}
	}
	
	@Test
	public void testDuration() {
		Media media = new Media();
		Integer duration = 120;
		media.setDuration(duration);
		if (media.getDuration() != duration) {
			fail("Problème dans l'ajout de la durée");
		}
	}
}
