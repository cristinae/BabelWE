#!/usr/bin/perl

###############################################################################
# Purpose: Truecases, detonenises and wraps into an xml IWSLT format by 
#          replacing the segments in the source xml file
# Author:  Cristina
# Date:    29/08/2017
###############################################################################

use strict;
use warnings;
use HTML::Entities;

#binmode(STDIN, ":utf8");
#binmode(STDOUT, ":utf8");

# In case is an n-best list start with this
# for i in *; do sed -n '1~10p'  $i | sed "s/||| /|/g" | cut -f2 -d'|' > $i.1best; done


# Command line arguments
my $num_args = $#ARGV + 1;
if ($num_args != 2) {
    print "\nUsage: $0 <srcFile> <rawTrads> \n";
    exit;
}

my $source=$ARGV[0];
my $trads=$ARGV[1];


#IWSLT naming
# <Set>.<Task>.<UserID>.primary.xml // <Set>.<Task>.<UserID>.contrastive1.xml
my $set='IWSLT17.tst2017';
# tasks: multilingual_small | multilingual_zero_shot 
my $task='multilingual_zero_shot';
my $userID='UdS-DFKI';
# submission: primary | contrastive1 | contrastive2
my $submission='primary';
my $sysID=$userID.'-'.$submission;
my $xml= $set.'.'.$task.'.'.$userID.'.'.$submission.'.xml';

# De-tokenise and de-truecase translation
# "Usage ./detokenizer.perl (-l [en|fr|it|cs|...]) < tokenizedfile > detokenizedfile\n";
#    get the language
print "$trads\n";

$trads =~ /\.(\w\w)2(\w\w)$/;
my $lanSRC = $1;
my $lanTGT = $2;
my $command = "perl ../detokenizer.perl -l $lanTGT -u < $trads > $trads.rc ";
system($command) ;

my %languages = (
    "en" => "english",
    "de" => "german",
    "ro" => "romanian",
    "it" => "italian",
    "nl" => "deutsch",
    "es" => "spanish",
    "fr" => "french"
);


# Load files
open SRC, "< $source" or die "could not open $source\n";
open TRD, "< $trads.rc" or die "could not open $trads\n";
# Output file
open OUT, "> $xml" or die "could not open \n";
my $i = 0;
while (<SRC>) {
    my $template = $_;
    chomp($template);
    if ($template =~ /^<srcset/) {
	$template =~ s/<srcset/<tstset/;
        $template =~ s/\">\s*$/\" trglang=\"$languages{$lanTGT}\" sysid=\"$sysID\">/;
    } elsif ($template =~ /^<refset/) {
	$template =~ s/<refset/<tstset/;
    } elsif ($template =~ /<\/srcset/) {
	$template =~ s/<\/srcset>/<\/tstset>/;
    } elsif ($template =~ /<\/refset/) {
	$template =~ s/<\/refset>/<\/tstset>/;
    }   
    # Let's be sure all cases are covered
    if ($template =~ /trglang/) {
        $template =~ s/trglang=\".+?\"/trglang=\"$languages{$lanTGT}\"/;
    }
    if ($template =~ /srclang/) {
        $template =~ s/srclang=\".+?\"/srclang=\"$languages{$lanSRC}\"/;
    }
    if ($template =~ /refid=\"ref\"/) {
	$template =~ s/refid=\"ref\"/sysid=\"$sysID\"/;
    } elsif ($template =~ /^<seg id=/){
        my $line = <TRD>;
         chomp($line);
        # the input has not xml encoding, but these two would break it
        $line =~ s/</&lt;/;
        $line =~ s/>/&gt;/;
	# $line = encode_entities($line);
        #print $line;
        $template =~ s/(<seg id=\"\d+\">).*(<\/seg>)/$1$line$2/; 
	$i++;
    }
    print OUT "$template\n";
}

print STDERR "$i lines have been wrapped into $xml\n";
close(SRC);
close(TRD);
close(OUT);

