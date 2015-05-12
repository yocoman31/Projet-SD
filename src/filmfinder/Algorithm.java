package filmfinder;

import java.util.ArrayList;

public class Algorithm {
	Database database;
	ArrayList<String> filmsvus = new ArrayList<String>();
	ArrayList<String> filmsPasVus = new ArrayList<String>();
	ArrayList<Integer> cptFilms = new ArrayList<Integer>();
	ArrayList<String> genres = new ArrayList<String>();
	ArrayList<Integer> coefgenres = new ArrayList<Integer>();
	ArrayList<String> directors = new ArrayList<String>();
	ArrayList<Integer> coefdirectors = new ArrayList<Integer>();
	ArrayList<String> casting = new ArrayList<String>();
	ArrayList<Integer> coefcasting = new ArrayList<Integer>();
	int intermediateduration;

	public Algorithm(ArrayList<String> filmsvus, Database database) {
		this.filmsvus = filmsvus;
		this.database = database;
		for (int i = 0; i < database.size(); i++) {
			filmsPasVus.add(database.get(i).getTitle());
		}
		for (int i = 0; i < filmsvus.size(); i++) {
			filmsPasVus.remove(filmsvus.get(i));
		}
		for (int i = 0; i < filmsPasVus.size(); i++) {
			cptFilms.add(0);
		}
	}

	/**
	 * Fonction qui compte le nombre d'apparition des genres dans les films vus
	 */
	public void setCoefficientsGenres() {
		int indice;
		boolean dedans = false;
		for (int i = 0; i < filmsvus.size(); i++) {
			for (int j = 0; j < database.size(); j++) {
				if (filmsvus.get(i).equals(database.get(j).getTitle())) {
					for (int k = 0; k < database.get(j).getGenre().length; k++) {
						if (!genres.isEmpty()) {
							for (int l = 0; l < genres.size(); l++) {
								if (database.get(j).getGenre()[k].equals(genres
										.get(l))) {
									dedans = true;
								} else {
									dedans = false;
								}
							}
						}
						if (!dedans) {
							genres.add(database.get(j).getGenre()[k]);
							coefgenres.add(1);
						} else {
							indice = genres
									.indexOf(database.get(j).getGenre()[k]);
							System.out.println(indice);
							coefgenres.set(indice, coefgenres.get(indice) + 1);
						}
					}
				}
			}
		}
	}

