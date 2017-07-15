package cat.trachemys.interlingua.babelNet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.google.common.collect.Multimap;

import cat.trachemys.interlingua.basics.log.BWELogger;
import cat.trachemys.interlingua.prepro.Normaliser;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelNetUtils;
import it.uniroma1.lcl.babelnet.data.BabelPOS;
import it.uniroma1.lcl.jlt.util.Language;
import it.uniroma1.lcl.jlt.util.ScoredItem;

/**
 * 
 * Main class of the babelNet package devoted to include babelNet IDs in an
 * input corpus.
 * 
 * @author cristina
 * @since Dec 15, 2016
 */
public class DataIDAnnotator {
	
	/** Logger */
	private static BWELogger logger = 
			new BWELogger (DataIDAnnotator.class.getSimpleName());

	/** Conversion into BabelNet tags*/
	private static Map<String, BabelPOS> posMapping = null;
	
	// This is somewhere else
	public final static String lineSeparator = System.lineSeparator();

	/** BufferedWriter */
    private BufferedWriter bw = null;

	/** Constructor */
	public DataIDAnnotator (String language) {
		PoSFactory pf= new PoSFactory();
		posMapping = pf.getPoSMapper(language);
	
	}

	/**
	 * Parses the command line arguments
	 * 	
	 * @param args
	 * 			Command line arguments 
	 * @return
	 */
	private static CommandLine parseArguments(String[] args)
	{	
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cLine = null;
		Options options= new Options();
		CommandLineParser parser = new BasicParser();

		options.addOption("l", "language", true, 
					"Language of the input text "
					+ "[ar|en|es|tr|de|fr|ro|nl|it]");		
		options.addOption("i", "input", true, 
					"Input file to annotate");		
		options.addOption("f", "format", true, 
					"Format of the input file "
					+ "[wpl|conll]");		
		options.addOption("h", "help", false, "This help");
		//options.addOption("c", "config", true,
		//        	"Configuration file for the lumpSTS project");

		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception:" + exp.getMessage() );			
		}	
		
