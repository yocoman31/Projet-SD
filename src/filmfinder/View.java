package filmfinder;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//TODO: afficher un screen de l'affiche

/**
 * Gui of the program
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class View extends JFrame {
	private static final long serialVersionUID = -4074747536669981312L;

	/**
	 * Graphics components
	 */
	private JList<Media> filmsList, filmsSeenList, recommandationList;
	JTextPane informations;
	private JButton addButton, removeButton, findButton;

	/**
	 * Model variables
	 */
	private Database database;
	private MediaAlgorithm mediaAlgorithm;

	/**
	 * Constructor initializing the GUI
	 * 
	 * @param database
	 *            : database used by the GUI
	 * @param mediaAlgorithm
	 *            : algorithm used by the GUI
	 */
	public View(Database database, MediaAlgorithm mediaAlgorithm) {
		super("Film Finder");
		this.database = database;
		this.mediaAlgorithm = mediaAlgorithm;
		// this.setSize(800, 600);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar();
		this.initList();

		informations = new JTextPane();
		informations.setContentType("text/html");
		informations.setOpaque(false);
		informations.setText("<h2>Informations</h2>");

		JPanel panButonAdd = new JPanel();
		JPanel panButonRemove = new JPanel();
		panButonAdd.setLayout(new GridBagLayout());
		panButonRemove.setLayout(new GridBagLayout());
		addButton = new JButton("   Add ->   ");
		removeButton = new JButton("<- Remove");
		panButonAdd.add(addButton);
		panButonRemove.add(removeButton);

		JPanel panButon = new JPanel();
		panButon.setLayout(new GridLayout(2, 1));
		panButon.add(panButonAdd);
		panButon.add(panButonRemove);
		JPanel panbtngo = new JPanel();
		JPanel panfind = new JPanel();
		panfind.setLayout(new GridBagLayout());
		findButton = new JButton("Find :");
		panbtngo.add(findButton);
		panfind.add(panbtngo);

		JPanel mainPanel = new JPanel();
		GridLayout mainLayout = new GridLayout(2, 3);
		mainPanel.setLayout(mainLayout);
		mainPanel.add(new JScrollPane(filmsList));
		mainPanel.add(panButon);
		mainPanel.add(new JScrollPane(filmsSeenList));
		mainPanel.add(new JScrollPane(informations));
		mainPanel.add(panfind);
		mainPanel.add(new JScrollPane(recommandationList));
		this.setContentPane(mainPanel);

		this.initAction();
		this.setVisible(true);

	}

	/**
	 * Method initializing action on button
	 */
	private void initAction() {
		filmsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (filmsList.isSelectionEmpty() == false) {
					Media t = (Media) database.getFilmsNotSeen().get(
							filmsList.getSelectedIndex());
					informations.setText(t.getInfoHtml());
				}
			}
		});
		filmsSeenList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (filmsSeenList.isSelectionEmpty() == false) {
					Media t = (Media) database.getFilmsSeen().get(
							filmsSeenList.getSelectedIndex());
					informations.setText(t.getInfoHtml());
				}
			}
		});
		recommandationList
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (recommandationList.isSelectionEmpty() == false) {
							Media t = (Media) database.getRecommendedFilms()
									.get(recommandationList.getSelectedIndex());
							informations.setText(t.getInfoHtml());
						}
					}
				});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filmsList.isSelectionEmpty() == false) {
					for (Media m : filmsList.getSelectedValuesList()) {
						if (!database.getFilmsSeen().contains(m)) {
							database.getFilmsSeen().add(m);
							database.getFilmsNotSeen().remove(m);
						}
					}
					filmsSeenList.updateUI();
					filmsList.clearSelection();
					filmsList.updateUI();
				}
			}
		});
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!database.getFilmsSeen().isEmpty()) {
					ArrayList<Media> resa = mediaAlgorithm.execute(10);
					recommandationList.setEnabled(true);
					for (int i = database.getRecommendedFilms().size() - 1; i >= 0; i--) {
						database.getRecommendedFilms().remove(i);
					}
					for (Media m : resa) {
						database.getRecommendedFilms().add(m);
					}
					recommandationList.updateUI();
				} else {
					JOptionPane.showMessageDialog(rootPane,
							"Have you never seen a film ?", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filmsSeenList.isSelectionEmpty() == false) {
					for (Media m : filmsSeenList.getSelectedValuesList()) {
						database.getFilmsNotSeen().add(m);
						database.getFilmsSeen().remove(m);
					}
					filmsSeenList.clearSelection();
					filmsSeenList.updateUI();
					database.getFilmsNotSeenModel().sort();
					filmsList.updateUI();
				}
			}
		});
	}

	/**
	 * Method initializing the model of the GUI
	 */
	private void initList() {
		filmsList = new JList<Media>(database.getFilmsNotSeenModel());
		database.getFilmsNotSeenModel().sort();
		filmsSeenList = new JList<Media>(database.getFilmsSeenModel());
		recommandationList = new JList<Media>(
				database.getRecommendedFilmsModel());
		recommandationList.setEnabled(false);
		Media defaultList = new Media();
		defaultList
				.setTitle("No recommandation, \nPlease click \"find\" to find new film");
		database.getRecommendedFilms().add(defaultList);
	}

	/**
	 * Method initializing the menu bar
	 */
	private void setJMenuBar() {
		JMenuItem closeItem = new JMenuItem("Close");
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		JMenuItem addDatabaseItem = new JMenuItem("Add database");
		addDatabaseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser dialogue = new JFileChooser();
				dialogue.showOpenDialog(rootPane);
				dialogue.setName("Select the database file");
				if (dialogue.getSelectedFile() != null) {
					try {
						if (dialogue.getSelectedFile().getPath()
								.endsWith(".txt")
								|| dialogue.getSelectedFile().getPath()
										.endsWith(".json")) {
							database.loadDatabaseFile(dialogue
									.getSelectedFile().getPath());
						} else
							JOptionPane.showMessageDialog(rootPane,
									"This is not a database!", "Error",
									JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(rootPane,
								"File not found or corrupted!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				filmsList.updateUI();
			}
		});

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(addDatabaseItem);
		fileMenu.add(closeItem);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);

		super.setJMenuBar(menuBar);
	}
}
