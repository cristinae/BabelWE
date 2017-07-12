package cat.trachemys.interlingua.babelNet;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cat.trachemys.interlingua.basics.log.BWELogger;

/**
 *
 * Set of methods to deal with the input/output files generated by the BabelNet annotation
 * 
 * @author cristina
 * @since Dec 19, 2016
 */
public class DataProcessor {
	
	/** Logger */
	private static BWELogger logger = 
			new BWELogger(DataProcessor.class.getSimpleName());

	public final static String lineSeparator = System.lineSeparator();

	
	/**
	 * Reads the nth factor from a string with 3 factors separated by a pipe
	 * 
	 * @param token
	 * 			string with all the factors
	 * @param n
	 * 			number of the factor to extract
	 * @return factor
	 * 			string with the nth factor
	 */
	public static String readFactor3(String token, int n) {
	
		String factor = null;
		
    	//System.out.println(token);

        Matcher m = Pattern.compile("(.+)\\|(.+)\\|(.+)").matcher(token);
        if (m.find()){
        	factor = m.group(n);
        } else {
        	logger.warn("Factor "+n+" coundn't be found for token "+token);
        }

		return factor;
	}

}