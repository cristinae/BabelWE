#!/usr/bin/env python
# -*- coding: utf-8 -*- 
# Author: Cristina


"""
Reads an input file anotated with wplmb and the corresponding target file and
and extracts the <2en> sentences with the desired factors (BN related)
"""

from itertools import izip
import os
import sys

def mapEN (entrada):
    if entrada.startswith('CC'):
       entrada=entrada.replace('CC','CONJUNCTION')
       return entrada
    if entrada.startswith('CD'):
       entrada=entrada.replace('CD','NOUN') 
       return entrada
    if entrada.startswith('DT'):
       entrada=entrada.replace('DT','DETERMINER')
       return entrada
    if entrada.startswith('EX'):
       entrada=entrada.replace('EX', '-')
       return entrada
    if entrada.startswith('FW'):
       entrada=entrada.replace('FW','NOUN') 
       return entrada
    if entrada.startswith('IN'):
       entrada=entrada.replace('IN','PREPOSITION')
       return entrada
    if entrada.startswith('JJR'):
       entrada=entrada.replace('JJR','ADJECTIVE')
       return entrada
    if entrada.startswith('JJS'):
       entrada=entrada.replace('JJS','ADJECTIVE') 
       return entrada
    if entrada.startswith('JJ'):
       entrada=entrada.replace('JJ','ADJECTIVE')
       return entrada
    if entrada.startswith('LS'):
       entrada=entrada.replace('LS', '-')
       return entrada
    if entrada.startswith('MD'):
       entrada=entrada.replace('MD','VERB') 
       return entrada
    if entrada.startswith('NNS'):
       entrada=entrada.replace('NNS','NOUN')
       return entrada
    if entrada.startswith('NNPS'):
       entrada=entrada.replace('NNPS','NOUN')
       return entrada
    if entrada.startswith('NNP'):
       entrada=entrada.replace('NNP','NOUN') 
       return entrada
    if entrada.startswith('NN'):
       entrada=entrada.replace('NN','NOUN') 
       return entrada
    if entrada.startswith('PDT'):
       entrada=entrada.replace('PDT','DETERMINER') 
       return entrada
    if entrada.startswith('POS'):
       entrada=entrada.replace('POS', '-') 
       return entrada
    if entrada.startswith('PRP$'):
       entrada=entrada.replace('PRP$','PRONOUN')
       return entrada
    if entrada.startswith('PRP'):
       entrada=entrada.replace('PRP','PRONOUN') 
       return entrada
    if entrada.startswith('RBR'):
       entrada=entrada.replace('RBR','ADVERB') 
       return entrada
    if entrada.startswith('RBS'):
       entrada=entrada.replace('RBS','ADVERB') 
       return entrada
    if entrada.startswith('RB'):
       entrada=entrada.replace('RB', 'ADVERB')
       return entrada
    if entrada.startswith('RP'):
       entrada=entrada.replace('RP', '-') 
       return entrada
    if entrada.startswith('SYM'):
       entrada=entrada.replace('SYM', '-') 
       return entrada
    if entrada.startswith('TO'):
       entrada=entrada.replace('TO','PREPOSITION') 
       return entrada
    if entrada.startswith('UH'):
       entrada=entrada.replace('UH','INTERJECTION') 
       return entrada
    if entrada.startswith('VBD'):
       entrada=entrada.replace('VBD','VERB') 
       return entrada
    if entrada.startswith('VBG'):
       entrada=entrada.replace('VBG','VERB') 
       return entrada
    if entrada.startswith('VBN'):
       entrada=entrada.replace('VBN','VERB') 
       return entrada
    if entrada.startswith('VBP'):
       entrada=entrada.replace('VBP','VERB') 
       return entrada
    if entrada.startswith('VBZ'):
       entrada=entrada.replace('VBZ','VERB') 
       return entrada
    if entrada.startswith('VB'):
       entrada=entrada.replace('VB','VERB') 
       return entrada
    if entrada.startswith('WDT'):
       entrada=entrada.replace('WDT','DETERMINER') 
       return entrada
    if entrada.startswith('WP$'):
       entrada=entrada.replace('WP$','PRONOUN') 
       return entrada
    if entrada.startswith('WP'):
       entrada=entrada.replace('WP','PRONOUN')
       return entrada
    if entrada.startswith('WRB'):
       entrada=entrada.replace('WRB','ADVERB') 
       return entrada
    return entrada


