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
			films.add("Spectre");
			films.add("Cendrillon");
			films.add("Game of Thrones");
			films.add("Fast & Furious 7");
			films.add("Home");
			films.add("Interstellar");
			films.add("Batman v Superman: Dawn of Justice");
			
			Algorithm algo = new Algorithm(films, database);
			
			algo.setCoefficientsGenres();
			algo.setCoefficientsCasting();
			algo.setCoefficientsDirectors();
			
			algo.setCompteurFilms();
			
			algo.recommandations(10);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
