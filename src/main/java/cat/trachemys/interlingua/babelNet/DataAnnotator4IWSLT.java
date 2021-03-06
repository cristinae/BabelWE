package cat.trachemys.interlingua.babelNet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.trachemys.interlingua.basics.log.BWELogger;
import cat.trachemys.interlingua.prepro.Metaphone3;
import cat.trachemys.interlingua.prepro.SBStemmer;
import it.uniroma1.lcl.babelnet.BabelNet;
//import it.uniroma1.lcl.babelnet.data.BabelPOS;   // BabelNet v3.7
import com.babelscape.util.UniversalPOS;  // BabelNet v4.0

/**
 * 
 * Main class to include additional factors to [token|PoS|lemma] including 
 * stems, "pseudocognates", and babelNet PoS and ID in an input corpus.
 * 
 * @author cristina
 * @since Jul 20, 2017
 */
public class DataAnnotator4IWSLT {
	
	/** Logger */
	private static BWELogger logger = 
			new BWELogger (DataAnnotator4IWSLT.class.getSimpleName());

	/** Conversion into BabelNet tags*/
	private static Map<String,UniversalPOS> posMapping = null;
	
	/** BufferedWriter */
    private BufferedWriter bw = null;

	/** Constructor */
	public DataAnnotator4IWSLT (String language) {
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
		options.addOption("k", "senses", true, 
				    "Number of senses per token (default 1)");		
		options.addOption("h", "help", false, "This help");
		//options.addOption("c", "config", true,
		//        	"Configuration file for the lumpSTS project");

		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception:" + exp.getMessage() );			
		}	
		
