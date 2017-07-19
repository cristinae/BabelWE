package cat.trachemys.interlingua.prepro;


/**
 * Creates an annotation object (tokeniser, stemmer, lemmatiser) for a given language.
 * The tool depends on the language and the task.
 *   
 * @author cristina
 * @since Nov 29, 2016
 */

public class AnnotatorFactory {

	/**
	 * Returns the correct tokeniser according to the input language
	 * 
	 * @param language
	 * @return
	 */
	public Tokeniser getTokeniser(String language) {
		if (language.equalsIgnoreCase("en")) {
			return new MOSESTokeniser();
		} else if (language.equalsIgnoreCase("es")) {
			return new MOSESTokeniser();
		} else if (language.equalsIgnoreCase("fr")) {
			return new MOSESTokeniser();
		} else if (language.equalsIgnoreCase("de")) {
			return new MOSESTokeniser();
		} else if (language.equalsIgnoreCase("ar")) {
			return new MADATokeniser();
		} 
		return null;
	}

	/**
	 * Returns the correct lemmatiser according to the input language
	 * 
	 * @param language
	 * @return
	 */
	public Lemmatiser getLemmatiser(String language) {
		if (language.equalsIgnoreCase("en")) {
			return new MOSESIXALemmatiser();
		} else if (language.equalsIgnoreCase("es")) {
			return new MOSESIXALemmatiser();
		} else if (language.equalsIgnoreCase("fr")) {
			return new MOSESIXALemmatiser();
		} else if (language.equalsIgnoreCase("de")) {
			return new MOSESIXALemmatiser();
		} else if (language.equalsIgnoreCase("ar")) {
			return new MADALemmatiser();
		} else if (language.equalsIgnoreCase("tr")) {
			return new MOSESturkishLemmatiser();
		}	
		return null;
	}
	
	
	/**
	 * Returns the correct stemmer according to the input language.
	 * Currently only Snowball is used.
	 * 
	 * @param language
	 * @return
	 */
	public Stemmer getStemmer(String language){
		if (language.equalsIgnoreCase("en")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("es")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("fr")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("de")) {
			return new SBStemmer();
//		} else if (language.equalsIgnoreCase("ar")) {
//			return new SnowballStemmer();
		} else if (language.equalsIgnoreCase("tr")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("ro")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("it")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("nl")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("da")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("ca")) {
			return new SBStemmer();
		} else if (language.equalsIgnoreCase("pt")) {
			return new SBStemmer();
		}
		return null;	
	}

}
