#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Author: Cristina

"""
Reads an input file anotated with wplmb and the corresponding BPEd file for words
and joins them by copying the factors into bped tokens
"""

from itertools import izip
import os
import sys

def main(inputFile, bpedFile):

    # Output file
    output = inputFile+'.bpe'
    try:
       os.remove(output)
    except OSError:
       pass

 
    # Reads the main files
    i=0
    with open(inputFile) as f, open(bpedFile) as bpe: 
        for x, y in izip(f, bpe):
	    i=i+1
            x = x.strip()
            y = y.strip()  
	    # join BPEd words
	    y = y.replace('@@ ', '@@')

	    factorsToken = x.split()
	    words = y.split()
	    newString = ''
 	    if (len(words) != len(factorsToken)):		
        	sys.stderr.write('Line %s has a different length in the two input files after joining BPEs\n' % i)
                print("{}\n{}\n\n".format(x, y))
                sys.exit(1)
	    for factors, word in izip(factorsToken, words):
		# all the factors except the leading word
		pslmbFactors = factors.split('|',1)[1]
		subunit = word.split('@@')
		if (len(subunit)==1):
		    newString = newString + subunit[0]+pslmbFactors + ' '
		else:
		    word = word.replace('@@', '@@'+pslmbFactors+' ')
		    newString = newString + word + pslmbFactors+' '

            #print("{}\n{}\n\n".format(x, newString))
	    with open(output, 'a') as of:
                 of.write(newString+'\n')
 

if __name__ == "__main__":
    
    if len(sys.argv) is not 3:
        sys.stderr.write('Usage: python %s wplmb w.bpe\n' % sys.argv[0])
        sys.exit(1)
    main(sys.argv[1], sys.argv[2])
