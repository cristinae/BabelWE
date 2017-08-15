# FOR THE FINAL SUBMISSION REMOVE fr AND es; CHANGE FILENAMES

# separa per doc
# We do this because of the topic
for i in *xml; do csplit $i -f $i.doc '/<doc docid=/' '{*}'; done
# esborra header (NO CALDRIA)
rm *xml.doc00

# CHECK: The title is not used in the template
#for i in *.xml.doc??; do  grep '<title>' $i | cut -d'>' -f2 | cut -d'<' -f1 > $i.txt ; grep '<seg ' $i | cut -d'>' -f2 | cut -d'<' -f1 >> $i.txt  ; done;
for i in *.xml.doc??; do grep '<seg ' $i | cut -d'>' -f2 | cut -d'<' -f1 >> $i.txt  ; done;

for i in *txt; do sed -i -e 's/^[ \t]*//;s/[ \t]*$//' $i; done

rename 's/.txt/.en/' *.en.xml.doc??.txt
rename 's/.txt/.it/' *.it.xml.doc??.txt
rename 's/.txt/.ro/' *.ro.xml.doc??.txt
rename 's/.txt/.nl/' *.nl.xml.doc??.txt
rename 's/.txt/.de/' *.de.xml.doc??.txt
rename 's/.txt/.fr/' *.fr.xml.doc??.txt
rename 's/.txt/.es/' *.es.xml.doc??.txt

# Text normalisation
for j in "en" "de" "ro" "it" "nl" "fr" "es";  do for i in *.$j; do perl ~/soft/mosesdecoder/scripts/tokenizer/normalize-punctuation.perl -l $j < $i > $i.norm ; done ;done
rename -f 's/.norm//' *norm

# Build a raw file for every language pair without the xml markup
for j in "en" "de" "ro" "it" "nl" "fr" "es"; do for i in "en" "de" "ro" "it" "nl" "fr" "es"; do cat IWSLT17.TED.tst2010.$i-$j.$i.xml.doc??.$i > IWSLT17.TED.tst2010.$i-$j.raw.$i; done; done 
for j in "en" "de" "ro" "it" "nl" "fr" "es"; do for i in "en" "de" "ro" "it" "nl" "fr" "es"; do cat IWSLT17.TED.tst2010.$j-$i.$i.xml.doc??.$i > IWSLT17.TED.tst2010.$j-$i.raw.$i; done; done 

# Delete files with 0 length (non-existing pairs in the previous loops)
find . -size 0 -delete
mkdir ./docs
mv *xml.doc* ./docs/.

# First annotation with lemmas
# 'old' languages
for j in "en" "de" "ro" "it" "nl" "fr" "es"; do for i in  "de" "fr" "es"; do java -cp /home/cristinae/pln/git/BabelWE/target/BabelWE-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.trachemys.interlingua.prepro.Annotator -c /home/cristinae/pln/git/BabelWE/babelWE.ini -a lem -l $i -i IWSLT17.TED.tst2010.$j-$i.raw.$i; java -cp /home/cristinae/pln/git/BabelWE/target/BabelWE-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.trachemys.interlingua.prepro.Annotator -c /home/cristinae/pln/git/BabelWE/babelWE.ini -a lem -l $i -i IWSLT17.TED.tst2010.$i-$j.raw.$i; done; done
find . -size 0 -delete
rm BabelWE-2017*.log
# 'new' languages -> treetagger
for i in *raw.en; do /home/cristinae/soft/treetagger/cmd/tree-tagger-english < $i > $i.tt; done
for i in *raw.ro; do /home/cristinae/soft/treetagger/cmd/tree-tagger-romanian < $i > $i.tt; done
for i in *raw.it; do /home/cristinae/soft/treetagger/cmd/tree-tagger-italian < $i > $i.tt; done
for i in *raw.nl; do /home/cristinae/soft/treetagger/cmd/tree-tagger-dutch < $i > $i.tt; done
for j in "en" "ro" "it" "nl";  do for i in IWSLT17.TED.tst2010.*.raw.$j; do java -cp /home/cristinae/pln/git/BabelWE/target/BabelWE-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.trachemys.interlingua.babelNet.TreeTagger2BabelWE -l $j -i $i.tt -r $i; done; done
rm *.tt

