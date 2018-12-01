package cat.trachemys.interlingua.prepro;

import java.io.File;
import java.util.Properties;

/**
 * Interface to tokenise raw text
 *   
 * @author cristina
 * @since Nov 29, 2016
 */

public interface Tokeniser {

	/** 
	 * Runs the tokeniser on an input file.
	 * 
	 * @param section
	 * 			Properties object with the config file
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the tokenisation
	 */
	public void execute(Properties p, File input, String lang, File output);

	
	/** 
	 * Runs the tokeniser on an input string. Returns the tokenised string.
	 * 
	 * @param section
   *      Properties object with the config file's proper section loaded
	 * @param input
	 * 			Input string text
	 * @param lang
	 * 			Language of the input text
	 * 
	 * @return 
	 * 			Tokenised string
	 */
	public String execute(Properties p, String input, String lang);

}
