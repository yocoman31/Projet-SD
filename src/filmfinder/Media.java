package filmfinder;

public class Media implements Comparable<Media> {
	public static enum Type {
		SERIE, FILM;
	}

	private String title;
	private Integer annee;
	private Type type;
	private String synopsy;
	private String[] director;
	private String[] casting;
	private String[] genre;
	private Integer duration;

	public Media(String title, Integer annee, Type type, String synopsy,
			String[] director, String[] casting, String[] genre,
			Integer duration) {
		this.title = title;
		this.annee = annee;
		this.type = type;
		this.synopsy = synopsy;
		this.director = director;
		this.casting = casting;
		this.genre = genre;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getSynopsy() {
		return synopsy;
	}

	public void setSynopsy(String synopsy) {
		this.synopsy = synopsy;
	}

	public String[] getDirector() {
		return director;
	}

	public void setDirector(String[] director) {
		this.director = director;
	}

	public String[] getCasting() {
		return casting;
	}

	public void setCasting(String[] casting) {
		this.casting = casting;
	}

	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String toString() {
		String res = this.title + " (" + this.annee;
		if (this.type == Media.Type.SERIE)
			res = res + " , TV Series";
		res = res + ")";
		return res;
	}

	public int compareTo(Media m) {
		return this.title.compareToIgnoreCase(m.title);
	}
}