def mapFR (entrada):
    if entrada.startswith('VIMP'):
       entrada= entrada.replace('VIMP','VERB')
       return entrada
    if entrada.startswith('VINF'):
       entrada= entrada.replace('VINF','VERB')
       return entrada
    if entrada.startswith('VS'):
       entrada= entrada.replace('VS','VERB')
       return entrada
    if entrada.startswith('VPP'):
       entrada= entrada.replace('VPP','VERB')
       return entrada
    if entrada.startswith('VPR'):
       entrada= entrada.replace('VPR','VERB')
       return entrada
    if entrada.startswith('NPP'):
       entrada= entrada.replace('NPP','NOUN')
       return entrada
    if entrada.startswith('NC'):
       entrada= entrada.replace('NC','NOUN')
       return entrada
    if entrada.startswith('CS'):
       entrada= entrada.replace('CS','CONJUNCTION')
       return entrada
    if entrada.startswith('CC'):
       entrada= entrada.replace('CC','CONJUNCTION')		
       return entrada
    if entrada.startswith('CLS'):
       entrada= entrada.replace('CLS','PRONOUN')
       return entrada
    if entrada.startswith('CLO'):
       entrada= entrada.replace('CLO','PRONOUN')
       return entrada
    if entrada.startswith('CLR'):
       entrada= entrada.replace('CLR','PRONOUN')
       return entrada
    if entrada.startswith('P+D'):
       entrada= entrada.replace('P+D','PREPOSITION')
       return entrada
    if entrada.startswith('P+PRO'):
       entrada= entrada.replace('P+PRO','PREPOSITION')	
       return entrada
    if entrada.startswith('PONCT'):
       entrada= entrada.replace('PONCT','-')	
       return entrada
    if entrada.startswith('ET'):
       entrada= entrada.replace('ET','NOUN')
       return entrada
    if entrada.startswith('ADJWH'):
       entrada= entrada.replace('ADJWH','ADJECTIVE')	
       return entrada
    if entrada.startswith('ADJ'):
       entrada= entrada.replace('ADJ','ADJECTIVE')
       return entrada
    if entrada.startswith('ADVWH'):
       entrada= entrada.replace('ADVWH','ADVERB')	
       return entrada
    if entrada.startswith('ADV'):
       entrada= entrada.replace('ADV','ADVERB')
       return entrada
    if entrada.startswith('PROWH'):
       entrada= entrada.replace('PROWH','PRONOUN')	
       return entrada
    if entrada.startswith('PROREL'):
       entrada= entrada.replace('PROREL','PRONOUN')	
       return entrada
    if entrada.startswith('PRO'):
       entrada= entrada.replace('PRO','PRONOUN')
       return entrada
    if entrada.startswith('PREF'):
       entrada= entrada.replace('PREF','-')
       return entrada
    if entrada.startswith('DETWH'):
       entrada= entrada.replace('DETWH','DETERMINER')	
       return entrada
    if entrada.startswith('DET'):
       entrada= entrada.replace('DET','DETERMINER')
       return entrada
    if entrada.startswith('V'):
       entrada= entrada.replace('V','VERB')
       return entrada
    if entrada.startswith('I'):
       entrada= entrada.replace('I','INTERJECTION') 
       return entrada
    if entrada.startswith('P'):
       entrada= entrada.replace('P','PREPOSITION')
       return entrada
    return entrada

def main(inputFile, L2File):

    # Output file
    outputL1 = '/media/cristinae/DATA2/pln/experiments/bnLang/'+inputFile+'.bp'
    outputL2 = '/media/cristinae/DATA2/pln/experiments/bnLang/'+L2File+'.word'
    try:
       os.remove(outputL1)
       os.remove(outputL2)
    except OSError:
       pass

 
    # Reads the main files
    i=0
    with open(inputFile) as f, open(L2File) as l2: 
        for x, y in izip(f, l2):
	    i=i+1
            x = x.strip()
            y = y.strip()  
            #if x.startswith("<2en>"):
            if x:
                
                factorsToken = x.split()
                newString = ''
            
                for factors in factorsToken:
                    # all the factors except the leading word
                    # pslmbFactors = '|'+factors.split('|',1)[1]
                    w = factors.split('|')[0]
                    b = factors.split('|')[3]
                    p = factors.split('|')[1]
                    
                    if (b=='-'):
                        newString = newString + mapEN(p) + ' '
                    else:
                        newString = newString + b + ' '
                        
                        #print("{}\n{}\n\n".format(x, newString))
                with open(outputL1, 'a') as of:
                    of.write(newString+'\n')
                with open(outputL2, 'a') as of:
                    of.write(x+'\n')
                

if __name__ == "__main__":
    
    if len(sys.argv) is not 3:
        sys.stderr.write('Usage: python %s wplmb w.bpe\n' % sys.argv[0])
        sys.exit(1)
    main(sys.argv[1], sys.argv[2])

