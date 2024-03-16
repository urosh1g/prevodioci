#!/bin/bash

set -xe

echo "Creating lexer"
jflex lexer.flex

echo "Creating parser"
cup -parser parser -symbols sym parser.cup

echo "Compiling all java files"
javac -cp ./java-cup-11b-runtime.jar *.java

echo "Running parser"
java -cp ./java-cup-11b-runtime.jar:. parser primer.txt
