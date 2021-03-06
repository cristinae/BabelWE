package cat.trachemys.interlingua.babelNet;

import java.util.HashMap;
import java.util.Map;

// import it.uniroma1.lcl.babelnet.data.BabelPOS;  // BN 3.7
import com.babelscape.util.UniversalPOS; // BN 4.0

/**
 * Mapping PoS tags for Arabic, English, French, Italian, Romanian, Dutch, Deutsch, Turkish and Spanish 
 * into the Universal Tag set. Due to a change of version of BabelNet API, the change has been done 
 * through the old BabelPOS tag set.
 * 
 * @author cristina
 * @since Dec 27, 2016
 */
public class PoSMaps {

	/**
	 * Mapping Arabic Mada 2.1 PoS tags into BabelNet.
	 * There is a 1-to-1 correspondence with English Penn Tree Bank
	 */
	static final Map<String, UniversalPOS> BN_POS_AR= new HashMap<String, UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864030L;
	    {
	    	put("noun", UniversalPOS.NOUN);
	    	put("noun_num", UniversalPOS.NOUN);
	    	put("noun_quant", UniversalPOS.NOUN);
	    	put("noun_prop", UniversalPOS.NOUN);
	    	put("adj", UniversalPOS.ADJ);
	    	put("adj_comp", UniversalPOS.ADJ);
	    	put("adj_num", UniversalPOS.ADJ);
	    	put("adv", UniversalPOS.ADV);
	    	put("adv_interrog", UniversalPOS.ADV);
	    	put("adv_rel", UniversalPOS.ADV);
	    	put("pron", UniversalPOS.PRON);
	    	put("pron_dem", UniversalPOS.PRON);
	    	put("pron_exclam", UniversalPOS.PRON);
	    	put("pron_interrog", UniversalPOS.PRON);
	    	put("pron_rel", UniversalPOS.PRON);
	    	put("verb", UniversalPOS.VERB);
	    	put("verb_pseudo", UniversalPOS.VERB);
	    	put("part", UniversalPOS.ADP);
	    	put("part_dem", UniversalPOS.DET);
	    	put("part_det", UniversalPOS.DET);
	    	put("part_focus", UniversalPOS.ADP);
	    	put("part_fut", UniversalPOS.ADP);
	    	put("part_interrog", UniversalPOS.ADP);
	    	put("part_neg", null); // think (RPs in PTB)
	    	put("part_restrict", UniversalPOS.ADP);
	    	put("part_verb", UniversalPOS.ADP);
	    	put("part_voc", UniversalPOS.ADP);
	    	put("prep", UniversalPOS.ADP);
	    	put("abbrev", UniversalPOS.NOUN);
	    	put("punc", null);
	    	put("conj", UniversalPOS.CCONJ);
	    	put("conj_sub", UniversalPOS.SCONJ);
	    	put("interj", UniversalPOS.INTJ);
	    	put("digit", UniversalPOS.NOUN); //think determiner?
	    	put("latin", UniversalPOS.NOUN);  //think
	    	put("joker", UniversalPOS.NOUN); //artificially added
		}
	};
	
	/**
	 * Mapping English Penn Tree Bank PoS into BabelNet
	 */
	static final Map<String,UniversalPOS> BN_POS_EN= new HashMap<String,UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864031L;
	    {
	    	put("CC", UniversalPOS.CCONJ);
	    	put("CD", UniversalPOS.NOUN); //think determiner?
	    	put("DT", UniversalPOS.DET);
	    	put("EX", null); //think
	    	put("FW", UniversalPOS.NOUN); //think
	    	put("IN", UniversalPOS.ADP);
	    	put("JJ", UniversalPOS.ADJ);
	    	put("JJR", UniversalPOS.ADJ);
	    	put("JJS", UniversalPOS.ADJ); 
	    	put("LS", null); //think 
	    	put("MD", UniversalPOS.VERB); 
	    	put("NN", UniversalPOS.NOUN); 
	    	put("NNS", UniversalPOS.NOUN);
	    	put("NNP", UniversalPOS.NOUN); 
	    	put("NNPS", UniversalPOS.NOUN);
	    	put("PDT", UniversalPOS.DET); 
	    	put("POS", null); //think 
	    	put("PRP", UniversalPOS.PRON); 
	    	put("PRP$", UniversalPOS.PRON);
	    	put("RB",  UniversalPOS.ADV);
	    	put("RBR", UniversalPOS.ADV); 
	    	put("RBS", UniversalPOS.ADV); 
	    	put("RP", null); //think
	    	put("SYM", null); //think 
	    	put("TO", UniversalPOS.ADP); 
	    	put("UH", UniversalPOS.INTJ); 
	    	put("VB", UniversalPOS.VERB); 
	    	put("VBD", UniversalPOS.VERB); 
	    	put("VBG", UniversalPOS.VERB); 
	    	put("VBN", UniversalPOS.VERB); 
	    	put("VBP", UniversalPOS.VERB); 
	    	put("VBZ", UniversalPOS.VERB); 
	    	put("WDT", UniversalPOS.DET); 
	    	put("WP", UniversalPOS.PRON);
	    	put("WP$", UniversalPOS.PRON); 
	    	put("WRB", UniversalPOS.ADV); 
	    	// Lowercased version
	    	put("cc", UniversalPOS.CCONJ);
	    	put("cd", UniversalPOS.NOUN); //think determiner?
	    	put("dt", UniversalPOS.DET);
	    	put("ex", null); //think
	    	put("fw", UniversalPOS.NOUN); //think
	    	put("in", UniversalPOS.ADP);
	    	put("jj", UniversalPOS.ADJ);
	    	put("jjr", UniversalPOS.ADJ);
	    	put("jjs", UniversalPOS.ADJ); 
	    	put("ls", null); //think 
	    	put("md", UniversalPOS.VERB); 
	    	put("nn", UniversalPOS.NOUN); 
	    	put("nns", UniversalPOS.NOUN);
	    	put("nnp", UniversalPOS.NOUN); 
	    	put("nnps", UniversalPOS.NOUN);
	    	put("pdt", UniversalPOS.DET); 
	    	put("pos", null); //think 
	    	put("prp", UniversalPOS.PRON); 
	    	put("prp$", UniversalPOS.PRON);
	    	put("rb",  UniversalPOS.ADV);
	    	put("rbr", UniversalPOS.ADV); 
	    	put("rbs", UniversalPOS.ADV); 
	    	put("rp", null); //think
	    	put("sym", null); //think 
	    	put("to", UniversalPOS.ADP); 
	    	put("uh", UniversalPOS.INTJ); 
	    	put("vb", UniversalPOS.VERB); 
	    	put("vbd", UniversalPOS.VERB); 
	    	put("vbg", UniversalPOS.VERB); 
	    	put("vbn", UniversalPOS.VERB); 
	    	put("vbp", UniversalPOS.VERB); 
	    	put("vbz", UniversalPOS.VERB); 
	    	put("wdt", UniversalPOS.DET); 
	    	put("wp", UniversalPOS.PRON);
	    	put("wp$", UniversalPOS.PRON); 
	    	put("wrb", UniversalPOS.ADV); 
	    	put("joker", UniversalPOS.NOUN); //artificially added
		}
	};
	
	/**
	 * Mapping a subset of the Ancora tagset into BabelNet.
	 * Since the Ancora tagset is much more detailed than Babelnet we only
	 * consider the first two characters to do the mapping.
	 */
	static final Map<String,UniversalPOS> BN_POS_ES= new HashMap<String,UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864032L;
	    {
	    	put("ao", UniversalPOS.ADJ);
	    	put("aq", UniversalPOS.ADJ);
	    	put("cc", UniversalPOS.CCONJ);
	    	put("cs", UniversalPOS.SCONJ);
	    	put("da", UniversalPOS.DET);
	    	put("dd", UniversalPOS.DET);
	    	put("de", UniversalPOS.DET);
	    	put("di", UniversalPOS.DET);
	    	put("dn", UniversalPOS.DET);
	    	put("dp", UniversalPOS.DET);
	    	put("dt", UniversalPOS.DET);
	    	put("f0", null); //punctuations
	    	put("fa", null);
	    	put("fc", null);
	    	put("fd", null);
	    	put("fe", null);
	    	put("fg", null);
	    	put("fh", null);
	    	put("fi", null);
	    	put("fp", null);
	    	put("fs", null);
	    	put("ft", null);
	    	put("fx", null);
	    	put("fz", null);
	    	put("i", UniversalPOS.INTJ);
	    	put("nc", UniversalPOS.NOUN);
	    	put("np", UniversalPOS.NOUN);
	    	put("p0", UniversalPOS.PRON);
	    	put("pd", UniversalPOS.PRON);
	    	put("pe", UniversalPOS.PRON);
	    	put("pi", UniversalPOS.PRON);
	    	put("pn", UniversalPOS.PRON);
	    	put("pp", UniversalPOS.PRON);
	    	put("pr", UniversalPOS.PRON);
	    	put("pt", UniversalPOS.PRON);
	    	put("px", UniversalPOS.PRON);
	    	put("rg", UniversalPOS.ADV);
	    	put("rn", UniversalPOS.ADV);
	    	put("sp", UniversalPOS.ADP);
	    	put("va", UniversalPOS.VERB);
	    	put("vm", UniversalPOS.VERB);
	    	put("vs", UniversalPOS.VERB);
	    	put("w", null); //dates
	    	put("z0", UniversalPOS.NOUN); //numerals
	    	put("zm", UniversalPOS.NOUN);
	    	put("zu", UniversalPOS.NOUN);
	    	put("joker", UniversalPOS.NOUN); //artificially added
		}
	};

	
	/**
	 * Mapping TS Wikipedia Data Set for Turkish which is the one used by the TS tagset
	 */
	static final Map<String,UniversalPOS> BN_POS_TR= new HashMap<String,UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864033L;
	    {
	    	put("Verb", UniversalPOS.VERB);
	    	put("Noun", UniversalPOS.NOUN);
	    	put("Adj", UniversalPOS.ADJ);
	    	put("Adv", UniversalPOS.ADV);
	    	put("Det", UniversalPOS.DET);
	    	put("Conj", UniversalPOS.CCONJ);
	    	put("Postp", UniversalPOS.ADP);         // Postposition
	    	put("Interj", UniversalPOS.INTJ); 		// Interjection
	    	put("Pron", UniversalPOS.PRON);
	    	put("Dup", null);  	       // Duplication
	    	put("Num", UniversalPOS.NOUN); //think determiner?       //	Number
	    	put("Punc", null);
	    	put("UnDef", UniversalPOS.DET);	 // Undefinite
	    	put("Ques", null);     //Question
	    	put("YY", null);       //	Misspell
	    	put("Abbr", UniversalPOS.NOUN);     //	Abbreviation
	    	put("intEmphasis", null);   //	Internet Emphasis
	    	put("intAbbrEng", null);    //	Internet English Abbreviation
	    	put("tinglish", null); 	    //	Tinglish
	    	put("bor", UniversalPOS.NOUN);	//	Borrowed
	    	put("intSlang", null); 
	    	put("joker", UniversalPOS.NOUN); //artificially added
		}
	};
		

	
	
	/**
	 * Mapping from the French Penn TreeBank. In the original FTB, words are split into 13 main 
	 * categories, themselves divided into 34 subcategories. The version of the treebank we used was 
	 * obtained by converting subcategories into a tagset consisting of 28 tags, with a granularity 
	 * that is intermediate between categories and subcategories. 
	 * https://alpage.inria.fr/statgram/frdep/Publications/crabbecandi-taln2008-final.pdf
	*/
	static final Map<String,UniversalPOS> BN_POS_FR= new HashMap<String,UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864034L;
	    {
	    	put("V", UniversalPOS.VERB);
	    	put("VIMP", UniversalPOS.VERB);
	    	put("VINF", UniversalPOS.VERB);
	    	put("VS", UniversalPOS.VERB);
	    	put("VPP", UniversalPOS.VERB);
	    	put("VPR", UniversalPOS.VERB);
	    	put("NPP", UniversalPOS.NOUN);
	    	put("NC", UniversalPOS.NOUN);
	    	put("CS", UniversalPOS.SCONJ);
	    	put("CC", UniversalPOS.CCONJ);		
	    	put("CLS", UniversalPOS.PRON);
	    	put("CLO", UniversalPOS.PRON);
	    	put("CLR", UniversalPOS.PRON);
	    	put("P", UniversalPOS.ADP);
	    	put("P+D", UniversalPOS.ADP);
	    	put("P+PRO", UniversalPOS.ADP);	
	    	put("I", UniversalPOS.INTJ); 
	    	put("PONCT", null);	
	    	put("ET", UniversalPOS.NOUN);	//	Borrowed
	    	put("ADJWH", UniversalPOS.ADJ);	
	    	put("ADJ", UniversalPOS.ADJ);
	    	put("ADVWH", UniversalPOS.ADV);	
	    	put("ADV", UniversalPOS.ADV);
	    	put("PROWH", UniversalPOS.PRON);	
	    	put("PRORE", UniversalPOS.PRON);	
	    	put("PRO", UniversalPOS.PRON);
	    	put("DETWH", UniversalPOS.DET);	
	    	put("DET", UniversalPOS.DET);
	    	put("joker", UniversalPOS.NOUN); //artificially added
	    	put("JOKER", UniversalPOS.NOUN); //artificially added
	    }
	};
		
	
	/**
	 * Mapping from the STTS Stuttgart Tübingen POS tagset for German 
	 * http://paula.petcu.tm.ro/init/default/post/opennlp-part-of-speech-tags
	 */
	static final Map<String,UniversalPOS> BN_POS_DE= new HashMap<String,UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864035L;
	    {
	    	put("ADJA", UniversalPOS.ADJ); 	// attributive adjective
	    	put("ADJD", UniversalPOS.ADJ); 	// adverbial or predicative adjective
	    	put("ADV", UniversalPOS.ADV); 	// Adverb
	    	put("APPR", UniversalPOS.ADP); 	// Preposition
	    	put("APPRART", UniversalPOS.ADP); // Preposition with article folded in
	    	put("APPO", UniversalPOS.ADP); 	// Postposition
	    	put("APZR", null);  			// Right part of circumposition
	    	put("ART", UniversalPOS.DET); 	// definite or indefinite article
	    	put("CARD", UniversalPOS.NOUN); 		// cardinal number
	    	put("FM", UniversalPOS.NOUN); 		// foreign word
	    	put("ITJ", UniversalPOS.INTJ); 	// interjection
	    	put("KOUI", UniversalPOS.SCONJ); 	// subordinating conjunction with 'zu' and infinitive
	    	put("KOUS", UniversalPOS.SCONJ); 	// subordinating conjunction with sentence
	    	put("KON", UniversalPOS.CCONJ); 	// coordinating conjunction
	    	put("KOKOM", UniversalPOS.CCONJ); 	// comparative conjunction
	    	put("NN", UniversalPOS.NOUN); 		// common noun
	    	put("NE", UniversalPOS.NOUN); 		// proper noun
	    	put("PDS", UniversalPOS.PRON); 	// substituting demonstrative pronoun
	    	put("PDAT", UniversalPOS.PRON); 	// attributive demonstrative pronoun
	    	put("PIS", UniversalPOS.PRON); 	// substituting indefinite pronoun
	    	put("PIAT", UniversalPOS.PRON); 	// attributive indefinite pronoun
	    	put("PIDAT", UniversalPOS.PRON); 	// attributive indefinite pronoun with a determiner
	    	put("PPER", UniversalPOS.PRON); 	// non-reflexive personal pronoun
	    	put("PPOSS", UniversalPOS.PRON); 	// substituting possessive pronoun
	    	put("PPOSAT", UniversalPOS.PRON); 	// attribute adding posessive pronoun
	    	put("PRELS", UniversalPOS.PRON); 	// substituting relative pronoun
	    	put("PRELAT", UniversalPOS.PRON); 	// attribute adding relative pronoun
	    	put("PRF", UniversalPOS.PRON); 	// reflexive personal pronoun
	    	put("PWS", UniversalPOS.PRON); 	// substituting interrogative pronoun
	    	put("PWAT", UniversalPOS.PRON); 	// attribute adding interrogative pronoun
	    	put("PWAV", UniversalPOS.PRON); 	// adverbial interrogative or relative pronoun
	    	put("PAV", UniversalPOS.ADV);  	// pronominal adverb
	    	put("PTKZU", null); 			// 'zu' before infinitive
	    	put("PTKNEG", null); 		// Negation particle
	    	put("PTKVZ", null); 			// particle part of separable verb
	    	put("PTKANT", null); 		// answer particle
	    	put("PTKA", null); 			// particle associated with adverb or adjective
	    	put("TRUNC", UniversalPOS.NOUN); 	// first member of compound noun
	    	put("VVFIN", UniversalPOS.VERB); 	// full finite verb
	    	put("VVIMP", UniversalPOS.VERB); 	// full imperative
	    	put("VVINF", UniversalPOS.VERB); 	// full infinitive
	    	put("VVIZU", UniversalPOS.VERB); 	// full infinitive with "zu"
	    	put("VVPP", UniversalPOS.VERB); 		// full past participle
	    	put("VAFIN", UniversalPOS.VERB);		// auxilliary finite verb
	    	put("VAIMP", UniversalPOS.VERB); 	// auxilliary imperative
	    	put("VAINF", UniversalPOS.VERB); 	// auxilliary infinitive
	    	put("VAPP", UniversalPOS.VERB); 		// auxilliary past participle
	    	put("VMFIN", UniversalPOS.VERB); 	// modal finite verb
	    	put("VMINF", UniversalPOS.VERB); 	// modal infinitive
	    	put("VMPP", UniversalPOS.VERB); 		// modal past participle
	    	put("XY", null); 			// Non word with special characters
	    	put("$,", null); 			// comma
	    	put("$.", null); 			// sentence ending punctuation
	    	put("$(", null); 			// other sentence internal punctuation		
	    	put("joker", UniversalPOS.NOUN); //artificially added
	    	put("JOKER", UniversalPOS.NOUN); //artificially added
	    }
	};


	/**
	 * Dutch TreeTagger part-of-speech tagset
	 * http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/dutch-tagset.txt
	 */
	static final Map<String,UniversalPOS> BN_POS_NL= new HashMap<String,UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864036L;
	    {
	    	put("$.", null);		// sentence-final punctuation
	    	put("adj", UniversalPOS.ADJ);	// adjective
	    	put("adj*kop", UniversalPOS.ADJ);		// truncated adjective
	    	put("adjabbr", UniversalPOS.ADJ);		// abbreviated adjective
	    	put("adv", UniversalPOS.ADV);		// adverb
	    	put("advabbr", UniversalPOS.ADV);		// abbreviated adverb
	    	put("conjcoord", UniversalPOS.CCONJ);	// coordinating conjunction
	    	put("conjsubo", UniversalPOS.SCONJ);	// subordinating conjunction
	    	put("det__art", UniversalPOS.DET);	// article
	    	put("det__demo", UniversalPOS.DET);	// attributively used demonstrative pronoun
	    	put("det__indef", UniversalPOS.DET);	// attributively used indefinite pronoun
	    	put("det__poss", UniversalPOS.DET);	// attributively used possessive pronoun
	    	put("det__quest", UniversalPOS.PRON);	// attributively used question pronoun
	    	put("det__rel", UniversalPOS.PRON);	// attributively used relative pronoun
	    	put("int", UniversalPOS.INTJ);		// interjection
	    	put("noun*kop", UniversalPOS.NOUN);	// truncated noun
	    	put("nounabbr", UniversalPOS.NOUN);		// abbreviated noun
	    	put("nounpl", UniversalPOS.NOUN);			// plural noun
	    	put("nounprop", UniversalPOS.NOUN);		// proper name
	    	put("nounsg", UniversalPOS.NOUN);			// singular noun
	    	put("num__card", UniversalPOS.NOUN);	// cardinal number
	    	put("num__ord", UniversalPOS.NOUN);	// ordinal number
	    	put("partte", null);		// particle "te"
	    	put("prep", UniversalPOS.ADP);		// preposition
	    	put("prepabbr", UniversalPOS.ADP);	// abbreviated preposition
	    	put("pronadv", UniversalPOS.ADV);		// pronomial adverb
	    	put("prondemo", UniversalPOS.PRON);		// demonstrative pronoun (used substitutively)
	    	put("pronindef", UniversalPOS.PRON);	// indefined pronoun
	    	put("pronpers", UniversalPOS.PRON);		// personal pronoun
	    	put("pronposs", UniversalPOS.PRON);		// possessive pronoun
	    	put("pronquest", UniversalPOS.PRON);		// question pronoun
	    	put("pronrefl", UniversalPOS.PRON);		// reflexive pronoun
	    	put("pronrel", UniversalPOS.PRON);			// relative pronoun
	    	put("punc", null);		// (non-sentential) punctuation
	    	put("verbinf", UniversalPOS.VERB);		// infinitival verb
	    	put("verbpapa", UniversalPOS.VERB);	// past participle verb
	    	put("verbpastpl", UniversalPOS.VERB);	// plural past tense verb
	    	put("verbpastsg", UniversalPOS.VERB);	// singular past tense verb
	    	put("verbpresp", UniversalPOS.VERB);	// present participle verb
	    	put("verbprespl", UniversalPOS.VERB);	// plural present tense verb
	    	put("verbpressg", UniversalPOS.VERB);	// singular present tense verb
	    	put("joker", UniversalPOS.NOUN); //artificially added
	    }
	};


	/**
	 * Italian TreeTagger part-of-speech tagset
	 * http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/italian-tagset.txt
	 * (Copyright Prof. Achim Stein, University of Stuttgart)
	 */
	static final Map<String,UniversalPOS> BN_POS_IT= new HashMap<String,UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864037L;
	    {
	    	put("ABR", null);		//abbreviation
	    	put("ADJ", UniversalPOS.ADJ);		//adjective
	    	put("ADV", UniversalPOS.ADV);		//adverb
	    	put("CON", UniversalPOS.CCONJ);		//conjunction
	    	put("DET:def", UniversalPOS.DET);	//definite article
	    	put("DET:indef", UniversalPOS.DET);	//indefinite article
	    	put("FW", UniversalPOS.NOUN);		//foreign word
	    	put("INT", UniversalPOS.INTJ);		//interjection
	    	put("LS", null);		//list symbol
	    	put("NOM", UniversalPOS.NOUN);		//noun
	    	put("NPR", UniversalPOS.NOUN);		//name
	    	put("NUM", UniversalPOS.NOUN);		//numeral
	    	put("PON", null);		//punctuation
	    	put("PRE", UniversalPOS.ADP);	//preposition
	    	put("PRE:det", UniversalPOS.ADP);		//preposition+article
	    	put("PRO", UniversalPOS.PRON);		//pronoun
	    	put("PRO:demo", UniversalPOS.PRON);		//demonstrative pronoun
	    	put("PRO:indef", UniversalPOS.PRON);		//indefinite pronoun
	    	put("PRO:inter", UniversalPOS.PRON);		//interrogative pronoun
	    	put("PRO:pers", UniversalPOS.PRON);		//personal pronoun
	    	put("PRO:poss", UniversalPOS.PRON);		//possessive pronoun
	    	put("PRO:refl", UniversalPOS.PRON);		//reflexive pronoun
	    	put("PRO:rela", UniversalPOS.PRON);		//relative pronoun
	    	put("SENT", null);		//sentence marker
	    	put("SYM", null);		//symbol
	    	put("VER:cimp", UniversalPOS.VERB);		//verb conjunctive imperfect
	    	put("VER:cond", UniversalPOS.VERB);		//verb conditional
	    	put("VER:cpre", UniversalPOS.VERB);		//verb conjunctive present
	    	put("VER:futu", UniversalPOS.VERB);		//verb future tense
	    	put("VER:geru", UniversalPOS.VERB);		//verb gerund
	    	put("VER:impe", UniversalPOS.VERB);		//verb imperative
	    	put("VER:impf", UniversalPOS.VERB);		//verb imperfect
	    	put("VER:infi", UniversalPOS.VERB);		//verb infinitive
	    	put("VER:pper", UniversalPOS.VERB);		//verb participle perfect
	    	put("VER:ppre", UniversalPOS.VERB);		//verb participle present
	    	put("VER:pres", UniversalPOS.VERB);		//verb present
	    	put("VER:refl:infi", UniversalPOS.VERB);		//verb reflexive infinitive
	    	put("VER:remo", UniversalPOS.VERB);		//verb simple past
	    	put("joker", UniversalPOS.NOUN); //artificially added
	    	put("JOKER", UniversalPOS.NOUN); //artificially added
	    }   
	};      
                


	/**
	 * Romanian TreeTagger part-of-speech tagset
	 * Only the first three characters are relevant for the Babelnet mapping, we use five
	 * http://nl.ijs.si/ME/V4/msd/tables/msd-human-ro.tbl
	 */
	static final Map<String,UniversalPOS> BN_POS_RO= new HashMap<String,UniversalPOS>(){
		private static final long serialVersionUID = 1652098132934864038L;
	    {
	    	put("01N01", UniversalPOS.NOUN);	//Noun common -Definiteness
	    	put("01N02", UniversalPOS.NOUN);	//Noun proper
	    	put("02V01", UniversalPOS.VERB);	//Vmip1s	Verb main 
	    	put("02V02", UniversalPOS.VERB);	//Vmip1s	Verb auxiliary
	    	put("03A01", UniversalPOS.ADJ);	//Adjective qualificative positive
	    	put("04P01", UniversalPOS.PRON);	//Pp1-sn--------s	Pronoun personal 
	    	put("04P02", UniversalPOS.PRON);	//Pd3-po	Pronoun demonstrative t
	    	put("04P03", UniversalPOS.PRON);	//Pi3	Pronoun indefinite 
	    	put("04P04", UniversalPOS.PRON);	//Ps1ms-s	Pronoun possessive 
	    	put("04P05", UniversalPOS.PRON);	//Px3--d--------s	Pronoun reflexive
	    	put("04P06", UniversalPOS.PRON);	//Pz3-sr	Pronoun negative
	    	put("04P07", UniversalPOS.PRON);	//Pw3--r	Pronoun int-rel 
	    	put("05D01", UniversalPOS.DET);	//Dd3-po---e	Determiner demonstrative 
	    	put("05D02", UniversalPOS.DET);	//Di3	Determiner indefinite 
	    	put("05D03", UniversalPOS.DET);	//Ds1ms-s	Determiner possessive first masculine singular singular
	    	put("05D04", UniversalPOS.DET);	//Dw3--r---e	Determiner int-rel third direct prenomin
	    	put("05D05", UniversalPOS.DET);	//Dz3-po---e	Determiner negative third plural oblique prenomin
	    	put("05D06", UniversalPOS.DET);	//Dh1ms	Determiner emphatic first masculine singular
	    	put("06T01", UniversalPOS.DET);	//Article definite singular oblique
	    	put("06T02", UniversalPOS.DET);	//Article indefinite plural oblique
	    	put("06T03", UniversalPOS.DET);	//Article possessive plural oblique
	    	put("06T04", UniversalPOS.DET);	//Article demonstrative plural oblique
	    	put("07R01", UniversalPOS.ADV);	//Adverb general positive
	    	put("07R02", UniversalPOS.ADV);	//Adverb particle
	    	put("07R03", UniversalPOS.ADV);	//Adverb negative
	    	put("07R05", UniversalPOS.ADV);	//Adverb int-rel +Clitic
	    	put("07R06", UniversalPOS.ADV);	//Adverb portmanteau08S01010100	Spsg	Adposition preposition simple genitive
	    	put("09C01", UniversalPOS.CCONJ);	//Conjunction coordinating simple simple positive
	    	put("09C02", UniversalPOS.SCONJ);	//Conjunction subordinating simple simple positive
	    	put("09C03", UniversalPOS.CCONJ);	//Conjunction portmanteau simple simple positive
	    	put("10M01", UniversalPOS.NOUN);	//Numeral cardinal singular digit
	    	put("10M02", UniversalPOS.NOUN);	//Numeral ordinal letter
	    	put("10M03", UniversalPOS.NOUN);	//Numeral fractal feminine singular direct letter -Definiteness
	    	put("10M04", UniversalPOS.NOUN);	//Numeral multiple masculine singular direct -Definiteness
	    	put("10M05", UniversalPOS.NOUN);	//Numeral collect plural direct
	    	put("11Q01", null);	//Particle negative
	    	put("11Q02", null);	//Particle infinitive
	    	put("11Q03", null);	//Particle subjunctive
	    	put("11Q05", null);	//Particle future
	    	put("12I", UniversalPOS.INTJ);		//Iterjection
	    	put("13Y00", null);	//Abbreviation
	    	put("13Y01", null);	//Abbreviation nominal
	    	put("13Y02", null);	//Abbreviation verbal
	    	put("13Y03", null);	//Abbreviation adjectival
	    	put("13Y04", null);	//Abbreviation adverbial
	    	put("13Y05", null);	//Abbreviation pronominal
	    	put("JOKER", UniversalPOS.NOUN); //artificially added
	    }           
	};              
                        


}