		if (cLine == null || !(cLine.hasOption("l")) ) {
			logger.error("Please, set the language.\n");
			formatter.printHelp(DataIDAnnotator.class.getSimpleName(),options );
			System.exit(1);
		}		
		if (cLine.hasOption("h")) {
			formatter.printHelp(DataIDAnnotator.class.getSimpleName(),options );
			System.exit(0);
		}
		if (!cLine.getOptionValue("f").equalsIgnoreCase("wpl") 
				&& !cLine.getOptionValue("f").equalsIgnoreCase("conll")){
			logger.error(cLine.getOptionValue("f")+" is a non-recognised data format.\n");
			formatter.printHelp(DataIDAnnotator.class.getSimpleName(),options );
			System.exit(1);			
		}
		return cLine;		
	}

	
	/**
	 * Main function to run the class, serves as example
	 * 
	 * @param args
	 * 		-l Language of the input text 
	 * 			(Arabic, English, Spanish, Turkish, French, German, Dutch, Italian, Romanian)
	 *      -i Input file 
	 *      -f Format of the input file 
	 *      	(wpl|conll)
	 * 		-c Configuration file
	 */

	public static void main(String[] args) {
		CommandLine cLine = parseArguments(args);
		
		// Language and layer
		String language = cLine.getOptionValue("l");

		// Input file
		File input = new File(cLine.getOptionValue("i"));
	
		// Format of the input file
		String format = cLine.getOptionValue("f");
		
		// Config file
		// Guessing if its an absolute or a relative path
		/*String conf = cLine.getOptionValue("c");
		String confFile;
		if (conf.startsWith(FileIO.separator)){
			confFile = conf;
		} else {
			confFile = System.getProperty("user.dir")+FileIO.separator+conf;
		}*/

		// Run
		DataIDAnnotator ann = new DataIDAnnotator (language);
		ann.annotate(input, language, format);

	}

	/**
	 * Does the actual annotation of the input file with the BabelNet ID
	 * 
	 * @param input
	 * 			input file to annotate
	 * 
	 */
	private void annotate(File input, String language, String format) {

		BabelNet bn = BabelNet.getInstance();

		// Initilise the writer
		File output = new File(input+"b");
		output.delete();
	    FileWriter fw = null;
		try {
			fw = new FileWriter(output, true);
			bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		// Read the input
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(input);
		    sc = new Scanner(inputStream, "UTF-8");
		    int i = 0;
		    while (sc.hasNext()) {
		        String line = sc.nextLine();
		        // Taking care of the input format
		        if (format.equalsIgnoreCase("wpl")){
			        i = annotateLineBufferWPL(line, language, bn, i);		        	
		        } else if (format.equalsIgnoreCase("conll")) {
			        i = annotateLineBufferCONLL(line, language, bn, i);		        			        	
		        }
		        		        
		        // Write every 10000 lines
		        if (i%10000==0){
		        	bw.flush();
		        }
		    }
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close everything
		    if (inputStream != null) {
		        try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    if (sc != null) {
		        sc.close();
		        try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}

	}

	private int annotateLineBufferWPL(String line, String language, BabelNet bn, int i) throws IOException {
        String[] tokens = line.split("\\s+");
        for (String token:tokens) {  
        	String id = "-";
        	String lemma = DataProcessor.readFactor3(token, 3);
        	String pos = DataProcessor.readFactor3(token, 2);
        	String word = DataProcessor.readFactor3(token, 1);
        	// This is a patch to solve the cases where a token has not been annotated
        	// "joker" is a toy PoS available in all the mappings as a BabelPOS.NOUN
        	// but is not present in the PoSAccept lists
        	if (lemma==null || pos==null){
        		lemma = token;
        		pos = "joker";
        		word = token;
        	}
        	id = getBNID(language, bn, lemma, pos);
    		bw.append(word+"|"+pos+"|"+lemma+"|"+id+" ");
        }
    	bw.newLine();
    	return i++;
	}


	private int annotateLineBufferCONLL(String line, String language, BabelNet bn, int i) throws IOException {
    	String id = "-";
        Matcher m = Pattern.compile("^(.+)\t(.+)\t(.+)").matcher(line);
        if (m.find()){
          	String lemma = m.group(3);
        	String pos = m.group(2);
        	String word = m.group(1);
        	if (lemma.equals("<unknown>")){ 
        		lemma = word;
        	}
        	id = getBNID(language, bn, lemma, pos);
    		bw.append(word+"|"+pos+"|"+lemma+"|"+id+" ");
        } else {
        	bw.newLine();
        	i++;
        }
        return i;
	}

	/**
	 * Kind of factory method to call the appropriate function to retrieve a BabelNet ID
	 * according to the input language
	 * 
	 * @param language
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID(String language, BabelNet bn, String lemma, String pos) {

		String id = "-";
		if (language.equalsIgnoreCase("en")) {
		    id = getBNID_en(bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("es")) {
		    id = getBNID_es(bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("ar")) {
		    id = getBNID_ar(bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("tr")) {
		    id = getBNID_tr(bn, lemma, pos);	 
		} else if (language.equalsIgnoreCase("fr")) {
			id = getBNID_fr(bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("de")) {
			id = getBNID_de(bn, lemma, pos);	
		}  else if (language.equalsIgnoreCase("nl")) {
			id = getBNID_nl(bn, lemma, pos);	
		}  
		return id;
	}

	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in English
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_en(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.EN;    	
    	
    	if(pos.equalsIgnoreCase("NNP") || pos.equalsIgnoreCase("NNPS")){ //NEs
    		ne = true;
    	} else if(PoSAccept.NEG_EN.contains(lemma)){                     //Negation      		
    		return NEG;
    	} else if(!PoSAccept.POS_EN_ACC.contains(pos)) {                 //Non-content PoS
    		return id;
    	}
     	
		BabelPOS bnPos = posMapping.get(pos);
		
    	if (bnPos == null){
    		return id;
    	} else {
    	   // NE are adding too much noise
    	   //id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang, ne);
    	   id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Spanish
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_es(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.ES;

		String pos2chars = "";
		if (pos.length() > 1){
	    	pos2chars = pos.substring(0, 2).toLowerCase(); 			
		} else {
	   		return id;			
		}

		if(pos2chars.equalsIgnoreCase("np")){                      //NEs
    		ne = true;
    	} else if(pos2chars.equalsIgnoreCase("rn")){               //Negation
    		return NEG;    		
    	} else if(!PoSAccept.POS_ES_ACC.contains(pos2chars)) {     //Non-content PoS
    		return id;
    	}
    	
		BabelPOS bnPos = posMapping.get(pos2chars); 

    	if (bnPos == null){
    		return id;
    	} else {
     	   // NE are adding too much noise
     	   //id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang, ne);
     	   id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Arabic
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_ar(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.AR;

    	if(pos.equalsIgnoreCase("noun_prop")){               //NEs
    		ne = true;    		
    	} else if(pos.equalsIgnoreCase("part_neg")){         //Negation
    		return NEG;
    	} else if(!PoSAccept.POS_AR_ACC.contains(pos)) {     //Non-content PoS
    		return id;
    	}
    	if (pos==lemma) {
    		pos = "noun";
    	}
    	    	    	
		BabelPOS bnPos = posMapping.get(pos); 
    	if (bnPos == null){
    		return id;
    	} else {
    		String lemmaClean = lemma.replaceAll("_\\d+", "");   //replaceAll but it should only happen at the end
    		lemmaClean = Normaliser.removeNonCharacters(lemmaClean);
    		lemmaClean = Normaliser.removeDiacriticsAR(lemmaClean);

     	    // NE are adding too much noise
     	    //id = BabelNetQuerier.retrieveID(bn, bnPos, lemmaClean, lang, ne);
    	    id = BabelNetQuerier.retrieveID(bn, bnPos, lemmaClean, lang);
    	}
		return id;
	}


	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Turkish
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_tr(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.TR;    	
    	
    	if(PoSAccept.NEG_TR.contains(lemma)){                     //Negation      		
    		return NEG;
    	} else if(!PoSAccept.POS_TR_ACC.contains(pos)) {          //Non-content PoS
    		return id;
    	}
     	
		BabelPOS bnPos = posMapping.get(pos);
		
    	if (bnPos == null){
    		return id;
    	} else {
    	   // NE are adding too much noise
    	   //id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang, ne);
    	   id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in French
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_fr(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.FR;

		if(PoSAccept.NEG_FR.contains(lemma)){                     	//Negation      		
    		return NEG;
    	} else if(!PoSAccept.POS_FR_ACC.contains(pos.toUpperCase())) {          //Non-content PoS
    		return id;
    	}
    		    	
		BabelPOS bnPos = posMapping.get(pos.toUpperCase()); 
    	if (bnPos == null){
    		return id;
    	} else {
     	    // NE are adding too much noise
     	    //id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang, ne);
    	    id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}

	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in German
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_de(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.DE;

		if(pos.equalsIgnoreCase("PTKNEG")){         //Negation
    		return NEG;
    	} else if(!PoSAccept.POS_DE_ACC.contains(pos.toUpperCase())) {          //Non-content PoS
    		return id;
    	}
		
		BabelPOS bnPos = posMapping.get(pos.toUpperCase()); 
    	if (bnPos == null){
    		return id;
    	} else {
     	    // NE are adding too much noise
     	    //id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang, ne);
    	    id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}


	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Dutch
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_nl(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.NL;
		
		String pos3chars = "";
		if (pos.length() > 1){
	    	pos3chars = pos.substring(0, 3).toLowerCase(); 			
		} else {
	   		return id;			
		}
		
		if(PoSAccept.NEG_NL.contains(lemma)){                     		//Negation      		
    		return NEG;
    	} else if(!PoSAccept.POS_NL_ACC.contains(pos3chars)) {          //Non-content PoS
    		return id;
    	}

		BabelPOS bnPos = posMapping.get(pos3chars); 

    	if (bnPos == null){
    		return id;
    	} else {
    	    id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}


	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Italian
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_it(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.IT;

		if(PoSAccept.NEG_IT.contains(lemma)){         			//Negation
    		return NEG;
    	} else if(!PoSAccept.POS_IT_ACC.contains(pos)) {        //Non-content PoS
    		return id;
    	}
		
		BabelPOS bnPos = posMapping.get(pos); 
    	if (bnPos == null){
    		return id;
    	} else {
    	    id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}


	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Romanian
	 * 
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNID_ro(BabelNet bn, String lemma, String pos) {
 
		String id = "-";
		boolean ne = false;
		String NEG = "NEG";
		Language lang = Language.RO;
		
		String pos5chars = "";
		if (pos.length() > 1){
	    	pos5chars = pos.substring(0, 5); 			
		} else {
	   		return id;			
		}

		if(pos5chars.equalsIgnoreCase("11Q01") || PoSAccept.NEG_RO.contains(lemma) ){         //Negation
    		return NEG;
    	} else if(!PoSAccept.POS_RO_ACC.contains(pos5chars.toUpperCase())) {          //Non-content PoS
    		return id;
    	}
		
		BabelPOS bnPos = posMapping.get(pos5chars.toUpperCase()); 
    	if (bnPos == null){
    		return id;
    	} else {
     	    id = BabelNetQuerier.retrieveID(bn, bnPos, lemma, lang);
    	}
		return id;
	}


	/**
	 * Get the top translation of a lemma
	 * DON'T USE
	 * 
	 * @param lemma
	 * @return
	 */
	private String get1stTrad_en(String lemma) {
		
		Multimap<Language, ScoredItem<String>> tradsAll = null;
		try {
			tradsAll = BabelNetUtils.getTranslations(Language.EN, lemma);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<ScoredItem<String>> trads = tradsAll.get(Language.ES);
   
		//System.out.println(trads);
		TreeMap<Double, String> mapTrads = new TreeMap<Double, String>();
		for (ScoredItem<String> trad : trads){
			if (!mapTrads.containsKey(-trad.getScore())) {
				mapTrads.put(-trad.getScore(), trad.getItem());}
		}
		//System.out.println(mapTrads.lastKey());
		//System.out.println(mapTrads.firstKey());
		return mapTrads.firstEntry().toString();
	}
	
}
