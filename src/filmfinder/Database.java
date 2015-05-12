package filmfinder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import filmfinder.Media.Type;

public class Database {
	private String file;
	private ArrayList<Media> medias;

	public Database() {
		medias = new ArrayList<Media>();
	}

	public Database(String file) throws FileNotFoundException {
		this();
		this.addDatabase(file);
	}

	public void addDatabase(String file) throws FileNotFoundException {
		this.file = file;
		Scanner scanner = new Scanner(new FileReader(file));
		while (scanner.hasNext()) {
			String tampon = scanner.nextLine();
			while (tampon.equals(""))
				tampon = scanner.nextLine();
			tampon = tampon.substring(tampon.indexOf(".") + 1);
			String titre = eraseSpace(tampon.substring(0, tampon.indexOf("(")));
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
			String synopsi = "";
			while (synopsi.equals(""))
				synopsi = scanner.nextLine();
			synopsi = eraseSpace(synopsi);
			String director[] = null, casting[] = null, genre[] = null;
			do {
				System.out.println("On boucle");
				tampon = scanner.nextLine();
				if (tampon.startsWith("Director")) {
					director = new String[countOccurence(tampon, ',')];
					tampon = tampon.substring(tampon.indexOf(':') + 1);
					for (int i = 0; i < director.length; i++) {
						if (tampon.indexOf(',') != -1) {
							director[i] = eraseSpace(tampon.substring(0,
									tampon.indexOf(',')));
							tampon.substring(tampon.indexOf(',') + 1);
						} else
							director[i] = eraseSpace(tampon);
					}
				} else if (tampon.startsWith("With")) {
					casting = new String[countOccurence(tampon, ',')];
					tampon = tampon.substring(tampon.indexOf(':') + 1);
					for (int i = 0; i < casting.length; i++) {
						System.out.println("Occurence: " + i);
						if (tampon.indexOf(',') != -1) {
							casting[i] = eraseSpace(tampon.substring(0,
									tampon.indexOf(',')));
							tampon = tampon.substring(tampon.indexOf(',') + 1);
						} else
							casting[i] = eraseSpace(tampon);
					}
				} else if (!tampon.equals("")) {
					genre = new String[countOccurence(tampon, '|')];
					tampon = tampon.substring(tampon.indexOf(':') + 1);
					for (int i = 0; i < genre.length; i++) {
						System.out.println("Occurence: " + i);
						if (tampon.indexOf('|') != -1) {
							genre[i] = eraseSpace(tampon.substring(0,
									tampon.indexOf('|')));
							tampon = tampon.substring(tampon.indexOf('|') + 1);
						} else if (tampon.indexOf("mins") != -1) {
							tampon = eraseSpace(tampon.substring(0,
									tampon.indexOf("mins")));
							temps = Integer.parseInt(tampon.substring(tampon
									.lastIndexOf(" ") + 1));
							tampon = tampon.substring(0,
									tampon.lastIndexOf(" "));
							genre[i] = eraseSpace(tampon);
						} else
							genre[i] = eraseSpace(tampon);
					}
				}
			} while (!tampon.equals(""));
			medias.add(new Media(titre, Integer.parseInt(annee), type, synopsi,
					director, casting, genre, temps));
			for (int i = 0; director != null && i < director.length; i++)
				System.out.println("-" + director[i] + "-");
			for (int i = 0; casting != null && i < casting.length; i++)
				System.out.println("-" + casting[i] + "-");
			for (int i = 0; genre != null && i < genre.length; i++)
				System.out.println("-" + genre[i] + "-");
		}

	}

	public static String eraseSpace(String s) {
		while (s.startsWith(" "))
			s = s.substring(1);
		while (s.endsWith(" "))
			s = s.substring(0, s.length() - 1);
		return s;
	}

	private int countOccurence(String s, char c) {
		String sub = s;
		int res = 1;
		while (sub.indexOf(c) != -1) {
			System.out.println("");
			res++;
			sub = sub.substring(sub.indexOf(c) + 1);
		}
		System.out.println("On a " + res);
		return res;
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