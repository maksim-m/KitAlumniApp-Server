package edu.kit.isco.KitAlumniApp.server.parser;

import java.util.ArrayList;


/**
 * 
 *
 * @param <E>
 * @version 0.1
 */
public interface Parser<E> {
	/**
	 * Initialize parser with URL, check internet connection etc.
	 */
    public void init();
    /**
     * 
     * @return Array with instances of ListViewItem.
     */
    public ArrayList<E> parseContent();
}
