package filmfinder;

//TODO: mettre à jour help :)
import java.io.IOException;
import java.util.ArrayList;

/**
 * Entry point of the program FindFinder, you run it in command line mode
 * (default mode) or in graphic mode. You can run it with different option: <br>
 * --casting-weight=[number] or -gw=[number] affect a weight to the casting for
 * the algorithm<br>
 * --director-weight=[number] or -dw=[number] affect a weight to the director
 * for the algorithm <br>
 * --type=[NONE|SERIES|FILMS] or -t=[NONE|SERIES|FILMS]
 * --duration-weight=[weight] or -durw=[weight] active the duration compute in
 * the algorithm<br>
 * --duration-shift=[number] or -durs=[number] set the duration shift to compute
 * the algorithm <br>
 * --file or -f [databasePath] to load the database file <br>
 * --gender-weight=[number] or -gw=[number] affect a weight to the gender for
 * the algorithm<br>
 * --graphic or -g to open the program in a graphic mode <br>
 * --help or -h to see this message<br>
 * --seen or -s [filmTitle] to add a film you've already seen <br>
 * --json [file] to select an JSON output for the recommended films<br>
 * -nb=[number] --number-of-recommendation=[number] number of recommendation
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class FilmFinder {

	public static void main(String[] args) {
		/* Create the database and initialize temporary variable */
		Database database = new Database();
		MediaAlgorithm mediaAlgorithm = new MediaAlgorithm(database);
		ArrayList<String> filmSeen = new ArrayList<String>();
		boolean cli = true;
		/* Parse arguments: */
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-f") || args[i].equals("--file")) {
				/* Adding a database file */
				i++;
				if (!args[i].endsWith(".txt") && !args[i].endsWith(".json")) {
					System.err.println(args[i] + " is not a database!");
					System.exit(1);
				}

				try {
					database.loadDatabaseFile(args[i]);
				} catch (IOException e) {
					System.err.println("File \"" + args[i]
							+ "\"not found or corrupted! ");
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
				mediaAlgorithm.setGenresWeight(Integer.parseInt(args[i]
						.substring(args[i].indexOf("=") + 1)));
			} else if (args[i].startsWith("-cw=")
					|| args[i].startsWith("--casting-weight=")) {
				mediaAlgorithm.setCastingWeight(Integer.parseInt(args[i]
						.substring(args[i].indexOf("=") + 1)));
			} else if (args[i].startsWith("-dw=")
					|| args[i].startsWith("--director-weight=")) {
				mediaAlgorithm.setDirectorWeight(Integer.parseInt(args[i]
						.substring(args[i].indexOf("=") + 1)));
			} else if (args[i].startsWith("-durw=")
					|| args[i].startsWith("--duration-weight=")) {
				mediaAlgorithm.setDurationShift(Integer.parseInt(args[i]
						.substring(args[i].indexOf("=") + 1)));
			} else if (args[i].startsWith("--duration-shift=")
					|| args[i].startsWith("-durs=[number]")) {
				mediaAlgorithm.setDurationWeight(Integer.parseInt(args[i]
						.substring(args[i].indexOf("=") + 1)));
			} else if (args[i].startsWith("--type=")
					|| args[i].startsWith("-t=")) {
				String type = args[i].substring(args[i].indexOf("=") + 1);
				if (type.equals("NONE")) {
					mediaAlgorithm.setType(Media.Type.NONE);
				} else if (type.equals("SERIES")) {
					mediaAlgorithm.setType(Media.Type.SERIE);
				} else if (type.equals("FILMS")) {
					mediaAlgorithm.setType(Media.Type.FILM);
				} else {
					System.err.println("Le type " + type + "est inconnu!");
					mediaAlgorithm.setType(Media.Type.NONE);
				}
			} else if (args[i].startsWith("-nb=")
					|| args[i].startsWith("--number-of-recommendation=")) {
				mediaAlgorithm.setNbRecommandation(Integer.parseInt(args[i]
						.substring(args[i].indexOf("=") + 1)));
			} else if (args[i].startsWith("--json")) {
				i++;
				database.setFileOutput(args[i]);
			} else {
				System.err
						.println("\""
								+ args[i]
								+ "\" is not an argument! Please read help using --help");
			}
		}
		/* Check the film and the number of film seen */
		database.addFilmsSeen(filmSeen);
		/* Run the program in correct mode */
		if (cli) {
			/* Check the database */
			if (database.getFilmsNotSeen().size() < 1) {
				System.err.println("Error, there is no database selected");
				System.exit(1);
			} else if (database.getFilmsNotSeen().isEmpty()) {
				System.err.println("Error, you've seen every film !");
				System.exit(1);
			} else if (database.getFilmsSeen().isEmpty()) {
				System.err
						.println("Error, there is no film you've seen in our database");
				System.exit(1);
			}
			ArrayList<Media> recommendedFilms = mediaAlgorithm.execute();
			for (Media m : recommendedFilms) {
				System.out.println("We advise you: " + m.toString());
			}
			if (!database.getFileOutput().equals("")) {

			}
		} else {
			@SuppressWarnings("unused")
			View vue = new View(database, mediaAlgorithm);
		}
	}
}