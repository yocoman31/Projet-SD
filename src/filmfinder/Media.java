package filmfinder;

/**
 * Class representing a media in the database
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class Media implements Comparable<Media> {

	/**
	 * Enumeration of type of media
	 *
	 */
	public static enum Type {
		SERIE, FILM;
	}

	/**
	 * Title of the media
	 */
	private String title;

	/**
	 * Year of the media
	 */
	private Integer year;

	/**
	 * Type of the media
	 */
	private Type type;

	/**
	 * Synopsis of the media
	 */
	private String synopsis;

	/**
	 * Directors of the media
	 */
	private String[] directors;

	/**
	 * Casting of the media
	 */
	private String[] casting;

	/**
	 * Genre of the media
	 */
	private String[] genres;

	/**
	 * Duration of the media
	 */
	private Integer duration;

	/**
	 * Constructor initializing the media
	 */
	public Media() {
		this.title = null;
		this.year = null;
		this.type = null;
		this.synopsis = null;
		this.directors = null;
		this.casting = null;
		this.genres = null;
		this.duration = null;
	}

	/**
	 * Constructor initializing the media
	 * 
	 * @param title
	 *            : title of the media
	 * @param year
	 *            :
	 * @param type
	 *            : type of the media
	 * @param synopsis
	 *            : synopsis of the media
	 * @param directors
	 *            : directors of the media
	 * @param casting
	 *            : casting of the media
	 * @param genres
	 *            : genres of the media
	 * @param duration
	 *            : duration of the media
	 */
	public Media(String title, Integer year, Type type, String synopsis,
			String[] directors, String[] casting, String[] genres,
			Integer duration) {
		this.title = title;
		this.year = year;
		this.type = type;
		this.synopsis = synopsis;
		this.directors = directors;
		this.casting = casting;
		this.genres = genres;
		this.duration = duration;
	}

	/**
	 * Method getting the title
	 * 
	 * @return the title of the media
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method setting the title of the media
	 * 
	 * @param title
	 *            : (new) title of the media
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method getting the year of the media
	 * 
	 * @return year of the media
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Method setting the year of the media
	 * 
	 * @param annee
	 *            : (new) year of the media
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Method getting the Type of the media
	 * 
	 * @return type of the media
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Method setting type the media
	 * 
	 * @param type
	 *            : (new) type of the media
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Method getting the synopsis of the media
	 * 
	 * @return synopsis of the media
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * Method setting the synopsis of the media
	 * 
	 * @param synopsy
	 *            : (new) synopsis of the media
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * Method getting the directors of the media
	 * 
	 * @return directors of the media
	 */
	public String[] getDirectors() {
		return directors;
	}

	/**
	 * Method setting the directors of the media
	 * 
	 * @param directors
	 */
	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	/**
	 * Method getting the casting of the media
	 * 
	 * @return the casting of the media
	 */
	public String[] getCasting() {
		return casting;
	}

	/**
	 * Method setting the casting of the media
	 * 
	 * @param casting
	 *            : (new) casting of the media
	 */
	public void setCasting(String[] casting) {
		this.casting = casting;
	}

	/**
	 * Method getting genres of the media
	 * 
	 * @return genres of the media
	 */
	public String[] getGenres() {
		return genres;
	}

	/**
	 * Method setting genres of the media
	 * 
	 * @param genres
	 *            : (new) genres of the media
	 */
	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	/**
	 * Method getting the duration of the media
	 * 
	 * @return duration of the media
	 */
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String toString() {
		String res = this.title + " (" + this.year;
		if (this.type == Media.Type.SERIE)
			res = res + " , TV Series";
		res = res + ")";
		return res;
	}

	public String getInfo() {
		String res = "<html<h2>Informations:</h2><b>Titre:</b> " + this.title
				+ "<br>";

		if (year != null)
			res += "<b>Année: </b>" + year + "<br>";
		if (type == Media.Type.FILM)
			res += "<b>Type:</b> Film<br>";
		else
			res += "<b>Type:</b> Serie<br>";
		res += "<b>Synopsy:</b> " + synopsis + "<br>";
		if (directors != null) {
			res += "<b>Réalisateur(s): </b>";
			for (int i = 0; i < directors.length; i++) {
				res += directors[i];
				if (i != directors.length - 1)
					res += ", ";
			}
			res += "<br>";
		}
		if (casting != null) {
			res += "<b>Casting :</b>";
			for (int i = 0; i < casting.length; i++) {
				res += casting[i];
				if (i != casting.length - 1)
					res += ", ";
			}
			res += "<br>";
		}
		if (genres != null) {
			res += "<b>Genre :</b>";
			for (int i = 0; i < genres.length; i++) {
				res += genres[i];
				if (i != genres.length - 1)
					res += ", ";
			}
			res += "<br>";
		}
		if (duration != null)
			res += "<b>Durée: </b>" + duration + "<br>";
		return res + "</html>";
	}

	public int compareTo(Media m) {
		return this.title.compareToIgnoreCase(m.title);
	}
}
