package cat.trachemys.interlingua.babelNet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Subset of PoS tags for Arabic, English, Spanish, Turkish, French, German, Italian, Dutch and Romanian  
 * for which we extract the BabelNet ID.
 * 
 * @author cristina
 * @since Dec 27, 2016
 */
public class PoSAccept {

	/**
	 * Selected PoS tags from the MadaMira 2.1 tag set for Arabic for which we want
	 * the BabelNet ID
	 */
	static final Set<String> POS_AR_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864030L;
	    {
	    	add("adj");    // Adjectives
	    	add("adj_comp");
	    	add("adj_num");
	    	add("noun");    // Nouns
	    	add("noun_num");
	    	add("noun_quant");
	    	add("noun_prop");	    	
	    	add("adv");     // Adverbs
	    	add("adv_interrog");
	    	add("adv_rel");
	    	add("verb");    // Verbs
	    	add("verb_pseudo");
	    	add("latin");   // Foreign words
		}
	};
	
	/**
	 * Selected PoS tags from the Penn Tree Bank tag set for English for which we want
	 * the BabelNet ID
	 */
	static final Set<String> POS_EN_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864031L;
	    {
	    	add("JJ");  //Adjectives
	    	add("JJR");
	    	add("JJS");
	    	add("NN");  //Nouns
	    	add("NNS");
	    	add("NNP");
	    	add("NNPS");
	    	add("RB");  //Adverbs
	    	add("RBR");
	    	add("RBS");
	    	add("WRB");
	    	//add("MD");  //Verbs
	    	add("VB");
	    	add("VBD");
	    	add("VBG");
	    	add("VBN");
	    	add("VBP");
	    	add("VBZ");
	    	add("FW");  //Foreign words
		}
	};

	/**
	 * Negations in English. Tokens that appear within the RP (particle) and RB (adverb) PoS tags
	 * of the Penn Tree Bank tag set for English 
	 */
	static final Set<String> NEG_EN= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864041L;
	    {
	    	add("not");
	    	add("n't");
	    	add("non");  //not seen
	    	add("no");   //not seen
		}
	};

	/**
	 * Selected PoS tags from the Ancora tag set for Spanish for which we want the BabelNet ID.
	 * Only the first two characters of the tag are considered.
	 */
	static final Set<String> POS_ES_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864032L;
	    {
	    	add("ao"); //Adjectives
	    	add("aq");
	    	add("dn"); //Numerals
	    	add("nc"); //Nouns
	    	add("np"); 
	    	add("rg"); //Adverbs
	    	//add("rn");
	    	//add("va"); //Verbs
	    	add("vm");
	    	add("vs");
	    	add("zm");  //Currency
	    	add("zu");  //unitats
		}
	};

	/**
	 * PoS tags from the Turkish tag set for which we want the BabelNet ID.
	 */
	static final Set<String> POS_TR_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864033L;
	    {
	    	add("Verb");
	    	add("Noun");
	    	add("Adj");
	    	add("Adv");
	    	add("Num"); //think determiner?       //	Number
	    	add("bor");	//	Borrowed
		}
	};

	/**
	 * Negations in Turkish. (from google translate!)
	 */
	static final Set<String> NEG_TR= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864042L;
	    {
	    	add("hayır");
	    	add("yok");
	    	add("değil");  
	    	add("hiçbir"); 
		}
	};
	
	
	/**
	 * PoS tags from the French tag set for which we want the BabelNet ID.
	 */
	static final Set<String> POS_FR_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864034L;
	    {
	    	//add("ADJWH", BabelPOS.ADJECTIVE);	
	    	add("ADJ");
	    	//add("ADVWH", BabelPOS.ADVERB);	
	    	add("ADV");
	    	add("NPP");
	    	add("NC");
	    	add("ET");
	    	add("V");
	    	add("VIMP");
	    	add("VINF");
	    	add("VS");
	    	add("VPP");
	    	add("VPR");

		}
	};


	/**
	 * Negations in French. 
	 */
	static final Set<String> NEG_FR= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864043L;
	    {
	    	add("non");
	    	add("ne");
	    	add("pas");  
	    	add("n'"); 
		}
	};

	
	/**
	 * PoS tags from the German tag set for which we want the BabelNet ID.
	 */
	static final Set<String> POS_DE_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864035L;
	    {
	    	
	    	add("ADJA"); 	// attributive adjective
	    	add("ADJD"); 	// adverbial or predicative adjective
	    	add("ADV"); 	// Adverb
	    	//add("CARD", BabelPOS.NOUN); 		// cardinal number
	    	add("FM"); 		// foreign word
	    	add("NN"); 		// common noun
	    	add("NE"); 		// proper noun
	    	add("TRUNC"); 	// first member of compound noun
	    	add("VVFIN"); 	// full finite verb
	    	add("VVIMP"); 	// full imperative
	    	add("VVINF"); 	// full infinitive
	    	add("VVIZU"); 	// full infinitive with "zu"
	    	add("VVPP"); 		// full past participle
	    	//add("VAFIN", BabelPOS.VERB);		// auxilliary finite verb
	    	//add("VAIMP", BabelPOS.VERB); 	// auxilliary imperative
	    	//add("VAINF", BabelPOS.VERB); 	// auxilliary infinitive
	    	//add("VAPP", BabelPOS.VERB); 		// auxilliary past participle
	    	//add("VMFIN", BabelPOS.VERB); 	// modal finite verb
	    	//add("VMINF", BabelPOS.VERB); 	// modal infinitive
	    	//add("VMPP", BabelPOS.VERB); 	// modal past participle
		}
	};

	
	/**
	 * Selected PoS tags from the Dutch tagset for which we want the BabelNet ID.
	 * Only the first characters of the tag are considered.
	 */
	static final Set<String> POS_NL_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864036L;
	    {
	    	add("adj");	// adjective
	    	add("adv");	// adverb
	    	add("nou");	//  noun
	    	add("num");	// cardinal/ordinal number
	    	add("ver"); // verbs
		}
	};

	/**
	 * Negations in Dutch.
	 * From  http://www.dutchgrammar.com/en/?n=WordOrder.45
	 */
	static final Set<String> NEG_NL= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864044L;
	    {
	    	add("niet");
	    	add("geen");
		}
	};

	/**
	 * Selected PoS tags from the Italian tagset for which we want the BabelNet ID.
	 */
	static final Set<String> POS_IT_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864037L;
	    {
	    	add("ADJ");		//adjective
	    	add("ADV");		//adverb
	    	add("FW");		//foreign word
	    	add("NOM");		//noun
	    	add("NPR");		//name
	    	add("NUM");		//numeral
	    	add("VER:cimp");	//verb conjunctive imperfect
	    	add("VER:cond");	//verb conditional
	    	add("VER:cpre");	//verb conjunctive present
	    	add("VER:futu");	//verb future tense
	    	add("VER:geru");	//verb gerund
	    	add("VER:impe");	//verb imperative
	    	add("VER:impf");	//verb imperfect
	    	add("VER:infi");	//verb infinitive
	    	add("VER:pper");	//verb participle perfect
	    	add("VER:ppre");	//verb participle present
	    	add("VER:pres");	//verb present
	    	add("VER:refl:infi");	//verb reflexive infinitive
	    	add("VER:remo");	//verb simple past               
	    }
	};

	/**
	 * Negations in Italian.
	 */
	static final Set<String> NEG_IT= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864045L;
	    {
	    	add("non");
		}
	};

	
	/**
	 * Selected PoS tags from the Romanian tagset for which we want the BabelNet ID.
	 */
	static final Set<String> POS_RO_ACC= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864038L;
	    {
	    	add("01N01");	//Noun common -Definiteness
	    	add("01N02");	//Noun proper
	    	add("02V01");	//Vmip1s	Verb main 
	    	add("03A01");	//Adjective qualificative positive
	    	add("07R01");	//Adverb general positive
	    	add("07R03");	//Adverb negative
	    	add("07R05");	//Adverb int-rel +Clitic
	    	add("07R06");	//Adverb portmanteau08S01010100	Spsg	Adposition preposition simple genitive
	    	add("10M01");	//Numeral cardinal singular digit
	    	add("10M02");	//Numeral ordinal letter
	    	add("10M03");	//Numeral fractal feminine singular direct letter -Definiteness
	    	add("10M04");	//Numeral multiple masculine singular direct -Definiteness
	    	add("10M05");	//Numeral collect plural direct
	    }
	};


	/**
	 * Negations in Romanian.
	 */
	static final Set<String> NEG_RO= new HashSet<String>(){
		private static final long serialVersionUID = 2652098132934864046L;
	    {
	    	add("nu");
		}
	};
	
	/**
	 * Romanian TreeTagger part-of-speech tagset. Conversion.
	 * http://nl.ijs.si/ME/V4/msd/tables/msd-human-ro.tbl
	 */
	static final Map<String, String> COMPUTER_FRIENDLY_ROTAG= new HashMap<String, String>(){
		private static final long serialVersionUID = 1752098132934864038L;
	    {
	    	put("Nc---n","01N010000000100");
	    	put("Nc-s-ny","01N010001000102");
	    	put("Ncm--n","01N010100000100");
	    	put("Ncms-n","01N010101000100");
	    	put("Ncms-ny","01N010101000102");
	    	put("Ncmsvn","01N010101010100");
	    	put("Ncmsvny","01N010101010102");
	    	put("Ncmsvy","01N010101010200");
	    	put("Ncmsry","01N010101020200");
	    	put("Ncmsryy","01N010101020202");
	    	put("Ncmsoy","01N010101030200");
	    	put("Ncmsoyy","01N010101030202");
	    	put("Ncmp-n","01N010102000100");
	    	put("Ncmp-ny","01N010102000102");
	    	put("Ncmpvy","01N010102010200");
	    	put("Ncmpry","01N010102020200");
	    	put("Ncmpryy","01N010102020202");
	    	put("Ncmpoy","01N010102030200");
	    	put("Ncmpoyy","01N010102030202");
	    	put("Ncf--n","01N010200000100");
	    	put("Ncf--ny","01N010200000102");
	    	put("Ncfs-n","01N010201000100");
	    	put("Ncfsvy","01N010201010200");
	    	put("Ncfsrn","01N010201020100");
	    	put("Ncfsrny","01N010201020102");
	    	put("Ncfsry","01N010201020200");
	    	put("Ncfsryy","01N010201020202");
	    	put("Ncfson","01N010201030100");
	    	put("Ncfsony","01N010201030102");
	    	put("Ncfsoy","01N010201030200");
	    	put("Ncfsoyy","01N010201030202");
	    	put("Ncfp-n","01N010202000100");
	    	put("Ncfp-ny","01N010202000102");
	    	put("Ncfpvy","01N010202010200");
	    	put("Ncfpry","01N010202020200");
	    	put("Ncfpryy","01N010202020202");
	    	put("Ncfpoy","01N010202030200");
	    	put("Ncfpoyy","01N010202030202");
	    	put("Np","01N020000000000");
	    	put("Npmsvn","01N020101010100");
	    	put("Npmsvy","01N020101010200");
	    	put("Npmsry","01N020101020200");
	    	put("Npmsoy","01N020101030200");
	    	put("Npmp-n","01N020102000100");
	    	put("Npmpry","01N020102020200");
	    	put("Npmpoy","01N020102030200");
	    	put("Npfsvy","01N020201010200");
	    	put("Npfsrn","01N020201020100");
	    	put("Npfsry","01N020201020200");
	    	put("Npfson","01N020201030100");
	    	put("Npfsoy","01N020201030200");
	    	put("Npfp-n","01N020202000100");
	    	put("Npfpry","01N020202020200");
	    	put("Npfpoy","01N020202030200");
	    	put("Vmip1s","02V01010101010000000000");
	    	put("Vmip1s----y","02V01010101010000000002");
	    	put("Vmip1p","02V01010101020000000000");
	    	put("Vmip1p----y","02V01010101020000000002");
	    	put("Vmip2s","02V01010102010000000000");
	    	put("Vmip2s----y","02V01010102010000000002");
	    	put("Vmip2p","02V01010102020000000000");
	    	put("Vmip2p----y","02V01010102020000000002");
	    	put("Vmip3","02V01010103000000000000");
	    	put("Vmip3-----y","02V01010103000000000002");
	    	put("Vmip3s","02V01010103010000000000");
	    	put("Vmip3s----y","02V01010103010000000002");
	    	put("Vmip3p","02V01010103020000000000");
	    	put("Vmip3p----y","02V01010103020000000002");
	    	put("Vmii1","02V01010201000000000000");
	    	put("Vmii1-----y","02V01010201000000000002");
	    	put("Vmii1s","02V01010201010000000000");
	    	put("Vmii1p","02V01010201020000000000");
	    	put("Vmii2s","02V01010202010000000000");
	    	put("Vmii2s----y","02V01010202010000000002");
	    	put("Vmii2p","02V01010202020000000000");
	    	put("Vmii2p----y","02V01010202020000000002");
	    	put("Vmii3s","02V01010203010000000000");
	    	put("Vmii3s----y","02V01010203010000000002");
	    	put("Vmii3p","02V01010203020000000000");
	    	put("Vmii3p----y","02V01010203020000000002");
	    	put("Vmis1s","02V01010301010000000000");
	    	put("Vmis1s----y","02V01010301010000000002");
	    	put("Vmis1p","02V01010301020000000000");
	    	put("Vmis1p----y","02V01010301020000000002");
	    	put("Vmis2s","02V01010302010000000000");
	    	put("Vmis2s----y","02V01010302010000000002");
	    	put("Vmis2p","02V01010302020000000000");
	    	put("Vmis2p----y","02V01010302020000000002");
	    	put("Vmis3s","02V01010303010000000000");
	    	put("Vmis3s----y","02V01010303010000000002");
	    	put("Vmis3p","02V01010303020000000000");
	    	put("Vmis3p----y","02V01010303020000000002");
	    	put("Vmil1s","02V01010401010000000000");
	    	put("Vmil1s----y","02V01010401010000000002");
	    	put("Vmil1p","02V01010401020000000000");
	    	put("Vmil1p----y","02V01010401020000000002");
	    	put("Vmil2s","02V01010402010000000000");
	    	put("Vmil2s----y","02V01010402010000000002");
	    	put("Vmil2p","02V01010402020000000000");
	    	put("Vmil2p----y","02V01010402020000000002");
	    	put("Vmil3s","02V01010403010000000000");
	    	put("Vmil3s----y","02V01010403010000000002");
	    	put("Vmil3p","02V01010403020000000000");
	    	put("Vmil3p----y","02V01010403020000000002");
	    	put("Vmsp1s","02V01020101010000000000");
	    	put("Vmsp1p","02V01020101020000000000");
	    	put("Vmsp2s","02V01020102010000000000");
	    	put("Vmsp2p","02V01020102020000000000");
	    	put("Vmsp3","02V01020103000000000000");
	    	put("Vmsp3-----y","02V01020103000000000002");
	    	put("Vmsp3s","02V01020103010000000000");
	    	put("Vmsp3s----y","02V01020103010000000002");
	    	put("Vmm-2s","02V01030002010000000000");
	    	put("Vmm-2s----y","02V01030002010000000002");
	    	put("Vmm-2p","02V01030002020000000000");
	    	put("Vmm-2p----y","02V01030002020000000002");
	    	put("Vmnp","02V01040100000000000000");
	    	put("Vmnp------y","02V01040100000000000002");
	    	put("Vmp--sm","02V01050000010100000000");
	    	put("Vmp--sm---y","02V01050000010100000002");
	    	put("Vmg","02V01060000000000000000");
	    	put("Vmg-------y","02V01060000000000000002");
	    	put("Va--1","02V02000001000000000000");
	    	put("Va--1-----y","02V02000001000000000002");
	    	put("Va--1s","02V02000001010000000000");
	    	put("Va--1s----y","02V02000001010000000002");
	    	put("Va--1p","02V02000001020000000000");
	    	put("Va--2s","02V02000002010000000000");
	    	put("Va--2s----y","02V02000002010000000002");
	    	put("Va--2p","02V02000002020000000000");
	    	put("Va--2p----y","02V02000002020000000002");
	    	put("Va--3","02V02000003000000000000");
	    	put("Va--3-----y","02V02000003000000000002");
	    	put("Va--3s","02V02000003010000000000");
	    	put("Va--3s----y","02V02000003010000000002");
	    	put("Va--3p","02V02000003020000000000");
	    	put("Va--3p----y","02V02000003020000000002");
	    	put("Vanp","02V02040100000000000000");
	    	put("Afp","03A01010000000000");
	    	put("Afp-p-n","03A01010002000100");
	    	put("Afp-p-ny","03A01010002000102");
	    	put("Afp-poy","03A01010002030200");
	    	put("Afpm--n","03A01010100000100");
	    	put("Afpms-n","03A01010101000100");
	    	put("Afpms-ny","03A01010101000102");
	    	put("Afpmsvn","03A01010101010100");
	    	put("Afpmsvy","03A01010101010200");
	    	put("Afpmsry","03A01010101020200");
	    	put("Afpmsryy","03A01010101020202");
	    	put("Afpmsoy","03A01010101030200");
	    	put("Afpmsoyy","03A01010101030202");
	    	put("Afpmp-n","03A01010102000100");
	    	put("Afpmp-ny","03A01010102000102");
	    	put("Afpmpry","03A01010102020200");
	    	put("Afpmpryy","03A01010102020202");
	    	put("Afpmpoy","03A01010102030200");
	    	put("Afpmpoyy","03A01010102030202");
	    	put("Afpf--n","03A01010200000100");
	    	put("Afpf--ny","03A01010200000102");
	    	put("Afpfsvn","03A01010201010100");
	    	put("Afpfsvy","03A01010201010200");
	    	put("Afpfsrn","03A01010201020100");
	    	put("Afpfsrny","03A01010201020102");
	    	put("Afpfsry","03A01010201020200");
	    	put("Afpfsryy","03A01010201020202");
	    	put("Afpfson","03A01010201030100");
	    	put("Afpfsony","03A01010201030102");
	    	put("Afpfsoy","03A01010201030200");
	    	put("Afpfsoyy","03A01010201030202");
	    	put("Afpfp-n","03A01010202000100");
	    	put("Afpfp-ny","03A01010202000102");
	    	put("Afpfpry","03A01010202020200");
	    	put("Afpfpryy","03A01010202020202");
	    	put("Afpfpon","03A01010202030100");
	    	put("Afpfpoy","03A01010202030200");
	    	put("Afpfpoyy","03A01010202030202");
	    	put("Afcms-n","03A01020101000100");
	    	put("Afcmp-n","03A01020102000100");
	    	put("Afcmpry","03A01020102020200");
	    	put("Afcmpoy","03A01020102030200");
	    	put("Afcfsrn","03A01020201020100");
	    	put("Afcfsry","03A01020201020200");
	    	put("Afcfson","03A01020201030100");
	    	put("Afcfsoy","03A01020201030200");
	    	put("Afcfp-n","03A01020202000100");
	    	put("Afcfpry","03A01020202020200");
	    	put("Afcfpoy","03A01020202030200");
	    	put("Afs","03A01030000000000");
	    	put("Afsms-n","03A01030101000100");
	    	put("Afsmsvy","03A01030101010200");
	    	put("Afsmsry","03A01030101020200");
	    	put("Afsmsoy","03A01030101030200");
	    	put("Afsmp-n","03A01030102000100");
	    	put("Afsmpry","03A01030102020200");
	    	put("Afsmpoy","03A01030102030200");
	    	put("Afsfsrn","03A01030201020100");
	    	put("Afsfsry","03A01030201020200");
	    	put("Afsfson","03A01030201030100");
	    	put("Afsfsoy","03A01030201030200");
	    	put("Afsfp-n","03A01030202000100");
	    	put("Afsfpry","03A01030202020200");
	    	put("Afsfpoy","03A01030202030200");
	    	put("Pp1-sn--------s","04P0101000101000000000000000001");
	    	put("Pp1-sd--------s","04P0101000103000000000000000001");
	    	put("Pp1-sd--------w","04P0101000103000000000000000002");
	    	put("Pp1-sd--y-----w","04P0101000103000002000000000002");
	    	put("Pp1-sa--------s","04P0101000104000000000000000001");
	    	put("Pp1-sa--------w","04P0101000104000000000000000002");
	    	put("Pp1-sa--y-----w","04P0101000104000002000000000002");
	    	put("Pp1-sr--------s","04P0101000106000000000000000001");
	    	put("Pp1-pd--------s","04P0101000203000000000000000001");
	    	put("Pp1-pd--------w","04P0101000203000000000000000002");
	    	put("Pp1-pd--y-----w","04P0101000203000002000000000002");
	    	put("Pp1-pa--------w","04P0101000204000000000000000002");
	    	put("Pp1-pa--y-----w","04P0101000204000002000000000002");
	    	put("Pp1-pr--------s","04P0101000206000000000000000001");
	    	put("Pp2-----------s","04P0102000000000000000000000001");
	    	put("Pp2-s---------s","04P0102000100000000000000000001");
	    	put("Pp2-sn--------s","04P0102000101000000000000000001");
	    	put("Pp2-sd--------s","04P0102000103000000000000000001");
	    	put("Pp2-sd--------w","04P0102000103000000000000000002");
	    	put("Pp2-sd--y-----w","04P0102000103000002000000000002");
	    	put("Pp2-sa--------s","04P0102000104000000000000000001");
	    	put("Pp2-sa--------w","04P0102000104000000000000000002");
	    	put("Pp2-sa--y-----w","04P0102000104000002000000000002");
	    	put("Pp2-sr--------s","04P0102000106000000000000000001");
	    	put("Pp2-so--------s","04P0102000107000000000000000001");
	    	put("Pp2-pd--------s","04P0102000203000000000000000001");
	    	put("Pp2-pd--------w","04P0102000203000000000000000002");
	    	put("Pp2-pd--y-----w","04P0102000203000002000000000002");
	    	put("Pp2-pa--------w","04P0102000204000000000000000002");
	    	put("Pp2-pa--y-----w","04P0102000204000002000000000002");
	    	put("Pp2-pr--------s","04P0102000206000000000000000001");
	    	put("Pp3-sd--------w","04P0103000103000000000000000002");
	    	put("Pp3-sd--y-----w","04P0103000103000002000000000002");
	    	put("Pp3-sr--------s","04P0103000106000000000000000001");
	    	put("Pp3-so--------s","04P0103000107000000000000000001");
	    	put("Pp3-p---------s","04P0103000200000000000000000001");
	    	put("Pp3-pd--------w","04P0103000203000000000000000002");
	    	put("Pp3-pd--y-----w","04P0103000203000002000000000002");
	    	put("Pp3-po--------s","04P0103000207000000000000000001");
	    	put("Pp3ms---------s","04P0103010100000000000000000001");
	    	put("Pp3msa--------w","04P0103010104000000000000000002");
	    	put("Pp3msa--y-----w","04P0103010104000002000000000002");
	    	put("Pp3msr--------s","04P0103010106000000000000000001");
	    	put("Pp3msr--y-----s","04P0103010106000002000000000001");
	    	put("Pp3mso--------s","04P0103010107000000000000000001");
	    	put("Pp3mpa--------w","04P0103010204000000000000000002");
	    	put("Pp3mpa--y-----w","04P0103010204000002000000000002");
	    	put("Pp3mpr--------s","04P0103010206000000000000000001");
	    	put("Pp3mpr--y-----s","04P0103010206000002000000000001");
	    	put("Pp3mpo--------s","04P0103010207000000000000000001");
	    	put("Pp3fs---------s","04P0103020100000000000000000001");
	    	put("Pp3fsa--------w","04P0103020104000000000000000002");
	    	put("Pp3fsa--y-----w","04P0103020104000002000000000002");
	    	put("Pp3fsr--------s","04P0103020106000000000000000001");
	    	put("Pp3fsr--y-----s","04P0103020106000002000000000001");
	    	put("Pp3fso--------s","04P0103020107000000000000000001");
	    	put("Pp3fpa--------w","04P0103020204000000000000000002");
	    	put("Pp3fpa--y-----w","04P0103020204000002000000000002");
	    	put("Pp3fpr--------s","04P0103020206000000000000000001");
	    	put("Pp3fpr--y-----s","04P0103020206000002000000000001");
	    	put("Pp3fpo--------s","04P0103020207000000000000000001");
	    	put("Pd3-po","04P0203000207000000000000000000");
	    	put("Pd3msr","04P0203010106000000000000000000");
	    	put("Pd3msr--y","04P0203010106000002000000000000");
	    	put("Pd3mso","04P0203010107000000000000000000");
	    	put("Pd3mpr","04P0203010206000000000000000000");
	    	put("Pd3mpr--y","04P0203010206000002000000000000");
	    	put("Pd3mpo","04P0203010207000000000000000000");
	    	put("Pd3fsr","04P0203020106000000000000000000");
	    	put("Pd3fsr--y","04P0203020106000002000000000000");
	    	put("Pd3fso","04P0203020107000000000000000000");
	    	put("Pd3fpr","04P0203020206000000000000000000");
	    	put("Pd3fpr--y","04P0203020206000002000000000000");
	    	put("Pd3fpo","04P0203020207000000000000000000");
	    	put("Pi3","04P0303000000000000000000000000");
	    	put("Pi3--r","04P0303000006000000000000000000");
	    	put("Pi3-sr","04P0303000106000000000000000000");
	    	put("Pi3-so","04P0303000107000000000000000000");
	    	put("Pi3-po","04P0303000207000000000000000000");
	    	put("Pi3msr","04P0303010106000000000000000000");
	    	put("Pi3msr--y","04P0303010106000002000000000000");
	    	put("Pi3mso","04P0303010107000000000000000000");
	    	put("Pi3mpr","04P0303010206000000000000000000");
	    	put("Pi3fsr","04P0303020106000000000000000000");
	    	put("Pi3fso","04P0303020107000000000000000000");
	    	put("Pi3fpr","04P0303020206000000000000000000");
	    	put("Ps1ms-s","04P0401010100010000000000000000");
	    	put("Ps1ms-p","04P0401010100020000000000000000");
	    	put("Ps1mp-s","04P0401010200010000000000000000");
	    	put("Ps1mp-p","04P0401010200020000000000000000");
	    	put("Ps1fsrs","04P0401020106010000000000000000");
	    	put("Ps1fsrp","04P0401020106020000000000000000");
	    	put("Ps1fp-s","04P0401020200010000000000000000");
	    	put("Ps1fp-p","04P0401020200020000000000000000");
	    	put("Ps2ms-s","04P0402010100010000000000000000");
	    	put("Ps2ms-p","04P0402010100020000000000000000");
	    	put("Ps2msrs-y","04P0402010106010002000000000000");
	    	put("Ps2mp-s","04P0402010200010000000000000000");
	    	put("Ps2mp-p","04P0402010200020000000000000000");
	    	put("Ps2fsrs","04P0402020106010000000000000000");
	    	put("Ps2fsrp","04P0402020106020000000000000000");
	    	put("Ps2fp-s","04P0402020200010000000000000000");
	    	put("Ps2fp-p","04P0402020200020000000000000000");
	    	put("Ps3ms-s","04P0403010100010000000000000000");
	    	put("Ps3mp-s","04P0403010200010000000000000000");
	    	put("Ps3fsrs","04P0403020106010000000000000000");
	    	put("Ps3fp-s","04P0403020200010000000000000000");
	    	put("Px3--d--------s","04P0503000003000000000000000001");
	    	put("Px3--d--------w","04P0503000003000000000000000002");
	    	put("Px3--d--y-----w","04P0503000003000002000000000002");
	    	put("Px3--a--------s","04P0503000004000000000000000001");
	    	put("Px3--a--------w","04P0503000004000000000000000002");
	    	put("Px3--a--y-----w","04P0503000004000002000000000002");
	    	put("Pz3-sr","04P0603000106000000000000000000");
	    	put("Pz3-so","04P0603000107000000000000000000");
	    	put("Pz3-po","04P0603000207000000000000000000");
	    	put("Pz3msr","04P0603010106000000000000000000");
	    	put("Pz3mso","04P0603010107000000000000000000");
	    	put("Pz3mpr","04P0603010206000000000000000000");
	    	put("Pz3fsr","04P0603020106000000000000000000");
	    	put("Pz3fso","04P0603020107000000000000000000");
	    	put("Pz3fpr","04P0603020206000000000000000000");
	    	put("Pw3--r","04P0703000006000000000000000000");
	    	put("Pw3-so","04P0703000107000000000000000000");
	    	put("Pw3-po","04P0703000207000000000000000000");
	    	put("Pw3msr","04P0703010106000000000000000000");
	    	put("Pw3mso","04P0703010107000000000000000000");
	    	put("Pw3mpr","04P0703010206000000000000000000");
	    	put("Pw3fsr","04P0703020106000000000000000000");
	    	put("Pw3fso","04P0703020107000000000000000000");
	    	put("Pw3fpr","04P0703020206000000000000000000");
	    	put("Dd3-po---e","05D010300020200000001");
	    	put("Dd3-po---o","05D010300020200000002");
	    	put("Dd3msr","05D010301010100000000");
	    	put("Dd3msr---e","05D010301010100000001");
	    	put("Dd3msr---o","05D010301010100000002");
	    	put("Dd3msr--yo","05D010301010100000202");
	    	put("Dd3mso","05D010301010200000000");
	    	put("Dd3mso---e","05D010301010200000001");
	    	put("Dd3mso---o","05D010301010200000002");
	    	put("Dd3mpr","05D010301020100000000");
	    	put("Dd3mpr---e","05D010301020100000001");
	    	put("Dd3mpr---o","05D010301020100000002");
	    	put("Dd3mpr--yo","05D010301020100000202");
	    	put("Dd3mpo","05D010301020200000000");
	    	put("Dd3fsr","05D010302010100000000");
	    	put("Dd3fsr---e","05D010302010100000001");
	    	put("Dd3fsr---o","05D010302010100000002");
	    	put("Dd3fsr--ye","05D010302010100000201");
	    	put("Dd3fsr--yo","05D010302010100000202");
	    	put("Dd3fso","05D010302010200000000");
	    	put("Dd3fso---e","05D010302010200000001");
	    	put("Dd3fso---o","05D010302010200000002");
	    	put("Dd3fpr","05D010302020100000000");
	    	put("Dd3fpr---e","05D010302020100000001");
	    	put("Dd3fpr---o","05D010302020100000002");
	    	put("Dd3fpr--y","05D010302020100000200");
	    	put("Dd3fpo","05D010302020200000000");
	    	put("Di3","05D020300000000000000");
	    	put("Di3------e","05D020300000000000001");
	    	put("Di3-----y","05D020300000000000200");
	    	put("Di3--r","05D020300000100000000");
	    	put("Di3--r---e","05D020300000100000001");
	    	put("Di3-s----e","05D020300010000000001");
	    	put("Di3-sr","05D020300010100000000");
	    	put("Di3-sr---e","05D020300010100000001");
	    	put("Di3-sr--y","05D020300010100000200");
	    	put("Di3-po","05D020300020200000000");
	    	put("Di3-po---e","05D020300020200000001");
	    	put("Di3ms","05D020301010000000000");
	    	put("Di3msr","05D020301010100000000");
	    	put("Di3msr---e","05D020301010100000001");
	    	put("Di3msr--y","05D020301010100000200");
	    	put("Di3mso---e","05D020301010200000001");
	    	put("Di3mp","05D020301020000000000");
	    	put("Di3mpr","05D020301020100000000");
	    	put("Di3mpr---e","05D020301020100000001");
	    	put("Di3fsr","05D020302010100000000");
	    	put("Di3fsr---e","05D020302010100000001");
	    	put("Di3fso","05D020302010200000000");
	    	put("Di3fso---e","05D020302010200000001");
	    	put("Di3fp","05D020302020000000000");
	    	put("Di3fpr","05D020302020100000000");
	    	put("Di3fpr---e","05D020302020100000001");
	    	put("Ds1ms-s","05D030101010001000000");
	    	put("Ds1ms-p","05D030101010002000000");
	    	put("Ds1msrs-y","05D030101010101000200");
	    	put("Ds1mp-s","05D030101020001000000");
	    	put("Ds1mp-p","05D030101020002000000");
	    	put("Ds1fsrs","05D030102010101000000");
	    	put("Ds1fsrs-y","05D030102010101000200");
	    	put("Ds1fsrp","05D030102010102000000");
	    	put("Ds1fsos","05D030102010201000000");
	    	put("Ds1fsos-y","05D030102010201000200");
	    	put("Ds1fsop","05D030102010202000000");
	    	put("Ds1fp-s","05D030102020001000000");
	    	put("Ds1fp-p","05D030102020002000000");
	    	put("Ds2ms-s","05D030201010001000000");
	    	put("Ds2ms-p","05D030201010002000000");
	    	put("Ds2msrs-y","05D030201010101000200");
	    	put("Ds2mp-s","05D030201020001000000");
	    	put("Ds2mp-p","05D030201020002000000");
	    	put("Ds2fsrs","05D030202010101000000");
	    	put("Ds2fsrs-y","05D030202010101000200");
	    	put("Ds2fsrp","05D030202010102000000");
	    	put("Ds2fsos","05D030202010201000000");
	    	put("Ds2fsos-y","05D030202010201000200");
	    	put("Ds2fsop","05D030202010202000000");
	    	put("Ds2fp-s","05D030202020001000000");
	    	put("Ds2fp-p","05D030202020002000000");
	    	put("Ds3ms-s","05D030301010001000000");
	    	put("Ds3msrs-y","05D030301010101000200");
	    	put("Ds3mp-s","05D030301020001000000");
	    	put("Ds3fsrs","05D030302010101000000");
	    	put("Ds3fsrs-y","05D030302010101000200");
	    	put("Ds3fsos","05D030302010201000000");
	    	put("Ds3fsos-y","05D030302010201000200");
	    	put("Ds3fp-s","05D030302020001000000");
	    	put("Dw3--r---e","05D040300000100000001");
	    	put("Dw3-po","05D040300020200000000");
	    	put("Dw3-po---e","05D040300020200000001");
	    	put("Dw3msr","05D040301010100000000");
	    	put("Dw3mso---e","05D040301010200000001");
	    	put("Dw3mpr","05D040301020100000000");
	    	put("Dw3fsr","05D040302010100000000");
	    	put("Dw3fso---e","05D040302010200000001");
	    	put("Dw3fpr","05D040302020100000000");
	    	put("Dz3-po---e","05D050300020200000001");
	    	put("Dz3msr---e","05D050301010100000001");
	    	put("Dz3mso---e","05D050301010200000001");
	    	put("Dz3mpr---e","05D050301020100000001");
	    	put("Dz3fsr---e","05D050302010100000001");
	    	put("Dz3fso---e","05D050302010200000001");
	    	put("Dh1ms","05D060101010000000000");
	    	put("Dh1mp","05D060101020000000000");
	    	put("Dh1fsr","05D060102010100000000");
	    	put("Dh1fso","05D060102010200000000");
	    	put("Dh1fp","05D060102020000000000");
	    	put("Dh2ms","05D060201010000000000");
	    	put("Dh2mp","05D060201020000000000");
	    	put("Dh2fsr","05D060202010100000000");
	    	put("Dh2fso","05D060202010200000000");
	    	put("Dh2fp","05D060202020000000000");
	    	put("Dh3ms","05D060301010000000000");
	    	put("Dh3mp","05D060301020000000000");
	    	put("Dh3fsr","05D060302010100000000");
	    	put("Dh3fso","05D060302010200000000");
	    	put("Dh3fp","05D060302020000000000");
	    	put("Tf-so","06T0100010200");
	    	put("Tfms-y","06T0101010002");
	    	put("Tfmsry","06T0101010102");
	    	put("Tfmsoy","06T0101010202");
	    	put("Tfmpry","06T0101020102");
	    	put("Tfmpoy","06T0101020202");
	    	put("Tffs-y","06T0102010002");
	    	put("Tffsoy","06T0102010202");
	    	put("Tffpry","06T0102020102");
	    	put("Tffpoy","06T0102020202");
	    	put("Ti-po","06T0200020200");
	    	put("Timsr","06T0201010100");
	    	put("Timsry","06T0201010102");
	    	put("Timso","06T0201010200");
	    	put("Timp-y","06T0201020002");
	    	put("Tifsr","06T0202010100");
	    	put("Tifsry","06T0202010102");
	    	put("Tifso","06T0202010200");
	    	put("Tifsoy","06T0202010202");
	    	put("Tifp-y","06T0202020002");
	    	put("Ts-po","06T0300020200");
	    	put("Tsms","06T0301010000");
	    	put("Tsmp","06T0301020000");
	    	put("Tsfs","06T0302010000");
	    	put("Tsfp","06T0302020000");
	    	put("Td-po","06T0400020200");
	    	put("Tdmsr","06T0401010100");
	    	put("Tdmso","06T0401010200");
	    	put("Tdmpr","06T0401020100");
	    	put("Tdfsr","06T0402010100");
	    	put("Tdfso","06T0402010200");
	    	put("Tdfpr","06T0402020100");
	    	put("Rgp","07R010100");
	    	put("Rgpy","07R010102");
	    	put("Rgc","07R010200");
	    	put("Rgs","07R010300");
	    	put("Rp","07R020000");
	    	put("Rp-y","07R020002");
	    	put("Rz","07R030000");
	    	put("Rw","07R050000");
	    	put("Rw-y","07R050002");
	    	put("Rc","07R060000");
	    	put("Spsg","08S01010100");
	    	put("Spsgy","08S01010102");
	    	put("Spsd","08S01010200");
	    	put("Spsa","08S01010300");
	    	put("Spsay","08S01010302");
	    	put("Spcg","08S01020100");
	    	put("Spca","08S01020300");
	    	put("Ccssp","09C0101010200");
	    	put("Ccsspy","09C0101010202");
	    	put("Cccsp","09C0102010200");
	    	put("Csssp","09C0201010200");
	    	put("Cssspy","09C0201010202");
	    	put("Cscsp","09C0202010200");
	    	put("Crssp","09C0301010200");
	    	put("Mc-s-d","10M01000100010000");
	    	put("Mc-p-l","10M01000200030000");
	    	put("Mcms-ln","10M01010100030100");
	    	put("Mcmsrl","10M01010101030000");
	    	put("Mcmsrly","10M01010101030200");
	    	put("Mcmsoly","10M01010102030200");
	    	put("Mcmp-l","10M01010200030000");
	    	put("Mcfsrln","10M01020101030100");
	    	put("Mcfsrly","10M01020101030200");
	    	put("Mcfsoln","10M01020102030100");
	    	put("Mcfsoly","10M01020102030200");
	    	put("Mcfp-l","10M01020200030000");
	    	put("Mcfp-ln","10M01020200030100");
	    	put("Mcfprln","10M01020201030100");
	    	put("Mcfprly","10M01020201030200");
	    	put("Mcfpoly","10M01020202030200");
	    	put("Mo---l","10M02000000030000");
	    	put("Mo---ln","10M02000000030100");
	    	put("Mo---lny","10M02000000030102");
	    	put("Mo-s-r","10M02000100020000");
	    	put("Moms-l","10M02010100030000");
	    	put("Moms-ln","10M02010100030100");
	    	put("Momsrly","10M02010101030200");
	    	put("Momsrlyy","10M02010101030202");
	    	put("Momsoly","10M02010102030200");
	    	put("Momsolyy","10M02010102030202");
	    	put("Momp-ln","10M02010200030100");
	    	put("Momprly","10M02010201030200");
	    	put("Momprlyy","10M02010201030202");
	    	put("Mompoly","10M02010202030200");
	    	put("Mompolyy","10M02010202030202");
	    	put("Mofs-l","10M02020100030000");
	    	put("Mofsrln","10M02020101030100");
	    	put("Mofsrly","10M02020101030200");
	    	put("Mofsrlyy","10M02020101030202");
	    	put("Mofsoln","10M02020102030100");
	    	put("Mofsoly","10M02020102030200");
	    	put("Mofsolyy","10M02020102030202");
	    	put("Mofp-ln","10M02020200030100");
	    	put("Mofprly","10M02020201030200");
	    	put("Mofprlyy","10M02020201030202");
	    	put("Mofpoly","10M02020202030200");
	    	put("Mofpolyy","10M02020202030202");
	    	put("Mffsrln","10M03020101030100");
	    	put("Mffsrly","10M03020101030200");
	    	put("Mffsoln","10M03020102030100");
	    	put("Mffsoly","10M03020102030200");
	    	put("Mffprln","10M03020201030100");
	    	put("Mffprly","10M03020201030200");
	    	put("Mffpoly","10M03020202030200");
	    	put("Mmmsr-n","10M04010101000100");
	    	put("Mmmsr-ny","10M04010101000102");
	    	put("Mmmsr-y","10M04010101000200");
	    	put("Mmmsr-yy","10M04010101000202");
	    	put("Mmmso-y","10M04010102000200");
	    	put("Mmmso-yy","10M04010102000202");
	    	put("Mmmpr-n","10M04010201000100");
	    	put("Mmmpr-ny","10M04010201000102");
	    	put("Mmmpr-y","10M04010201000200");
	    	put("Mmmpr-yy","10M04010201000202");
	    	put("Mmmpo-y","10M04010202000200");
	    	put("Mmmpo-yy","10M04010202000202");
	    	put("Mmfsr-n","10M04020101000100");
	    	put("Mmfsr-ny","10M04020101000102");
	    	put("Mmfsr-y","10M04020101000200");
	    	put("Mmfsr-yy","10M04020101000202");
	    	put("Mmfso-n","10M04020102000100");
	    	put("Mmfso-ny","10M04020102000102");
	    	put("Mmfso-y","10M04020102000200");
	    	put("Mmfso-yy","10M04020102000202");
	    	put("Mmfp--n","10M04020200000100");
	    	put("Mmfp--ny","10M04020200000102");
	    	put("Mmfpr-y","10M04020201000200");
	    	put("Mmfpr-yy","10M04020201000202");
	    	put("Mmfpo-y","10M04020202000200");
	    	put("Mmfpo-yy","10M04020202000202");
	    	put("Ml-pr","10M05000201000000");
	    	put("Ml-po","10M05000202000000");
	    	put("Mlmpr","10M05010201000000");
	    	put("Mlmpo","10M05010202000000");
	    	put("Mlfpr","10M05020201000000");
	    	put("Mlfpo","10M05020202000000");
	    	put("Qz","11Q010000");
	    	put("Qz-y","11Q010002");
	    	put("Qn","11Q020000");
	    	put("Qn-y","11Q020002");
	    	put("Qs","11Q030000");
	    	put("Qf","11Q050000");
	    	put("I","12I");
	    	put("Y","13Y0000000000");
	    	put("Yn","13Y0100000000");
	    	put("Ynmsry","13Y0101010101");
	    	put("Ynmsoy","13Y0101010201");
	    	put("Ynmsvy","13Y0101010301");
	    	put("Ynmpry","13Y0101020101");
	    	put("Ynmpoy","13Y0101020201");
	    	put("Ynmpvy","13Y0101020301");
	    	put("Ynfsry","13Y0102010101");
	    	put("Ynfsoy","13Y0102010201");
	    	put("Ynfpvy","13Y0102020301");
	    	put("Yv","13Y0200000000");
	    	put("Ya","13Y0300000000");
	    	put("Yr","13Y0400000000");
	    	put("Yp","13Y0500000000");
	    	put("Yp-sr","13Y0500010100");
	    	put("Yp-so","13Y0500010200");
	    	put("Yp-p","13Y0500020000");
	    	put("Ypms","13Y0501010000");
	    	put("Ypfs","13Y0502010000");
	    	put("X","14X");	    	
	    }           
	};              

}

