package cat.trachemys.interlingua.prepro;

import java.io.File;
import java.util.Properties;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import cat.trachemys.interlingua.basics.Check;
import cat.trachemys.interlingua.basics.FileIO;
import cat.trachemys.interlingua.basics.log.BWELogger;
import cat.trachemys.interlingua.LumpIni;
import cat.trachemys.interlingua.lumpConfig;

/**
 * Class to annotate raw text. Currently supports tokenisation and lemmatisation of
 * Arabic, English, Spanish, French and German
 *   
 * @author cristina
 * @since Nov 28, 2016
 */
public class Annotator {

	/** Language */
	private final String lang;
	/** Layer of annotation */
	private final String layer;
	
	/** Configuration file */
//	private static Properties p;

	/** Configuration file section */
	private static Section section;

	/** Annotation object*/
	private final AnnotatorFactory annFactory;
	
	/** Logger */
	private static BWELogger logger = 
			new BWELogger (Annotator.class.getSimpleName());

	/** Constructors */
	public Annotator (String language, String layer, String iniFile) {
		Check.notNull(language);
		Check.notNull(layer);
		Check.notNull(iniFile);
		this.lang = language;
		this.layer = layer;
//		p = lumpConfig.getProperties(iniFile);
		section = LumpIni.getProperties(iniFile);
		logger = new BWELogger(this.getClass().getCanonicalName());
		annFactory = new AnnotatorFactory();		
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
					"Language of the input text (ar/en/es/tr/fr/de)");		
		options.addOption("a", "annotation", true, 
					"Annotation layer (tok, lem)");		
		options.addOption("i", "input", true, 
					"Input file to annotate -one sentence per line-");		
		options.addOption("h", "help", false, "This help");
		options.addOption("c", "config", true,
		        	"Configuration file for the BabelWE project");

		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception :" + exp.getMessage() );			
		}	
		
		if (cLine.hasOption("h")) {
			formatter.printHelp(Annotator.class.getSimpleName(),options );
			System.exit(0);
		}

		if (cLine == null || !(cLine.hasOption("l")) ) {
			logger.error("Please, set the language\n");
			formatter.printHelp(Annotator.class.getSimpleName(),options );
			System.exit(1);
		}		
		if (!(cLine.hasOption("a")) ) {
			logger.error("Please, set the desired annotation layer\n");
			formatter.printHelp(Annotator.class.getSimpleName(),options );
			System.exit(1);
		}		

		return cLine;		
	}

	
	/**
	 * Main function to run the class, serves as example
	 * 
	 * @param args
	 * 		-l Language of the input text (Arabic, English, Spanish)
	 * 		-a Annotation layer (tokens, lemmas)
	 *      -i Input text/file
	 * 		-c Configuration file
	 */
	public static void main(String[] args){
		CommandLine cLine = parseArguments(args);
		
		// Language and layer
		String language = cLine.getOptionValue("l");
		String layer = cLine.getOptionValue("a");

		// Input file
		File input = new File(cLine.getOptionValue("i"));
		
		// Config file
		// Guessing if its an absolute or a relative path
		String conf = cLine.getOptionValue("c");
		String confFile;
		if (conf.startsWith(FileIO.separator)){
			confFile = conf;
		} else {
			confFile = System.getProperty("user.dir")+FileIO.separator+conf;
		}

		// Run
		Annotator ann = new Annotator (language, layer, confFile);
		ann.annotate(input);
		
		// String to string version
		// String out = ann.annotateString("¿Cómo se tokeniza esto? Bien!");
		//System.out.println(out);

	}

	/**
	 * Does the actual annotation of the input file at the corresponding layer
	 * of annotation
	 * 
	 * @param input
	 * 			input file to annotate
	 * 
	 */
	private void annotate(File input) {

		// Tokenisation
		if(layer.equalsIgnoreCase("tok")){
			File output = new File(input+".tok");
			Tokeniser tok = annFactory.getTokeniser(lang);
			tok.execute(section, input, lang, output);
		// Lemmatisation	
		} else if (layer.equalsIgnoreCase("lem")){
			File output = new File(input+".wpl");
			Lemmatiser lem = annFactory.getLemmatiser(lang);
			lem.execute(section, input, lang, output);
		}
	}



	/**
	 * Does the actual annotation of an input string at the corresponding layer
	 * of annotation. Returns the annotated string
	 * 
	 * @param input
	 * 			input string to annotate
	 * 
	 * @return annOutput
	 */
	private String annotateString(String input) {

		String annOutput = "NOT ANNOTATED";
		// Tokenisation
		if(layer.equalsIgnoreCase("tok")){
			Tokeniser tok = annFactory.getTokeniser(lang);
			annOutput = tok.execute(section, input, lang);
		// Lemmatisation	
		} else if (layer.equalsIgnoreCase("lem")){
			Lemmatiser lem = annFactory.getLemmatiser(lang);
			annOutput = lem.execute(section, input, lang);
		}
		return annOutput;
	}

	
	/** 
	 * Utils 
	 * 
	 * TODO This should be somewhere else
	 */
	public static void checkExists(String input, String errorMessage){
		if (!new File(input).isFile()){
			logger.error(errorMessage + input);
		}
	}
	
	/** 
	 * Getters 
	 */
	//TODO albarron, Dec 20. Not sure why we need these getters here;
	public int getPropertyInt(String key){
		return Integer.valueOf(section.get(key));
	} 
	
	public String getPropertyStr(String key){
		return section.get(key);
	} 
}