/*
 * 
 * ENGLISH
 
Alphabetical list of part-of-speech tags used in the Penn Treebank Project:
https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html

Number Tag Description

1. 		CC 		Coordinating conjunction
2. 		CD 		Cardinal number
3. 		DT 		Determiner
4. 		EX 		Existential there
5. 		FW 		Foreign word
6. 		IN 		Preposition or subordinating conjunction
7. 		JJ 		Adjective
8. 		JJR 	Adjective, comparative
9. 		JJS 	Adjective, superlative
10. 	LS 		List item marker
11. 	MD 		Modal
12. 	NN 		Noun, singular or mass
13. 	NNS 	Noun, plural
14. 	NNP 	Proper noun, singular
15. 	NNPS 	Proper noun, plural
16. 	PDT 	Predeterminer
17. 	POS 	Possessive ending
18. 	PRP 	Personal pronoun
19. 	PRP$ 	Possessive pronoun
20. 	RB 		Adverb
21. 	RBR 	Adverb, comparative
22. 	RBS 	Adverb, superlative
23. 	RP 		Particle
24. 	SYM 	Symbol
25. 	TO 		to
26. 	UH 		Interjection
27. 	VB 		Verb, base form
28. 	VBD 	Verb, past tense
29. 	VBG 	Verb, gerund or present participle
30. 	VBN 	Verb, past participle
31. 	VBP 	Verb, non-3rd person singular present
32. 	VBZ 	Verb, 3rd person singular present
33. 	WDT 	Wh-determiner
34. 	WP 		Wh-pronoun
35. 	WP$ 	Possessive wh-pronoun
36. 	WRB 	Wh-adverb 


* SPANISH
*	 
* http://nlp.stanford.edu/software/spanish-faq.shtml#tagset
Tag	Description	Example(s)

Adjectives
ao0000	Adjective (ordinal)	primera, segundo, últimos
aq0000	Adjective (descriptive)	populares, elegido, emocionada, andaluz
Conjunctions
cc	Conjunction (coordinating)	y, o, pero
cs	Conjunction (subordinating)	que, como, mientras
Determiners
da0000	Article (definite)	el, la, los, las
dd0000	Demonstrative	este, esta, esos
de0000	"Exclamative" 	qué (¡Qué pobre!)
di0000	Article (indefinite)	un, muchos, todos, otros
dn0000	Numeral	tres, doscientas
dp0000	Possessive	sus, mi
dt0000	Interrogative	cuántos, qué, cuál
Punctuation
f0	Other	&, @
faa	Inverted exclamation mark	¡
fat	Exclamation mark	!
fc	Comma	,
fd	Colon	:
fe	Double quote	"
fg	Hyphen	-
fh	Forward slash	/
fia	Inverted question mark	¿
fit	Question mark	?
fp	Period / full-stop	.
fpa	Left parenthesis	(
fpt	Right parenthesis	)
fs	Ellipsis	..., etcétera
ft	Percent sign	%
fx	Semicolon	;
fz	Single quote	'
Interjections
i	Interjection	ay, ojalá, hola
Nouns
nc00000	Unknown common noun (neologism, loanword)	minidisc, hooligans, re-flotamiento
nc0n000	Common noun (invariant number)	hipótesis, campus, golf
nc0p000	Common noun (plural)	años, elecciones
nc0s000	Common noun (singular)	lista, hotel, partido
np00000	Proper noun	Málaga, Parlamento, UFINSA
Pronouns
p0000000	Impersonal se	se
pd000000	Demonstrative pronoun	éste, eso, aquellas
pe000000	"Exclamative" pronoun	qué
pi000000	Indefinite pronoun	muchos, uno, tanto, nadie
pn000000	Numeral pronoun	dos miles, ambos
pp000000	Personal pronoun	ellos, lo, la, nos
pr000000	Relative pronoun	que, quien, donde, cuales
pt000000	Interrogative pronoun	cómo, cuánto, qué
px000000	Possessive pronoun	tuyo, nuestra
Adverbs
rg	Adverb (general)	siempre, más, personalmente
rn	Adverb (negating)	no
Prepositions
sp000	Preposition	en, de, entre
Verbs
vag0000	Verb (auxiliary, gerund)	habiendo
vaic000	Verb (auxiliary, indicative, conditional)	habría, habríamos
vaif000	Verb (auxiliary, indicative, future)	habrá, habremos
vaii000	Verb (auxiliary, indicative, imperfect)	había, habíamos
vaip000	Verb (auxiliary, indicative, present)	ha, hemos
vais000	Verb (auxiliary, indicative, preterite)	hubo, hubimos
vam0000	Verb (auxiliary, imperative)	haya
van0000	Verb (auxiliary, infinitive)	haber
vap0000	Verb (auxiliary, participle)	habido
vasi000	Verb (auxiliary, subjunctive, imperfect)	hubiera, hubiéramos, hubiese
vasp000	Verb (auxiliary, subjunctive, present)	haya, hayamos
vmg0000	Verb (main, gerund)	dando, trabajando
vmic000	Verb (main, indicative, conditional)	daría, trabajaríamos
vmif000	Verb (main, indicative, future)	dará, trabajaremos
vmii000	Verb (main, indicative, imperfect)	daba, trabajábamos
vmip000	Verb (main, indicative, present)	da, trabajamos
vmis000	Verb (main, indicative, preterite)	dio, trabajamos
vmm0000	Verb (main, imperative)	da, dé, trabaja, trabajes, trabajemos
vmn0000	Verb (main, infinitive)	dar, trabjar
vmp0000	Verb (main, participle)	dado, trabajado
vmsi000	Verb (main, subjunctive, imperfect)	diera, diese, trabajáramos, trabajésemos
vmsp000	Verb (main, subjunctive, present)	dé, trabajemos
vsg0000	Verb (semiauxiliary, gerund)	siendo
vsic000	Verb (semiauxiliary, indicative, conditional)	sería, serían
vsif000	Verb (semiauxiliary, indicative, future)	será, seremos
vsii000	Verb (semiauxiliary, indicative, imperfect)	era, éramos
vsip000	Verb (semiauxiliary, indicative, present)	es, son
vsis000	Verb (semiauxiliary, indicative, preterite)	fue, fuiste
vsm0000	Verb (semiauxiliary, imperative)	sea, sé
vsn0000	Verb (semiauxiliary, infinitive)	ser
vsp0000	Verb (semiauxiliary, participle)	sido
vssf000	Verb (semiauxiliary, subjunctive, future)	fuere
vssi000	Verb (semiauxiliary, subjunctive, imperfect)	fuera, fuese, fuéramos
vssp000	Verb (semiauxiliary, subjunctive, present)	sea, seamos
Dates
w	Date	octubre, jueves, 2002
Numerals
z0	Numeral	547.000, 04, 52,52
zm	Numeral qualifier (currency)	dólares, euros
zu	Numeral qualifier (other units)	km, cc



TURKISH

TS Wikipedia Data Set PosTag List
#
POSTag
TAG
Tag Used in Data Set
1
Verb
Verb
_Verb
2
Noun
Noun
_Noun
3
Adj
Adjective
_Adj
4
Adv
Adverb
_Adverb
5
Det
Determiner
_Det
6
Conj
Conjunction
_Conj
7
Postp
Postposition
_Postp
8
Interj
Interjection
_Interj
9
Pron
Pronoun
_Pron
10
Dup
Duplication
_Dup
11
Num
Number
_Num
12
Punc
Punctuation
_Punc
13
UnDef
Undefinite
_UnDef
14
Ques
Question
_Ques
15
YY
Misspell
_YY
16
Abbr
Abbreviation
_Abbr
17
intEmphasis
Internet Emphasis
_intEmphasis
18
intAbbrEng
Internet English Abbreviation
_intAbbrEnglish
19
tinglish
Tinglish
_tinglish
20
bor
Borrowed
_bor
21
intSlang
Internet Slang
_intSlang
The tags “YY, Abbr, intEmphasis, intAbbrEng, tinglish, bor and intSlang” are processed by 




* FRENCH
* http://www.llf.cnrs.fr/Gens/Abeille/French-Treebank-fr.php
* 
* The tagset with the 28 tags is on page 8 of this paper:
 http://alpage.inria.fr/statgram/frdep/Publications/crabbecandi-taln2008-final.pdf
* 

TAG	CAT	SOUS MODE
V	V	-	indicatif		
VIMP	V	-	impératif		
VINF	V	-	infinitif		
VS	V	-	subjonctif		
VPP	V	-	participe passé		
VPR	V	-	participe présent	
NPP	N	P	-			
NC	N	C	-			
CS	C	S	-			
CC	C	C	-					
CLS	CL	suj	-	
CLO	CL	obj	-	
CLR	CL	refl	-	
P	P	-	-	
P+D	voir texte		
P+PRO	voir texte		
I	I	-	-	
PONCT	PONCT	-	-	
ET	ET	-	-
ADJWH	A	int	-
ADJ	A	¬int	-
ADVWH	ADV	int	-
ADV	ADV	¬int	-
PROWH	PRO	int	-
PROREL	PRO	rel	-
PRO	PRO	¬(int | rel)	-
DETWH	D	int	-
DET	D	¬int	-


*  GERMAN
*  http://paula.petcu.tm.ro/init/default/post/opennlp-part-of-speech-tags
*  
*  Table 3. Supposed POS tagset for German (the STTS Stuttgart Tübingen tag set)


Number  Tag   Description
1. 	ADJA 	attributive adjective
2. 	ADJD 	adverbial or predicative adjective
3. 	ADV 	Adverb
4. 	APPR 	Preposition
5. 	APPRART 	Preposition with article folded in
6. 	APPO 	Postposition
7. 	APZR 	Right part of circumposition
8. 	ART 	definite or indefinite article
9. 	CARD 	cardinal number
10. 	FM 	foreign word
11. 	ITJ 	interjection
12. 	KOUI 	subordinating conjunction with 'zu' and infinitive
13. 	KOUS 	subordinating conjunction with sentence
14. 	KON 	coordinating conjunction
15. 	KOKOM 	comparative conjunction
16. 	NN 	common noun
17. 	NE 	proper noun
18. 	PDS 	substituting demonstrative pronoun
19. 	PDAT 	attributive demonstrative pronoun
20. 	PIS 	substituting indefinite pronoun
21. 	PIAT 	attributive indefinite pronoun
22. 	PIDAT 	attributive indefinite pronoun with a determiner
23. 	PPER 	non-reflexive personal pronoun
24. 	PPOSS 	substituting possessive pronoun
25. 	PPOSAT 	attribute adding posessive pronoun
26. 	PRELS 	substituting relative pronoun
27. 	PRELAT 	attribute adding relative pronoun
28. 	PRF 	reflexive personal pronoun
29. 	PWS 	substituting interrogative pronoun
30. 	PWAT 	attribute adding interrogative pronoun
31. 	PWAV 	adverbial interrogative or relative pronoun
32. 	PAV 	pronominal adverb
33. 	PTKZU 	'zu' before infinitive
34. 	PTKNEG 	Negation particle
35. 	PTKVZ 	particle part of separable verb
36. 	PTKANT 	answer particle
37. 	PTKA 	particle associated with adverb or adjective
38. 	TRUNC 	first member of compound noun
39. 	VVFIN 	full finite verb
40. 	VVIMP 	full imperative
41. 	VVINF 	full infinitive
42. 	VVIZU 	full infinitive with "zu"
43. 	VVPP 	full past participle
44. 	VAFIN 	auxilliary finite verb
45. 	VAIMP 	auxilliary imperative
46. 	VAINF 	auxilliary infinitive
47. 	VAPP 	auxilliary past participle
48. 	VMFIN 	modal finite verb
49. 	VMINF 	modal infinitive
50. 	VMPP 	modal past participle
51. 	XY 	Non word with special characters
52. 	$, 	comma
53. 	$. 	sentence ending punctuation
54. 	$( 	other sentence internal punctuation



DUTCH
http://www.cis.uni-muenchen.de/~schmid/tools/TreeTagger/data/dutch-tagset.txt

$.		sentence-final punctuation
adj		adjective
adj*kop		truncated adjective
adjabbr		abbreviated adjective
adv		adverb
advabbr		abbreviated adverb
conjcoord	coordinating conjunction
conjsubo	subordinating conjunction
det__art	article
det__demo	attributively used demonstrative pronoun
det__indef	attributively used indefinite pronoun
det__poss	attributively used possessive pronoun
det__quest	attributively used question pronoun
det__rel	attributively used relative pronoun
int		interjection
noun*kop	truncated noun
nounabbr	abbreviated noun
nounpl		plural noun
nounprop	proper name
nounsg		singular noun
num__card	cardinal number
num__ord	ordinal number
partte		particle "te"
prep		preposition
prepabbr	abbreviated preposition
pronadv		pronomial adverb
prondemo	demonstrative pronoun (used substitutively)
pronindef	indefined pronoun
pronpers	personal pronoun
pronposs	possessive pronoun
pronquest	question pronoun
pronrefl	reflexive pronoun
pronrel		relative pronoun
punc		(non-sentential) punctuation
verbinf		infinitival verb
verbpapa	past participle verb
verbpastpl	plural past tense verb
verbpastsg	singular past tense verb
verbpresp	present participle verb
verbprespl	plural present tense verb
verbpressg	singular present tense verb




Italian tagset used in the TreeTagger parameter file
(Copyright Prof. Achim Stein, University of Stuttgart)

ABR	abbreviation
ADJ	adjective
ADV	adverb
CON	conjunction
DET:def	definite article
DET:indef	indefinite article
FW	foreign word
INT	interjection
LS	list symbol
NOM	noun
NPR	name
NUM	numeral
PON	punctuation
PRE	preposition
PRE:det	preposition+article
PRO	pronoun
PRO:demo	demonstrative pronoun
PRO:indef	indefinite pronoun
PRO:inter	interrogative pronoun
PRO:pers	personal pronoun
PRO:poss	possessive pronoun
PRO:refl	reflexive pronoun
PRO:rela	relative pronoun
SENT	sentence marker
SYM	symbol
VER:cimp	verb conjunctive imperfect
VER:cond	verb conditional
VER:cpre	verb conjunctive present
VER:futu	verb future tense
VER:geru	verb gerund
VER:impe	verb imperative
VER:impf	verb imperfect
VER:infi	verb infinitive
VER:pper	verb participle perfect
VER:ppre	verb participle present
VER:pres	verb present
VER:refl:infi	verb reflexive infinitive
VER:remo	verb simple past


Reduced tagset list for rumanian where only the first 5 characters are supposed to be used
(I removed the other ones!)
http://nl.ijs.si/ME/V4/msd/tables/msd-human-ro.tbl


01N010000000100	Nc---n	Noun common -Definiteness
01N020000000000	Np	Noun proper
02V01010101010000000000	Vmip1s	Verb main 
02V02000001000000000000	Va--1	Verb auxiliary 
03A01010000000000	Afp	Adjective qualificative positive
04P0101000101000000000000000001	Pp1-sn--------s	Pronoun personal 
04P0203000207000000000000000000	Pd3-po	Pronoun demonstrative t
04P0303000000000000000000000000	Pi3	Pronoun indefinite 
04P0401010100010000000000000000	Ps1ms-s	Pronoun possessive 
04P0503000003000000000000000001	Px3--d--------s	Pronoun reflexive
04P0603000106000000000000000000	Pz3-sr	Pronoun negative
04P0703000006000000000000000000	Pw3--r	Pronoun int-rel 

05D010300020200000001	Dd3-po---e	Determiner demonstrative 
05D020300000000000000	Di3	Determiner indefinite 
05D030101010001000000	Ds1ms-s	Determiner possessive first masculine singular singular
05D040300000100000001	Dw3--r---e	Determiner int-rel third direct prenomin
05D050300020200000001	Dz3-po---e	Determiner negative third plural oblique prenomin
05D060101010000000000	Dh1ms	Determiner emphatic first masculine singular

06T0100010200	Tf-so	Article definite singular oblique
06T0200020200	Ti-po	Article indefinite plural oblique
06T0300020200	Ts-po	Article possessive plural oblique
06T0400020200	Td-po	Article demonstrative plural oblique

07R010100	Rgp	Adverb general positive
07R020000	Rp	Adverb particle
07R020002	Rp-y	Adverb particle +Clitic
07R030000	Rz	Adverb negative
07R050000	Rw	Adverb int-rel
07R050002	Rw-y	Adverb int-rel +Clitic
07R060000	Rc	Adverb portmanteau08S01010100	Spsg	Adposition preposition simple genitive

09C0101010200	Ccssp	Conjunction coordinating simple simple positive
09C0201010200	Csssp	Conjunction subordinating simple simple positive
09C0301010200	Crssp	Conjunction portmanteau simple simple positive

10M01000100010000	Mc-s-d	Numeral cardinal singular digit
10M02000000030000	Mo---l	Numeral ordinal letter
10M03020101030100	Mffsrln	Numeral fractal feminine singular direct letter -Definiteness
10M04010101000100	Mmmsr-n	Numeral multiple masculine singular direct -Definiteness
10M05000201000000	Ml-pr	Numeral collect plural direct
11Q010000	Qz	Particle negative
11Q010002	Qz-y	Particle negative +Clitic
11Q020000	Qn	Particle infinitive
11Q020002	Qn-y	Particle infinitive +Clitic
11Q030000	Qs	Particle subjunctive
11Q050000	Qf	Particle future
12I	I	Interjection
13Y0000000000	Y	Abbreviation
13Y0100000000	Yn	Abbreviation nominal
13Y0200000000	Yv	Abbreviation verbal
13Y0300000000	Ya	Abbreviation adjectival
13Y0400000000	Yr	Abbreviation adverbial
13Y0500000000	Yp	Abbreviation pronominal
14X	X	Residual

*
*/


