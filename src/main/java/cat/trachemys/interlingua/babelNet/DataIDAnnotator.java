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
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelNetUtils;
//import it.uniroma1.lcl.babelnet.data.BabelPOS;   // BabelNet v3.7
import com.babelscape.util.UniversalPOS;  // BabelNet v4.0
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
	private static Map<String, UniversalPOS> posMapping = null;
	
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
		
		if (cLine.hasOption("h")) {
			formatter.printHelp(DataIDAnnotator.class.getSimpleName(),options );
			System.exit(0);
		}
		if (cLine == null || !(cLine.hasOption("l")) ) {
			logger.error("Please, set the language.\n");
			formatter.printHelp(DataIDAnnotator.class.getSimpleName(),options );
			System.exit(1);
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
	 */

	public static void main(String[] args) {
		CommandLine cLine = parseArguments(args);
		
		// Language
		String language = cLine.getOptionValue("l");

		// Input file
		File input = new File(cLine.getOptionValue("i"));
	
		// Format of the input file
		String format = cLine.getOptionValue("f");
		
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
	public void annotate(File input, String language, String format) {

		BabelNet bn = BabelNet.getInstance();

		// Initilise the writer
		logger.info("Starting BN ID's annotation...");
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
		        	logger.info("Sentence "+i);
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

	/**
	 * Retrieves the BN ID for all the tokens in an input sentence in wpl format.
	 * Actualises the number of sentences i.
	 * 
	 * @param line
	 * @param language
	 * @param bn
	 * @param i
	 * @return
	 * @throws IOException
	 */
	private int annotateLineBufferWPL(String line, String language, BabelNet bn, int i) throws IOException {
        String[] tokens = line.split("\\s+");
        for (String token:tokens) {  
        	String id = "-";
        	String lemma = DataProcessor.readFactor3(token, 3);
        	String pos = DataProcessor.readFactor3(token, 2);
        	String word = DataProcessor.readFactor3(token, 1);
        	// This is a patch to solve the cases where a token has not been annotated
        	// "joker" is a toy PoS available in all the mappings as a UniversalPOS.NOUN
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


	/**
	 * Retrieves the BN ID for all the tokens in an input sentence in conll format.
	 * Actualises the number of sentences i.
	 * 
	 * @param line
	 * @param language
	 * @param bn
	 * @param i
	 * @return
	 * @throws IOException
	 */
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
		    id = BabelNetFiltering4ID.getBNID_en(posMapping, bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("es")) {
		    id = BabelNetFiltering4ID.getBNID_es(posMapping, bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("ar")) {
		    id = BabelNetFiltering4ID.getBNID_ar(posMapping, bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("tr")) {
		    id = BabelNetFiltering4ID.getBNID_tr(posMapping, bn, lemma, pos);	 
		} else if (language.equalsIgnoreCase("fr")) {
			id = BabelNetFiltering4ID.getBNID_fr(posMapping, bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("de")) {
			id = BabelNetFiltering4ID.getBNID_de(posMapping, bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("nl")) {
			id = BabelNetFiltering4ID.getBNID_nl(posMapping, bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("it")) {
			id = BabelNetFiltering4ID.getBNID_it(posMapping, bn, lemma, pos);	
		} else if (language.equalsIgnoreCase("ro")) {
			id = BabelNetFiltering4ID.getBNID_ro(posMapping, bn, lemma, pos);	
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
		tradsAll = BabelNetUtils.getTranslations(Language.EN, lemma);
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
