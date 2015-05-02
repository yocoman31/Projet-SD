package filmfinder;

import java.io.FileNotFoundException;

public class Filmfinder {
	public static void main(String[] args) {
		System.out.println("Bonjour");
		for (String a : args)
			System.out.println(a);
		try {
			Database database = new Database(args[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
