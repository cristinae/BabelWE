package cat.trachemys.interlingua.prepro;

import java.io.File;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.Among;
import org.tartarus.snowball.ext.danishStemmer;
import org.tartarus.snowball.ext.dutchStemmer;
import org.tartarus.snowball.ext.englishStemmer;
import org.tartarus.snowball.ext.finnishStemmer;
import org.tartarus.snowball.ext.frenchStemmer;
import org.tartarus.snowball.ext.germanStemmer;
import org.tartarus.snowball.ext.hungarianStemmer;
import org.tartarus.snowball.ext.italianStemmer;
import org.tartarus.snowball.ext.norwegianStemmer;
import org.tartarus.snowball.ext.portugueseStemmer;
import org.tartarus.snowball.ext.romanianStemmer;
import org.tartarus.snowball.ext.russianStemmer;
import org.tartarus.snowball.ext.spanishStemmer;
import org.tartarus.snowball.ext.swedishStemmer;
import org.tartarus.snowball.ext.turkishStemmer;

//import cat.trachemys.interlingua.basics.log.BWELogger;

/**
 * Implements the Stemmer class calling the Snowball stemmer
 * 
 * @author cristina
 * @since Jul 19, 2017
 */
public class SBStemmer implements Stemmer {

	/** Logger */
	//private static BWELogger logger = 
	//		new BWELogger(SBStemmer.class.getSimpleName());

	/** 
	 * Runs the stemmer on an input file.
	 * 
	 * TODO: implement when needed
	 * 
	 * @param input
	 * 			Input file
	 * @param lang
	 * 			Language of the input text
	 * @param output
	 * 			File where to store the lemmas
	 */
	public void execute(File input, String lang, File output) {
		
	}

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
	public String execute(String input, String lang) {
		
		SnowballStemmer stemmer = loadStemmer(lang);	
		stemmer.setCurrent(input);
		stemmer.stem();
		String tokOutput = stemmer.getCurrent();
		
		return tokOutput;		
	}

	
	
	private  SnowballStemmer loadStemmer(String l){

		if (l.equals("da"))	return new danishStemmer();
		else if (l.equals("nl"))	return new dutchStemmer();
		else if (l.equals("en"))	return new englishStemmer();
		else if (l.equals("fi"))	return new finnishStemmer();	
		else if (l.equals("fr"))	return new frenchStemmer();
		else if (l.equals("de"))	return new germanStemmer();
		else if (l.equals("hu"))	return new hungarianStemmer(); 
		else if (l.equals("it"))	return new italianStemmer();
		else if (l.equals("no"))	return new norwegianStemmer();
		else if (l.equals("pt"))	return new portugueseStemmer();
		else if (l.equals("ro"))	return new romanianStemmer();
		else if (l.equals("ru"))	return new russianStemmer();
		else if (l.equals("es"))	return new spanishStemmer();
		else if (l.equals("se"))	return new swedishStemmer();	
		else if (l.equals("tk"))	return new turkishStemmer();	
		
		return null;	
	}

}
