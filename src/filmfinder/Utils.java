package filmfinder;

public final class Utils {
	public final static String eraseSpace(String s) {
		while (s.startsWith(" "))
			s = s.substring(1);
		while (s.endsWith(" "))
			s = s.substring(0, s.length() - 1);
		return s;
	}

	public final static int countOccurence(String s, char c) {
		int res = 1;
		while (s.indexOf(c) != -1) {
			res++;
			s = s.substring(s.indexOf(c) + 1);
		}
		return res;
	}
}
