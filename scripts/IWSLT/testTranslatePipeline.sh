

files=("IWSLT17.TED.tst2010.de-it.raw.de.wplmb.2trad"  "IWSLT17.TED.tst2010.en-fr.raw.en.wplmb.2trad"  "IWSLT17.TED.tst2010.es-en.raw.en.wplmb.2trad"  "IWSLT17.TED.tst2010.de-it.raw.it.wplmb.2trad"  "IWSLT17.TED.tst2010.en-fr.raw.fr.wplmb.2trad"  "IWSLT17.TED.tst2010.es-en.raw.es.wplmb.2trad" "IWSLT17.TED.tst2010.de-nl.raw.de.wplmb.2trad"  "IWSLT17.TED.tst2010.en-it.raw.en.wplmb.2trad"  "IWSLT17.TED.tst2010.it-nl.raw.it.wplmb.2trad" "IWSLT17.TED.tst2010.de-nl.raw.nl.wplmb.2trad"  "IWSLT17.TED.tst2010.en-it.raw.it.wplmb.2trad"  "IWSLT17.TED.tst2010.it-nl.raw.nl.wplmb.2trad" "IWSLT17.TED.tst2010.de-ro.raw.de.wplmb.2trad"  "IWSLT17.TED.tst2010.en-nl.raw.en.wplmb.2trad"  "IWSLT17.TED.tst2010.it-ro.raw.it.wplmb.2trad" "IWSLT17.TED.tst2010.de-ro.raw.ro.wplmb.2trad"  "IWSLT17.TED.tst2010.en-nl.raw.nl.wplmb.2trad"  "IWSLT17.TED.tst2010.it-ro.raw.ro.wplmb.2trad" "IWSLT17.TED.tst2010.en-de.raw.de.wplmb.2trad"  "IWSLT17.TED.tst2010.en-ro.raw.en.wplmb.2trad"  "IWSLT17.TED.tst2010.nl-ro.raw.nl.wplmb.2trad" "IWSLT17.TED.tst2010.en-de.raw.en.wplmb.2trad"  "IWSLT17.TED.tst2010.en-ro.raw.ro.wplmb.2trad"  "IWSLT17.TED.tst2010.nl-ro.raw.ro.wplmb.2trad")

for i in ${files[@]}; do 
    perl  extract4MNMT.perl submission/test2010/$i 0 > submission/test2010/$i.w
done
    
for i in ${files[@]}; do python ~/sge/soft/nematus/subword-nmt/apply_bpe.py -c nematus/model/L1L2.120k.w.bpe <  submission/test2010/$i.w > submission/test2010/$i.w.bpe; done

for i in ${files[@]}; do 
    python addBPE2factored.py submission/test2010/$i submission/test2010/$i.w.bpe
done

for i in ${files[@]}; do
    #perl  extract4MNMT.perl submission/test2010/$i.bped 0 > submission/test2010/$i.w.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 > submission/test2010/$i.wp.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 2 > submission/test2010/$i.ws.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 3 > submission/test2010/$i.wl.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 4 > submission/test2010/$i.wm.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 5 > submission/test2010/$i.wb.bpe

    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 2 > submission/test2010/$i.wps.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 3 > submission/test2010/$i.wpl.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 4 > submission/test2010/$i.wpm.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 5 > submission/test2010/$i.wpb.bpe

    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 2 4 > submission/test2010/$i.wpsm.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 3 4 > submission/test2010/$i.wplm.bpe

    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 2 5 > submission/test2010/$i.wpsb.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 3 5 > submission/test2010/$i.wplb.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 4 5 > submission/test2010/$i.wpmb.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 2 4 5 > submission/test2010/$i.wpsmb.bpe
    perl  extract4MNMT.perl submission/test2010/$i.bped 0 1 3 4 5 > submission/test2010/$i.wplmb.bpe
done

# nomes per als casos de la submission de moment                                                                       
for i in ${files[@]}; do
    #IWSLT17.TED.tst2010.en-nl.raw.en.wplmb.2trad                                                                      
    fileTags=${i/raw/t}
    fileTags=${fileTags/.wplmb.2trad/}
    perl enrichFile.perl submission/test2010/$fileTags  submission/test2010/$i.wpsm.bpe > submission/test2010/$i.wpsmt.bpe
    perl enrichFile.perl submission/test2010/$fileTags  submission/test2010/$i.wpsmb.bpe > submission/test2010/$i.wpsmbt.bpe
    perl enrichFile.perl submission/test2010/$fileTags  submission/test2010/$i.w.bpe > submission/test2010/$i.wt.bpe
done



