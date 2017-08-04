package cat.trachemys.interlingua.babelNet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cat.trachemys.interlingua.basics.log.BWELogger;

/**
 * 
 * Converter from text annotated with PoS and lemma using TreeTagger to the
 * wpl format expected by BabelWe. 
 * 
 * @author cristina
 * @since Jul 16, 2017
 */
public class TreeTagger2BabelWE {
	
	/** Logger */
	private static BWELogger logger = 
			new BWELogger (TreeTagger2BabelWE.class.getSimpleName());


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

		options.addOption("i", "input", true, 
					"Input file in TreeTagger format to annotate");		
		options.addOption("r", "raw", true, 
					"Raw input file, one sentence per line");		
		options.addOption("h", "help", false, "This help");
		options.addOption("l", "language", true, 
				"Language of the input text "
				+ "[en|es|de|fr|ro|nl|it|ca]");		

		try {			
		    cLine = parser.parse( options, args );
		} catch( ParseException exp ) {
			logger.error( "Unexpected exception:" + exp.getMessage() );			
		}	
		
		if (cLine.hasOption("h")) {
			formatter.printHelp(TreeTagger2BabelWE.class.getSimpleName(),options );
			System.exit(0);
		}
		if (cLine == null || !(cLine.hasOption("l")) ) {
			logger.error("Please, set the language.\n");
			formatter.printHelp(DataIDAnnotator.class.getSimpleName(),options );
			System.exit(1);
		}		
		if (cLine == null || !(cLine.hasOption("i")) ) {
			logger.error("Please, point to the input file in TreeTagger format.\n");
			formatter.printHelp(TreeTagger2BabelWE.class.getSimpleName(),options );
			System.exit(1);
		}		
		if (cLine == null || !(cLine.hasOption("r")) ) {
			logger.error("Please, point to the input file in raw format.\n");
			formatter.printHelp(TreeTagger2BabelWE.class.getSimpleName(),options );
			System.exit(1);
		}		
		return cLine;		
	}

	
	/**
	 * Main function to run the class, serves as example
	 * 
	 * @param args
	 *      -i Input file 
	 *      -r Raw file 
	 * 		-l Language of the input text 
	 * 			(English, Spanish, French, German, Dutch, Italian, Romanian, Catalan)
	 */

	public static void main(String[] args) {
		CommandLine cLine = parseArguments(args);
		
		// Language
		String language = cLine.getOptionValue("l");
		
		// Input file
		File input = new File(cLine.getOptionValue("i"));
	
		// Raw file
		String raw = cLine.getOptionValue("r");
		
		// Run
		File rawTok = tokeniseRaw(raw, language);
		convert(input, rawTok);
		rawTok.delete();

		// Extra to complete the pipeline
		// DataIDAnnotator ann = new DataIDAnnotator (language);
		// ann.annotate(new File(input.toString().concat(".wpl")), language, "wpl");

	}

	
	/**
	 * Tokenises a file using TreeTagger scripts for compatibility
	 * 
	 * @param raw
	 * @param lang
	 * @return
	 */
	private static File tokeniseRaw(String raw, String lang) {

	    ClassLoader classLoader = TreeTagger2BabelWE.class.getClassLoader();
    	
    	// Parameters needed to tokenise raw text as TT does
		String param1 = null;
		String param2 = "-x"; //parametre de sucre!
    	InputStream abbrevations = null;
    	File tmpAbbr = null;
    	String abbr = null;
		if (lang.equalsIgnoreCase("en")){
			abbrevations = classLoader.getResourceAsStream("abbreviationsTT/english-abbreviations");
			tmpAbbr = getResourceAsFile(abbrevations);
			abbr = tmpAbbr.toString();
			param2 = "-e";
		} else if (lang.equalsIgnoreCase("es")){
			abbrevations = classLoader.getResourceAsStream("abbreviationsTT/spanish-abbreviations");
			tmpAbbr = getResourceAsFile(abbrevations);
			abbr = tmpAbbr.toString();
		} else if (lang.equalsIgnoreCase("nl")){
			abbrevations = classLoader.getResourceAsStream("abbreviationsTT/dutch-abbreviations");
			tmpAbbr = getResourceAsFile(abbrevations);
			abbr = tmpAbbr.toString();
		} else if (lang.equalsIgnoreCase("de")){
			abbrevations = classLoader.getResourceAsStream("abbreviationsTT/german-abbreviations-utf8");
			tmpAbbr = getResourceAsFile(abbrevations);
			abbr = tmpAbbr.toString();
		} else if (lang.equalsIgnoreCase("ca")){
			abbrevations = classLoader.getResourceAsStream("abbreviationsTT/catalan-abbreviations");
			tmpAbbr = getResourceAsFile(abbrevations);
			abbr = tmpAbbr.toString();
			param2 = "-c";
		} else if (lang.equalsIgnoreCase("it")){
			abbrevations = classLoader.getResourceAsStream("abbreviationsTT/italian-abbreviations");
			tmpAbbr = getResourceAsFile(abbrevations);
			abbr = tmpAbbr.toString();
			param2 = "-i";
		} else if (lang.equalsIgnoreCase("ro")){
			abbrevations = classLoader.getResourceAsStream("abbreviationsTT/romanian-abbreviations");
			tmpAbbr = getResourceAsFile(abbrevations);
			abbr = tmpAbbr.toString();
			param2 = "-r";
		} else if (lang.equalsIgnoreCase("fr")){
			abbrevations = classLoader.getResourceAsStream("abbreviationsTT/french-abbreviations-utf8");
			tmpAbbr = getResourceAsFile(abbrevations);
			abbr = tmpAbbr.toString();
			param2 = "-f";
		} else {
			logger.error("Language '"+lang+"' is not recognised by the tokeniser.");
		}


    	InputStream input = classLoader.getResourceAsStream("tokenizeTT.perl");
    	File tokeniser = getResourceAsFile(input);
		String exe = tokeniser.toString();
		
		param1 = "-a "+abbr;
		String[] commandTok = {"perl", exe, param1, param2};
		//perl tokenizeTT.perl -a abbreviations/english-abbreviations -e 
		// /home/cristinae/soft/treetagger/cmd/train.en-de.en  > kk
		
		// Run the tokeniser in a separate system process
		ProcessBuilder builder = new ProcessBuilder(commandTok);	
		//System.out.println(builder.command());
		builder.redirectInput(new File(raw));
		File output = new File(raw.concat(".tok"));
		builder.redirectOutput(output);
		logger.info("Starting tokenisation...");
		try {
			Process process = builder.start();
			//Wait to get exit value
		    int exitValue = process.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Some error occurred when starting the ProcessBuilder for tokenisation "+
			"of file "+input.toString()+" with TreeTagger tokeniser.");
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error("Some error occurred waiting for the tokenisation ProcessBuilder "+
			"of file "+input.toString()+" with TreeTagger tokeniser.");
		} 
		
		//logger.info("Tokenisation done.");
		tokeniser.delete();
		tmpAbbr.delete();

        return output;
	}

	
    /**
     * Given an raw input file with the same tokenisation as the output of TreeTagger
     * fIn creates a file with format and extension .wpl
     * 
     * @param fIn
     * @param raw
     */
	private static void convert(File fIn, File raw) {
		
		File fOut = new File(fIn.toString().concat(".wpl"));
		// Initilise the writer
		fOut.delete();
		//FileIO.deleteFile(fOut);
	    FileWriter fw = null;
	    BufferedWriter bw = null;
		try {
			fw = new FileWriter(fOut, true);
			bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read the input
		FileInputStream inputStream = null;
		FileInputStream inputStreamRaw = null;
		Scanner sc = null;
		Scanner scRaw = null;
		try {
		    inputStream = new FileInputStream(fIn);
		    sc = new Scanner(inputStream, "UTF-8");
		    inputStreamRaw = new FileInputStream(raw);
		    scRaw = new Scanner(inputStreamRaw, "UTF-8");
		    int i = 0;
			logger.info("Starting conversion...");
		    while (scRaw.hasNext()) {
		        String line = scRaw.nextLine();
		        String[] tokens = line.split("\\s+");
		        for (String token:tokens) {  
			        String lineTT = sc.nextLine();
			        Matcher m = Pattern.compile("^(.+)\t(.+)\t(.+)").matcher(lineTT);
			        if (m.find()){
				        //lineFactors = line.replace("\t", "|");	
			        	String lemma = m.group(3);
			        	String word = m.group(1);
			        	// Issues with treetagger
			        	lemma = lemma.replace("<unknown>", word);
			        	lemma = lemma.replace("@card@", word);
			        	// Sometimes more than one lemma is available, we ad hoc keep the 1st one
			        	if (lemma.contains("|")){
			        		lemma = lemma.substring(0,lemma.indexOf("|"));
			        	}
				        bw.append(word+"|"+m.group(2)+"|"+lemma+" ");
			        }
		        }
		        // Write every 10000 lines
		        if (i%10000==0){
		        	bw.flush();
		        }
		        i++;
		        bw.newLine();
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
					inputStreamRaw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    if (sc != null) {
		        sc.close();
		        scRaw.close();
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
     * Loads a resource as a file given an input stream
     * https://stackoverflow.com/questions/676097/java-resource-as-file
     * 
     * @param in
     * @return
     */
    public static File getResourceAsFile(InputStream in) {
        try {
            if (in == null) {
                return null;
            }

            File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
            //tempFile.deleteOnExit();

            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                //copy stream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    

}
