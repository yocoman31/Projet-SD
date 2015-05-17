package filmfinder;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

/**
 * Class containing utilitary method
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public final class Utils {
	/**
	 * Function erasing side space in a String
	 * 
	 * @param s
	 *            : string to erase
	 * @return erased string
	 */
	public final static String eraseSpace(String s) {
		while (s.startsWith(" "))
			s = s.substring(1);
		while (s.endsWith(" "))
			s = s.substring(0, s.length() - 1);
		return s;
	}

	/**
	 * Function counting the number of occurrence of a character in a String
	 * 
	 * @param s
	 *            : string
	 * @param c
	 *            : character to search
	 * @return the number of occurrence
	 */
	public final static int countOccurence(String s, char c) {
		int res = 1;
		while (s.indexOf(c) != -1) {
			res++;
			s = s.substring(s.indexOf(c) + 1);
		}
		return res;
	}

	/**
	 * Function formating a String tab with comma
	 * 
	 * @param s
	 *            : String tab to format
	 * @return formated String
	 */
	public final static String formatWithComma(String[] s) {
		String res = "";
		for (int i = 0; i < s.length; i++) {
			res += s[i];
			if (i != s.length - 1)
				res += ", ";
		}
		return res;
	}

	public final static JsonArray arrayToJson(String[] array) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (String s : array) {
			builder.add(s);
		}
		return builder.build();
	}
}
