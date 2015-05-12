package filmfinder;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
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
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class View extends JFrame {
	private static final long serialVersionUID = -4074747536669981312L;
	private JList<Media> lis;
	ArrayListModel T4;
	ArrayListModel filmsVus;
	JList<Media> listfv;
	ArrayListModel filmsres;
	JList<Media> listRes;
	Database database;
	JButton add, remove;
	private JPanel pan = new JPanel();
	JTextPane titre1;
	JScrollPane titre1pan;

	public View(Database database) {
		super("Film Finder");
		GridLayout lay = new GridLayout(2, 3);
		pan = new JPanel();
		pan.setLayout(lay);
		this.database = database;
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu test1 = new JMenu("Fichier");

		// TODO: FAIRE UN POPUP
		JMenu test2 = new JMenu("A propos");

		View vu = this;
		JMenuItem addDatabaseItem = new JMenuItem("Add text database");

		JMenuItem closeItem = new JMenuItem("Close");
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		test1.add(addDatabaseItem);
		test1.add(closeItem);
		menuBar.add(test1);
		menuBar.add(test2);
		this.setJMenuBar(menuBar);

		T4 = new ArrayListModel();
		lis = new JList<Media>(T4);
		for (Media m : database.getMedias()) {
			T4.add(m);
		}
		T4.sort();

		pan.add(new JScrollPane(lis));

		JPanel panButon1 = new JPanel();
		JPanel panButon2 = new JPanel();
		panButon1.setLayout(new GridBagLayout());
		panButon2.setLayout(new GridBagLayout());
		add = new JButton("   Add ->   ");
		add.setSize(100, 50);
		remove = new JButton("<- Remove");

		// add.setMaximumSize(new Dimension(50, 50));
		panButon1.add(add);
		panButon2.add(remove);
		JPanel panButon = new JPanel();
		panButon.setLayout(new GridLayout(2, 1));
		panButon.add(panButon1);
		panButon.add(panButon2);
		pan.add(panButon);
		// lis = new JList<String>();

		filmsVus = new ArrayListModel();
		listfv = new JList<Media>(filmsVus);

		listRes = new JList<Media>();

		pan.add(new JScrollPane(listfv));
		titre1 = new JTextPane();
		titre1.setContentType("text/html");
		titre1.setOpaque(false);
		// titre1.setBackground(Color.);
		// titre1.setEditable(false);
		// titre1.set
		titre1.setText("<h2>Informations</h2>");
		titre1pan = new JScrollPane(titre1);
		// titre1pan.add(titre1);
		pan.add(titre1pan);

		JPanel panbtngo = new JPanel();
		JPanel panin = new JPanel();
		panin.setLayout(new GridBagLayout());
		// panbtngo.setLayout(new GridLayout());
		JButton btngo = new JButton("Search");
		panbtngo.add(btngo);
		panin.add(panbtngo);
		pan.add(panin);

		this.setContentPane(pan);

		JLabel titre2 = new JLabel("Résultat");
		pan.add(titre2);

		lis.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (lis.isSelectionEmpty() == false) {
					Media t = (Media) T4.getElementAt(lis.getSelectedIndex());
					System.out.println(t.getInfo());
					titre1.setText(t.getInfo());
				}
			}
		});
		listfv.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listfv.isSelectionEmpty() == false) {
					Media t = (Media) filmsVus.getElementAt(listfv
							.getSelectedIndex());
					System.out.println(t.getInfo());
					titre1.setText(t.getInfo());
				}
				// System.out.println(t.getInfo());
				// titre1.setText(t.getInfo());
				// titre1.setSelectionStart(0);
				// listfv.updateUI();
			}
		});
		addDatabaseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser dialogue = new JFileChooser();
				int res = dialogue.showOpenDialog(vu);
				dialogue.setName("Select the database file");
				if (dialogue.getSelectedFile() != null) {
					try {
						if (dialogue.getSelectedFile().getPath()
								.endsWith(".txt")) {
							database.addDatabase(dialogue.getSelectedFile()
									.getPath());
							update();
						} else
							JOptionPane.showMessageDialog(vu,
									"This is not a database!", "Error",
									JOptionPane.ERROR_MESSAGE);
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(vu, "File not found!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lis.isSelectionEmpty() == false) {
					// List<Media> listinter=lis.getSelectedValuesList()
					for (Media m : lis.getSelectedValuesList()) {
						if (!filmsVus.contains(m)) {
							filmsVus.add(m);
							T4.remove(m);
						}
					}
					listfv.updateUI();
					lis.clearSelection();
					lis.updateUI();
				}
			}
		});
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listfv.isSelectionEmpty() == false) {
					// List<Media> listinter=lis.getSelectedValuesList()
					for (Media m : listfv.getSelectedValuesList()) {
						T4.add(m);
						filmsVus.remove(m);
					}
					listfv.clearSelection();
					listfv.updateUI();
					T4.sort();
					lis.updateUI();
				}
			}
		});

		this.setVisible(true);

	}

	public void update() {
		for (Media m : database.getMedias()) {
			T4.add(m);
		}
		T4.sort();

		lis.updateUI();
	}
}

class ArrayListModel extends AbstractListModel {

	private List data = new ArrayList();

	public boolean contains(Object o) {
		return data.contains(o);
	}

	public Object getElementAt(int index) {
		return this.data.get(index);
	}

	public int getSize() {
		return this.data.size();
	}

	public void remove(Object o) {
		data.remove(o);
	}

	public void add(Object element) {
		// On ajoute un élément à la liste :
		this.data.add(element);
		// On envoi un évenement pour signaler l'ajout :
		// fireIntervalAdded(this, this.getSize(), this.getSize());
	}

	public void sort() {
		// On trie les données :
		Collections.sort(this.data);
		// On envoi un évenement pour signaler que les données ont changées :
		// fireContentsChanged(this, 0, this.getSize());
	}

}
