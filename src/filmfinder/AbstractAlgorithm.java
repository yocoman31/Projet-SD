package filmfinder;

import java.util.HashMap;

public abstract class AbstractAlgorithm implements Algorithm {
	protected int nbRecommandation;
	protected HashMap<Media, Integer> filmsNotSeen;
	/**
	 * Searching configuration type
	 */
	protected Media.Type type;
	/**
	 * Database used by the algorithm
	 */
	protected Database database;

	/**
	 * Constructor model
	 * 
	 * @param database
	 *            : used database
	 */
	public AbstractAlgorithm(Database database) {
		this.database = database;
		filmsNotSeen = new HashMap<Media, Integer>();
	}

	public void initAlgorithm() {
		filmsNotSeen.clear();
		for (Media m : database.getFilmsNotSeen())
			filmsNotSeen.put(m, 0);
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

	/**
	 * Setter modifying the number of recommendation
	 * 
	 * @return number of recommendation
	 */
	public Integer getNbRecommandation() {
		return nbRecommandation;
	}

	public void setNbRecommandation(Integer nbRecommandation) {
		this.nbRecommandation = nbRecommandation;
	}
}
