JFLAGS = -g
JC = javac
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
classes: $(CLASSES:.java=.class)

clean:
	del /Q /S *.class
