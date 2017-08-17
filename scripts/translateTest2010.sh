#!/bin/bash

#$ -M cristinae@dfki.de
#$ -m eas
#$ -V
#$ -S /bin/bash

#$ -cwd
#$ -wd /home/cristinae/sge/

#$ -o /home/cristinae/sge/IWSLT/submission/logs/tradsTed.o.txt
#$ -e /home/cristinae/sge/IWSLT/submission/logs/tradsTed.e.txt

#$ -l gpu=1
#$ -l h=exs-91207.sb.dfki.de
# -l h_vmem=10G

export CUDA_HOME=/usr/local/cuda-7.5/
export LD_LIBRARY_PATH=${CUDA_HOME}/lib64:/home/santanu/cuda:$LD_LIBRARY_PATH
export PATH=$PATH:${CUDA_HOME}/bin


# inputs
testFile=$1
src=$2
tgt=$3
sistema=$4
model=model_L1L2${sistema}_small_v120k.npz
device=gpu2

# path to nematus ( https://www.github.com/rsennrich/nematus )
mosesdecoder=/raid/bin/moses/
nematus=/home/cristinae/sge/IWSLT/nematus

DATE=`date +%Y-%m-%d`
bleuFile=/home/cristinae/sge/IWSLT/submission/BLEU.$sistema.$DATE

testPath=$nematus/../submission/test2010/
test="$testPath$testFile"
trad=${test/2trad/2$tgt}
trad=${trad/test2010/trads2010}


#THEANO_FLAGS=mode=FAST_RUN,floatX=float32,device=$device,on_unused_input=warn python $nematus/nematus/translate.py \
#     -m $nematus/model/$model \
#     -i $test \
#     -o $trad.${src}2${tgt} \
#      -n -p 14

tradClean=${trad/.bpe/}
#sed 's/\@\@ //g' $trad.${src}2${tgt} > $tradClean.${src}2${tgt}

echo "${src}2${tgt} " >>  $bleuFile
#IWSLT17.TED.tst2010.en-it.raw.en.wplmb.w.tc
pair=${test%.raw*} # remove suffix starting with ".raw"
#ref=${test/.2trad.$sistema.bpe/.w.tc}
ref=$pair.raw.$tgt.wplmb.w.tc
echo "$mosesdecoder/scripts/generic/multi-bleu.perl $ref < $tradClean.${src}2${tgt} >> $bleuFile"
$mosesdecoder/scripts/generic/multi-bleu.perl $ref < $tradClean.${src}2${tgt} >> $bleuFile

rm $trad.${src}2${tgt}

