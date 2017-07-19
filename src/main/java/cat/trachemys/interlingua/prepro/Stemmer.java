package cat.trachemys.interlingua.prepro;

import java.io.File;

/**
 * Interface to stem raw text
 *   
 * @author cristina
 * @since Jul 19, 2017
 */
public interface Stemmer {

	/** 
	 * Runs the stemmer on an input file.
	 * 
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the lemmas
	 */
	public void execute(File input, String lang, File output);

	
	/** 
	 * Runs the stemmer on an input word. Returns the string with the stem.
	 * 
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 
	 * @return 
	 * 			String with the stem
	 */
	public String execute(String input, String lang);

}
