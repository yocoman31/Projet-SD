package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import filmfinder.Media;
import filmfinder.Media.Type;

public class TestMedia {

	@Test
	public void testMedia() {
		String titre = "Test titre";
		Integer year = 2000;
		Type type = Type.FILM;
		String synopsis = "C'est l'histoire d'un test qui fonctionnait à merveille";
		String[] directors = { "director1", "director2", "director3" };
		String[] casting = { "acteur1", "acteur2", "acteur3" };
		String[] genres = { "genre1", "genre2", "genre3" };
		Integer duration = 120;
		Media media = new Media(titre, year, type, synopsis, directors,
				casting, genres, duration);
		assertEquals("Erreur sur le titre dans le constructeur", titre, media.getTitle());
		assertEquals("Erreur sur l'année dans le constructeur", year, media.getYear());
		assertEquals("Erreur sur le type dans le constructeur", type, media.getType());
		assertEquals("Erreur sur le synopsis dans le constructeur", synopsis, media.getSynopsis());
		assertArrayEquals("Erreur sur les direteurs dans le constructeur", directors, media.getDirectors());
		assertArrayEquals("Erreur sur le casting dans le constructeur", casting, media.getCasting());
		assertArrayEquals("Erreur sur le genre dans le constructeur", genres, media.getGenres());
		assertEquals("Erreur sur la durée dans le constructeur", duration, media.getDuration());
	}

	@Test
	public void testTitle() {
		Media media = new Media();
		String titre = "Test titre";
		media.setTitle(titre);
		assertEquals("Erreur dans setTitle()", titre, media.getTitle());
	}

	@Test
	public void testYear() {
		Media media = new Media();
		Integer year = 2000;
		media.setYear(year);
		assertEquals("Erreur dans setYear()", year, media.getYear());
	}

	@Test
	public void testType() {
		Media media = new Media();
		Type type = Type.FILM;
		media.setType(type);
		assertEquals("Erreur dans setType()", type, media.getType());
	}

	@Test
	public void testSynopsis() {
		Media media = new Media();
		String synopsis = "C'est l'histoire d'un test qui fonctionnait à merveille";
		media.setSynopsis(synopsis);
		assertEquals("Erreur dans setSynopsis()", synopsis, media.getSynopsis());
	}

	@Test
	public void testDirectors() {
		Media media = new Media();
		String[] directors = { "director1", "director2", "director3" };
		media.setDirectors(directors);
		assertArrayEquals("Erreur dans setDirectors()", directors, media.getDirectors());
	}

	@Test
	public void testCasting() {
		Media media = new Media();
		String[] casting = { "acteur1", "acteur2", "acteur3" };
		media.setCasting(casting);
		assertArrayEquals("Erreur dans setCasting()", casting, media.getCasting());
	}

	@Test
	public void testGenres() {
		Media media = new Media();
		String[] genres = { "genre1", "genre2", "genre3" };
		media.setGenres(genres);
		assertArrayEquals("Erreur dans setGenre()", genres, media.getGenres());
	}

	@Test
	public void testDuration() {
		Media media = new Media();
		Integer duration = 120;
		media.setDuration(duration);
		assertEquals("Erreur dans setDuration()", duration, media.getDuration());
	}
}
