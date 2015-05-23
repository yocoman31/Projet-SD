package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import filmfinder.Utils;

public class TestUtils {

	@Test
	public void testEraseSpace() {
		String message = "   Ceci est un test   ";
		String expected = "Ceci est un test";
		String got = Utils.eraseSpace(message);
		assertEquals("Problème eraseSpace", expected, got);
	}

	@Test
	public void testCountOccurence() {
		String message = "Bonjour, ceci est un test";
		int got = Utils.countOccurence(message, 'e');
		int expected = 3;
		assertEquals("Problème countOccurence", expected, got);
	}

	@Test
	public void testFormatWithComma() {
		String[] s = {"test1", "test2", "test3"};
		String expected = "test1, test2, test3";
		String got = Utils.formatWithComma(s);
		assertEquals("Problème formatWithComma", expected, got);
		
	}

}
