# Preprocessing for BabelWE

Run the preprocessing and annotation through the cat.trachemys.interlingua.prepro.Annotator class for 
ar/en/es/fr/de. External lemmatisation with TreeTagger is expected for the other languages.


```
java -cp BabelWE-0.0.1-SNAPSHOT-jar-with-dependencies.jar cat.trachemys.interlingua.prepro.Annotator -h

usage: Annotator
 -a,--annotation <arg>   Annotation layer (tok, lem)
 -c,--config <arg>       Configuration file for the BabelWE project
 -h,--help               This help
 -i,--input <arg>        Input file to annotate -one sentence per line-
 -l,--language <arg>     Language of the input text (ar/en/es/tr/fr/de)

```