		if (cLine.hasOption("h")) {
			formatter.printHelp(DataAnnotator4IWSLT.class.getSimpleName(),options );
			System.exit(0);
		}
		if (cLine == null || !(cLine.hasOption("l")) ) {
			logger.error("Please, set the language.\n");
			formatter.printHelp(DataAnnotator4IWSLT.class.getSimpleName(),options );
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
	 *      -k Number of synsets to be extracted
	 *      -f Format of the input file 
	 *      	(wpl|conll)
	 */

	public static void main(String[] args) {
		CommandLine cLine = parseArguments(args);
		
		// Language
		String language = cLine.getOptionValue("l");

		// Input file
		File input = new File(cLine.getOptionValue("i"));
	
		// Number of synsets to be extracted
		int k=1;
		if (cLine.hasOption("k")) {
  		   k = Integer.valueOf(cLine.getOptionValue("k"));
		}
		
		// Run
		DataAnnotator4IWSLT ann = new DataAnnotator4IWSLT (language);
		ann.annotate(input, language, k);

	}

	/**
	 * Does the actual annotation of the input file with the BabelNet ID
	 * 
	 * @param input
	 * 			input file to annotate
	 * 
	 */
	public void annotate(File input, String language, int k) {

		BabelNet bn = BabelNet.getInstance();

		// Initilise the writer
		logger.info("Starting with factor annotation (stems/metaphone3/BNpos&ids)...");
		File output = new File(input+"mb");
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
		    int i = 1;
		    while (sc.hasNext()) {
		        String line = sc.nextLine();
			    annotateLineBufferWPL(line, language, bn, k);		        			        		        
		        // Write every 10000 lines
		        if (i%10000==0){
		        	bw.flush();
		        	logger.info("Sentence "+i);
		        }
		        i++;
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
	private void annotateLineBufferWPL(String line, String language, BabelNet bn, int k) throws IOException {
        String[] tokens = line.split("\\s+");
        for (String token:tokens) {  
        	String noResult = "-";
        	String lemma = DataProcessor.readFactor3(token, 3);
        	String pos = DataProcessor.readFactor3(token, 2);
        	String word = DataProcessor.readFactor3(token, 1);
        	// This is a patch to solve the cases where a token has not been annotated
        	// "joker" is a toy PoS available in all the mappings as a BabelPOS.NOUN
        	// but is not present in the PoSAccept lists
        	if (pos==null){
        		lemma = token;
        		pos = "joker";
        		word = token;
        	}
           	if (lemma==null){
        		lemma = token;
        	}
           	
          	SBStemmer st = new SBStemmer();
           	String stem = st.execute(word, language);
           	if (stem == null){
           		stem = noResult;
           	} else {
           		stem = stem.toLowerCase();
           	}
           	
    		Metaphone3 m3 = new Metaphone3();
    		m3.SetWord(word);
    		m3.Encode();
           	String metaphone =  m3.GetMetaph();
           	if (metaphone.equalsIgnoreCase("")){
           		metaphone = noResult;
           	}
          	
           	String posBN = getBNpos(language, pos);
           	if (posBN == null){
           		posBN = noResult;
           	}
        	String idBN = getBNID(language, bn, lemma, pos, k);
           	if (idBN == null){
           		idBN = noResult;
           	}
     		bw.append(word+"|"+pos+"|"+posBN+"|"+stem+"|"+lemma+"|"+metaphone+"|"+idBN+" ");
        }
    	bw.newLine();
    	return;
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
	private String getBNID(String language, BabelNet bn, String lemma, String pos, int k) {

		String id = "-";
		if (language.equalsIgnoreCase("en")) {
		    id = BabelNetFiltering4ID.getBNID_en(posMapping, bn, lemma, pos, k);	
		} else if (language.equalsIgnoreCase("es")) {
		    id = BabelNetFiltering4ID.getBNID_es(posMapping, bn, lemma, pos, k);	
		} else if (language.equalsIgnoreCase("ar")) {
		    id = BabelNetFiltering4ID.getBNID_ar(posMapping, bn, lemma, pos, k);	
		} else if (language.equalsIgnoreCase("tr")) {
		    id = BabelNetFiltering4ID.getBNID_tr(posMapping, bn, lemma, pos, k);	 
		} else if (language.equalsIgnoreCase("fr")) {
			id = BabelNetFiltering4ID.getBNID_fr(posMapping, bn, lemma, pos, k);	
		} else if (language.equalsIgnoreCase("de")) {
			id = BabelNetFiltering4ID.getBNID_de(posMapping, bn, lemma, pos, k);	
		} else if (language.equalsIgnoreCase("nl")) {
			id = BabelNetFiltering4ID.getBNID_nl(posMapping, bn, lemma, pos, k);	
		} else if (language.equalsIgnoreCase("it")) {
			id = BabelNetFiltering4ID.getBNID_it(posMapping, bn, lemma, pos, k);	
		} else if (language.equalsIgnoreCase("ro")) {
			id = BabelNetFiltering4ID.getBNID_ro(posMapping, bn, lemma, pos, k);	
		}  
		return id;
	}

	/**
	 * Kind of factory method to call the appropriate function to retrieve a BabelNet PoS
	 * according to the input language
	 * 
	 * @param language
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	private String getBNpos(String language, String pos) {

		String id = "-";
		if (language.equalsIgnoreCase("en")) {
		    id = BabelNetFiltering4ID.getBNpos_en(posMapping, pos);	
		} else if (language.equalsIgnoreCase("es")) {
		    id = BabelNetFiltering4ID.getBNpos_es(posMapping, pos);	
		} else if (language.equalsIgnoreCase("ar")) {
		    id = BabelNetFiltering4ID.getBNpos_ar(posMapping, pos);	
		} else if (language.equalsIgnoreCase("tr")) {
		    id = BabelNetFiltering4ID.getBNpos_tr(posMapping, pos);	 
		} else if (language.equalsIgnoreCase("fr")) {
			id = BabelNetFiltering4ID.getBNpos_fr(posMapping, pos);	
		} else if (language.equalsIgnoreCase("de")) {
			id = BabelNetFiltering4ID.getBNpos_de(posMapping, pos);	
		} else if (language.equalsIgnoreCase("nl")) {
			id = BabelNetFiltering4ID.getBNpos_nl(posMapping, pos);	
		} else if (language.equalsIgnoreCase("it")) {
			id = BabelNetFiltering4ID.getBNpos_it(posMapping, pos);	
		} else if (language.equalsIgnoreCase("ro")) {
			id = BabelNetFiltering4ID.getBNpos_ro(posMapping, pos);	
		}  
		return id;
	}


	
}
