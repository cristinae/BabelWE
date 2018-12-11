# BabelWE
### Collection of utilities to work with BabelNet synsets

Current implemented languages:

Complete pipeline available for Arabic, German, English, French, Spanish, Turkish (no lemmatisation done for tk). A previous lemmatisation with Tree tagger is expected for Italian, Romanian and Dutch.

___
### Set-up and installation

1. Download and install the BabelNet API and its dependencies <br />
[API download] (http://babelnet.org/data/4.0/BabelNet-API-4.0.1.zip) <br />
`unzip BabelNet-API-4.0.1.zip` <br />
`mvn install:install-file -Dfile=lib/lcl-jlt-2.4.jar -DgroupId=it.uniroma1.lcl.jlt -DartifactId=lcl-jlt -Dversion=2.4 -Dpackaging=jar` <br />
`mvn install:install-file -Dfile=lib/babelscape-data-commons-1.0.jar -DgroupId=com.babelscape -DartifactId=babelscape-data-commons -Dversion=1.0 -Dpackaging=jar` <br />
`unzip -p babelnet-api-4.0.1.jar META-INF/maven/it.uniroma1.lcl.babelnet/babelnet-api/pom.xml | grep -vP '<(scope|systemPath)>' >babelnet-api-4.0.1.pom` <br />
(consider using homebrew's ggrep if on OsX)<br />
`mvn install:install-file -Dfile=babelnet-api-4.0.1.jar -DpomFile=babelnet-api-4.0.1.pom` <br />

2. Download BabelNet indices and make the API aware of them <br />
[Indices download] (http://babelnet.org/login) <br />
`tar xjvf babelnet-4.0.1-index.tar.bz2` <br />
- In `./BabelNet-API-4.0.1/config/babelnet.var.properties` include the path to the index:  <br />
 `babelnet.dir=/home/usr/BabelNet-4.0.1` <br />
- In `./BabelNet-API-4.0.1/config/jlt.var.properties` include the path to WordNet:  <br />
 `jlt.wordnetPrefix=/usr/local/share/wordnet` <br />
- Move the `./BabelNet-API-4.0.1/config` folder to your ${basedir}  <br />


If you need to annotate Arabic corpora: <br />

3. Download and install MADAMIRA jar <br />
[License for downloading](http://innovation.columbia.edu/technologies/cu14012_arabic-language-disambiguation-for-natural-language-processing-applications) <br />
`mvn install:install-file -Dfile={$PATH}/MADAMIRA-release-20160516-2.1/MADAMIRA-release-20160516-2.1.jar -DgroupId=edu.columbia.ccls.madamira  -DartifactId=MADAMIRA-release -Dversion=20160516-2.1 -Dpackaging=jar` <br />


Finally: <br />

4. Download and install this repository <br />
`git clone https://github.com/cristinae/BabelWE.git` <br />
`mvn clean dependency:copy-dependencies package` <br />


### External resources
1. Download the IXA pipes for tokenisation and lemmatisation of English, Spanish, French and German. They are used as an external executable, no need for installation.<br />
[Download page](http://ixa2.si.ehu.es/ixa-pipes/download.html)<br />
Include their path in the configuration file babelWE.ini<br />

2. Use the Moses tokeniser included in the ./scripts folder<br />
Include its path in the configuration file babelWE.ini<br /><br />


```
### External software and models
# IXA pipe
ixaTok=/home/cristinae/soft/processors/ixa/ixa-pipe-tok-1.8.5-exec.jar
ixaLem=/home/cristinae/soft/processors/ixa/ixa-pipe-pos-1.5.1-exec.jar
posEs=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/es/es-pos-perceptron-autodict01-ancora-2.0.bin
lemEs=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/es/es-lemma-perceptron-ancora-2.0.bin
posEn=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/en/en-pos-perceptron-autodict01-conll09.bin
lemEn=/home/cristinae/soft/processors/ixa/morph-models-1.5.0/en/en-lemma-perceptron-conll09.bin

# Moses
mosesTok=/home/cristinae/soft/processors/moses/tokenizerNO2html.perl
```

3. For Italian, Romanian and Dutch we expect input to be already lemmatised using Treetagger, but the lemmatisation pipeline with TreeTagger is not included yet.
