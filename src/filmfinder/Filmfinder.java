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

		} else {
			System.out.println("cond vraie!");
			final View view = new View(database);
		}
		System.out.println("Fin");
	}
}
