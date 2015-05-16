package filmfinder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class use to compute the Algorithm
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class MediaAlgorithm {

	/**
	 * Films not seen by the user
	 */
	private HashMap<Media, Integer> filmsNotSeen;
	/**
	 * Coefficients of the critter
	 */
	private HashMap<String, Integer> coefGenres, coefDirectors, coefCasting;
	/**
	 * Configuration variables
	 */
	private Integer averageDuration, genreWeight, castingWeight,
			directorWeight, durationShift, durationWeight;
	private Media.Type type;
	/**
	 * Database used by the algorithm
	 */
	private Database database;

	/**
	 * Constructor initializing algorithm
	 * 
	 * @param database
	 *            : database used by the algorithm
	 */
	public MediaAlgorithm(Database database) {
		this.database = database;
		averageDuration = null;
		genreWeight = null;
		castingWeight = null;
		directorWeight = null;
		durationShift = null;
		durationWeight = null;
		filmsNotSeen = new HashMap<Media, Integer>();
		coefGenres = new HashMap<String, Integer>();
		coefDirectors = new HashMap<String, Integer>();
		coefCasting = new HashMap<String, Integer>();
	}

	/**
	 * Private method initializing the HashMap
	 */
	private void initAlgorithm() {
		filmsNotSeen.clear();
		for (Media m : database.getFilmsNotSeen())
			filmsNotSeen.put(m, 0);
		coefGenres.clear();
		coefDirectors.clear();
		coefCasting.clear();
	}

	/**
	 * Method computing critter coefficients
	 */
	private void computeCoefficients() {
		int sum = 0;
		int cpt = 0;
		for (Media m : database.getFilmsSeen()) {
			if (m.getGenres() != null) {
				for (String g : m.getGenres()) {
					if (!coefGenres.isEmpty() && coefGenres.containsKey(g))
						coefGenres.replace(g, coefGenres.get(g) + 1);
					else
						coefGenres.put(g, 1);
				}
			}
			if (m.getDirectors() != null) {
				for (String d : m.getDirectors()) {
					if (!coefDirectors.isEmpty()
							&& coefDirectors.containsKey(d))
						coefDirectors.replace(d, coefDirectors.get(d) + 1);
					else
						coefDirectors.put(d, 1);
				}
			}
			if (m.getCasting() != null) {
				for (String c : m.getCasting()) {
					if (!coefCasting.isEmpty() && coefCasting.containsKey(c))
						coefCasting.replace(c, coefCasting.get(c) + 1);
					else
						coefCasting.put(c, 1);

				}
			}
			if (m.getDuration() != null) {
				sum += m.getDuration();
				cpt++;
			}
		}
		if (cpt != 0)
			averageDuration = sum / cpt;
	}

	/**
	 * Function computing films rate
	 */
	private void computeFilmsRate() {
		for (Media m : filmsNotSeen.keySet()) {
			if (m.getGenres() != null) {
				for (String g : m.getGenres())
					if (coefGenres.get(g) != null)
						filmsNotSeen.replace(m, coefGenres.get(g) * genreWeight
								+ filmsNotSeen.get(m));
			}
			if (m.getDirectors() != null) {
				for (String d : m.getDirectors())
					if (coefDirectors.get(d) != null)
						filmsNotSeen.replace(m, coefDirectors.get(d)
								* genreWeight + filmsNotSeen.get(m));
			}
			if (m.getCasting() != null) {
				for (String c : m.getCasting())
					if (coefCasting.get(c) != null)
						filmsNotSeen.replace(m, coefCasting.get(c)
								* castingWeight + filmsNotSeen.get(m));
			}
			if (durationShift != null && durationWeight != null
					&& m.getDuration() != null)
				if (m.getDuration() <= averageDuration + averageDuration
						+ averageDuration
						&& m.getDuration() >= averageDuration + averageDuration)
					filmsNotSeen.replace(m,
							durationWeight + filmsNotSeen.get(m));
		}
	}

	/**
	 * Method executing the algorithm
	 * 
	 * @param n
	 *            : number of film you want
	 * @return list of recommended films
	 */
	public ArrayList<Media> execute(int n) {
		initAlgorithm();
		if (genreWeight == null)
			genreWeight = 1;
		if (castingWeight == null)
			castingWeight = 1;
		if (directorWeight == null)
			directorWeight = 1;
		this.computeCoefficients();
		this.computeFilmsRate();
		ArrayList<Media> res = new ArrayList<Media>();
		for (int i = 0; i < n; i++) {
			int max = 0;
			Media mMax = null;
			for (Media m : filmsNotSeen.keySet()) {
				// TODO: prise en compte des types
				if (filmsNotSeen.get(m) > max && !res.contains(m)) {
					max = filmsNotSeen.get(m);
					mMax = m;
				}
			}
			res.add(mMax);
		}
		return res;
	}

	/**
	 * Getter returning the genre's weight
	 * 
	 * @return genres's weight
	 */
	public Integer getGenreWeight() {
		return genreWeight;
	}

	/**
	 * Setter modifying genre's weight
	 * 
	 * @param genreWeight
	 *            : new genre Weight
	 */
	public void setGenresWeight(Integer genreWeight) {
		this.genreWeight = genreWeight;
	}

	/**
	 * Getter returning casting's weight
	 * 
	 * @return castingWeight
	 */
	public Integer getCastingWeight() {
		return castingWeight;
	}

	/**
	 * Setter Modifying casting's weight
	 * 
	 * @param castingWeight
	 *            : new casting weight
	 */
	public void setCastingWeight(Integer castingWeight) {
		this.castingWeight = castingWeight;
	}

	/**
	 * Getter returning duration weight
	 * 
	 * @return durationWeight
	 */
	public Integer getDurationWeight() {
		return durationWeight;
	}

	/**
	 * Setter modifying duration's weight
	 * 
	 * @param durationWeight
	 *            : new duration weight
	 */
	public void setDurationWeight(Integer durationWeight) {
		this.durationWeight = durationWeight;
	}

	/**
	 * Getter returning director weight
	 * 
	 * @return directorWeight
	 */
	public Integer getDirectorWeight() {
		return directorWeight;
	}

	/**
	 * Setter modifying director's weight
	 * 
	 * @param directorWeight
	 *            : new director weight
	 */
	public void setDirectorWeight(Integer directorWeight) {
		this.directorWeight = directorWeight;
	}

	/**
	 * Getter returning Duration shift
	 * 
	 * @return durationShift
	 */
	public Integer getDurationShift() {
		return durationShift;
	}

	/**
	 * Setter modifying duration shift
	 * 
	 * @param durationShift
	 *            : new duration shift
	 */
	public void setDurationShift(Integer durationShift) {
		this.durationShift = durationShift;
	}

	/**
	 * Getter returning the type of media
	 * 
	 * @return type of media
	 */
	public Media.Type getType() {
		return type;
	}

	/**
	 * Setter modifying the type of media
	 * 
	 * @param type
	 *            : new type of media
	 */
	public void setType(Media.Type type) {
		this.type = type;
	}
}
