#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Counts the number of words in a file specified in another file
(coverage of an input file by a list of words)
"""

import sys
import os
import re
from collections import Counter

def readDictionary(inputFile):
    freqs = {}
    with open(inputFile) as f:
        for line in f:
           (key, val) = line.split()
           freqs[int(key)] = val
    return freqs


def getCommonFileName(inputFile):
    #ex: train.en-it.it.wplmb.lema
    m = re.search('\w+\.(\w\w-\w\w.)lema.\w\w.wplmb\.(\w+)', inputFile)
    if m:
       prefix = m.group(1)
       extension = m.group(2)
       #name = prefix+extension
       name = 'commons.'+prefix+extension
    #name = 'train.commonAll.m3'

    return name


def getLanguagePair(inputFile):
    #ex: train.en-it.it.wplmb.lema
    m = re.search('\w+\.(\w\w-\w\w).lema.\w\w.wplmb\.\w+', inputFile)
    if m:
       pair = m.group(1)
    return pair


def getLanguage(inputFile):
    #ex: train.en-it.it.wplmb.lema
    m = re.search('\w+\.\w\w-\w\w.lema.(\w\w).wplmb\.\w+', inputFile)
    if m:
       lang = m.group(1)
    return lang


def main(inputFile):

    # Reads the main file and counts the frequecies of words
    with open(inputFile) as f:
	words = f.read().split()
        wordcount = Counter(words)

    # Reads the list of words and sums its frequencies in the main file
    with open(getCommonFileName(inputFile)) as f:
	commonWords=0
        for line in f:
  	     commonWords = commonWords + wordcount[line.rstrip()]
  	     #print wordcount[line.rstrip()]
    #for item in wordcount.items(): print("{}\t{}".format(*item))

    # prints the results
    percentage = float(commonWords)/float(len(words))
    print("{}.{}: {} out of {} (a {:.1%})".format(getLanguagePair(inputFile),getLanguage(inputFile),commonWords,len(words),percentage)) 


if __name__ == "__main__":
    
    if len(sys.argv) is not 2:
        sys.stderr.write('Usage: python %s train \n' % sys.argv[0])
        sys.exit(1)
    main(sys.argv[1])
