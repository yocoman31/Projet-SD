package filmfinder;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Entry point of the program FindFinder, you run it in command line mode
 * (default mode) or in graphic mode. You can run it with different option: <br>
 * --file or -f [databasePath] to load the database file <br>
 * --seen or -s [filmTitle] to add a film you've already seen <br>
 * --graphic or -g to open the program in a graphic mode <br>
 * --help or -h to see this message
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class Filmfinder {
	public static void main(String[] args) {

		// Create the database and initialize temporary variable
		Database database = new Database();
		ArrayList<String> filmSeen = new ArrayList<String>();
		boolean cli = true;

		// Check arguments:
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f") || args[i].equals("--file")) {
				// Add the next file if it exists
				i++;
				try {
					database.addDatabaseFile(args[i]);
				} catch (FileNotFoundException e) {
					System.err.println("File \"" + args[i] + "\"not found! ");
				}
			} else if (args[i].equals("-s") || args[i].equals("--seen")) {
				// Add a film in filmSeen
				i++;
				String concat = "";
				while (i < args.length && !args[i].startsWith("-")) {
					concat = concat + " " + args[i];
					i++;
				}
				filmSeen.add(Utils.eraseSpace(concat));
				System.out.println(Utils.eraseSpace(concat));
				i--;
			} else if (args[i].equals("-g") || args[i].equals("--graphic")) {
				cli = false;
			} else if (args[i].equals("-h") || args[i].equals("--help")) {
				System.out
						.println("Welcom to FindFinder, this programme helps you to find a film");
				System.out
						.println("(default mode) or in graphic mode. You can run it with different option:");
				System.out
						.println("--file or -f [databasePath] to load the database file");
				System.out
						.println("--seen or -s [filmTitle] to add a film you've already seen");
				System.out
						.println("--graphic or -g to open the program in a graphic mode");
				System.out.println("--help or -h to see this message");
			} else {
				System.out.println("Mauvais argument!");
			}
		}

		if (cli) {
			Algorithm algo = new Algorithm(filmSeen, database);

			int poidsgenre = 1;
			int poidscasting = 1;
			int poidsdirector = 1;
			boolean duration = false;

			algo.setCoefficientsGenres();
			algo.setCoefficientsCasting();
			algo.setCoefficientsDirectors();
			algo.setCoefficientsDuration();

			algo.setCompteurFilms(poidsgenre, poidsdirector, poidscasting,
					duration);
			algo.recommandations(10, "tout");
		} else {
			System.out.println("cond vraie!");
			View view = new View(database);
		}
	}
}