# Full annotation wppslmb
echo "PARALLELITZA AIXO"
cd /home/cristinae/pln/git/BabelWE
for j in "en" "de" "ro" "it" "nl" "fr" "es";  do for i in /media/cristinae/DATA1/pln/experiments/IWSLT/submit/test2010/IWSLT17.TED.*.raw.$j.wplIWSLT17.TED.*.raw.$j.wpl; do java -cp /home/cristinae/pln/git/BabelWE/target/BabelWE-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.trachemys.interlingua.babelNet.DataAnnotator4IWSLT -l $j -i $i; done; done
rm BabelWE-2017*.log
cd /media/cristinae/DATA1/pln/experiments/IWSLT/submit/test2010/

# Just in case sth went wrong and it is too late
for i in *wplmb; do sed -i -e 's/||/|-|/g' $i; done

# Truecasing
for i in *wplmb; do perl /media/cristinae/DATA1/pln/experiments/IWSLT/TEDbn/extract4MNMT.perl $i 0 > $i.w; done

for i in *en.wplmb.w; do  perl ~/soft/mosesdecoder/scripts/recaser/truecase.perl --model /media/cristinae/DATA1/pln/experiments/IWSLT/TEDbn/competition/anotated/truecaser/modelTC.EpWP.en < $i > $i.tc; done;
for i in *de.wplmb.w; do  perl ~/soft/mosesdecoder/scripts/recaser/truecase.perl --model /media/cristinae/DATA1/pln/experiments/IWSLT/TEDbn/competition/anotated/truecaser/modelTC.EpWP.de < $i > $i.tc; done;
for i in *ro.wplmb.w; do  perl ~/soft/mosesdecoder/scripts/recaser/truecase.perl --model /media/cristinae/DATA1/pln/experiments/IWSLT/TEDbn/competition/anotated/truecaser/modelTC.EpWP.ro < $i > $i.tc; done;
for i in *it.wplmb.w; do  perl ~/soft/mosesdecoder/scripts/recaser/truecase.perl --model /media/cristinae/DATA1/pln/experiments/IWSLT/TEDbn/competition/anotated/truecaser/modelTC.TED.it < $i > $i.tc; done;
for i in *nl.wplmb.w; do  perl ~/soft/mosesdecoder/scripts/recaser/truecase.perl --model /media/cristinae/DATA1/pln/experiments/IWSLT/TEDbn/competition/anotated/truecaser/modelTC.TED.nl < $i > $i.tc; done;
for i in *es.wplmb.w; do  perl ~/soft/mosesdecoder/scripts/recaser/truecase.perl --model /media/cristinae/DATA1/pln/experiments/IWSLT/TEDbn/competition/anotated/truecaser/modelTC.EpWP.es < $i > $i.tc; done;
for i in *fr.wplmb.w; do  perl ~/soft/mosesdecoder/scripts/recaser/truecase.perl --model /media/cristinae/DATA1/pln/experiments/IWSLT/TEDbn/competition/anotated/truecaser/modelTC.EpWP.fr < $i > $i.tc; done;

# Prepare for NMT
for j in "en" "de" "ro" "it" "nl" "fr" "es";  do for i in "en" "de" "ro" "it" "nl" "fr" "es"; do python /home/cristinae/pln/git/BabelWE/scripts/prepare4MNMT.py IWSLT17.TED.tst2010.$i-$j.raw.$i.wplmb $j; done; done;
for j in "en" "de" "ro" "it" "nl" "fr" "es";  do for i in "en" "de" "ro" "it" "nl" "fr" "es"; do python /home/cristinae/pln/git/BabelWE/scripts/prepare4MNMT.py IWSLT17.TED.tst2010.$i-$j.raw.$j.wplmb $i; done; done;
find . -size 0 -delete

# send to translate!
mkdir simetrics
mv *de-en* *it-de* *it-en* *nl-de* *nl-en* *nl-it* *ro-de* *ro-en* *ro-it* *ro-nl* ./simetrics
# els que traduim en els dos sentits
# de-it de-nl de-ro en-de en-fr en-it en-nl en-ro es-en it-nl it-ro nl-ro
tar -cvf test2trad.tar *2trad

