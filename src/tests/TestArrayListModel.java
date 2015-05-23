package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import filmfinder.ArrayListModel;
import filmfinder.Media;
import filmfinder.Media.Type;

public class TestArrayListModel {

	ArrayListModel liste;
		
	@Test
	public void testArrayListModel() {
		liste = new ArrayListModel();
		assertEquals("Problème constructeur vide", 0, liste.getSize());
	}

	@Test
	public void testArrayListModelArrayListOfMedia() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media = new Media();
		mediaListe.add(media);
		liste = new ArrayListModel(mediaListe);
		assertEquals("Problème constructeur avec liste", mediaListe.size(), liste.getSize());
	}

	@Test
	public void testGetData() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media1 = new Media();
		Media media2 = new Media("titre", 2000, Type.FILM, "synopsis", null, null, null, 100);
		mediaListe.add(media1);
		mediaListe.add(media2);
		liste = new ArrayListModel(mediaListe);
		assertEquals("Problème avec getData", mediaListe, liste.getData());
	}

	@Test
	public void testContains() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media = new Media();
		mediaListe.add(media);
		liste = new ArrayListModel(mediaListe);
		assertTrue("Problème avec contains", liste.getData().contains(media));
	}

	@Test
	public void testGetElementAt() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media1 = new Media();
		Media media2 = new Media("titre", 2000, Type.FILM, "synopsis", null, null, null, 100);
		mediaListe.add(media1);
		mediaListe.add(media2);
		liste = new ArrayListModel(mediaListe);
		for (int j = 0; j < mediaListe.size(); j++) {
			assertEquals("Problème avec getElementAt", mediaListe.get(j), liste.getElementAt(j));	
		}
	}

	@Test
	public void testGetSize() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media1 = new Media();
		Media media2 = new Media("titre", 2000, Type.FILM, "synopsis", null, null, null, 100);
		mediaListe.add(media1);
		mediaListe.add(media2);
		liste = new ArrayListModel(mediaListe);
		assertEquals("Problème avec getSize", mediaListe.size(), liste.getSize());	
	}

	@Test
	public void testIsEmpty() {
		liste = new ArrayListModel();
		assertTrue("Problème avec isEmpty", liste.isEmpty());
	}

	@Test
	public void testRemoveMedia() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media1 = new Media();
		Media media2 = new Media("titre", 2000, Type.FILM, "synopsis", null, null, null, 100);
		mediaListe.add(media1);
		mediaListe.add(media2);
		liste = new ArrayListModel(mediaListe);
		liste.remove(media1);
		mediaListe.remove(media1);
		assertEquals("Problème avec remove Media", mediaListe, liste.getData());	
	}

	@Test
	public void testRemoveInt() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media1 = new Media();
		Media media2 = new Media("titre", 2000, Type.FILM, "synopsis", null, null, null, 100);
		mediaListe.add(media1);
		mediaListe.add(media2);
		liste = new ArrayListModel(mediaListe);
		liste.remove(1);
		mediaListe.remove(1);
		assertEquals("Problème avec remove Int", mediaListe, liste.getData());
	}

	@Test
	public void testAdd() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media1 = new Media();
		Media media2 = new Media("titre", 2000, Type.FILM, "synopsis", null, null, null, 100);
		mediaListe.add(media1);
		liste = new ArrayListModel(mediaListe);
		liste.add(media2);
		mediaListe.add(media2);
		assertEquals("Problème avec add Media", mediaListe, liste.getData());
	}

	@Test
	public void testSort() {
		ArrayList<Media> mediaListe = new ArrayList<Media>();
		Media media1 = new Media("a", 2000, Type.FILM, "synopsis", null, null, null, 100);
		Media media2 = new Media("d", 2000, Type.FILM, "synopsis", null, null, null, 100);
		Media media3 = new Media("b", 2000, Type.FILM, "synopsis", null, null, null, 100);
		Media media4 = new Media("c", 2000, Type.FILM, "synopsis", null, null, null, 100);
		mediaListe.add(media1);
		mediaListe.add(media2);
		mediaListe.add(media3);
		mediaListe.add(media4);
		liste = new ArrayListModel(mediaListe);
		ArrayList<Media> mediaListeSort = new ArrayList<Media>();
		mediaListeSort.add(media1);
		mediaListeSort.add(media3);
		mediaListeSort.add(media4);
		mediaListeSort.add(media2);
		liste.sort();
		assertEquals("Problème avec add Media", mediaListeSort, liste.getData());
	}

}
