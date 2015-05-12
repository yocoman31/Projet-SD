package filmfinder;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Entry point of the program FindFinder, you run it in command line mode
 * (default mode) or in graphic mode. You can run it with different option: <br>
 * --casting-weight=[number] or -gw=[number] affect a weight to the casting for
 * the algorithm<br>
 * --director-weight=[number] or -dw=[number] affect a weight to the director
 * for the algorithm <br>
 * --duration-on or -d active the duration compute in the algorithm<br>
 * --file or -f [databasePath] to load the database file <br>
 * --gender-weight=[number] or -gw=[number] affect a weight to the gender for
 * the algorithm<br>
 * --graphic or -g to open the program in a graphic mode <br>
 * --help or -h to see this message<br>
 * --seen or -s [filmTitle] to add a film you've already seen <br>
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class Filmfinder {
	public static void main(String[] args) {
		/* Create the database and initialize temporary variable */
		Database database = new Database();
		Integer genderWeight = null, castingWeight = null, directorWeight = null;
		Boolean duration = null;
		// TODO: vérifier la structure
		ArrayList<String> filmSeen = new ArrayList<String>();

		boolean cli = true;
		/* Check arguments: */
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f") || args[i].equals("--file")) {
				/* Adding a database file */
				i++;
				try {
					database.loadDatabaseFile(args[i]);
				} catch (FileNotFoundException e) {
					System.err.println("File \"" + args[i] + "\"not found! ");
					System.exit(1);
				}
			} else if (args[i].equals("-s") || args[i].equals("--seen")) {
				/* Adding a film in filmSeen */
				i++;
				String concat = "";
				/*
				 * While they are no other option, the next argument is a part
				 * of the title
				 */
				while (i < args.length && !args[i].startsWith("-"))
					concat = concat + " " + args[i++];
				filmSeen.add(Utils.eraseSpace(concat));
				i--;
			} else if (args[i].equals("-g") || args[i].equals("--graphic")) {
				cli = false;
			} else if (args[i].equals("-h") || args[i].equals("--help")) {
				System.out
						.println("Welcom to FindFinder, this programme helps you to find a film \n(default mode) or in graphic mode. You can run it with different option:\n--file or -f [databasePath] to load the database file\n--seen or -s [filmTitle] to add a film you've already seen\n--graphic or -g to open the program in a graphic mode\n--help or -h to see this message");
			} else if (args[i].startsWith("-gw=")
					|| args[i].startsWith("--gender-weight=")) {
				genderWeight = Integer.parseInt(args[i].substring(args[i]
						.indexOf("=") + 1));
			} else if (args[i].startsWith("-cw=")
					|| args[i].startsWith("--casting-weight=")) {
				castingWeight = Integer.parseInt(args[i].substring(args[i]
						.indexOf("=") + 1));
			} else if (args[i].startsWith("-dw=")
					|| args[i].startsWith("--director-weight=")) {
				directorWeight = Integer.parseInt(args[i].substring(args[i]
						.indexOf("=") + 1));
			} else if (args[i].equals("-d") || args[i].equals("--duration-on")) {
				duration = true;
			} else {
				System.err
						.println("\""
								+ args[i]
								+ "\" is not an argument! Please read help using --help");
			}
		}
		/* Run the program in correct mode */
		if (cli) {
			if (database.isEmpty() == true) {
				System.err.println("Error, there is no database selected");
				System.exit(1);
			}
			Algorithm.execute(filmSeen, database, genderWeight, castingWeight,
					directorWeight, duration);
		} else {
			new View(database);
		}
	}
}
