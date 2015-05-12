package filmfinder;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Filmfinder {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Bonjour");
		for (String a : args)
			System.out.println(a);
		final Database database = new Database();
		ArrayList<String> film = new ArrayList<String>();
		boolean cli = true;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f") || args[i].equals("--file")) {
				i++;
				database.addDatabase(args[i]);
			} else if (args[i].equals("-s") || args[i].equals("--seen")) {
				i++;
				String concat = "";
				while (i < args.length && !args[i].startsWith("-")) {
					concat = concat + " " + args[i];
					i++;
				}
				film.add(Database.eraseSpace(concat));
				System.out.println(Database.eraseSpace(concat));
				i--;
			} else if (args[i].equals("-g") || args[i].equals("--graphic")) {
				cli = false;
			} else if (args[i].equals("-h") || args[i].equals("--help")) {
				// TODO: à remplir !
			} else {

				System.out.println("Mauvais argument!");
			}
		}
		if (cli) {
			Algorithm algo = new Algorithm(film, database);
			
			int poidsgenre = 1;
			int poidscasting = 1;
			int poidsdirector = 1;
			boolean duration = false;
		
			algo.setCoefficientsGenres();
			algo.setCoefficientsCasting();
			algo.setCoefficientsDirectors();
			algo.setCoefficientsDuration();
			
			algo.setCompteurFilms(poidsgenre, poidsdirector, poidscasting, duration);
			algo.recommandations(10, "tout");
		} else {
			System.out.println("cond vraie!");
			final View view = new View(database);
		}
		System.out.println("Fin");
	}
}
