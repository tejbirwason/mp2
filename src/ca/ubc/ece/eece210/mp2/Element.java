package ca.ubc.ece.eece210.mp2;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class to represent an entity in the catalogue. The element (in
 * this implementation) can either be an album or a genre.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public abstract class Element {
	String name;
	private List<Element> childrenElements = new ArrayList<Element>();
	
	/**
	 * Returns all the children of this entity. They can be albums or genres. In
	 * this particular application, only genres can have children. Therefore,
	 * this method will return the albums or genres contained in this genre.
	 * 
	 * @return the children
	 */
	public List<Element> getChildren() {
		return childrenElements;
	}

	/**
	 * Adds a child to this entity. Basically, it is adding an album or genre to
	 * an existing genre
	 * 
	 * @param b
	 *            the entity to be added.
	 */
	protected void addChild(Element b) {
		if ( this.hasChildren() && !this.childrenElements.contains(b) ) {
				childrenElements.add(b);
		} else {
			throw new IllegalArgumentException("Cannot have children or "
				+ "already contains this child" );
		}
	}

	/**
	 * Removes a child from this entity. Basically, it is removing 
	 * an album or genre from an existing genre
	 * 
	 * @param b		the child to be removed
	 */
	protected void removeChild(Element b) {
		if ( this.hasChildren() && this.childrenElements.contains(b) ) {
				childrenElements.remove(b);
		} else {
			throw new IllegalArgumentException("Cannot have children or "
				+ "child does not exist." );
		}
	}

	
	
	/**
	 * Abstract method to determine if a given entity can (or cannot) contain
	 * any children.
	 * 
	 * @return true if the entity can contain children, or false otherwise.
	 */
	public abstract boolean hasChildren();
}