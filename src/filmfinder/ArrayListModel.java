package filmfinder;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;

/**
 * Class use as a model of arrayList
 * 
 * @author Yoni Levy & Romain Tissier
 *
 */
public class ArrayListModel extends AbstractListModel<Media> {

	private static final long serialVersionUID = 2334568404074205527L;

	/**
	 * List containing data
	 */
	private ArrayList<Media> data;

	/**
	 * Constructor initializing the model
	 */
	public ArrayListModel() {
		data = new ArrayList<Media>();
	}

	/**
	 * Constructor initializing the model with an existing List
	 * 
	 * @param arrayList
	 *            : list to copy in the model
	 */
	public ArrayListModel(ArrayList<Media> arrayList) {
		super();
		data = new ArrayList<Media>();
		data.addAll(arrayList);
	}

	/**
	 * Getter returning the data
	 * 
	 * @return data of the model
	 */
	public ArrayList<Media> getData() {
		return data;
	}

	/**
	 * Observer returning if the model contains a media or not
	 * 
	 * @param m
	 *            : media to test
	 * @return boolean answer
	 */
	public boolean contains(Media m) {
		return data.contains(m);
	}

	/**
	 * Getter returning an element at an index
	 * 
	 * @param index
	 *            : index
	 * @return Media at the index in the model
	 */
	public Media getElementAt(int index) {
		return this.data.get(index);
	}

	/**
	 * Getter returning the size of the model
	 * 
	 * @return size of the model
	 */
	public int getSize() {
		return this.data.size();
	}

	/**
	 * Getter returning if the model is empty
	 * 
	 * @return if the model is empty
	 */
	public boolean isEmpty() {
		return this.data.isEmpty();
	}

	/**
	 * Method removing an element in the model
	 * 
	 * @param m
	 *            : media to remove
	 */
	public void remove(Media m) {
		data.remove(m);
	}

	/**
	 * Method removing an media from an index in the model
	 * 
	 * @param index
	 *            : index
	 */
	public void remove(int index) {
		data.remove(index);
	}

	/**
	 * Method adding a media in the model
	 * 
	 * @param element
	 *            : media to add in the model
	 */
	public void add(Media element) {
		this.data.add(element);
	}

	/**
	 * Method sorting the model
	 */
	public void sort() {
		Collections.sort(this.data);
	}

}
