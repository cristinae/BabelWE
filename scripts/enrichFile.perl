#!/usr/bin/perl

###############################################################################
# Purpose: Gathering tags in file A and adding them as factors to all words of
#          file B
# Author:  Cristina
# Date:    17/07/2017
###############################################################################

use strict;
use warnings;

#binmode(STDIN, ":utf8");
#binmode(STDOUT, ":utf8");

# Command line arguments
my $num_args = $#ARGV + 1;
if ($num_args != 2) {
    print "\nUsage: $0 <tagsFile> <textFile>  >  factoredfile \n";
    exit;
}

my $tags=$ARGV[0];
my $completeFile=$ARGV[1];

# Load files
open TAGS, "< $tags" or die "could not open $tags\n";
open FILE2, "< $completeFile" or die "could not open $completeFile\n";

while (!eof(TAGS) and !eof(FILE2)) {
    my $tag = <TAGS>;
    my $line2 = <FILE2>;
    chomp($tag);
    #chomp($line2);
    my $outFactored = $line2 =~ s/\s+/|$tag /gr;
    $outFactored =~ s/\n/|$tag\n/;
    print "$outFactored\n";
}
