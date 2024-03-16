#!/bin/bash

set -xe

echo "Creating lexer"
jflex MPLexer.flex

echo "Creating parser"
cup -parser MPParser -symbols sym MPParser.cup

echo "Compiling all java files"
javac -cp ./java-cup-11b-runtime.jar *.java

echo "Running parser"
java -cp ./java-cup-11b-runtime.jar:. MPParser primer.txt
