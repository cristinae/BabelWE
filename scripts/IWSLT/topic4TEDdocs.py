#!/usr/bin/env python
# -*- coding: utf-8 -*-

## Usage:
## Script to assign a topic from a previously estimated BTM model
## https://github.com/xiaohuiyan/BTM
## to TED documents in parallel corpora according to their keywords


import re
import os
import sys, getopt
import subprocess
from itertools import izip

def keywords2IDs(keywords, dictionary):
    # This is the preprocessing for training BTM
    keywords = keywords.replace(" ","")
    keywords = keywords.replace(","," ")
    IDs = ''
    for word in keywords.split():
        IDs = IDs + dictionary[word] + " "       
    return IDs


def estimateTopic(keywords, d):
    btm = '/home/cristinae/soft/BTM/src/btm'
    outputPath = '/media/cristinae/DATA1/pln/experiments/IWSLT/TEDtopic/BTM/label/'
    docIDs = keywords2IDs(keywords, d)
    docFile = outputPath+"tmpDoc.txt"
    with open(docFile, "w") as doc:
         doc.write(docIDs)
    # Executing BTM
    # ../src/btm inf sum_b $K $dwid_pt $model_dir
    command = btm + ' inf sum_b 20 ' + docFile +' '+ outputPath
    subprocess.call(command, shell=True)
    # this is not working
    #p = subprocess.call([btm, 'inf', 'sum_b', '20', docFile, outputPath], shell=True)
    resultFile = outputPath+"k20.pz_d"
    percentages = ''
    with open(resultFile, "r") as topics:
	 for percentagesLine in topics:
             continue
         percentages = percentagesLine.split()
    topic = 't'+str(percentages.index(max(percentages)))
    return topic


def main(argv):
   dictBTM = '/media/cristinae/DATA1/pln/experiments/IWSLT/TEDtopic/BTM/voca_inv.txt'
   folder = '/media/cristinae/DATA1/pln/experiments/IWSLT/DeEnItNlRo-DeEnItNlRo/'
   srcLang = 'en'
   trgLang = 'de'
   try:
      opts, args = getopt.getopt(argv,"hs:t:",["source=","target="])
   except getopt.GetoptError:
      print 'topic4TEDdocs.py -f folder -s srcLan -t trgLan'
      sys.exit(2)
   for opt, arg in opts:
      if opt == '-h':
         print 'topic4TEDdocs.py -f folder -s srcLan -t trgLan'
         sys.exit()
      elif opt in ("-f", "--folder"):
         folder = arg
      elif opt in ("-s", "--source"):
         srcLang = arg
      elif opt in ("-t", "--target"):
         trgLang = arg
 
   pair = srcLang+'-'+trgLang
   srcFile = folder + 'train.tags.'+pair+'.'+srcLang
   trgFile = folder + 'train.tags.'+pair+'.'+trgLang
   folder = folder+'/'+pair

   # Load BTM dictionary
   d = {}
   with open(dictBTM) as f:
      d = dict([line.split() for line in f])

   fileName = 'None'
   sourceSentences = ''
   targetSentences = ''
   i = 10000  #so that they are listed in the same order

   patternIni = re.compile("^\s*<url>(.+)</url>")
   patternTitle = re.compile("^\s*<title>(.+)</title>")
   patternID = re.compile("^\s*<talkid>(\d+)</talkid>")
   patternIDx = re.compile("^\s*<doc docid=\"(\d+)\"\s")
   patternKeywords = re.compile("^\s*<keywords>talks, (.+)</keywords>\s*$")

   if not os.path.exists(folder):
       os.makedirs(folder)

   topic = ''
   with open(srcFile) as src, open(trgFile) as trg: 
      for ss, ts in izip(src, trg):
        ss=ss.lstrip()
        ts=ts.lstrip()
 	if patternIni.match(ss):
 	     if i>10000:
 	        #fileBase = folder+'/'+str(i)+'_'+name
	        fileBase = folder+'/'+name+'.'
		print fileBase
 	        with open(fileBase+srcLang,'wb') as fs:
                  fs.write(sourceSentences)
	        with open(fileBase+trgLang,'wb') as ft:
   	          ft.write(targetSentences)
		sourceSentences = ''
		targetSentences = ''
	     i += 1
	elif patternID.match(ss): 
	     name = 't'+patternID.match(ss).group(1)
	     if not patternID.match(ts):
		print 'Some error occurred: IDs are not aligned'
	elif patternKeywords.match(ss): 
	     keywords = patternKeywords.match(ss).group(1)
	     if not patternKeywords.match(ts):
		print 'Some error occurred: IDs are not aligned'
             topic = estimateTopic(keywords, d) 
	elif patternTitle.match(ss): 
	     sourceSentences = topic + "\n"
	     if patternTitle.match(ts):
	        targetSentences = topic + "\n"
	     else:
		print 'Some error occurred: Titles are not aligned'
 	elif not ss.startswith("<"): 
	     sourceSentences = sourceSentences + topic + "\n"
	     if not ts.startswith("<"):
	        targetSentences = targetSentences + topic + "\n"
	     else:
		print 'Some error occurred: Texts are not aligned'



if __name__ == "__main__":
   main(sys.argv[1:])
          
