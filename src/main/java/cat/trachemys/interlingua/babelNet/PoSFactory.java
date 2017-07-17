package cat.trachemys.interlingua.babelNet;

import java.util.Map;

import cat.trachemys.interlingua.basics.log.BWELogger;
import it.uniroma1.lcl.babelnet.data.BabelPOS;

/**
 * Creates a PoSMaps object depending on the language. Currently a language has only
 * a possible set of tags.
 *   
 * @author cristina
 * @since Dec 27, 2016
 */

public class PoSFactory {

	/** Logger */
	private static BWELogger logger = 
			new BWELogger (PoSFactory.class.getSimpleName());


	/**
	 * Returns the adequate set of tag converter according to the input language
	 * 
	 * @param language
	 * @return
	 */
	public Map<String, BabelPOS> getPoSMapper(String language) {
		if (language.equalsIgnoreCase("en")) {
			logger.warn("Penn TreeBank PoS tags are expected for English.");
			return PoSMaps.BN_POS_EN;
		} else if (language.equalsIgnoreCase("es")) {
			logger.warn("Ancora PoS tags are expected for Spanish.");
			return PoSMaps.BN_POS_ES;
		} else if (language.equalsIgnoreCase("ar")) {
			logger.warn("Arabic Mada 2.1 PoS tags are expected for Arabic.");
			return PoSMaps.BN_POS_AR;
		} else if (language.equalsIgnoreCase("tr")) {
			logger.warn("TS Wikipedia Data Set PoS tags are expected for Turkish.");
			return PoSMaps.BN_POS_TR;
		} else if (language.equalsIgnoreCase("fr")) {
			logger.warn("The reduced Penn TreeBank PoS tags are expected for French.");
			return PoSMaps.BN_POS_FR;
		} else if (language.equalsIgnoreCase("de")) {
			logger.warn("STTS Stuttgart Tübingen PoS tags are expected for German.");
			return PoSMaps.BN_POS_DE;
		} else if (language.equalsIgnoreCase("nl")) {
			logger.warn("Dutch TreeTagger PoS tags are expected for Dutch.");
			return PoSMaps.BN_POS_NL;
		} else if (language.equalsIgnoreCase("it")) {
			logger.warn("Italian TreeTagger PoS tags are expected for Italian.");
			return PoSMaps.BN_POS_IT;
		} else if (language.equalsIgnoreCase("ro")) {
			logger.warn("Romanian TreeTagger PoS tags are expected for Romanian.");
			return PoSMaps.BN_POS_RO;
		} else {
			logger.error("There is no PoS mapping implemented for language "+language+".");
		}
		return null;
	}

}
