package filmfinder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import filmfinder.Media.Type;

/**
 * Class use as a model of the database
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class Database {

	/**
	 * Lists used as model
	 */
	private ArrayListModel filmsNotSeen, filmsSeen, recommendedFilms;

	/**
	 * Constructor initializing the database Model
	 * 
	 * @param medias
	 *            : medias to copy in the database
	 */
	public Database() {
		super();
		filmsNotSeen = new ArrayListModel();
		filmsSeen = new ArrayListModel();
		recommendedFilms = new ArrayListModel();
	}

	/**
	 * Method adding film seen by the user
	 * 
	 * @param films
	 *            : films seen by the user
	 */
	public void addFilmsSeen(ArrayList<String> films) {
		if (!films.isEmpty()) {
			for (String s : films) {
				boolean test = false;
				for (Media m : filmsNotSeen.getData()) {
					if (s.equals(m.getTitle())) {
						filmsSeen.add(m);
						filmsNotSeen.remove(m);
						test = true;
						break;
					}
				}
				if (!test)
					System.err.println("Error, there is no film called \"" + s
							+ "\" in the database");
			}
		}
	}

	/**
	 * Getter returning the films seen by the user
	 * 
	 * @return films seen by the user
	 */
	public ArrayListModel getFilmsSeenModel() {
		return filmsSeen;
	}

	public ArrayList<Media> getFilmsSeen() {
		return filmsSeen.getData();
	}

	/**
	 * Getter returning the list of recommended films
	 * 
	 * @return list of recommended films
	 */
	public ArrayListModel getRecommendedFilmsModel() {
		return recommendedFilms;
	}

	/**
	 * Getter returning the list of recommended films
	 * 
	 * @return list of recommended films
	 */
	public ArrayList<Media> getRecommendedFilms() {
		return recommendedFilms.getData();
	}

	/**
	 * Getter returning the list of film not seen by the user
	 * 
	 * @return list of film not seen by the user
	 */
	public ArrayListModel getFilmsNotSeenModel() {
		return filmsNotSeen;
	}

	/**
	 * Getter returning the list of film not seen by the user
	 * 
	 * @return list of film not seen by the user
	 */
	public ArrayList<Media> getFilmsNotSeen() {
		return filmsNotSeen.getData();
	}

	/**
	 * Method use to load a database file and fill the Database Object
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
				tampon = scanner.nextLine();
				if (tampon.startsWith("Director")) {
					directors = new String[Utils.countOccurence(tampon, ',')];
					tampon = tampon.substring(tampon.indexOf(':') + 1);
					for (int i = 0; i < directors.length; i++) {
						if (tampon.indexOf(',') != -1) {
							directors[i] = Utils.eraseSpace(tampon.substring(0,
									tampon.indexOf(',')));
							tampon = tampon.substring(tampon.indexOf(',') + 1);
						} else
							directors[i] = Utils.eraseSpace(tampon);
					}
				} else if (tampon.startsWith("With")) {
					casting = new String[Utils.countOccurence(tampon, ',')];
					tampon = tampon.substring(tampon.indexOf(':') + 1);
					for (int i = 0; i < casting.length; i++) {
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
			filmsNotSeen.add(new Media(titre, Integer.parseInt(annee), type,
					synopsi, directors, casting, genre, temps));
		}
		scanner.close();
	}
}