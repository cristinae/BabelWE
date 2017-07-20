package cat.trachemys.interlingua.babelNet;

import java.util.Map;

import cat.trachemys.interlingua.prepro.Normaliser;
import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.data.BabelPOS;
import it.uniroma1.lcl.jlt.util.Language;

/**
 * Methods to filter tokens according to its PoS before querying BabelNet. All languages must have
 * its own method
 * 
 * @author cristina
 * @since Jul 20, 2017
 */
public class BabelNetFiltering4ID {
	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in English
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static String getBNID_en(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS (PTB tagset) the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_en(Map<String, BabelPOS> posMapping, String pos) {
 
		BabelPOS bnPos = posMapping.get(pos);	
		return bnPos.toString();
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Spanish
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static  String getBNID_es(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS (ancora tagset) the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_es(Map<String, BabelPOS> posMapping, String pos) {
		 
		String pos2chars = "";
		if (pos.length() > 1){
	    	pos2chars = pos.substring(0, 2).toLowerCase(); 			
		} else {
	   		return null;			
		}
    	
		BabelPOS bnPos = posMapping.get(pos2chars); 
		return bnPos.toString();
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Arabic
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static String getBNID_ar(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS (MADAMIRA tagset) the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_ar(Map<String, BabelPOS> posMapping, String pos) {
		     	    	    	
		BabelPOS bnPos = posMapping.get(pos); 
		return bnPos.toString();
	}


	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Turkish
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static String getBNID_tr(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS () the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_tr(Map<String, BabelPOS> posMapping, String pos) {
		 
		BabelPOS bnPos = posMapping.get(pos);
		return bnPos.toString();
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in French
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static String getBNID_fr(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS (treetagger tagset) the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_fr(Map<String, BabelPOS> posMapping, String pos) {
		     	
		BabelPOS bnPos = posMapping.get(pos.toUpperCase()); 
		return bnPos.toString();
	}

	
	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in German
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static String getBNID_de(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS (treetagger && IXA tagset) the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_de(Map<String, BabelPOS> posMapping, String pos) {
		 		
		BabelPOS bnPos = posMapping.get(pos.toUpperCase()); 
		return bnPos.toString();
	}

	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Dutch
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static String getBNID_nl(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS (treetagger) the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_nl(Map<String, BabelPOS> posMapping, String pos) {
		 
		String pos3chars = "";
		if (pos.length() > 1){
	    	pos3chars = pos.substring(0, 3).toLowerCase(); 			
		} else {
	   		return null;			
		}

		BabelPOS bnPos = posMapping.get(pos3chars); 
		return bnPos.toString();
	}


	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Italian
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static String getBNID_it(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS (treetagger) the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_it(Map<String, BabelPOS> posMapping, String pos) {
		 
		BabelPOS bnPos = posMapping.get(pos); 
		return bnPos.toString();
	}

	/**
	 * Given a lemma and a PoS the method retrieves the BN id for a subset of selected PoS
	 * in Romanian
	 * 
	 * @param posMapping
	 * @param bn
	 * @param lemma
	 * @param pos
	 * @return
	 */
	protected static String getBNID_ro(Map<String, BabelPOS> posMapping, BabelNet bn, String lemma, String pos) {
 
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
	 * Given a PoS (treetagger) the method retrieves the BN PoS reduced tagset 
	 * 
	 * @param posMapping
	 * @param pos
	 * @return
	 */
	protected static String getBNpos_ro(Map<String, BabelPOS> posMapping, String pos) {
		 		
		String pos5chars = "";
		if (pos.length() > 1){
	    	pos5chars = pos.substring(0, 5); 			
		} else {
	   		return null;			
		}
		
		BabelPOS bnPos = posMapping.get(pos5chars.toUpperCase()); 
		return bnPos.toString();
	}

}