	/**
	 * Fonction qui compte le nombre d'apparition des directeurs dans les films
	 * vus
	 */
	public void setCoefficientsDirectors() {
		int indice;
		boolean dedans = false;
		for (int i = 0; i < filmsvus.size(); i++) {
			for (int j = 0; j < database.size(); j++) {
				if (filmsvus.get(i).equals(database.get(j).getTitle())) {
					if (database.get(j).getDirector() != null) {
						for (int k = 0; k < database.get(j).getDirector().length; k++) {
							if (!directors.isEmpty()) {
								for (int l = 0; l < directors.size(); l++) {
									if (database.get(j).getDirector()[k]
											.equals(directors.get(l))) {
										dedans = true;
									} else {
										dedans = false;
									}
								}
							}
							if (!dedans) {
								directors.add(database.get(j).getDirector()[k]);
								coefdirectors.add(1);
							} else {
								indice = directors.indexOf(database.get(j)
										.getDirector()[k]);
								coefdirectors.set(indice,
										coefdirectors.get(indice) + 1);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Fonction qui compte le nombre d'apparition des acteurs dans les films vus
	 */
	public void setCoefficientsCasting() {
		int indice;
		boolean dedans = false;
		for (int i = 0; i < filmsvus.size(); i++) {
			for (int j = 0; j < database.size(); j++) {
				if (filmsvus.get(i).equals(database.get(j).getTitle())) {
					for (int k = 0; k < database.get(j).getCasting().length; k++) {
						if (!casting.isEmpty()) {
							for (int l = 0; l < casting.size(); l++) {
								if (database.get(j).getCasting()[k]
										.equals(casting.get(l))) {
									dedans = true;
								} else {
									dedans = false;
								}
							}
						}
						if (!dedans) {
							casting.add(database.get(j).getCasting()[k]);
							coefcasting.add(1);
						} else {
							indice = casting.indexOf(database.get(j)
									.getCasting()[k]);
							coefcasting
									.set(indice, coefcasting.get(indice) + 1);
						}
					}
				}
			}
		}
	}

	/**
	 * Fonction qui calcule la durée moyenne des films vus
	 */
	public void setCoefficientsDuration() {
		int total = 0;
		int cpt = 0;
		for (int i = 0; i < filmsvus.size(); i++) {
			for (int j = 0; j < database.size(); j++) {
				if (filmsvus.get(i).equals(database.get(j).getTitle())) {
					if (database.get(j).getDuration() != null) {
						total = total + database.get(j).getDuration();
						cpt += 1;
					}
				}
			}
		}
		if (cpt != 0) {
			intermediateduration = total / cpt;
		}
	}

	/**
	 * Fonction qui calcule les compteurs pour chacun des films non vus
	 * 
	 * @param poidsgenre
	 *            : poids des genres dans le calcul
	 * @param poidsdirector
	 *            : poids des directeurs dans le calcul
	 * @param poidscasting
	 *            : poids des acteurs dans le calcul
	 * @param duration
	 *            : si vrai alors on prend en compte la durée des films dans le
	 *            calcul
	 */
	public void setCompteurFilms(int poidsgenre, int poidsdirector,
			int poidscasting, boolean duration) {
		// incrémentation du compteur pour les genres
		for (int i = 0; i < filmsPasVus.size(); i++) {
			for (int j = 0; j < database.size(); j++) {
				if (filmsPasVus.get(i).equals(database.get(j).getTitle())) {
					for (int k = 0; k < database.get(j).getGenre().length; k++) {
						for (int l = 0; l < genres.size(); l++) {
							if (database.get(j).getGenre()[k].equals(genres
									.get(l))) {
								cptFilms.set(i, cptFilms.get(i) + poidsgenre
										* coefgenres.get(l));
							}
						}
					}
				}
			}

		}

		// incrémentation du compteur pour les directors
		for (int i = 0; i < filmsPasVus.size(); i++) {
			for (int j = 0; j < database.size(); j++) {
				if (filmsPasVus.get(i).equals(database.get(j).getTitle())) {
					if (database.get(j).getDirector() != null) {
						for (int k = 0; k < database.get(j).getDirector().length; k++) {
							for (int l = 0; l < directors.size(); l++) {
								if (database.get(j).getDirector()[k]
										.equals(directors.get(l))) {
									cptFilms.set(i,
											cptFilms.get(i) + poidsdirector
													* coefdirectors.get(l));
								}
							}
						}
					}
				}
			}

		}

		// incrémentation du compteur pour les castings
		for (int i = 0; i < filmsPasVus.size(); i++) {
			for (int j = 0; j < database.size(); j++) {
				if (filmsPasVus.get(i).equals(database.get(j).getTitle())) {
					for (int k = 0; k < database.get(j).getCasting().length; k++) {
						for (int l = 0; l < casting.size(); l++) {
							if (database.get(j).getCasting()[k].equals(casting
									.get(l))) {
								cptFilms.set(i, cptFilms.get(i) + poidscasting
										* coefcasting.get(l));
							}
						}
					}
				}
			}

		}

		// incrémentation du compteur si durée comprise entre +/- la durée
		// moyenne
		if (duration) {
			for (int i = 0; i < filmsPasVus.size(); i++) {
				for (int j = 0; j < database.size(); j++) {
					if (filmsPasVus.get(i).equals(database.get(j).getTitle())) {
						if (database.get(j).getDuration() != null) {
							if (database.get(j).getDuration() <= intermediateduration + 15
									&& database.get(j).getDuration() >= intermediateduration - 15) {
								cptFilms.set(i, cptFilms.get(i) + 1);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Fonction qui retourne les n films/série les mieux classés
	 * 
	 * @param n
	 * @param type
	 *            : permet d'afficher les séries ou les films ou les deux
	 */
	public ArrayList<String> recommandations(int n, String type) {
		ArrayList<String> res = new ArrayList<String>();
		if (type.equals("film")) {
			for (int i = 0; i < filmsPasVus.size(); i++) {
				for (int j = 0; j < database.size(); j++) {
					if (filmsPasVus.get(i).equals(database.get(j).getTitle())) {
						if (database.get(j).getType().equals(Media.Type.SERIE)) {
							filmsPasVus.remove(i);
							cptFilms.remove(i);
						}
					}
				}
			}
		} else if (type.equals("série")) {
			for (int i = 0; i < filmsPasVus.size(); i++) {
				for (int j = 0; j < database.size(); j++) {
					if (filmsPasVus.get(i).equals(database.get(j).getTitle())) {
						if (database.get(j).getType().equals(Media.Type.FILM)) {
							filmsPasVus.remove(i);
							cptFilms.remove(i);
						}
					}
				}
			}
		} else {
			type = "film/série";
		}
		for (int i = 0; i < n; i++) {
			int max = cptFilms.get(0);
			int indiceMax = 0;
			for (int j = 1; j < cptFilms.size(); j++) {
				if (cptFilms.get(j) > max) {
					indiceMax = j;
					max = cptFilms.get(j);
				}
			}
			System.out.println("Le " + (i + 1) + "ème " + type
					+ " recommandé est : " + filmsPasVus.get(indiceMax)
					+ " avec un score de " + max);
			res.add(filmsPasVus.get(indiceMax));
			cptFilms.remove(indiceMax);
			filmsPasVus.remove(indiceMax);
		}
		return res;

	}

	public final static void execute(ArrayList<String> filmSeen,
			Database database, Integer poidsgenre, Integer poidscasting,
			Integer poidsdirector, Boolean duration) {
		Algorithm algo = new Algorithm(filmSeen, database);
		if (poidsgenre == null)
			poidsgenre = 1;
		if (poidscasting == null)
			poidscasting = 1;
		if (poidsdirector == null)
			poidsdirector = 1;
		if (duration == null)
			duration = false;

		algo.setCoefficientsGenres();
		algo.setCoefficientsCasting();
		algo.setCoefficientsDirectors();
		algo.setCoefficientsDuration();

		algo.setCompteurFilms(poidsgenre, poidsdirector, poidscasting, duration);
		algo.recommandations(10, "tout");
	}
}
