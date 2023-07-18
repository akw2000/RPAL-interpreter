JFLAGS = -g
JC = javac -d bin
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

# List all the Java source files here
CLASSES = \
    lexical_analyzer/LexicalAnalyzer.java \
    lexical_analyzer/Token.java \
    rpal20.java

# Target for building the classes
default: classes

# Rule for compiling Java source files into classes
classes: | bin
	$(JC) $(JFLAGS) $(CLASSES)

bin:
	mkdir bin

test:
	java -cp bin rpal20 rpal_test_programs/rpal_01 > output.01
	fc output.01 rpal_test_programs/output01.test

# both build and test
check: default test

clean:
	del /Q /S bin