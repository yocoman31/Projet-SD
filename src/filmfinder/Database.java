//TODO: Implémenter le JSON
package filmfinder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

import filmfinder.Media.Type;

/**
 * Class representing a film database
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class Database extends LinkedList<Media> {

	private static final long serialVersionUID = -5768949048626790295L;

	/**
	 * Constructor initializing a database
	 * 
	 * @param file
	 *            : path of the file containing a list of film
	 * @throws FileNotFoundException
	 */
	public Database(String file) throws FileNotFoundException {
		super();
		this.loadDatabaseFile(file);
	}

	/**
	 * Constructor initializing the superclass
	 */
	public Database() {
		super();
	}

	/**
	 * Methode use to load a database file and fill the Database Object
	 * 
	 * @param file
	 *            : path of the file containing a list of film
	 * @throws FileNotFoundException
	 */
	public void loadDatabaseFile(String file) throws FileNotFoundException {
		// TODO: utiliser des régex
		Scanner scanner = new Scanner(new FileReader(file));
		while (scanner.hasNext()) {
			String tampon = scanner.nextLine();
			while (tampon.equals(""))
				tampon = scanner.nextLine();
			tampon = tampon.substring(tampon.indexOf(".") + 1);
			String titre = Utils.eraseSpace(tampon.substring(0,
					tampon.indexOf("(")));
			String annee = tampon.substring(tampon.indexOf("(") + 1,
					tampon.indexOf(")"));
			Type type;
			Integer temps = null;
			if (annee.indexOf(" ") == -1) {
				type = Media.Type.FILM;
			} else {
				type = Media.Type.SERIE;
				annee = annee.substring(0, 4);
			}
			String synopsi = "";
			while (synopsi.equals(""))
				synopsi = scanner.nextLine();
			synopsi = Utils.eraseSpace(synopsi);
			String directors[] = null, casting[] = null, genre[] = null;
			do {
				System.out.println("On boucle");
				tampon = scanner.nextLine();
				if (tampon.startsWith("Director")) {
					directors = new String[Utils.countOccurence(tampon, ',')];
					tampon = tampon.substring(tampon.indexOf(':') + 1);
					for (int i = 0; i < directors.length; i++) {
						if (tampon.indexOf(',') != -1) {
							directors[i] = Utils.eraseSpace(tampon.substring(0,
									tampon.indexOf(',')));
							tampon.substring(tampon.indexOf(',') + 1);
						} else
							directors[i] = Utils.eraseSpace(tampon);
					}
				} else if (tampon.startsWith("With")) {
					casting = new String[Utils.countOccurence(tampon, ',')];
					tampon = tampon.substring(tampon.indexOf(':') + 1);
					for (int i = 0; i < casting.length; i++) {
						System.out.println("Occurence: " + i);
						if (tampon.indexOf(',') != -1) {
							casting[i] = Utils.eraseSpace(tampon.substring(0,
									tampon.indexOf(',')));
							tampon = tampon.substring(tampon.indexOf(',') + 1);
						} else
							casting[i] = Utils.eraseSpace(tampon);
					}
				} else if (!tampon.equals("")) {
					genre = new String[Utils.countOccurence(tampon, '|')];
					tampon = tampon.substring(tampon.indexOf(':') + 1);
					for (int i = 0; i < genre.length; i++) {
						System.out.println("Occurence: " + i);
						if (tampon.indexOf('|') != -1) {
							genre[i] = Utils.eraseSpace(tampon.substring(0,
									tampon.indexOf('|')));
							tampon = tampon.substring(tampon.indexOf('|') + 1);
						} else if (tampon.indexOf("mins") != -1) {
							tampon = Utils.eraseSpace(tampon.substring(0,
									tampon.indexOf("mins")));
							temps = Integer.parseInt(tampon.substring(tampon
									.lastIndexOf(" ") + 1));
							tampon = tampon.substring(0,
									tampon.lastIndexOf(" "));
							genre[i] = Utils.eraseSpace(tampon);
						} else
							genre[i] = Utils.eraseSpace(tampon);
					}
				}
			} while (!tampon.equals(""));
			this.add(new Media(titre, Integer.parseInt(annee), type, synopsi,
					directors, casting, genre, temps));
			for (int i = 0; directors != null && i < directors.length; i++)
				System.out.println("-" + directors[i] + "-");
			for (int i = 0; casting != null && i < casting.length; i++)
				System.out.println("-" + casting[i] + "-");
			for (int i = 0; genre != null && i < genre.length; i++)
				System.out.println("-" + genre[i] + "-");
		}
		scanner.close();
	}

}