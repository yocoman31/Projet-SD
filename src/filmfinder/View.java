package filmfinder;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * GUI of the program
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
	private JSlider sliderCasting, sliderGenre, sliderDirectors;
	private JComboBox<String> typeComboBox;
	private JComboBox<Integer> nbResComboBox;
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
		informations.setEditable(false);
		// informations.setSelectionStart(0);
		informations.setCaretPosition(0);
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

		findButton = new JButton("Find :");
		JPanel btnFindpan = new JPanel();
		btnFindpan.add(findButton);

		JLabel labelGenre = new JLabel("Genre coeficient:");
		JPanel labelGenrePan = new JPanel();
		labelGenrePan.add(labelGenre);

		sliderGenre = new JSlider();
		sliderGenre.setMinimum(1);
		sliderGenre.setMaximum(51);
		sliderGenre.setValue(26);

		JLabel labelCasting = new JLabel("Casting coeficient:");
		JPanel labelCastingPan = new JPanel();
		labelCastingPan.add(labelCasting);

		sliderCasting = new JSlider();
		sliderCasting.setMinimum(1);
		sliderCasting.setMaximum(51);
		sliderCasting.setValue(26);

		JLabel labelDirectors = new JLabel("Directors coeficient:");
		JPanel labelDirectorsPan = new JPanel();
		labelDirectorsPan.add(labelDirectors);

		sliderDirectors = new JSlider();
		sliderDirectors.setMinimum(1);
		sliderDirectors.setMaximum(51);
		sliderDirectors.setValue(26);

		JLabel labelType = new JLabel("Type:");
		JPanel labelTypePan = new JPanel();
		labelTypePan.add(labelType);

		typeComboBox = new JComboBox<String>();
		typeComboBox.addItem("None");
		typeComboBox.addItem("Series");
		typeComboBox.addItem("Films");

		JLabel labelnbRes = new JLabel("Number of results:");
		JPanel labelnbResPan = new JPanel();
		labelnbResPan.add(labelnbRes);

		nbResComboBox = new JComboBox<Integer>();
		for (int i = 1; i < 31; i++)
			nbResComboBox.addItem(i);
		nbResComboBox.setSelectedIndex(9);

		JPanel panConfig = new JPanel();
		panConfig.setLayout(new BoxLayout(panConfig, BoxLayout.PAGE_AXIS));
		panConfig.add(btnFindpan);
		panConfig.add(labelGenrePan);
		panConfig.add(sliderGenre);
		panConfig.add(labelCastingPan);
		panConfig.add(sliderCasting);
		panConfig.add(labelDirectorsPan);
		panConfig.add(sliderDirectors);
		panConfig.add(labelTypePan);
		panConfig.add(typeComboBox);
		panConfig.add(labelnbResPan);
		panConfig.add(nbResComboBox);

		JPanel panConfigVertical = new JPanel();
		panConfigVertical.setLayout(new GridBagLayout());
		panConfigVertical.add(panConfig);

		JPanel mainPanel = new JPanel();
		GridLayout mainLayout = new GridLayout(2, 3);
		mainPanel.setLayout(mainLayout);
		mainPanel.add(new JScrollPane(filmsList));
		mainPanel.add(panButon);
		mainPanel.add(new JScrollPane(filmsSeenList));
		mainPanel.add(new JScrollPane(informations));
		mainPanel.add(panConfigVertical);
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
					informations.setCaretPosition(0);
				}
			}
		});
		filmsSeenList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (filmsSeenList.isSelectionEmpty() == false) {
					Media t = (Media) database.getFilmsSeen().get(
							filmsSeenList.getSelectedIndex());
					informations.setText(t.getInfoHtml());
					informations.setCaretPosition(0);
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
							informations.setCaretPosition(0);
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
					for (int i = database.getRecommendedFilms().size() - 1; i >= 0; i--) {
						database.getRecommendedFilms().remove(i);
					}
					mediaAlgorithm.setGenresWeight(sliderGenre.getValue());
					mediaAlgorithm.setCastingWeight(sliderCasting.getValue());
					mediaAlgorithm.setDirectorWeight(sliderDirectors.getValue());

					mediaAlgorithm.setNbRecommandation((Integer) nbResComboBox
							.getSelectedItem());

					if (typeComboBox.getSelectedItem() == "Films") {
						mediaAlgorithm.setType(Media.Type.FILM);
					} else if (typeComboBox.getSelectedItem() == "Series") {
						mediaAlgorithm.setType(Media.Type.SERIE);
					} else {
						mediaAlgorithm.setType(Media.Type.NONE);
					}

					ArrayList<Media> resa = mediaAlgorithm.execute();
					recommandationList.setEnabled(true);
					for (Media m : resa) {
						database.getRecommendedFilms().add(m);
					}
					if (!database.getFileOutput().equals("")) {
						try {
							database.saveOutPutToJSON();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(rootPane,
									"Failled to save output to JSON", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
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

		JMenuItem saveRecommendedFilms = new JMenuItem(
				"Save recommended films list");
		saveRecommendedFilms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser dialogue = new JFileChooser();
				dialogue.setName("Choose your save file");
				dialogue.showSaveDialog(rootPane);
				if (dialogue.getSelectedFile() != null) {
					String outPutFile = dialogue.getSelectedFile().getPath();
					if (!outPutFile.endsWith(".json"))
						outPutFile += ".json";
					database.setFileOutput(outPutFile);
					try {
						database.saveOutPutToJSON();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(rootPane,
								"Failled to save film list!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					JOptionPane.showMessageDialog(rootPane, "Film list saved!",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(addDatabaseItem);
		fileMenu.add(saveRecommendedFilms);
		fileMenu.add(closeItem);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);

		super.setJMenuBar(menuBar);
	}
}
