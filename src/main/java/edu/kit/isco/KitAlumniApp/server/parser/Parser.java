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
     * Parses content from a html page and converts them into dataobjects for further processing.
     * @return returns a list of parsed objects
     */
    public ArrayList<E> parseContent();
}
