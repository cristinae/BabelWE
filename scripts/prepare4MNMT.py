#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Reads an input file anotated with wplmb and does the following processing:
- truecase the word as read from another input file
- remove language-dependant PoS
- lowercase stem and lemma
- adds the language tag for ML-NMT
"""

from itertools import izip
import os
import sys

def main(inputFile, trg):

    # Output file
    output = inputFile+'.source'
    try:
       os.remove(output)
    except OSError:
       pass

    # Additional tag for MLNMT
    langTag = '<2'+trg+'>|-|-|-|-|- '

    # Reads the main file together with the trucased version (with additional extension TCext)
    TCext = '.w.tc'
    i=0
    with open(inputFile) as f, open(inputFile+TCext) as tcf: 
        for x, y in izip(f, tcf):
	    i=i+1
            x = x.strip()
            y = y.strip()  
	    factorsToken = x.split()
	    words = y.split()
            newString = langTag
	    if (len(words) != len(factorsToken)):		
        	sys.stderr.write('Line %s has a different length in the two input files \n' % i)
                print("{}\n{}\n\n".format(x, y))
                sys.exit(1)
	    for factors, word in izip(factorsToken, words):
		factor = factors.split('|')
		newString = newString + word+'|'+factor[2]+'|'+factor[3].lower()+'|' \
                            + factor[4].lower()+'|'+factor[5]+'|'+factor[6]+' '

            #print("{}\n{}\n\n".format(x, newString))
	    with open(output, 'a') as of:
                 of.write(newString+'\n')
 

if __name__ == "__main__":
    
    if len(sys.argv) is not 3:
        sys.stderr.write('Usage: python %s wplmb trgLan\n' % sys.argv[0])
        sys.exit(1)
    main(sys.argv[1], sys.argv[2])
