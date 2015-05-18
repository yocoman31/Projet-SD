package filmfinder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

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
	private String fileOutput;

	/**
	 * Constructor initializing the database Model
	 * 
	 * @param medias
	 *            : medias to copy in the database
	 */
	public Database() {
		super();
		fileOutput = "";
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
	 * @throws IOException
	 */
	public void loadDatabaseFile(String file) throws IOException {
		if (file.endsWith(".txt"))
			loadTextDatabase(file);
		else if (file.endsWith(".json"))
			loadJSONDatabase(file);
		filmsNotSeen.sort();
	}

	/**
	 * Method loading a text database
	 * 
	 * @param file
	 *            : path to the database
	 * @throws FileNotFoundException
	 */
	private void loadTextDatabase(String file) throws IOException {
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
			String synopsis = "";
			while (synopsis.equals(""))
				synopsis = scanner.nextLine();
			if (!synopsis.startsWith("Director"))
				synopsis = Utils.eraseSpace(synopsis);
			String directors[] = null, casting[] = null, genre[] = null;
			do {
				if (synopsis.startsWith("Director")) {
					tampon = synopsis;
					synopsis = "No informations";
				} else
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
					synopsis, directors, casting, genre, temps));
		}
		scanner.close();

		ArrayList<Media> database = new ArrayList<Media>();
		database.addAll(filmsSeen.getData());
		database.addAll(filmsNotSeen.getData());

		this.saveToJSON(file.substring(0, file.lastIndexOf(".")) + ".json",
				database);
	}

	/**
	 * Method saving the database to the Json format
	 * 
	 * @param file
	 *            : output file
	 * @throws FileNotFoundException
	 */
	public void saveToJSON(String file, ArrayList<Media> listOfMedia)
			throws IOException {
		JsonArrayBuilder databaseBuilder = Json.createArrayBuilder();

		for (Media m : listOfMedia) {
			databaseBuilder.add(m.toJson());
		}
		JsonObject res = Json.createObjectBuilder()
				.add("Medias", databaseBuilder.build()).build();

		OutputStream output;
		output = new FileOutputStream(file);
		JsonWriter writer = Json.createWriter(output);
		writer.writeObject(res);

	}

	public void saveOutPutToJSON() throws IOException {
		saveToJSON(fileOutput, recommendedFilms.getData());
	}

	/**
	 * Method loading a database in the json format
	 * 
	 * @param file
	 *            : database to load
	 * @throws IOException
	 */
	private void loadJSONDatabase(String file) throws IOException {
		InputStream input;
		input = new FileInputStream(file);
		JsonReader jsonObjectReader = Json.createReader(input);
		JsonObject test = jsonObjectReader.readObject();
		JsonArray testArray = test.getJsonArray("Medias");
		for (int i = 0; i < testArray.size(); i++) {
			String titre = testArray.getJsonObject(i).getString("Title");
			Integer year = testArray.getJsonObject(i).getInt("Year");
			String stype = testArray.getJsonObject(i).getString("Type");
			Media.Type type = Media.Type.NONE;
			if (stype.equals("SERIE"))
				type = Media.Type.SERIE;
			else if (stype.equals("FILM"))
				type = Media.Type.FILM;
			String synopsis = testArray.getJsonObject(i).getString("Synopsis");
			JsonArray directorsArray = testArray.getJsonObject(i).getJsonArray(
					"Directors");
			String[] directors = null;
			if (directorsArray != null) {
				directors = new String[directorsArray.size()];
				for (int j = 0; directorsArray != null
						&& j < directorsArray.size(); j++) {
					directors[j] = directorsArray.getString(j);
				}
			}
			JsonArray castingArray = testArray.getJsonObject(i).getJsonArray(
					"Casting");
			String[] casting = null;
			if (castingArray != null) {
				casting = new String[castingArray.size()];
				for (int j = 0; castingArray != null && j < castingArray.size(); j++) {
					casting[j] = castingArray.getString(j);
				}
			}
			JsonArray genresArray = testArray.getJsonObject(i).getJsonArray(
					"Genres");
			String[] genres = null;
			if (genresArray != null) {
				genres = new String[genresArray.size()];
				for (int j = 0; genresArray != null && j < genresArray.size(); j++) {
					genres[j] = genresArray.getString(j);
				}
			}
			Integer duration = null;
			if (testArray.getJsonObject(i).keySet().contains("Duration")) {
				duration = testArray.getJsonObject(i).getInt("Duration");
			}
			filmsNotSeen.add(new Media(titre, year, type, synopsis, directors,
					casting, genres, duration));
		}
		input.close();
	}

	public String getFileOutput() {
		return fileOutput;
	}

	public void setFileOutput(String fileOutput) {
		this.fileOutput = fileOutput;
	}
}