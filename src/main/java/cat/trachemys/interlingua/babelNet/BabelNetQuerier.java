package cat.trachemys.interlingua.babelNet;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetComparator;
import it.uniroma1.lcl.kb.SynsetType;
//import it.uniroma1.lcl.babelnet.data.UniversalPOS;   // BabelNet v3.7
import com.babelscape.util.UniversalPOS;  // BabelNet v4.0
import com.babelscape.util.POS;
import it.uniroma1.lcl.jlt.util.Language;


/**
 * Set of queries to the BabelNet index
 * 
 * @author cristina
 * @since Dec 15, 2016
 */
public class BabelNetQuerier {
	
	/** Logger */
	//private static LumpLogger logger = 
	//		new LumpLogger (BabelNetQuerier.class.getSimpleName());

	
	/**
	 * Looks for the synset for a specific lemma with PoS pos. The ranking is done as in BabelNet
	 * (sense numbers are used to sort the BabelSynsets corresponding to WordNet synsets)
	 * (1) puts WordNet synsets first; 
	 * (2) sorts WordNet synsets based on the sense number of a specific input word; 
	 * (3) sorts Wikipedia synsets lexicographically based on their main sense.
	 * 
	 * @param bn
	 * @param bnPos2
	 * @param lemma
	 * @param lang
	 * @return String
	 */
	public static String retrieveID(BabelNet bn, UniversalPOS bnPos2, String lemma, Language lang) {
		
		String bnID = "-";
		List<BabelSynset> synsets = null;
		synsets = bn.getSynsets(lemma, lang);
		if(synsets.size()==0){
			//System.out.println("No synsets for this lemma");
			return bnID;				
		}
		// lemma whose sense numbers are used to sort the BabelSynsets corresponding to WordNet synsets
		Collections.sort(synsets, new BabelSynsetComparator(lemma, lang));

		// We keep the top result with a matching PoS
        for (BabelSynset synset : synsets) {			
			POS bnpos = synset.getPOS();
			if (bnPos2.equals(bnpos)){
				return synset.getID().toString();
			}
		}
        
        // If no sense with matching PoS has been found we return the top1 sense irrespective of the PoS
        bnID = synsets.get(0).getID().toString();	
		return bnID;
	}

	
	
	/**
	 * Looks for the synset with more relations for a given lemma with pos. If it's a NE, tries
	 * to give preference to NEs.
	 * Not working properly, use {@code retrieveID(BabelNet bn, UniversalPOS pos, String lemma, Language lang)}
	 * instead.
	 * 
	 * @param bn
	 * @param pos
	 * @param lemma
	 * @param lang
	 * @param NE
	 * @return String
	 */
	public static String retrieveID(BabelNet bn, UniversalPOS pos, String lemma, Language lang, boolean NE) {
		
		String bnID = "-";
		Map<String, Integer> idChoices = new HashMap<String, Integer>();
		
		List<BabelSynset> synsets = null;
		synsets = bn.getSynsets(lemma, lang);
		// lemma whose sense numbers are used to sort the BabelSynsets corresponding to WordNet synsets
		Collections.sort(synsets, new BabelSynsetComparator(lemma, lang));

		/*
		//VERSION 1
		//This one is adding to much noise
		//(there can be some errors now due to cuts and pastes)
		boolean existNE = false;
		BabelSynset synsetNE = null;
        for (BabelSynset synset : synsets) {
			
			UniversalPOS bnpos = synset.getPOS();
			if (pos.equals(bnpos)){
				// If it is not a NE we keep the top result with a matching PoS
				if (!NE) {
					return synset.getID().toString();
					// If it is a NE we wait for the first NE
				} else {
					SynsetType type = synset.getSynsetType();
					//Integer relacions = synset.getEdges().size();
					if(type.equals(SynsetType.NAMED_ENTITY)){
						//Integer weight = 100;
						//idChoices.put(synset.getID().toString(), weight);
						existNE = true;
						synsetNE = synset;
						break;
					}					
				}
			}
		}        
        if (!existNE) {
        	return synsets.get(0).getId().toString();
        } else{
				int relsNE = synsetNE.getEdges().size();
				int relsTOP = synsets.get(0).getEdges().size();
				if (relsNE > 10*relsTOP){
					return synsetNE.getId().toString();
				} else {
					return synsets.get(0).getId().toString();				
				}
        }
	    */
        
		//VERSION 2
		// Not working properly because getEdges() returns incorrect results for some synsets 
		// (0, differing from the BabelNet interface also). Why??
		// Tried with
		// List<BabelSynsetIDRelation> successorsEdges = synsetId.getRelatedIDs();
		// System.out.println("# OUTGOING EDGES: " + successorsEdges.size());
		// But not working (pointer error) for some synsets, probably those without WN??
        for (BabelSynset synset : synsets) {
			Integer relacions = synset.getOutgoingEdges().size();
			if (NE){                      // If it's tagged as a NE we expect BN to have the tag also
				SynsetType type = synset.getType();
				if(type.equals(SynsetType.NAMED_ENTITY)){
					//UniversalPOS bnpos = synset.getPOS();  
					//if (pos.equals(bnpos)){           // and we don't mind the PoS (which is a noun)
					//Extra weight to NEs in case there are nonNEs that also match
					Integer relacionsAugment = relacions+100;
					idChoices.put(synset.getID().toString(), relacionsAugment);
					//}
				} else {
					idChoices.put(synset.getID().toString(), relacions);
				}
			} else {					  // It it's not a NE we want the sense with more relations
				POS bnpos = synset.getPOS();
				if (pos.equals(bnpos)){
					idChoices.put(synset.getID().toString(), relacions);
				}
			}
			//System.out.println("Synset ID: " + synset.getID() + lemma + relacions);
       

        }
        
        if (!idChoices.isEmpty()){
        	bnID = getMaxfromMap(idChoices);
        } else {
        	//logger.warn("No BabelNet ID was found for lemma" + lemma + 
        	//		" with PoS " + pos.toString());
        }

		return bnID;
	}

	
	
	/**
	 * Retrieves the key with a maximum value from a Map<String, Integer>
	 * 
	 * @param mapSI
	 * @return String
	 */
	private static String getMaxfromMap(Map<String, Integer> mapSI){
		
	    Comparator<? super Entry<String, Integer>> maxValueComparator = (
	            entry1, entry2) -> entry1.getValue().compareTo(
	            entry2.getValue());

	    String keyMax = mapSI.entrySet().stream().max(maxValueComparator).get().getKey();
		
	    return keyMax;
	}
	

}
