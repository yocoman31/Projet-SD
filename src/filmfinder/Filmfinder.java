package filmfinder;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Filmfinder {
	public static void main(String[] args) {
		System.out.println("Bonjour");
		for (String a : args)
			System.out.println(a);
		try {
			Database database = new Database(args[0]);
			ArrayList<String> films = new ArrayList<String>();
			films.add("Fast and Furious");
			films.add("Fast & Furious 7");
			
			Algorithm algo = new Algorithm(films, database);
			
			int poidsgenre = 1;
			int poidscasting = 1;
			int poidsdirector = 1;
			boolean duration = false;
			
		
			algo.setCoefficientsGenres();
			algo.setCoefficientsCasting();
			algo.setCoefficientsDirectors();
			algo.setCoefficientsDuration();
			
			algo.setCompteurFilms(poidsgenre, poidsdirector, poidscasting, duration);
			algo.recommandations(10, "tout");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
