#!/bin/bash

model=$1

DATE=`date +%Y-%m-%d`
echo "" > BLEU.$model.$DATE

j=0
for i in ./test2010/*.${model}.bpe; do
    j=$((j+1))
    # IWSLT17.TED.tst2010.nl-ro.raw.ro.wplmb.2trad.wpm.bpe
    filename=${i#*2010/} #remove prefix ending in "2010/" 
    echo $filename
    tmp4pair=${filename#*tst2010.} #remove prefix ending in "tst2010."
    pair=${tmp4pair%.raw*} # remove suffix starting with ".raw"
    tmp4src=${filename#*raw.} #remove prefix ending in ".raw"
    src=${tmp4src%.wplmb*} # remove suffix starting with ".wplmb"
    tmp4trg=${pair/-/} # removes/replaces the -
    trg=${tmp4trg/$src/} # removes/replaces the source language
    oldjob=$((j-1))
    qsub -N job$j -hold_jid job$oldjob ./translateTest2010.sh ${filename} ${src} ${trg} ${model}
    #qsub  ./translateTest2010.sh ${filename} ${src} ${trg} ${model}

    # moved into the qsub
    #echo "${src}2${trg} " >>  BLEU.$model.$DATE
    #ref=${i/.2trad.$model.bpe/.w.tc}
    #trad=${i/2trad/2$trg}
    #trad=${trad/test2010/trads2010}
    #$mosesdecoder/scripts/generic/multi-bleu.perl $ref < $trad.${src}2${trg} >> BLEU.$model.$DATE
done
