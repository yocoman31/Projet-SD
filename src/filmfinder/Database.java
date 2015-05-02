package filmfinder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import filmfinder.Media.Type;

public class Database {
	private String file;
	private ArrayList<Media> medias;

	public Database(String file) throws FileNotFoundException {
		this.file = file;
		Scanner scanner = new Scanner(new FileReader(file));
		while (scanner.hasNext()) {
			String tampon = scanner.nextLine();
			tampon = tampon.substring(tampon.indexOf(".") + 1);
			String titre = eraseSpace(tampon.substring(0, tampon.indexOf("(")));
			String annee = tampon.substring(tampon.indexOf("(") + 1,
					tampon.indexOf(")"));
			Type type;
			if (annee.indexOf(" ") == -1) {
				type = Media.Type.FILM;
			} else {
				type = Media.Type.SERIE;
				annee = annee.substring(0, 4);
			}

			String synopsi = "";
			while (synopsi.equals(""))
				synopsi = scanner.nextLine();
			synopsi = eraseSpace(synopsi);
			String director = "", casting[] = null, genre[] = null;
			do {
				tampon = scanner.nextLine();
				if (tampon.startsWith("Director")) {
					director = tampon.substring(tampon.indexOf(":") + 1);
					director = eraseSpace(director);
				} else if (tampon.startsWith("With")) {
					/* TODO: compter les acteurs et les enregistrer */
					// casting = new String[];
				} else if (!tampon.equals(" ")) {
					/* TODO: compter les genres et les enregistrer */
				}
			} while (!tampon.equals(""));

			System.out.println("-" + titre + "-");
			System.out.println("-" + annee + "-");
			System.out.println("-" + type + "-");
			System.out.println("-" + synopsi + "-");
			System.out.println("-" + director + "-");
			for (int i = 0; casting != null && i < casting.length; i++)
				System.out.println("-" + casting[i] + "-");
			for (int i = 0; genre != null && i < genre.length; i++)
				System.out.println("-" + genre[i] + "-");
			break;
		}

	}

	private String eraseSpace(String s) {
		while (s.startsWith(" "))
			s = s.substring(1);
		while (s.endsWith(" "))
			s = s.substring(0, s.length() - 1);
		return s;
	}

	public ArrayList<Media> getMedias() {
		return medias;
	}

	public void setMedias(ArrayList<Media> medias) {
		this.medias = medias;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}