# Corpora Annotation with BabelNet IDs

Include the BabelNet IDs into a file with already annotated data with cat.lump.sts2017.babelNet.DataIDAnnotator class:

```
java -cp BabelWE-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.trachemys.interlingua.babelNet.DataIDAnnotator -h

usage: DataIDAnnotator
 -f,--format <arg>     Format of the input file [wpl|conll]
 -h,--help             This help
 -i,--input <arg>      Input file to annotate
 -l,--language <arg>   Language of the input text
                       [ar|en|es|tr|de|fr|ro|nl|it]
                       
ex:
java -cp target/BabelWE-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.trachemys.interlingua.babelNet.DataIDAnnotator -l en -f conll -i file.wpl
```

### Data Formats

#### wpl

Raw text with one sentence per line. For each token, factors word (w), PoS (p) and lemma (l) are separated by pipes as output by the prepro package. 

Input: w1|p1|l1 w2|p1|l2 ... wn|pn|ln

Output: w1|p1|l1|b1 w2|p1|l2|b2 ... wn|pn|ln|bn

#### conll

conll-like format without headers as output by TreeTagger, one word per line and sentences separated by blank lines.

w1   p1   l1 
w2   p1   l2 
... 
wn   pn   ln

w1   p1   l1 
w2   p1   l2 
... 
wm   pm   lm











